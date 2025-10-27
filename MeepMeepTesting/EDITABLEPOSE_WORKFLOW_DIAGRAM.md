# 📊 Visual Workflow: MeepMeep ↔ Robot Code

```
┌─────────────────────────────────────────────────────────────────────┐
│                    THE EDITABLEPOSE WORKFLOW                         │
└─────────────────────────────────────────────────────────────────────┘


STEP 1: DESIGN IN MEEPMEEP (Desktop - No Robot Needed!)
═══════════════════════════════════════════════════════
┌─────────────────────────────────────────────────────────────────┐
│ MeepMeepTesting.java                                            │
├─────────────────────────────────────────────────────────────────┤
│                                                                  │
│  public static EditablePose                                     │
│      start  = new EditablePose(-36, -63, Math.PI/2),           │
│      score  = new EditablePose(-55, -55, Math.PI/4),           │
│      sample = new EditablePose(-48, -40, Math.PI/2);           │
│                                                                  │
│  myBot.runAction(                                               │
│      myBot.getDrive().actionBuilder(start.toPose2d())          │
│          .strafeToLinearHeading(                               │
│              score.toVector2d(), score.heading)                │
│          .strafeToLinearHeading(                               │
│              sample.toVector2d(), sample.heading)              │
│          .build()                                               │
│  );                                                             │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
                            │
                            │ Right-click → Run
                            ▼
                    ┌───────────────┐
                    │   MeepMeep    │
                    │  Visualization │
                    │  🖥️  Window    │
                    └───────────────┘
                            │
                            │ ✅ Path looks good!
                            ▼


STEP 2: COPY TO ROBOT CODE (Exact Same Values!)
════════════════════════════════════════════════
┌─────────────────────────────────────────────────────────────────┐
│ BlueLeftAuto.java (or your Auto OpMode)                         │
├─────────────────────────────────────────────────────────────────┤
│                                                                  │
│  @Config                           ← Enables Dashboard tuning   │
│  @Autonomous(name = "Blue Left")                                │
│  public class BlueLeftAuto extends LinearOpMode {               │
│                                                                  │
│      // PASTE: Same poses from MeepMeep                         │
│      public static EditablePose                                 │
│          start  = new EditablePose(-36, -63, Math.PI/2), ◄─────┐│
│          score  = new EditablePose(-55, -55, Math.PI/4), ◄─────┤│
│          sample = new EditablePose(-48, -40, Math.PI/2);◄─────┤││
│                                                                 │││
│      @Override                                                  │││
│      public void runOpMode() {                                  │││
│          MecanumDrive drive = new MecanumDrive(                 │││
│              hardwareMap, start.toPose2d()                      │││
│          );                                                     │││
│                                                                 │││
│          waitForStart();                                        │││
│                                                                 │││
│          // PASTE: Same trajectory from MeepMeep               │││
│          Action auto = drive.actionBuilder(start.toPose2d())   │││
│              .strafeToLinearHeading(                           │││
│                  score.toVector2d(), score.heading)            │││
│              .stopAndAdd(robot.score()) ← Add subsystem actions│││
│              .strafeToLinearHeading(                           │││
│                  sample.toVector2d(), sample.heading)          │││
│              .stopAndAdd(robot.pickup())                       │││
│              .build();                                          │││
│                                                                 │││
│          Actions.runBlocking(auto);                            │││
│      }                                                          │││
│  }                                                              │││
└─────────────────────────────────────────────────────────────────┘││
            │                                                      ││
            │ Deploy to robot                                     ││
            ▼                                                      ││
                                                                   ││
                                                                   ││
STEP 3: TEST ON ROBOT                                             ││
═════════════════════════                                         ││
┌───────────────┐                                                 ││
│  Robot 🤖     │  Runs autonomous                                ││
│               │  Follows path!                                  ││
└───────┬───────┘                                                 ││
        │                                                          ││
        │ Check FTC Dashboard                                     ││
        ▼                                                          ││
┌──────────────────────────────┐                                 ││
│  FTC Dashboard               │                                 ││
│  http://192.168.43.1:8080    │                                 ││
│                               │                                 ││
│  📊 Shows:                   │                                 ││
│  • Robot's actual path       │                                 ││
│  • Target path               │                                 ││
│  • Localization data         │                                 ││
└──────────────┬───────────────┘                                 ││
               │                                                  ││
               │ Hmm, needs adjustment...                        ││
               ▼                                                  ││
                                                                  ││
                                                                  ││
STEP 4: TUNE IN DASHBOARD (No Redeploy!)                         ││
═════════════════════════════════════════                         ││
┌──────────────────────────────────────────────────────┐         ││
│  FTC Dashboard → Configuration Tab                   │         ││
│                                                       │         ││
│  BlueLeftAuto                                         │         ││
│    ├─ start                                           │         ││
│    │   ├─ x: -36.0          ← Adjust live!          │         ││
│    │   ├─ y: -63.0                                   │         ││
│    │   └─ heading: 1.57                              │         ││
│    ├─ score                                           │         ││
│    │   ├─ x: -54.5  ← Changed from -55!   ──────────┼─────────┘│
│    │   ├─ y: -55.2  ← Changed from -55!   ──────────┼──────────┘
│    │   └─ heading: 0.785                             │
│    └─ sample                                          │
│        ├─ x: -48.0                                   │
│        ├─ y: -40.0                                   │
│        └─ heading: 1.57                              │
│                                                       │
│  🔄 Changes apply IMMEDIATELY (no redeploy!)        │
└──────────────────────────────────────────────────────┘
                            │
                            │ Re-run autonomous
                            ▼
                    ┌───────────────┐
                    │  Robot 🤖     │  Perfect! ✅
                    └───────────────┘
                            │
                            ▼


STEP 5: UPDATE MEEPMEEP (Keep in Sync)
═══════════════════════════════════════
┌─────────────────────────────────────────────────────────────────┐
│ MeepMeepTesting.java                                            │
├─────────────────────────────────────────────────────────────────┤
│                                                                  │
│  public static EditablePose                                     │
│      start  = new EditablePose(-36, -63, Math.PI/2),           │
│      score  = new EditablePose(-54.5, -55.2, Math.PI/4), ← Updated!
│      sample = new EditablePose(-48, -40, Math.PI/2);           │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
                            │
                            │ Re-run MeepMeep
                            ▼
                    ┌───────────────┐
                    │   MeepMeep    │  Path matches reality! ✅
                    │  Visualization │
                    └───────────────┘


═══════════════════════════════════════════════════════════════════
                       KEY BENEFITS
═══════════════════════════════════════════════════════════════════

✅ Design WITHOUT the robot (fast iteration)
✅ EXACT SAME values in simulation and robot code
✅ Tune without redeploying (thanks to @Config)
✅ Visual feedback (MeepMeep + Dashboard)
✅ No coordinate mistakes (single source of truth)


═══════════════════════════════════════════════════════════════════
                     THE MAGIC: EditablePose
═══════════════════════════════════════════════════════════════════

EditablePose exists in BOTH projects with IDENTICAL code:
  • MeepMeepTesting/src/.../EditablePose.java
  • TeamCode/src/.../control/motion/EditablePose.java

This enables copying coordinates directly!

┌────────────────────────┐         ┌────────────────────────┐
│  EditablePose pose     │         │  EditablePose pose     │
│  = new EditablePose(   │  SAME   │  = new EditablePose(   │
│      24, 24, PI/4      │  ═════  │      24, 24, PI/4      │
│  );                    │         │  );                    │
│                        │         │                        │
│  MeepMeepTesting       │         │  TeamCode              │
└────────────────────────┘         └────────────────────────┘


═══════════════════════════════════════════════════════════════════
                     QUICK REFERENCE
═══════════════════════════════════════════════════════════════════

EditablePose Methods:
  .toPose2d()       → For starting pose
  .toVector2d()     → For target position
  .heading          → For target heading
  .angleTo(other)   → Angle between poses
  .distTo(other)    → Distance between poses

Common Trajectory Commands:
  .strafeToLinearHeading(pos, heading)  ← Most common
  .splineToLinearHeading(pose, tangent) ← Smooth curves
  .lineToX(x) / .lineToY(y)             ← Simple movement
  .turn(angle)                           ← Turn in place
  .stopAndAdd(action)                    ← Do subsystem action
  .afterTime(sec, action)                ← Timed action
  .waitSeconds(sec)                      ← Pause

Files Created:
  ✓ EditablePose.java (TeamCode)
  ✓ EditablePose.java (MeepMeepTesting)
  ✓ MeepMeepTesting.java (Updated with examples)
  ✓ TemplateAuto.java (Template for your autos)
  ✓ MEEPMEEP_WORKFLOW_GUIDE.md (This file!)
  ✓ QUICK_START_MEEPMEEP.md (Quick reference)


═══════════════════════════════════════════════════════════════════
                      🎉 YOU'RE READY! 🎉
═══════════════════════════════════════════════════════════════════
```

