# FTC 21836 Into The Deep - Complete Repository Analysis

## Executive Summary

This repository represents a highly sophisticated FTC robot codebase for the 2024-2025 "Into The Deep" season. The architecture demonstrates professional-level software engineering with:

1. **Advanced Localization**: Uses GoBilda Pinpoint sensor (IMU + odometry pods) as a drop-in replacement for dead wheels
2. **RoadRunner 1.0 Integration**: Full implementation with MeepMeep testing workflow
3. **State Machine Architecture**: Complex subsystems with FSM (Finite State Machine) design
4. **Performance Optimization**: Cached hardware, bulk reads, and efficient update loops
5. **Iterative Development**: Clear workflow from MeepMeep simulation → Robot testing → Deployment

---

## 📁 Project Structure Overview

```
21836-IntoTheDeep/
├── TeamCode/                    # Main robot code (Android module)
├── MeepMeepTesting/            # Path simulation module (Java desktop)
├── FtcRobotController/         # SDK base (untouched)
├── build.gradle files          # Dependency management
└── settings.gradle             # Module configuration
```

---

## 🔧 Build System & Dependencies

### Root `build.gradle`
```groovy
buildscript {
    ext {
        kotlin_version = '2.0.21'  // Kotlin support enabled
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:8.7.0'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
}
```

### TeamCode `build.gradle`
**Key Dependencies:**
```groovy
// RoadRunner 1.0 (latest)
implementation "com.acmerobotics.roadrunner:core:1.0.0"
implementation "com.acmerobotics.roadrunner:actions:1.0.0"

// FTC Dashboard for tuning
implementation "com.acmerobotics.dashboard:dashboard:0.4.16"

// Pinpoint/OTOS sensor integration
implementation "page.j5155.roadrunner:ftc-otos:0.1.2+0.1.14"

// FTCLib for utilities
implementation 'org.ftclib.ftclib:vision:2.1.0'
implementation 'org.ftclib.ftclib:core:2.1.1'

// Kotlin standard library
implementation 'androidx.core:core-ktx:1.15.0'
```

**Key Repositories:**
```groovy
repositories {
    maven { url = 'https://maven.brott.dev/' }          // RoadRunner
    maven { url = 'https://repo.dairy.foundation/releases' }  // Pinpoint
}
```

### MeepMeepTesting `build.gradle`
```groovy
plugins {
    id 'java-library'
    id 'org.jetbrains.kotlin.jvm'
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation 'com.acmerobotics.roadrunner:MeepMeep:0.1.6'
}
```

**Module Configuration (`settings.gradle`):**
```groovy
include ':FtcRobotController'
include ':TeamCode'
include ':MeepMeepTesting'  // Separate desktop module
```

---

## 🚗 RoadRunner Framework

### 1. MecanumDrive.java (Core Drive Class)

**Location:** `TeamCode/roadrunner/MecanumDrive.java`

**Purpose:** Base mecanum drive implementation with RoadRunner 1.0 integration

**Key Features:**

```java
@Config
public class MecanumDrive {
    // Tunable parameters via FTC Dashboard
    public static class Params {
        // IMU orientation
        public RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection;
        public RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection;
        
        // Odometry scalars
        public double inPerTick = 1;  // For Pinpoint (1 tick = 1 inch)
        public double lateralInPerTick = 0.6496757774509637;  // Tuned lateral
        public double trackWidthTicks = 11.622540030865755;
        
        // Feedforward
        public double kS = 0.01;  // Static friction
        public double kV = 0.17;  // Velocity gain
        public double kA = 0.04;  // Acceleration gain
        
        // Motion constraints
        public double maxWheelVel = 60;
        public double maxAngVel = 5.4;
        public double maxProfileAccel = 60;
        
        // PID gains
        public double axialGain = 8;
        public double lateralGain = 8;
        public double headingGain = 8;
    }
}
```

**Field-Centric Driving:**
```java
public void run(double xCommand, double yCommand, double turnCommand, 
                boolean useSlowMode, boolean useFieldCentric) {
    // Counter-rotate by robot heading for field-centric
    double heading = useFieldCentric ? pose.heading.toDouble() : 0.5 * PI;
    double cos = cos(-heading);
    double sin = sin(-heading);
    
    double rotatedX = xCommand * cos - yCommand * sin;
    double rotatedY = yCommand * cos + xCommand * sin;
    
    if (useSlowMode) {
        rotatedY *= SLOW_FACTOR;
        rotatedX *= SLOW_FACTOR;
        turnCommand *= SLOW_FACTOR;
    }
    
    setDrivePowers(new PoseVelocity2d(
        new Vector2d(rotatedX, rotatedY),
        -turnCommand
    ));
}
```

**Action System:**
```java
public final class FollowTrajectoryAction implements Action {
    private final TimeTrajectory timeTrajectory;
    
    @Override
    public boolean run(@NonNull TelemetryPacket p) {
        // Get target pose from trajectory
        Pose2dDual<Time> txWorldTarget = timeTrajectory.get(t);
        
        // Update localization
        PoseVelocity2d robotVelRobot = updatePoseEstimate();
        
        // Calculate error
        Pose2d error = txWorldTarget.value().minusExp(pose);
        
        // Check termination
        if (t >= timeTrajectory.duration && 
            abs(error.heading.toDouble()) <= admissibleError.heading) {
            return false;  // Action complete
        }
        
        // Compute control command
        PoseVelocity2dDual<Time> command = new HolonomicController(...)
            .compute(txWorldTarget, pose, robotVelRobot);
        
        // Apply feedforward + feedback
        MecanumKinematics.WheelVelocities<Time> wheelVels = kinematics.inverse(command);
        // ... set motor powers
        
        return true;  // Continue running
    }
}
```

