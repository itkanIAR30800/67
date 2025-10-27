# Quick Reference Guide - FTC Framework Porting

## 🚀 5-Minute Overview

This repo uses **RoadRunner 1.0 + Pinpoint Localization + MeepMeep** for a complete FTC framework.

**Key Innovation:** `EditablePose` class exists in BOTH MeepMeepTesting and TeamCode, enabling:
```
Design Path in MeepMeep → Copy Code → Run on Robot → Iterate
```

---

## 📦 Essential Files to Copy

### 1. Build System (COPY ALL)
```
build.gradle
build.common.gradle  
build.dependencies.gradle
settings.gradle (add MeepMeepTesting module)
TeamCode/build.gradle (add RoadRunner dependencies)
MeepMeepTesting/build.gradle
```

### 2. RoadRunner Core (COPY ALL)
```
TeamCode/roadrunner/
├── MecanumDrive.java          ⭐ CRITICAL
├── PinpointDrive.java         ⭐ If using Pinpoint
├── Localizer.java
├── TwoDeadWheelLocalizer.java (alternative)
├── ThreeDeadWheelLocalizer.java (alternative)
├── Drawing.java               ⭐ Dashboard viz
└── tuning/
    ├── LocalizationTest.java  ⭐ Test first!
    ├── SplineTest.java
    └── ManualFeedbackTuner.java
```

### 3. Utilities (COPY ALL)
```
TeamCode/subsystem/utility/
├── BulkReader.java            ⭐ Performance boost
└── cachedhardware/
    ├── CachedDcMotorEx.java   ⭐ Prevents I2C spam
    ├── CachedMotorEx.java
    └── CachedSimpleServo.java
```

### 4. Control Framework (COPY ALL)
```
TeamCode/control/
├── FirstTerminateAction.kt    ⭐ Custom action
├── motion/
│   └── EditablePose.java      ⭐⭐⭐ CRITICAL BRIDGE
└── controller/
    ├── Controller.java
    └── PIDController.java
```

### 5. MeepMeep Module (COPY STRUCTURE)
```
MeepMeepTesting/
├── build.gradle
└── src/main/java/.../
    ├── EditablePose.java      ⭐⭐⭐ SAME AS TEAMCODE
    └── MeepMeepTesting.java   (TEMPLATE)
```

---

## ⚡ Quick Start Steps

### Step 1: Setup (30 min)
```bash
# 1. Copy gradle files to your DECODE repo
# 2. Update settings.gradle to include MeepMeepTesting
# 3. Sync gradle (may take a few minutes)
```

### Step 2: Port MecanumDrive (1 hour)
```java
// In MecanumDrive.Params, update:
public double inPerTick = 1;  // For Pinpoint: leave as 1
public double lateralInPerTick = 0.649...;  // Tune with LateralRampLogger

// Update motor names to match your config:
leftFront = new CachedDcMotorEx(hardwareMap.get(DcMotorEx.class, "leftFront"));
// etc.

// Update IMU orientation:
public RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection;
public RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection;
```

### Step 3: Test Localization (15 min)
```java
// Run LocalizationTest OpMode
// Drive in squares, check if pose matches
// If not: adjust lateralInPerTick
```

### Step 4: Create Robot.java (30 min)
```java
public final class Robot {
    public final PinpointDrive drivetrain;
    // Add your subsystems here
    
    public Robot(HardwareMap hardwareMap, Pose2d startPose) {
        drivetrain = new PinpointDrive(hardwareMap, startPose);
        // Initialize subsystems
    }
    
    public void run() {
        // Update all subsystems
    }
}
```

### Step 5: Setup MeepMeep (45 min)
```java
// 1. Copy MeepMeepTesting/build.gradle
// 2. Create EditablePose.java in BOTH packages (EXACT SAME FILE)
// 3. Create MeepMeepTesting.java with main() method
// 4. Run as Java application (not Android)
```

### Step 6: Design First Path (30 min)
```java
// In MeepMeepTesting.java:
public static EditablePose
    start = new EditablePose(0, 0, 0),
    score = new EditablePose(24, 24, PI/4);

// Build trajectory
builder.actionBuilder(start.toPose2d())
    .strafeToLinearHeading(score.toVector2d(), score.heading)
    .build();

// Run MeepMeep desktop app - visualize path
```

