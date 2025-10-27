# 🚀 MeepMeep to Robot Workflow Guide

## The Problem You're Solving

You want to design autonomous paths **without the robot**, then easily transfer that code to run **on the robot**. This is exactly what the MeepMeep + EditablePose workflow enables!

---

## 📦 What You Have Now

### 1. **EditablePose Class** (exists in TWO places)
- `TeamCode/src/.../control/motion/EditablePose.java`
- `MeepMeepTesting/src/.../EditablePose.java`

These files are **IDENTICAL**. They're the bridge between simulation and reality.

### 2. **MeepMeepTesting.java** (simulation)
- Desktop Java application (runs on your computer, NOT on robot)
- Visualizes paths on a virtual field
- Fast iteration - no deployment needed!

### 3. **TemplateAuto.java** (robot code)
- Actual autonomous OpMode
- Runs on the robot
- Uses same EditablePose values as MeepMeep

---

## 🔄 The Complete Workflow

### Step 1: Design in MeepMeep (NO ROBOT NEEDED!)

```java
// In MeepMeepTesting.java

// Define your poses
public static EditablePose
    start = new EditablePose(0, 0, 0),
    score = new EditablePose(24, 24, Math.PI/4),
    sample = new EditablePose(30, 0, 0);

// Build trajectory
myBot.runAction(myBot.getDrive().actionBuilder(start.toPose2d())
    .strafeToLinearHeading(score.toVector2d(), score.heading)
    .strafeToLinearHeading(sample.toVector2d(), sample.heading)
    .build());
```

**To run:** Right-click `MeepMeepTesting.java` → Run as Java Application

**What you see:** Animated robot following your path on the field!

---

### Step 2: Copy to Robot Code

```java
// In TemplateAuto.java (or your actual Auto OpMode)

// EXACT SAME poses
public static EditablePose
    start = new EditablePose(0, 0, 0),
    score = new EditablePose(24, 24, Math.PI/4),
    sample = new EditablePose(30, 0, 0);

// EXACT SAME trajectory code
Action auto = drive.actionBuilder(start.toPose2d())
    .strafeToLinearHeading(score.toVector2d(), score.heading)
    .stopAndAdd(robot.score())  // Add subsystem actions!
    .strafeToLinearHeading(sample.toVector2d(), sample.heading)
    .stopAndAdd(robot.intake())
    .build();

Actions.runBlocking(auto);
```

**Key difference:** Added `.stopAndAdd()` for subsystem actions

---

### Step 3: Test on Robot

1. Deploy code to robot
2. Run your Auto OpMode
3. Open FTC Dashboard: `http://192.168.43.1:8080/dash`
4. Watch the robot's actual path vs target path

---

### Step 4: Tune Without Redeploying

If the robot isn't quite hitting the right spots:

1. Open FTC Dashboard
2. Go to "Configuration" tab
3. Find your Auto class (e.g., `TemplateAuto`)
4. Edit the pose values directly:
   ```
   TemplateAuto.score.x = 25.5
   TemplateAuto.score.y = 23.8
   ```
5. Re-run autonomous **without redeploying!**
6. Repeat until perfect

---

### Step 5: Update MeepMeep (Verify Changes)

After tuning on the robot:

1. Copy the new pose values back to `MeepMeepTesting.java`
2. Re-run MeepMeep to visualize the updated path
3. Confirm it looks correct

---

## 🎯 Key Concepts

### What is EditablePose?

A simple container for position (x, y) and heading that works in BOTH MeepMeep and Robot code.

```java
EditablePose pose = new EditablePose(24, 24, Math.PI/4);

// Convert to different formats as needed:
Pose2d fullPose = pose.toPose2d();        // For start pose
Vector2d position = pose.toVector2d();     // For target position
double heading = pose.heading;             // For target heading

// Calculate relationships:
double distance = pose1.distTo(pose2);     // Distance between poses
double angle = pose1.angleTo(pose2);       // Angle from pose1 to pose2
```

### Why Use EditablePose?

**Without EditablePose:**
```java
// MeepMeep
myBot.getDrive().actionBuilder(new Pose2d(0, 0, 0))
    .strafeToLinearHeading(new Vector2d(24, 24), Math.PI/4)
    
// Robot - have to retype coordinates, easy to make mistakes!
drive.actionBuilder(new Pose2d(0, 0, 0))
    .strafeToLinearHeading(new Vector2d(24, 23.5), Math.PI/4)  // Oops, typo!
```