**Trajectory Builder:**
```java
public TrajectoryActionBuilder actionBuilder(Pose2d beginPose) {
    return new TrajectoryActionBuilder(
        TurnAction::new,
        FollowTrajectoryAction::new,
        new TrajectoryBuilderParams(...),
        beginPose, 0.0,
        defaultTurnConstraints,
        defaultVelConstraint, 
        defaultAccelConstraint
    );
}
```

### 2. PinpointDrive.java (Advanced Localization)

**Location:** `TeamCode/roadrunner/PinpointDrive.java`

**Purpose:** Extends MecanumDrive with GoBilda Pinpoint sensor integration

**Key Code:**

```java
@Config
public class PinpointDrive extends MecanumDrive {
    
    public static class Params {
        public String pinpointDeviceName = "pinpoint";
        
        // Odometry pod offsets (mm)
        public double xOffset = 5.5;
        public double yOffset = -48.97683;
        
        // Pod type (ticks per mm)
        public double encoderResolution = goBILDA_SWINGARM_POD;
        
        // Pod directions
        public GoBildaPinpointDriver.EncoderDirection xDirection = FORWARD;
        public GoBildaPinpointDriver.EncoderDirection yDirection = REVERSED;
        
        // Use Pinpoint IMU for tuning (instead of hub IMU)
        public boolean usePinpointIMUForTuning = true;
    }
    
    public boolean trackHeadingOnly = false;  // For odometry-free mode
    
    @Override
    public PoseVelocity2d updatePoseEstimate() {
        // Check if external code modified pose (relocalization)
        if (lastPinpointPose != pose) {
            pinpoint.setPositionRR(pose);
        }
        
        // Update sensor
        if (trackHeadingOnly)
            pinpoint.update(ONLY_UPDATE_HEADING);
        else
            pinpoint.update();
        
        pose = pinpoint.getPositionRR();
        lastPinpointPose = pose;
        
        // Log to FTC Dashboard
        FlightRecorder.write("ESTIMATED_POSE", new PoseMessage(pose));
        
        return pinpoint.getVelocityRR();
    }
    
    // Relocalization with right joystick
    public void setHeadingWithStick(double x, double y) {
        if (x*x + y*y >= 0.64) {  // Deadzone
            setHeading(-atan2(y, x));
        }
    }
}
```

**Why This Design:**
- **Drop-in replacement**: Extends MecanumDrive, so all tuning OpModes work
- **Sensor fusion**: Uses Pinpoint's integrated IMU + odometry
- **Relocalization**: Easy heading reset with gamepad stick
- **Dashboard integration**: Real-time pose logging

### 3. Other Localizers

**TwoDeadWheelLocalizer.java** - For teams using 2 odometry pods + IMU
**ThreeDeadWheelLocalizer.java** - For teams using 3 odometry pods
**DriveLocalizer** (inner class) - Motor encoder localization fallback

All implement `Localizer` interface:
```java
public interface Localizer {
    Twist2dDual<Time> update();
}
```

---

## 🎮 OpMode Architecture

### Auto.java - Autonomous OpMode

**Location:** `TeamCode/opmode/Auto.java`

**Structure:**

```java
@Config
@Autonomous(preselectTeleOp = "Tele")
public final class Auto extends LinearOpMode {
    
    // State machine for autonomous
    enum State {
        SCORING_PRELOAD,
        INTAKING_1, SCORING_1,
        INTAKING_2, SCORING_2,
        INTAKING_3, SCORING,
        DRIVING_TO_SUB,
        TAKING_PICTURE,
        SUB_INTAKING,
        PARKING
    }
    
    // Configuration menu
    enum AutonConfig {
        CONFIRMING,
        EDITING_ALLIANCE,
        EDITING_SIDE,
        EDITING_CYCLES
    }
    
    // Tunable field positions (via Dashboard)
    public static EditablePose
        intaking1 = new EditablePose(-61, -54, PI/3),
        intaking2 = new EditablePose(-62, -51.5, 1.463...),
        intaking3 = new EditablePose(-58, -50, 2*PI/3),
        scoring = new EditablePose(-56, -56, PI/4),
        chamberRight = new EditablePose(0.5*WIDTH_ROBOT + 0.375, -33, PI/2),
        park1 = new EditablePose(-0.5*SIZE_TILE, -2*SIZE_TILE, PI);
    
    // Wait times and constants
    public static double
        WAIT_SCORE_BASKET = 0.2,
        WAIT_INTAKE = 1,
        EXTEND_SAMPLE_1 = 21,
        VEL_INCHING = 5;
}
```

**Init Configuration:**
```java
@Override
public void runOpMode() throws InterruptedException {
    // Auto-calculate intake headings
    intaking1.heading = intaking1.angleTo(sample1);
    intaking2.heading = intaking2.angleTo(sample2);
    intaking3.heading = intaking3.angleTo(sample3);
    
    // Initialize with multiple telemetry
    mTelemetry = new MultipleTelemetry(telemetry);
    
    // Create robot
    Robot robot = new Robot(hardwareMap, pose);
    
    // Config menu loop
    while (opModeInInit() && timer.seconds() < 5) {
        // Dpad navigation
        if (gamepadEx1.wasJustPressed(DPAD_UP)) 
            selection = selection.plus(-1);
        
        // X to toggle settings
        switch (selection) {
            case EDITING_ALLIANCE:
                if (x) isRedAlliance = !isRedAlliance;
                break;
            // ...
        }
        
        printConfig(selection);
    }
}
```