### Step 7: Copy to Auto.java (15 min)
```java
// In Auto.java - EXACT SAME POSES
public static EditablePose
    start = new EditablePose(0, 0, 0),
    score = new EditablePose(24, 24, PI/4);

// EXACT SAME TRAJECTORY CODE
Action auto = robot.drivetrain.actionBuilder(start.toPose2d())
    .strafeToLinearHeading(score.toVector2d(), score.heading)
    .build();

Actions.runBlocking(auto);
```

---

## 🎯 Critical Code Patterns

### Pattern 1: EditablePose (The Bridge)
```java
// Define ONCE in Auto.java
public static EditablePose scoring = new EditablePose(24, 24, PI/4);

// Use in trajectory
.strafeToLinearHeading(scoring.toVector2d(), scoring.heading)

// SAME pose in MeepMeepTesting.java
public static EditablePose scoring = new EditablePose(24, 24, PI/4);

// Update in Dashboard → Change in both files → Consistent everywhere
```

### Pattern 2: Cached Hardware
```java
// DON'T do this:
motor.setPower(0.5);  // Every loop = I2C spam

// DO this:
CachedDcMotorEx motor = new CachedDcMotorEx(hardwareMap.get(...));
motor.setPower(0.5);  // Only sends when value changes
```

### Pattern 3: Action Composition
```java
// Subsystem actions
Action intake = robot.intake::grab;
Action score = robot.deposit::score;

// Combine in trajectory
Action auto = robot.drivetrain.actionBuilder(start)
    .strafeToLinearHeading(intakePos.toVector2d(), intakePos.heading)
    .stopAndAdd(intake)
    .strafeToLinearHeading(scorePos.toVector2d(), scorePos.heading)
    .stopAndAdd(score)
    .build();

Actions.runBlocking(auto);
```

### Pattern 4: Parallel Actions
```java
// Start intake WHILE driving
.afterTime(0, () -> robot.intake.extend())
.strafeToLinearHeading(target.toVector2d(), target.heading)
// Intake finishes during drive - saves time!
```

### Pattern 5: Custom Constraints
```java
// Slow down for precise pickup
MinVelConstraint slowConstraint = new MinVelConstraint(Arrays.asList(
    new TranslationalVelConstraint(5),    // 5 in/s
    new AngularVelConstraint(0.75)        // 0.75 rad/s
));

.lineToY(pickupY, slowConstraint)  // Use for specific segment
```

### Pattern 6: State Machine Subsystem
```java
public class Intake {
    enum State { STANDBY, EXTENDING, GRABBING, RETRACTING }
    private State state = STANDBY;
    private ElapsedTime timer = new ElapsedTime();
    
    public void run() {
        switch (state) {
            case STANDBY:
                if (shouldGrab) {
                    state = EXTENDING;
                    timer.reset();
                }
                break;
            case EXTENDING:
                extendo.extend();
                if (timer.seconds() > 0.5) state = GRABBING;
                break;
            // etc.
        }
    }
}
```

---

## 🔍 Debugging Checklist

### Localization Issues
- [ ] Run LocalizationTest
- [ ] Drive 48" forward - does pose say 48"?
- [ ] Drive 48" left - does pose say 48"?
- [ ] If not: tune `lateralInPerTick` in MecanumDrive.Params

### Path Following Issues
- [ ] Check motor directions (use MecanumDirectionDebugger)
- [ ] Verify PID gains (start with axialGain = lateralGain = headingGain = 8)
- [ ] Check feedforward (kS, kV, kA)
- [ ] Use Dashboard to visualize path vs actual

### MeepMeep Not Working
- [ ] Is MeepMeepTesting a separate Java module? (not Android)
- [ ] Does EditablePose exist in BOTH packages?
- [ ] Are poses EXACTLY the same values?
- [ ] Run as Java application (green play button)

### Actions Not Running
- [ ] Is `Actions.runBlocking()` called?
- [ ] Does action return `false` when complete?
- [ ] Check Dashboard for errors
- [ ] Add telemetry to action's `run()` method

---

## 📊 Tuning Parameters

### MecanumDrive.Params (Start Here)
```java
// Feedforward (tune with AngularRampLogger, ForwardRampLogger)
public double kS = 0.01;   // Static friction
public double kV = 0.17;   // Velocity
public double kA = 0.04;   // Acceleration

// Motion limits
public double maxWheelVel = 60;      // in/s
public double maxAngVel = 5.4;       // rad/s
public double maxProfileAccel = 60;  // in/s²

// PID gains (tune with ManualFeedbackTuner)
public double axialGain = 8;      // Forward/backward tracking
public double lateralGain = 8;    // Left/right tracking
public double headingGain = 8;    // Rotation tracking
```