**With EditablePose:**
```java
// Define ONCE, use everywhere
EditablePose score = new EditablePose(24, 24, Math.PI/4);

// MeepMeep
.strafeToLinearHeading(score.toVector2d(), score.heading)

// Robot (EXACT SAME)
.strafeToLinearHeading(score.toVector2d(), score.heading)
```

---

## 📋 Common Trajectory Patterns

### Pattern 1: Straight Lines
```java
// Just X movement
.lineToX(24)

// Just Y movement  
.lineToY(-48)

// Diagonal line, maintain heading
.lineToConstantHeading(new Vector2d(24, 24))

// Or with EditablePose
.lineToConstantHeading(target.toVector2d())
```

### Pattern 2: Strafe to Position
```java
// Change position AND heading (most common)
.strafeToLinearHeading(score.toVector2d(), score.heading)

// Change position, maintain heading
.strafeTo(target.toVector2d())
```

### Pattern 3: Smooth Splines
```java
// Smooth curve to target (better for speed)
.splineToLinearHeading(
    target.toPose2d(),
    start.angleTo(target)  // Initial tangent angle
)

// Multiple splines for smooth path
.splineToLinearHeading(score1.toPose2d(), start.angleTo(score1))
.splineToLinearHeading(sample.toPose2d(), score1.angleTo(sample))
.splineToLinearHeading(score2.toPose2d(), sample.angleTo(score2))
```

### Pattern 4: Turn in Place
```java
// Turn to specific heading
.turnTo(Math.PI/2)

// Turn by relative angle
.turn(Math.toRadians(90))
```

### Pattern 5: Add Subsystem Actions
```java
// Stop and do something
.stopAndAdd(robot.score())

// Do something during movement (parallel)
.afterTime(0.5, robot.prepareToScore())  // After 0.5 seconds into trajectory

// Do something at distance marker
.afterDisp(12, robot.lowerIntake())  // After 12 inches traveled
```

---

## 🎓 Road Runner Trajectory Builder Reference

### Movement Commands

| Command | What it does | When to use |
|---------|--------------|-------------|
| `.lineToX(x)` | Move to X coordinate | Simple forward/back |
| `.lineToY(y)` | Move to Y coordinate | Simple left/right |
| `.lineToConstantHeading(Vector2d)` | Line to position, keep heading | Precise positioning |
| `.lineToLinearHeading(Pose2d)` | Line to position, interpolate heading | Gradual turns |
| `.strafeToLinearHeading(Vector2d, heading)` | Strafe to position, interpolate heading | **Most common** |
| `.strafeTo(Vector2d)` | Strafe to position, keep heading | Quick repositioning |
| `.splineToLinearHeading(Pose2d, tangent)` | Smooth curve with heading change | Fast, smooth paths |
| `.turn(angle)` | Turn in place | Precise orientation |
| `.turnTo(heading)` | Turn to absolute heading | Face specific direction |

### Action Commands

| Command | What it does |
|---------|--------------|
| `.stopAndAdd(Action)` | Stop moving, do action, continue |
| `.afterTime(seconds, Action)` | Do action after time delay |
| `.afterDisp(inches, Action)` | Do action after distance traveled |
| `.waitSeconds(seconds)` | Pause trajectory |

### Constraint Commands

| Command | What it does |
|---------|--------------|
| `.setTangent(angle)` | Set initial direction for next spline |
| `.setReversed(boolean)` | Drive backwards |
| `.setConstraints(vel, accel)` | Temporarily change speed limits |
| `.resetConstraints()` | Return to default speed limits |

---

## 🛠️ Troubleshooting

### "My robot doesn't follow the path accurately"

1. **Check localization**: Run `LocalizationTest` OpMode
2. **Tune follower PID**: Adjust gains in `MecanumDrive.Params`
3. **Verify constraints**: Make sure MeepMeep constraints match robot

### "Robot goes to wrong position"

1. **Check starting pose**: Make sure `drive = new MecanumDrive(hardwareMap, start.toPose2d())`
2. **Verify field coordinates**: Field is 144" x 144", center is (0, 0)
3. **Check motor directions**: Ensure motors are configured correctly

### "Path looks good in MeepMeep but bad on robot"