**Trajectory Building with Actions:**
```java
// Build complex action sequence
Action scorePreload = robot.drivetrain.actionBuilder(pose)
    .strafeTo(chamberRight.toVector2d())
    .stopAndAdd(scoreSpecimen(robot))
    .build();

Action intakeSample1 = robot.drivetrain.actionBuilder(scoring.toPose2d())
    // Start intake while driving
    .afterTime(0, () -> {
        robot.intake.setRollerAndAngle(SPEED_INTAKING);
        robot.intake.extendo.setTarget(EXTEND_SAMPLE_1);
    })
    // Navigate to sample
    .strafeToLinearHeading(intaking1.toVector2d(), intaking1.heading)
    // Wait until intake has sample or timeout
    .stopAndAdd(new FirstTerminateAction(
        t -> !(robot.intake.hasSample() || timer.seconds() >= WAIT_MAX),
        new SleepAction(WAIT_MAX_INTAKE)
    ))
    // Inch forward for sample
    .setTangent(intaking1.heading)
    .lineToY(intaking1.y + Y_INCHING_FORWARD, inchingConstraint)
    .build();

// Execute in main loop
Actions.runBlocking(new SequentialAction(
    scorePreload,
    intakeSample1,
    scoreSample1,
    // ...
));
```

**Custom Action - FirstTerminateAction:**
```kotlin
// Terminates when ANY action completes (not all)
data class FirstTerminateAction(
    val initialActions: List<Action>
) : Action {
    override fun run(p: TelemetryPacket): Boolean {
        if (actions.isEmpty()) return false
        return actions.all { it.run(p) }  // Continue until one finishes
    }
}
```

### Tele.java - TeleOp Mode

**Location:** `TeamCode/opmode/Tele.java`

**Key Features:**

```java
@TeleOp
public final class Tele extends LinearOpMode {
    
    @Override
    public void runOpMode() throws InterruptedException {
        // Timer for endgame alerts
        ElapsedTime matchTimer = new ElapsedTime();
        double TELE = 120;
        double CLIMB_TIME = TELE - 15;
        
        // Initialize robot with pose from Auto
        Robot robot = new Robot(hardwareMap, Auto.pose);
        
        // LED indicator for climb warning
        LEDIndicator indicator = new LEDIndicator(hardwareMap, "green", "red");
        
        // Main loop
        while (opModeIsActive()) {
            // Manual drive
            robot.drivetrain.run(
                gamepad1.left_stick_x,
                -gamepad1.left_stick_y,
                -gamepad1.right_stick_x,
                slowModeLocked || gamepad1.left_bumper,  // Slow mode
                useFieldCentric
            );
            
            // Subsystem state transitions
            if (gamepad2.a) robot.deposit.transferFromIntake();
            if (gamepad2.y) robot.deposit.scoreBasket(HIGH);
            if (gamepad2.x) robot.intake.startIntaking();
            
            // Relocalize heading with right stick
            robot.drivetrain.setHeadingWithStick(
                gamepad1.right_stick_x, 
                gamepad1.right_stick_y
            );
            
            // Climb warning
            if (matchTimer.seconds() >= CLIMB_TIME) {
                indicator.setState(GREEN);
            }
            
            // Update all subsystems
            robot.run();
            
            // Telemetry
            robot.printTelemetry();
            telemetry.update();
        }
    }
}
```

---

## 🤖 Subsystem Architecture

### Robot.java - Main Robot Class

**Location:** `TeamCode/subsystem/Robot.java`

**Design Pattern:** Composition-based subsystem aggregation

```java
@Config
public final class Robot {
    
    public final PinpointDrive drivetrain;
    public final Intake intake;
    public final Deposit deposit;
    public final BulkReader bulkReader;
    public final SimpleServoPivot headlight;
    
    private final ElapsedTime loopTimer = new ElapsedTime();
    
    public Robot(HardwareMap hardwareMap, Pose2d startPose) {
        // Initialize drivetrain first (other subsystems may depend on it)
        drivetrain = new PinpointDrive(hardwareMap, startPose);
        bulkReader = new BulkReader(hardwareMap);
        
        // Initialize subsystems
        intake = new Intake(hardwareMap);
        deposit = new Deposit(hardwareMap, drivetrain);
        
        headlight = new SimpleServoPivot(0, HEADLIGHT_POWER, 
            new CachedSimpleServo(hardwareMap, "headlight", 0, 1));
    }
    
    // Called every loop
    public void run() {
        intake.run(deposit);  // Intake needs deposit reference for transfer
        deposit.run();
        headlight.run();
    }
    
    public boolean hasSample() {
        return intake.hasSample() || deposit.hasSample();
    }
    
    public void printTelemetry() {
        mTelemetry.addData("LOOP TIME", loopTimer.seconds());
        loopTimer.reset();
        
        drivetrain.printTelemetry();
        intake.printTelemetry();
        deposit.printTelemetry();
    }
}
```

### Intake.java - Intake Subsystem

**Location:** `TeamCode/subsystem/Intake.java`

**FSM Design:**

