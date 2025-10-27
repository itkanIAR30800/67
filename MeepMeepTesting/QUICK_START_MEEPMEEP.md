# ⚡ Quick Start: MeepMeep to Robot in 5 Minutes

## ✅ Setup Checklist (One-Time)

- [x] ✅ EditablePose.java created in TeamCode (`control/motion/EditablePose.java`)
- [x] ✅ EditablePose.java created in MeepMeepTesting (IDENTICAL copy)
- [x] ✅ MeepMeepTesting.java updated with comprehensive examples
- [x] ✅ TemplateAuto.java created as starter template

**You're all set!** The infrastructure is ready to use.

---

## 🚀 Create Your First Auto Path (5 min)

### 1️⃣ Design in MeepMeep (2 min)

Open `MeepMeepTesting/src/.../MeepMeepTesting.java`

**Edit the poses** (lines ~45-57):
```java
public static EditablePose
    start = new EditablePose(-36, -63, Math.PI/2),     // Your start pos
    score = new EditablePose(-55, -55, Math.PI/4),     // Where to score
    sample = new EditablePose(-48, -40, Math.PI/2);    // Where to pickup
```

**Edit the trajectory** (lines ~75-80):
```java
myBot.runAction(myBot.getDrive().actionBuilder(start.toPose2d())
    .strafeToLinearHeading(score.toVector2d(), score.heading)
    .strafeToLinearHeading(sample.toVector2d(), sample.heading)
    .build());
```

**Run it:** Right-click file → Run 'MeepMeepTesting.main()'

**Watch:** Animated robot follows your path! ✨

---

### 2️⃣ Copy to Robot (2 min)

**Option A: Use Template**
1. Copy `TemplateAuto.java` → `BlueLeftAuto.java` (or whatever you want)
2. Replace the pose values (copy from MeepMeep)
3. Replace the trajectory code (copy from MeepMeep)
4. Rename class to match filename

**Option B: Create From Scratch**
```java
package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.control.motion.EditablePose;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

@Config
@Autonomous(name = "My First Auto")
public class MyFirstAuto extends LinearOpMode {
    
    // PASTE POSES FROM MEEPMEEP HERE
    public static EditablePose
        start = new EditablePose(-36, -63, Math.PI/2),
        score = new EditablePose(-55, -55, Math.PI/4),
        sample = new EditablePose(-48, -40, Math.PI/2);
    
    @Override
    public void runOpMode() {
        MecanumDrive drive = new MecanumDrive(hardwareMap, start.toPose2d());
        
        waitForStart();
        
        // PASTE TRAJECTORY FROM MEEPMEEP HERE
        Action auto = drive.actionBuilder(start.toPose2d())
            .strafeToLinearHeading(score.toVector2d(), score.heading)
            .strafeToLinearHeading(sample.toVector2d(), sample.heading)
            .build();
        
        Actions.runBlocking(auto);
    }
}
```

---

### 3️⃣ Test on Robot (1 min)

1. Deploy to robot
2. Select your auto from Driver Station
3. Press INIT → START
4. Watch it go! 🤖

---

## 🎯 Common Trajectory Patterns

### Basic Movement
```java
// Strafe to position with heading (most common)
.strafeToLinearHeading(target.toVector2d(), target.heading)

// Smooth spline curve
.splineToLinearHeading(target.toPose2d(), start.angleTo(target))

// Just move forward/back
.lineToX(24)

// Just strafe left/right
.lineToY(-48)

// Turn in place
.turn(Math.toRadians(90))
```

### With Subsystems
```java
.strafeToLinearHeading(score.toVector2d(), score.heading)
.stopAndAdd(robot.score())  // Do action when you get there

.afterTime(0.5, robot.prepareIntake())  // Do action after 0.5s

.afterDisp(12, robot.lowerArm())  // Do action after 12 inches
```

---

## 🔧 Troubleshooting

### ❌ Robot doesn't go where expected

**Check starting pose:**
```java
// Make sure this matches your actual start position!
MecanumDrive drive = new MecanumDrive(hardwareMap, start.toPose2d());
```

**Check field coordinates:**
- Field center is (0, 0)
- Red alliance: Negative Y side
- Blue alliance: Positive Y side
- Field is 144" × 144"

### ❌ Path looks different in MeepMeep vs Robot

**Update MeepMeep constraints to match robot:**
```java
// In MeepMeepTesting.java, around line 66
.setConstraints(
    60,                      // Match MecanumDrive.PARAMS.maxWheelVel
    60,                      // Match MecanumDrive.PARAMS.maxProfileAccel
    Math.toRadians(180),     // Match PARAMS.maxAngVel
    Math.toRadians(180),     // Match PARAMS.maxAngAccel
    15                       // Your actual track width
)
```

### ❌ Can't tune values in Dashboard

Make sure:
1. Class has `@Config` annotation
2. Poses are `public static`
3. Dashboard is connected: `http://192.168.43.1:8080/dash`

---

## 📖 Learn More

- **Full Workflow Guide:** `MEEPMEEP_WORKFLOW_GUIDE.md` (in repo root)
- **Road Runner Docs:** https://learnroadrunner.com
- **Your Architecture Docs:**
    - `MATURE_ARCHITECTURE_DIAGRAMS.md`
    - `MATURE_QUICK_REFERENCE.md`
    - `MATURE_REPO_ANALYSIS.md`

---

## 🎉 You're Ready to Go!

The workflow is:
1. **Design** in MeepMeep (fast iteration, no robot)
2. **Copy** to Auto OpMode (exact same poses/code)
3. **Test** on robot (verify it works)
4. **Tune** in Dashboard (adjust without redeploying)
5. **Update** MeepMeep (keep simulation in sync)

**Pro tip:** Keep MeepMeep open while coding. Every time you change a trajectory, run MeepMeep first to see if it looks right!

**Happy autonomous programming! 🚀**