### PinpointDrive.Params
```java
// Odometry pod offsets (measure from robot center to pod)
public double xOffset = 5.5;       // mm, left = positive
public double yOffset = -48.97;    // mm, forward = positive

// Pod type (don't change unless using different pods)
public double encoderResolution = goBILDA_SWINGARM_POD;

// Pod directions (test with LocalizationTest)
public EncoderDirection xDirection = FORWARD;
public EncoderDirection yDirection = REVERSED;
```

---

## 🎓 Common Mistakes

### ❌ DON'T: Different poses in MeepMeep vs Auto
```java
// MeepMeepTesting.java
EditablePose scoring = new EditablePose(24, 24, PI/4);

// Auto.java  
EditablePose scoring = new EditablePose(25, 23, PI/4);  // DIFFERENT!
```
**Result:** Path looks good in MeepMeep, fails on robot

### ✅ DO: Same values everywhere
```java
// Both files - IDENTICAL
public static EditablePose scoring = new EditablePose(24, 24, PI/4);
```

### ❌ DON'T: Block in subsystem run()
```java
void run() {
    extendo.extend();
    Thread.sleep(500);  // BLOCKS ENTIRE LOOP!
    claw.close();
}
```

### ✅ DO: Use state machine with timer
```java
void run() {
    switch (state) {
        case EXTENDING:
            extendo.extend();
            if (timer.seconds() > 0.5) state = CLOSING;
            break;
        case CLOSING:
            claw.close();
            break;
    }
}
```

### ❌ DON'T: Set motor power every loop
```java
void run() {
    motor.setPower(0.5);  // Sends I2C command every loop!
}
```

### ✅ DO: Use CachedDcMotorEx
```java
CachedDcMotorEx motor = new CachedDcMotorEx(...);
void run() {
    motor.setPower(0.5);  // Only sends when value changes
}
```

---

## 🚀 Performance Tips

1. **Use BulkReader**
   ```java
   BulkReader bulkReader = new BulkReader(hardwareMap);
   // At start of loop:
   bulkReader.bulkRead();
   ```

2. **Cache All Hardware**
   ```java
   CachedDcMotorEx motor = new CachedDcMotorEx(hardwareMap.get(...));
   CachedSimpleServo servo = CachedSimpleServo.getAxon(hardwareMap, "servo");
   ```

3. **Passive Motion**
   ```java
   // Start lift WHILE driving to save time
   .afterTime(0, () -> robot.lift.goToHeight(HIGH))
   .strafeToLinearHeading(target.toVector2d(), target.heading)
   ```

4. **Minimize Object Allocation**
   ```java
   // DON'T create new objects in loop
   void run() {
       Vector2d v = new Vector2d(x, y);  // BAD - allocates every loop
   }
   
   // DO reuse objects
   private final Vector2d velocity = new Vector2d(0, 0);
   void run() {
       velocity.x = x;
       velocity.y = y;
   }
   ```

---

## 📱 Dashboard Usage

### View Live Data
1. Connect to robot WiFi
2. Open browser: `http://192.168.43.1:8080/dash`
3. View telemetry, field view, graphs

### Tune Parameters
```java
@Config
public class MecanumDrive {
    public static class Params {
        public double axialGain = 8;  // Shows up in Dashboard
    }
}
```
1. Change value in Dashboard
2. No need to redeploy!
3. Values reset on app restart (save good values to code)

### View Trajectories
- Actions automatically draw path on field view
- Blue = current position
- Green = target position
- Line = planned path

---

## 🎯 Next Steps for DECODE

1. **Week 1:** Copy gradle + MecanumDrive + test localization
2. **Week 2:** Create Robot.java + basic subsystems
3. **Week 3:** Setup MeepMeep + design first path
4. **Week 4:** Implement Auto.java + test on robot
5. **Week 5:** Build TeleOp + driver practice
6. **Week 6-8:** Iterate and optimize

**Pro Tip:** Don't wait for robot to be done! Setup MeepMeep and design paths with estimated dimensions. Adjust when robot is ready.

---

## 📚 Further Reading

- Full analysis: See `REPO_ANALYSIS.md` (75+ pages)
- RoadRunner docs: https://rr.brott.dev/docs/v1-0/
- MeepMeep: https://github.com/NoahBres/MeepMeep
- FTC Discord: #roadrunner-help channel

---

**Remember:** The key to this framework is **EditablePose** + **MeepMeep**. Master that workflow and you'll iterate 10x faster than competitors! 🚀