```java
@Config
public final class Intake {
    
    enum State {
        EJECTING_SAMPLE,
        STANDBY,
        BUCKET_RETRACTING,
        EXTENDO_RE_EXTENDING,
        EXTENDO_RETRACTING,
        SETTLING,
        ARM_ENTERING_BUCKET,
        COUNTER_ROLLING,
        CLAW_CLOSING,
        ARM_EXITING_BUCKET
    }
    
    // Hardware
    private final CachedMotorEx roller;
    private final ColorSensor colorSensor;
    private final CachedSimpleServo bucketR, bucketL;
    private final TouchSensor bucketSensor;
    public final Extendo extendo;
    
    // State
    private State state = STANDBY;
    private Sample sample;  // RED, BLUE, NEUTRAL, BARNACLE
    private final ElapsedTime timer = new ElapsedTime();
    
    // Config
    public boolean retractBucketBeforeExtendo = true;
    public boolean specimenMode = false;
    public boolean doTransfer = true;
    
    void run(Deposit deposit) {
        switch (state) {
            case STANDBY:
                // Read color sensor
                hsv = colorSensor.getHSV();
                sample = hsvToSample(hsv);
                
                // Auto-transfer if sample detected
                if (sample != null && doTransfer) {
                    state = BUCKET_RETRACTING;
                    timer.reset();
                }
                break;
                
            case BUCKET_RETRACTING:
                setBucket(ANGLE_BUCKET_RETRACTED);
                if (timer.seconds() >= TIME_BUCKET_RETRACT) {
                    state = EXTENDO_RETRACTING;
                }
                break;
                
            case EXTENDO_RETRACTING:
                extendo.setTarget(0);
                if (extendo.atPosition(0)) {
                    state = ARM_ENTERING_BUCKET;
                    deposit.transferFromIntake();
                }
                break;
                
            // ... more states
        }
        
        // Always update hardware
        roller.set(rollerSpeed);
        setBucket(bucketAngle);
        extendo.run();
    }
    
    // Sample detection with HSV bounds
    public static Sample hsvToSample(HSV hsv) {
        return
            hsv.between(minWhite, maxWhite) ? BARNACLE :
            hsv.between(minRed, maxRed) ? RED :
            hsv.between(minBlue, maxBlue) ? BLUE :
            hsv.between(minYellow, maxYellow) ? NEUTRAL :
            null;
    }
}
```

### Deposit.java - Scoring Subsystem

**Location:** `TeamCode/subsystem/Deposit.java`

**Multi-State FSM:**

```java
@Config
public final class Deposit {
    
    enum State {
        STANDBY,
        // Sample scoring sequence
        ENTERING_BUCKET, COUNTER_ROLLING, CLAW_CLOSING, EXITING_BUCKET,
        LIFT_MOVING_TO_BASKET, ARM_MOVING_TO_BASKET,
        AT_BASKET, FALLING_BASKET, BASKET_TO_STANDBY,
        // Specimen scoring sequence
        WAITING_FOR_BUCKET, MOVING_TO_INTAKING_SPEC, INTAKING_SPECIMEN,
        GRABBING_SPECIMEN, RAISING_SPECIMEN, RAISED_TO_STANDBY,
        STANDBY_TO_CHAMBER, AT_CHAMBER, RELEASING_SPECIMEN,
        RELEASED_SPEC_TO_STANDBY
    }
    
    enum Position { FLOOR, LOW, HIGH }
    
    // Arm presets
    public static class ArmPosition {
        double arm, wrist;
        ArmPosition(double arm, double wrist) {
            this.arm = arm; this.wrist = wrist;
        }
    }
    
    public static ArmPosition
        STANDBY = new ArmPosition(120, 35),
        BASKET = new ArmPosition(313, 150),
        CHAMBER = new ArmPosition(150, 80),
        IN_INTAKE = new ArmPosition(95, 70);
    
    // Hardware
    public final Lift lift;
    private final CachedSimpleServo claw, wrist, armR, armL;
    private final MecanumDrive dt;  // For position-based logic
    
    void run() {
        switch (state) {
            case AT_BASKET:
                // Wait for robot to drive away before lowering
                boolean farEnoughFromBasket = 
                    dt.pose.position.x - lastBasketPos.position.x > distFromBasketLiftDown.x &&
                    dt.pose.position.y - lastBasketPos.position.y > distFromBasketLiftDown.y;
                
                if (timer.seconds() >= TIME_MAX_SAMPLE_RELEASE || farEnoughFromBasket) {
                    nextState();
                }
                break;
                
            case STANDBY_TO_CHAMBER:
                // Passively lift during drive
                double passiveHeight = min(
                    HEIGHT_CHAMBER_HIGH,
                    HEIGHT_CHAMBER_HIGH * (timer.seconds() / TIME_STANDBY_TO_CHAMBER)
                );
                lift.setTarget(passiveHeight);
                
                if (timer.seconds() >= TIME_STANDBY_TO_CHAMBER) {
                    nextState();
                }
                break;
                
            // ... more states
        }
        
        // Update hardware
        setArm(state.armPosition);
        lift.run();
    }
}
```

**Key Design Patterns:**
- **Enum-based FSM**: Clear state transitions
- **Timer-based transitions**: Non-blocking state machine
- **Position-based logic**: Uses drivetrain pose for smart scoring
- **Passive motion**: Lift moves during driving to save cycle time

---

## 🎯 Utilities & Optimization

### 1. Cached Hardware

**Problem:** Setting motor power every loop causes I2C overhead

**Solution:** Only update hardware when value changes

```java
public final class CachedDcMotorEx {
    public final DcMotorEx motor;
    private double lastPower = Double.NaN;
    
    public void setPower(double power) {
        if (power == lastPower) return;  // Skip if unchanged
        motor.setPower(lastPower = power);
    }
}
```

**Used in:** All drivetrain motors, intake roller, lift motors

### 2. Bulk Reads

**Purpose:** Read all hub sensors in one I2C transaction

```java
public final class BulkReader {
    private final List<LynxModule> revHubs;
    
    public BulkReader(HardwareMap hardwareMap) {
        revHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : revHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
    }
    
    public void bulkRead() {
        for (LynxModule hub : revHubs) {
            hub.clearBulkCache();  // Refresh all sensor data
        }
    }
}
```

**Used in:** Called at start of Robot.run()

### 3. EditablePose Utility

**Purpose:** Shared data structure between MeepMeep and TeamCode

