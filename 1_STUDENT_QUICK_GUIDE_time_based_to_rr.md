# 🎓 FTC Autonomous: Time-Based → RoadRunner

This is for: Start -> Move backwards -> Shoot 3 balls -> Strafe Left

Next: FSM and more advanced: This is for: Start -> Move backwards -> Shoot 3 balls -> Collect -> Shoot 3 balls, etc.

## 🚨 Problem: Time-Based Driving Is Unreliable

**OLD CODE:**
```java
runtime.reset();
while (opModeIsActive() && (runtime.seconds() < 0.75)) {
    for (int i = 0; i < amount_of_motors; i++) {
        motors[i].setPower(-FORWARD_SPEED);
    }
}
```

**Why it fails:**
- Fresh battery (13V): 0.75s = 18 inches ✅
- Dying battery (11V): 0.75s = 15 inches ❌ (3 inches short!)
- **Works in practice, fails at competition**

---

## ✅ Solution: Position-Based Control

**NEW CODE:**
```java
EditablePose startPos = new EditablePose(START_X, START_Y, Math.toRadians(90));
EditablePose backupPos = new EditablePose(START_X, START_Y + BACKUP_DISTANCE, Math.toRadians(90));

MecanumDrive drive = new MecanumDrive(hardwareMap, startPos.toPose2d());

Action auto = drive.actionBuilder(startPos.toPose2d())
    .strafeToLinearHeading(backupPos.toVector2d(), backupPos.heading)
    .stopAndAdd(shootBallsAction())
    .strafeToLinearHeading(strafePos.toVector2d(), strafePos.heading)
    .build();
    
Actions.runBlocking(auto);
```

**Why it works:**
- Uses wheel encoders to track exact position
- Adjusts power to compensate for battery voltage
- Accurate to ±0.5 inches vs ±3 inches
- **Reliable at competition**

---

## 📊 Comparison

| Feature | Time-Based (OLD) | Position-Based (NEW) |
|---------|------------------|----------------------|
| **Command** | "Run 0.75 seconds" | "Move to Y + 6 inches" |
| **Accuracy** | ±3 inches | ±0.5 inches |
| **Battery impact** | 😱 Huge | ✅ None |
| **Tuning** | 5 min (recompile) | 10 sec (dashboard) |
| **Code length** | 200+ lines | 150 lines |

---

## 🔑 3 Key Improvements

### 1️⃣ Time → Position

**OLD:**
```java
while (runtime.seconds() < 0.75) {
    motor.setPower(0.5);
}
// Did we go 6 inches? Who knows! 🤷
```

**NEW:**
```java
.strafeToLinearHeading(backupPos.toVector2d(), backupPos.heading)
// Robot KNOWS it moved exactly 6 inches ✅
```

### 2️⃣ Hard-Coded → Dashboard Tunable

**OLD:**
```java
static final double FORWARD_SPEED = 0.52;  // Can't change!
```

**NEW:**
```java
@Config
public static double BACKUP_DISTANCE = 6;  // Change in dashboard!
```

Visit `http://192.168.43.1:8080/dash` to adjust values without recompiling!

### 3️⃣ Motor Arrays → RoadRunner

**OLD:**
```java
int amount_of_motors = 4;
private DcMotor[] motors = new DcMotor[amount_of_motors];

for (int i = 0; i < amount_of_motors; i++) {
    motors[i].setPower(-FORWARD_SPEED);
}
// What is this doing? 😵
```

**NEW:**
```java
MecanumDrive drive = new MecanumDrive(hardwareMap, startPos.toPose2d());
.strafeToLinearHeading(backupPos.toVector2d(), backupPos.heading)
// Clear! ✨
```

---

## 🎯 Quick Start Guide

### Step 1: Define Positions
```java
@Config
public class MyAuto extends LinearOpMode {
    public static double START_X = -36;
    public static double START_Y = -60;
    public static double BACKUP_DISTANCE = 6;
}
```

### Step 2: Calculate Poses
```java
EditablePose startPos = new EditablePose(START_X, START_Y, Math.toRadians(90));
EditablePose backupPos = new EditablePose(START_X, START_Y + BACKUP_DISTANCE, Math.toRadians(90));
```

### Step 3: Build Path
```java
MecanumDrive drive = new MecanumDrive(hardwareMap, startPos.toPose2d());

Action auto = drive.actionBuilder(startPos.toPose2d())
    .strafeToLinearHeading(backupPos.toVector2d(), backupPos.heading)
    .stopAndAdd(shootBallsAction())
    .strafeToLinearHeading(strafePos.toVector2d(), strafePos.heading)
    .build();
    
Actions.runBlocking(auto);
```

---

## 🏆 Competition Workflow

### OLD: 5+ minutes per change
1. Edit code
2. Build (2 min)
3. Deploy (3 min)
4. Test → Wrong? Start over 😫

### NEW: 10 seconds per change  
1. Test in MeepMeep (optional)
2. Deploy once
3. Adjust in dashboard (10 sec)
4. Test → Wrong? Adjust again ✨

---

## 💡 Key Takeaways

✅ **Position control beats time-based** - Reliable at any battery voltage

✅ **Dashboard tuning saves time** - Adjust between matches in seconds

✅ **RoadRunner simplifies code** - No more confusing motor arrays

✅ **Test with dying battery** - If it works on low voltage, it works anywhere

---

## 🔧 Next Level: Reusable Hardware

**Current issue:** Hardware initialization is duplicated in every autonomous file.

**Better approach:** Create a `RobotHardware` class:

```java
// RobotHardware.java - Write once, use everywhere
public class RobotHardware {
    public DcMotor intakeMotor;
    public DcMotor shooterRightMotor;
    public DcMotor shooterLeftMotor;
    public Servo transferArm;
    
    public void init(HardwareMap hardwareMap) {
        // All hardware initialization here
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        // ... etc
    }
}

// Then in your autonomous:
private RobotHardware robot = new RobotHardware();

public void runOpMode() {
    robot.init(hardwareMap);  // One line!
    // Use robot.shooterLeftMotor, robot.transferArm, etc.
}
```

**Benefits:**
- Write hardware init **once**, use in all OpModes (blue auto, red auto, teleop)
- Fix bugs in **one place**
- Add new hardware? Update **one file**
- Professional team organization

**When to do this:** When you create your second autonomous (red side) or add more hardware.

---