1. **Update MeepMeep constraints** to match actual robot performance
2. **Check wheel diameter/track width** in `MecanumDrive.Params`
3. **Verify lateral multiplier** (for mecanum drift)

### "Can't edit poses in Dashboard"

1. Make sure class has `@Config` annotation
2. Make sure poses are `public static`
3. Refresh Dashboard after code change

---

## 📚 Example: Complete Autonomous Routine

### In MeepMeepTesting.java:
```java
public static EditablePose
    start = new EditablePose(-36, -63, Math.PI/2),
    preload = new EditablePose(-55, -55, Math.PI/4),
    sample1 = new EditablePose(-48, -40, Math.PI/2),
    sample2 = new EditablePose(-58, -40, Math.PI/2),
    sample3 = new EditablePose(-52, -40, Math.PI/2),
    park = new EditablePose(-60, -60, Math.PI);

myBot.runAction(myBot.getDrive().actionBuilder(start.toPose2d())
    .splineToLinearHeading(preload.toPose2d(), start.angleTo(preload))
    .strafeToLinearHeading(sample1.toVector2d(), sample1.heading)
    .strafeToLinearHeading(preload.toVector2d(), preload.heading)
    .strafeToLinearHeading(sample2.toVector2d(), sample2.heading)
    .strafeToLinearHeading(preload.toVector2d(), preload.heading)
    .strafeToLinearHeading(sample3.toVector2d(), sample3.heading)
    .strafeToLinearHeading(preload.toVector2d(), preload.heading)
    .strafeToLinearHeading(park.toVector2d(), park.heading)
    .build());
```

### In Auto.java (copy-paste, add actions):
```java
public static EditablePose
    start = new EditablePose(-36, -63, Math.PI/2),
    preload = new EditablePose(-55, -55, Math.PI/4),
    sample1 = new EditablePose(-48, -40, Math.PI/2),
    sample2 = new EditablePose(-58, -40, Math.PI/2),
    sample3 = new EditablePose(-52, -40, Math.PI/2),
    park = new EditablePose(-60, -60, Math.PI);

Action auto = drive.actionBuilder(start.toPose2d())
    .splineToLinearHeading(preload.toPose2d(), start.angleTo(preload))
    .stopAndAdd(robot.scorePreload())
    
    .strafeToLinearHeading(sample1.toVector2d(), sample1.heading)
    .stopAndAdd(robot.pickupSample())
    .strafeToLinearHeading(preload.toVector2d(), preload.heading)
    .stopAndAdd(robot.scoreSample())
    
    .strafeToLinearHeading(sample2.toVector2d(), sample2.heading)
    .stopAndAdd(robot.pickupSample())
    .strafeToLinearHeading(preload.toVector2d(), preload.heading)
    .stopAndAdd(robot.scoreSample())
    
    .strafeToLinearHeading(sample3.toVector2d(), sample3.heading)
    .stopAndAdd(robot.pickupSample())
    .strafeToLinearHeading(preload.toVector2d(), preload.heading)
    .stopAndAdd(robot.scoreSample())
    
    .strafeToLinearHeading(park.toVector2d(), park.heading)
    .build();

Actions.runBlocking(auto);
```

---

## ✅ Quick Reference Checklist

**Before Running MeepMeep:**
- [ ] EditablePose.java exists in MeepMeepTesting package
- [ ] Poses defined at top of MeepMeepTesting.java
- [ ] Trajectory built using `.actionBuilder()`
- [ ] Bot constraints match expected robot performance

**Before Running on Robot:**
- [ ] EditablePose.java exists in TeamCode package
- [ ] Same pose values copied from MeepMeep
- [ ] Same trajectory code copied from MeepMeep
- [ ] Subsystem actions added with `.stopAndAdd()`
- [ ] Starting pose set: `new MecanumDrive(hardwareMap, start.toPose2d())`
- [ ] Class marked with `@Config` for Dashboard tuning

**After First Robot Run:**
- [ ] Check Dashboard for actual vs target path
- [ ] Tune pose values in Dashboard if needed
- [ ] Copy tuned values back to MeepMeep
- [ ] Verify in MeepMeep

---

## 🎉 You're Ready!

You now have a complete workflow for designing autonomous routines without the robot! The key innovation is **EditablePose** - one set of coordinates that works in both simulation and reality.

**Happy coding, and good luck this season! 🤖**