```java
public final class EditablePose {
    public double x, y, heading;
    
    // Constructors
    public EditablePose(double x, double y, double heading) { ... }
    public EditablePose(Pose2d pose) { ... }
    
    // Conversions
    public Pose2d toPose2d() { return new Pose2d(x, y, heading); }
    public Vector2d toVector2d() { return new Vector2d(x, y); }
    
    // Utilities
    public double angleTo(EditablePose target) {
        return atan2(target.y - this.y, target.x - this.x);
    }
    
    public double distTo(EditablePose target) {
        return hypot(target.y - this.y, target.x - this.x);
    }
}
```

**Key Insight:** Same class exists in both TeamCode and MeepMeepTesting packages

### 4. Controller Framework

**Location:** `TeamCode/control/controller/`

**Structure:**
- `Controller.java` - Base interface
- `FeedbackController.java` - PID-based
- `FeedforwardController.java` - Model-based
- `FullStateController.java` - LQR-style
- `PIDController.java` - Standard implementation

**Example:**
```java
public interface Controller {
    double calculate(double reference, double state);
    void reset();
}

public class PIDController implements FeedbackController {
    private double kP, kI, kD;
    private double lastError, integral;
    
    public double calculate(double reference, double state) {
        double error = reference - state;
        integral += error;
        double derivative = error - lastError;
        lastError = error;
        
        return kP * error + kI * integral + kD * derivative;
    }
}
```

---

## 🧪 MeepMeep Testing Framework

### MeepMeepTesting.java

**Location:** `MeepMeepTesting/src/main/java/.../MeepMeepTesting.java`

**Purpose:** Desktop simulation of autonomous paths

**Key Code:**

```java
public class MeepMeepTesting {
    // COPY-PASTE same constants from Auto.java
    public static double
        LENGTH_ROBOT = 14.2,
        WIDTH_ROBOT = 14.2,
        EXTEND_SAMPLE_1 = 21,
        WAIT_SCORE_BASKET = 0.25;
    
    // COPY-PASTE same poses from Auto.java
    public static EditablePose
        intaking1 = new EditablePose(-61, -54, PI/3),
        scoring = new EditablePose(-56, -56, PI/4),
        chamberRight = new EditablePose(0.5*WIDTH_ROBOT + 0.375, -33, PI/2);
    
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(720);
        
        // Calculate headings (same as Auto.java)
        intaking1.heading = intaking1.angleTo(sample1);
        
        // Create bot entity
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
            .setConstraints(50, 50, 5.3, 20, 15.649)
            .setDimensions(WIDTH_ROBOT, LENGTH_ROBOT)
            .setStartPose(pose)
            .build();
        
        // Build trajectory (EXACT SAME as Auto.java)
        TrajectoryActionBuilder builder = myBot.getDrive()
            .actionBuilder(pose);
        
        builder = builder
            .strafeToLinearHeading(intaking1.toVector2d(), intaking1.heading)
            .stopAndAdd(scoreSample())  // Dummy action
            .afterTime(0, () -> {
                // Simulate intake extending
            })
            .lineToY(intaking1.y + Y_INCHING_FORWARD, inchingConstraint)
            .strafeToLinearHeading(scoring.toVector2d(), scoring.heading);
        
        // Run simulation
        myBot.runAction(builder.build());
        
        // Show field
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
            .setDarkMode(true)
            .addEntity(myBot)
            .start();
    }
    
    // Dummy actions for simulation
    private static Action scoreSample() {
        return new SequentialAction(
            new SleepAction(WAIT_APPROACH_BASKET),
            new SleepAction(WAIT_SCORE_BASKET)
        );
    }
}
```

**Workflow:**
1. **Design in MeepMeep:** Run desktop app, adjust poses visually
2. **Copy poses to Auto.java:** Use same EditablePose values
3. **Copy trajectory code:** Builder syntax is identical
4. **Test on robot:** Deploy to robot, fine-tune constants
5. **Iterate:** Repeat until perfect

**Why This Works:**
- **Same RoadRunner API**: MeepMeep uses RoadRunner core
- **Shared data structures**: EditablePose works in both
- **Visual feedback**: See path before robot testing
- **Fast iteration**: No deploy/wait cycle

---

## 🔍 Advanced Features

### 1. Vision Pipeline Integration

**Location:** `TeamCode/control/vision/`

**SampleDetector:**
```java
public class SampleDetector {
    enum Pipeline {
        YELLOW_RED,   // Detect yellow samples, reject red
        YELLOW_BLUE,  // Detect yellow samples, reject blue
        RED,          // Detect red specimens
        BLUE          // Detect blue specimens
    }
    
    private VisionPortal visionPortal;
    private SampleDetectionPipeline pipeline;
    
    public SampleDetector(HardwareMap hardwareMap) {
        pipeline = new SampleDetectionPipeline();
        visionPortal = new VisionPortal.Builder()
            .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
            .addProcessor(pipeline)
            .build();
    }
    
    public Sample getDetectedSample() {
        return pipeline.getDetectedSample();
    }
}
```

**Used in Auto:** Take snapshot of field, find samples dynamically

### 2. Motion Profiling

**PIDDriver:**
```java
public class PIDDriver {
    private final FeedbackController xController;
    private final FeedbackController yController;
    private final FeedbackController headingController;
    
    public PoseVelocity2d calculate(Pose2d target, Pose2d current) {
        double xVel = xController.calculate(target.x, current.x);
        double yVel = yController.calculate(target.y, current.y);
        double angVel = headingController.calculate(
            target.heading.log(), 
            current.heading.log()
        );
        
        return new PoseVelocity2d(new Vector2d(xVel, yVel), angVel);
    }
}
```

### 3. Dynamic Constraints

**Velocity Constraints:**
```java
// Slow down for precise intaking
MinVelConstraint inchingConstraint = new MinVelConstraint(Arrays.asList(
    new TranslationalVelConstraint(VEL_INCHING),  // 5 in/s
    new AngularVelConstraint(VEL_ANG_INCHING)     // 0.75 rad/s
));

// Fast sweeping motion
MinVelConstraint sweepConstraint = new MinVelConstraint(Arrays.asList(
    new TranslationalVelConstraint(6.5),
    new AngularVelConstraint(0.5)
));

// Use in trajectory
builder.lineToY(y, inchingConstraint);
```

### 4. Passive Lift Motion

**Key Optimization:** Lift moves during driving to save time

```java
// In Auto.java
Action goToBasket = robot.drivetrain.actionBuilder(intaking1.toPose2d())
    .afterTime(0, () -> {
        // Start lifting immediately
        robot.deposit.goToBasket(HIGH);
    })
    .strafeToLinearHeading(scoring.toVector2d(), scoring.heading)
    .build();

// In Deposit.java - lift gradually rises during drive
case LIFT_MOVING_TO_BASKET:
    double progress = timer.seconds() / expectedDriveTime;
    lift.setTarget(lerp(currentHeight, targetHeight, progress));
    break;
```

---

## 📊 Tuning OpModes

**Location:** `TeamCode/roadrunner/tuning/`

### LocalizationTest.java
**Purpose:** Test odometry accuracy

```java
@TeleOp(group = "quickstart")
public class LocalizationTest extends LinearOpMode {
    public void runOpMode() {
        PinpointDrive drive = new PinpointDrive(hardwareMap, new Pose2d(0, 0, 0));
        
        while (opModeIsActive()) {
            // Manual drive
            drive.setDrivePowers(new PoseVelocity2d(
                new Vector2d(-gamepad1.left_stick_y, -gamepad1.left_stick_x),
                -gamepad1.right_stick_x
            ));
            
            drive.updatePoseEstimate();
            
            // Display pose
            telemetry.addData("x", drive.pose.position.x);
            telemetry.addData("y", drive.pose.position.y);
            telemetry.addData("heading", Math.toDegrees(drive.pose.heading.toDouble()));
            
            // Dashboard visualization
            TelemetryPacket packet = new TelemetryPacket();
            Drawing.drawRobot(packet.fieldOverlay(), drive.pose);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }
    }
}
```

### SplineTest.java
**Purpose:** Test path following accuracy

### ManualFeedbackTuner.java
**Purpose:** Live-tune PID gains via Dashboard

---

## 🎨 Design Philosophy Summary

### 1. **Separation of Concerns**
- **Drivetrain:** Only handles motion
- **Subsystems:** Handle mechanisms with FSMs
- **Robot:** Coordinates everything
- **OpModes:** High-level logic

### 2. **Configuration-Driven**
- All tunable parameters are `public static` with `@Config`
- Change values in FTC Dashboard without redeploying
- Same constants shared between MeepMeep and robot

### 3. **State Machine Architecture**
- Clear state transitions
- Non-blocking (timer-based)
- Easy to debug (print current state)

### 4. **Optimization Without Complexity**
- Cached hardware writes
- Bulk sensor reads
- Minimal object allocation in loops

### 5. **Test-Driven Development**
- MeepMeep for path testing
- Tuning OpModes for subsystems
- LocalizationTest for odometry

---

## 🚀 Framework for Next Season (DECODE)

### Essential Files to Port

#### 1. **Gradle Configuration**
```
build.gradle (root)
build.common.gradle
build.dependencies.gradle
TeamCode/build.gradle
MeepMeepTesting/build.gradle
settings.gradle
```

#### 2. **RoadRunner Framework**
```
roadrunner/
├── MecanumDrive.java       ⭐ Core drive class
├── PinpointDrive.java      ⭐ Pinpoint localization (if using)
├── Localizer.java          ⭐ Interface
├── TwoDeadWheelLocalizer.java
├── ThreeDeadWheelLocalizer.java
├── Drawing.java            ⭐ Dashboard visualization
├── tuning/
│   ├── LocalizationTest.java
│   ├── SplineTest.java
│   └── ManualFeedbackTuner.java
└── message/                (For logging)
```

#### 3. **Utility Classes**
```
subsystem/utility/
├── BulkReader.java         ⭐ I2C optimization
├── cachedhardware/
│   ├── CachedDcMotorEx.java    ⭐
│   ├── CachedMotorEx.java
│   └── CachedSimpleServo.java  ⭐
├── LEDIndicator.java
└── SimpleServoPivot.java
```

#### 4. **Control Framework**
```
control/
├── FirstTerminateAction.kt    ⭐ Custom action type
├── controller/
│   ├── Controller.java
│   ├── PIDController.java
│   ├── FeedbackController.java
│   └── FeedforwardController.java
├── motion/
│   ├── EditablePose.java       ⭐ MeepMeep bridge
│   └── PIDDriver.java
└── filter/                     (Kalman, etc.)
```

#### 5. **MeepMeep Module**
```
MeepMeepTesting/
├── build.gradle            ⭐ Desktop module setup
└── src/main/java/
    └── EditablePose.java   ⭐ Same as TeamCode version
    └── MeepMeepTesting.java    (Template)
```

### Porting Workflow

#### Step 1: Setup Build System
1. Copy all gradle files
2. Update `settings.gradle` to include MeepMeepTesting
3. Add RoadRunner dependencies to TeamCode/build.gradle
4. Sync gradle

#### Step 2: Port RoadRunner
1. Copy entire `roadrunner/` folder
2. Update `MecanumDrive.Params` for your robot dimensions
3. Choose localizer (Pinpoint, dead wheels, or drive encoders)
4. Run `LocalizationTest` to verify

#### Step 3: Create Subsystems
1. Copy utility classes (BulkReader, CachedHardware)
2. Create `Robot.java` class
3. Implement subsystems with FSMs
4. Test each subsystem individually

#### Step 4: Setup MeepMeep
1. Copy MeepMeepTesting module
2. Create EditablePose in both packages
3. Define field positions in Auto.java
4. Copy same positions to MeepMeepTesting.java

#### Step 5: Build Autonomous
1. Design path in MeepMeep
2. Copy trajectory builder code to Auto.java
3. Add subsystem actions (intake, score, etc.)
4. Test on robot, iterate

### Code Template for New Season

**Auto.java Structure:**
```java
@Config
@Autonomous(preselectTeleOp = "Tele")
public final class Auto extends LinearOpMode {
    
    // Field constants
    public static double SIZE_TILE = 23.625;
    public static double FIELD_SIZE = 141;
    
    // Robot dimensions
    public static double LENGTH_ROBOT = 18;  // Update
    public static double WIDTH_ROBOT = 18;   // Update
    
    // Tunable poses (SAME as MeepMeepTesting)
    public static EditablePose
        startPose = new EditablePose(0, 0, 0),
        scoringPose = new EditablePose(24, 24, PI/4),
        intakePose = new EditablePose(-48, -48, PI);
    
    // Config menu
    enum Config { ALLIANCE, SIDE, CYCLES }
    
    @Override
    public void runOpMode() {
        // Initialize
        Robot robot = new Robot(hardwareMap, startPose.toPose2d());
        
        // Config menu (copy from this repo)
        // ...
        
        // Build trajectory
        Action auto = robot.drivetrain.actionBuilder(startPose.toPose2d())
            .strafeToLinearHeading(scoringPose.toVector2d(), scoringPose.heading)
            .stopAndAdd(robot.deposit::score)
            .strafeToLinearHeading(intakePose.toVector2d(), intakePose.heading)
            .stopAndAdd(robot.intake::grab)
            .build();
        
        waitForStart();
        Actions.runBlocking(auto);
    }
}
```

**MeepMeepTesting.java Structure:**
```java
public class MeepMeepTesting {
    // EXACT SAME constants as Auto.java
    public static double SIZE_TILE = 23.625;
    public static double LENGTH_ROBOT = 18;
    public static double WIDTH_ROBOT = 18;
    
    // EXACT SAME poses as Auto.java
    public static EditablePose
        startPose = new EditablePose(0, 0, 0),
        scoringPose = new EditablePose(24, 24, PI/4),
        intakePose = new EditablePose(-48, -48, PI);
    
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        
        RoadRunnerBotEntity bot = new DefaultBotBuilder(meepMeep)
            .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
            .setDimensions(WIDTH_ROBOT, LENGTH_ROBOT)
            .build();
        
        // EXACT SAME trajectory as Auto.java
        TrajectoryActionBuilder builder = bot.getDrive()
            .actionBuilder(startPose.toPose2d())
            .strafeToLinearHeading(scoringPose.toVector2d(), scoringPose.heading)
            .waitSeconds(0.5)  // Simulate scoring
            .strafeToLinearHeading(intakePose.toVector2d(), intakePose.heading)
            .waitSeconds(0.5); // Simulate intake
        
        bot.runAction(builder.build());
        
        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
            .addEntity(bot)
            .start();
    }
}
```

---

## 🎓 Key Learnings

### What Makes This Repo Excellent

1. **Professional Architecture**
    - Clean separation between drivetrain, subsystems, and OpModes
    - State machines for complex mechanisms
    - Dependency injection (drivetrain passed to Deposit)

2. **Performance Optimizations**
    - Cached hardware writes (only update when changed)
    - Bulk I2C reads (all sensors in one transaction)
    - Passive motion (lift moves during driving)

3. **Iterative Development**
    - MeepMeep for visual path design
    - Copy-paste trajectory code to robot
    - Dashboard tuning without redeploying

4. **Maintainability**
    - All constants are `@Config` (live tuning)
    - EditablePose shared between modules
    - Clear naming conventions

5. **RoadRunner 1.0 Mastery**
    - Proper use of Actions API
    - Custom actions (FirstTerminateAction)
    - Dynamic constraints per path segment
    - Integrated Dashboard logging

### Common Pitfalls Avoided

1. **No Blocking Code**: All subsystems use timers, not `sleep()`
2. **No Tight Coupling**: Robot class coordinates, but subsystems are independent
3. **No Magic Numbers**: Everything is a named constant
4. **No Duplicate Code**: MeepMeep and Auto share EditablePose

---

## 📝 Migration Checklist for DECODE

### Phase 1: Foundation (Week 1)
- [ ] Copy gradle files and sync project
- [ ] Add RoadRunner 1.0 dependencies
- [ ] Copy MecanumDrive.java and tune for new robot
- [ ] Copy PinpointDrive.java OR appropriate localizer
- [ ] Run LocalizationTest and verify odometry

### Phase 2: Utilities (Week 2)
- [ ] Copy utility classes (BulkReader, CachedHardware)
- [ ] Create Robot.java shell
- [ ] Test hardware with simple OpMode

### Phase 3: Subsystems (Weeks 3-4)
- [ ] Design subsystem state machines
- [ ] Implement Intake FSM
- [ ] Implement Deposit/Scorer FSM
- [ ] Test each subsystem individually

### Phase 4: MeepMeep (Week 5)
- [ ] Setup MeepMeepTesting module
- [ ] Create EditablePose in both packages
- [ ] Define field positions
- [ ] Design autonomous paths visually

### Phase 5: Autonomous (Weeks 6-7)
- [ ] Create Auto.java with config menu
- [ ] Copy trajectory code from MeepMeep
- [ ] Add subsystem actions to trajectory
- [ ] Test and iterate

### Phase 6: TeleOp (Week 8)
- [ ] Create Tele.java
- [ ] Implement manual controls
- [ ] Add relocalization
- [ ] Add driver assists

### Phase 7: Polish (Ongoing)
- [ ] Tune PID gains
- [ ] Optimize cycle times
- [ ] Add telemetry
- [ ] Document code

---

## 🔗 External Resources

### RoadRunner
- Official Docs: https://rr.brott.dev/docs/v1-0/
- GitHub: https://github.com/acmerobotics/road-runner
- Discord: FTC Discord #roadrunner-help

### MeepMeep
- GitHub: https://github.com/NoahBres/MeepMeep
- Documentation: In repository README

### Pinpoint Sensor
- SparkFun Product Page: https://www.sparkfun.com/products/23847
- Integration Library: https://github.com/j5155/FtcOtos

### FTC SDK
- Official SDK: https://github.com/FIRST-Tech-Challenge/FtcRobotController
- Documentation: https://ftc-docs.firstinspires.org/

---

## 📄 File Summary Table

| File | Purpose | Priority | Notes |
|------|---------|----------|-------|
| `MecanumDrive.java` | Core drivetrain | ⭐⭐⭐⭐⭐ | Must have |
| `PinpointDrive.java` | Advanced localization | ⭐⭐⭐⭐ | If using Pinpoint |
| `Auto.java` | Autonomous OpMode | ⭐⭐⭐⭐⭐ | Template |
| `Tele.java` | TeleOp OpMode | ⭐⭐⭐⭐⭐ | Template |
| `Robot.java` | Subsystem coordinator | ⭐⭐⭐⭐⭐ | Architecture |
| `Intake.java` | Example subsystem | ⭐⭐⭐ | Adapt for DECODE |
| `Deposit.java` | Example subsystem | ⭐⭐⭐ | Adapt for DECODE |
| `EditablePose.java` | MeepMeep bridge | ⭐⭐⭐⭐⭐ | Key innovation |
| `MeepMeepTesting.java` | Path simulator | ⭐⭐⭐⭐⭐ | Game changer |
| `BulkReader.java` | Performance | ⭐⭐⭐⭐ | Must have |
| `CachedDcMotorEx.java` | Performance | ⭐⭐⭐⭐ | Must have |
| `FirstTerminateAction.kt` | Custom action | ⭐⭐⭐ | Very useful |
| `LocalizationTest.java` | Tuning | ⭐⭐⭐⭐ | Must test |
| `Drawing.java` | Visualization | ⭐⭐⭐ | Dashboard |
| `PIDController.java` | Control theory | ⭐⭐⭐ | Optional |

---

## 🎯 Final Recommendations

### For Your DECODE Repo:

1. **Start with MecanumDrive**
    - Port it exactly as-is
    - Only change robot dimensions and motor names
    - This is your foundation

2. **Setup MeepMeep Immediately**
    - Don't wait until autonomous season
    - Use it for driver practice paths too
    - Test every path before robot testing

3. **Use EditablePose Everywhere**
    - Define all field positions once
    - Share between MeepMeep and robot code
    - Update in one place, works everywhere

4. **Build Subsystems with FSMs**
    - Draw state diagram first
    - Implement with enums
    - Use timers, not blocking code

5. **Optimize Early**
    - Use CachedHardware from day 1
    - Setup BulkReader immediately
    - These prevent issues later

6. **Document as You Go**
    - Add comments to @Config variables
    - Keep README updated
    - Future you will thank present you

### What to Change:

1. **Robot Dimensions**: Update LENGTH_ROBOT, WIDTH_ROBOT
2. **Motor Names**: Match your hardware config
3. **Subsystems**: Adapt Intake/Deposit for DECODE mechanisms
4. **Field Positions**: New game, new poses
5. **MeepMeep Background**: Use DECODE field image

### What to Keep Exactly:

1. **Build System**: Gradle setup is universal
2. **MecanumDrive**: Works for any mecanum robot
3. **Utility Classes**: BulkReader, CachedHardware
4. **EditablePose**: Perfect bridge to MeepMeep
5. **OpMode Structure**: Config menu, telemetry

---

## 📞 Questions to Ask Your Team

Before porting, answer these:

1. **Localization Method?**
    - Pinpoint sensor? (Copy PinpointDrive)
    - Dead wheels? (Copy TwoDeadWheelLocalizer)
    - Drive encoders? (Use DriveLocalizer)

2. **Robot Dimensions?**
    - Measure LENGTH_ROBOT and WIDTH_ROBOT
    - Needed for MecanumDrive tuning

3. **Subsystem Types?**
    - Intake mechanism?
    - Scoring mechanism?
    - Hanging mechanism?
    - Design FSMs for each

4. **Development Priorities?**
    - Driver control first? (Port Tele.java)
    - Autonomous first? (Port Auto.java + MeepMeep)

---

## 🎉 Conclusion

This repository represents a **production-ready FTC framework** with:
- Professional software architecture
- Optimal performance characteristics
- Rapid iteration via MeepMeep
- Maintainable, documented code

The **key innovation** is the **EditablePose bridge** enabling true iterative development:
1. Design in MeepMeep (fast, visual)
2. Copy to Auto.java (exact same code)
3. Test on robot (same constants)
4. Tune via Dashboard (no redeploy)
5. Update MeepMeep (verify changes)

**For DECODE season:** Port the framework, adapt the subsystems, dominate the competition! 🏆

---

**Document Version:** 1.0  
**Date:** October 26, 2025  
**Repository:** 21836-IntoTheDeep  
**Author:** Analysis by GitHub Copilot

