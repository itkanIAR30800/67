# Architecture Diagrams - FTC Framework

## 🏗️ System Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                         FTC ROBOT SYSTEM                         │
├─────────────────────────────────────────────────────────────────┤
│                                                                  │
│  ┌────────────┐    ┌──────────────┐    ┌──────────────┐       │
│  │  Tele.java │    │  Auto.java   │    │ Test OpModes │       │
│  │  (TeleOp)  │    │(Autonomous)  │    │   (Tuning)   │       │
│  └─────┬──────┘    └──────┬───────┘    └──────┬───────┘       │
│        │                   │                    │                │
│        └───────────────────┴────────────────────┘                │
│                            │                                     │
│                   ┌────────▼─────────┐                          │
│                   │   Robot.java     │                          │
│                   │  (Coordinator)   │                          │
│                   └────────┬─────────┘                          │
│                            │                                     │
│        ┌───────────────────┼───────────────────┐                │
│        │                   │                   │                │
│  ┌─────▼─────┐      ┌─────▼──────┐     ┌─────▼──────┐         │
│  │ Drivetrain│      │   Intake   │     │  Deposit   │         │
│  │(Pinpoint) │      │   (FSM)    │     │   (FSM)    │         │
│  └─────┬─────┘      └─────┬──────┘     └─────┬──────┘         │
│        │                   │                   │                │
│  ┌─────▼─────────────────┬▼┬──────────────────▼────┐          │
│  │         Hardware Layer (REV Hubs, Motors,       │          │
│  │           Servos, Sensors, Pinpoint)            │          │
│  └─────────────────────────────────────────────────┘          │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘

                              │
                              │ Gradle Module Boundary
                              ▼

┌─────────────────────────────────────────────────────────────────┐
│                    MEEPMEEP (Desktop Java)                       │
├─────────────────────────────────────────────────────────────────┤
│                                                                  │
│  ┌──────────────────────────────────────────────────────┐      │
│  │          MeepMeepTesting.java (main)                 │      │
│  │  • Copies poses from Auto.java (EditablePose)        │      │
│  │  • Copies trajectory code from Auto.java             │      │
│  │  • Visualizes path on field                          │      │
│  │  • No robot hardware needed!                         │      │
│  └──────────────────────────────────────────────────────┘      │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## 🔄 Development Workflow

```
┌──────────────┐
│ 1. DESIGN    │  MeepMeep Desktop App
│   IN MEEP    │  • Define EditablePose positions
│   MEEP       │  • Build trajectory with RoadRunner API
└──────┬───────┘  • Visualize path on field
       │           • Iterate quickly (no robot!)
       │
       ▼
┌──────────────┐
│ 2. COPY TO   │  Auto.java
│   AUTO.JAVA  │  • Paste EXACT SAME EditablePose values
└──────┬───────┘  • Paste EXACT SAME trajectory builder code
       │           • Add subsystem actions (.stopAndAdd)
       │
       ▼
┌──────────────┐
│ 3. TEST ON   │  Robot Testing
│   ROBOT      │  • Deploy to robot
└──────┬───────┘  • Run autonomous
       │           • Check Dashboard for actual vs target
       │
       ▼
┌──────────────┐
│ 4. TUNE      │  FTC Dashboard
│   PARAMETERS │  • Adjust EditablePose values
└──────┬───────┘  • Adjust timing constants
       │           • NO REDEPLOY NEEDED (@Config)
       │
       ▼
┌──────────────┐
│ 5. VERIFY    │  Back to MeepMeep
│   IN MEEP    │  • Update same values in MeepMeepTesting
└──────┬───────┘  • Verify changes look good
       │           • Repeat cycle
       │
       └──────────► LOOP UNTIL PERFECT
```

---

## 🔌 RoadRunner Action Flow

```
Auto.java OpMode
       │
       ▼
┌──────────────────────────────────────────┐
│  Actions.runBlocking(                    │
│      new SequentialAction(               │
│          scorePreload,                   │
│          intakeSample1,                  │
│          scoreSample1,                   │
│          park                            │
│      )                                   │
│  )                                       │
└──────────────────┬───────────────────────┘
                   │
       ┌───────────┴───────────┐
       │                       │
       ▼                       ▼
┌─────────────┐       ┌────────────────┐
│ Trajectory  │       │   Subsystem    │
│   Action    │       │     Action     │
└──────┬──────┘       └────────┬───────┘
       │                       │
       │                       │
       ▼                       ▼
┌─────────────────────────────────────┐
│   PinpointDrive.FollowTrajectory    │
│   • Update localization             │
│   • Calculate error                 │
│   • Compute control (PID)           │
│   • Apply feedforward               │
│   • Set motor powers                │
│   • Draw on Dashboard               │
│   • Return true (continue)          │
│     or false (done)                 │
└─────────────────┬───────────────────┘
                  │
                  ▼
         ┌────────────────┐
         │  Motor Powers  │
         │  (Hardware)    │
         └────────────────┘
```

---

## 🎮 TeleOp Loop Structure

```
┌─────────────────────────────────────────┐
│         Tele.java runOpMode()           │
└────────────────┬────────────────────────┘
                 │
                 ▼
        ┌─────────────────┐
        │  while (active) │◄────────┐
        └────────┬─────────┘         │
                 │                   │
    ┌────────────┴────────────┐      │
    │                         │      │
    ▼                         ▼      │
┌─────────┐            ┌──────────┐ │
│ Gamepad │            │  Robot   │ │
│  Input  │            │  .run()  │ │
└────┬────┘            └─────┬────┘ │
     │                       │      │
     │                       │      │
     ▼                       ▼      │
┌─────────────┐      ┌────────────┐│
│ Drivetrain  │      │ Subsystems ││
│   .run()    │      │  .run()    ││
│ • Field     │      │ • Intake   ││
│   centric   │      │ • Deposit  ││
│ • Slow mode │      │ • Lift     ││
└─────────────┘      └────────────┘│
                                    │
         ┌──────────────────────────┘
         │
         ▼
    ┌─────────┐
    │Telemetry│
    │ Update  │
    └─────────┘
```

---

## 🤖 Subsystem State Machine Example (Intake)

```
              STANDBY
                 │
                 │ sample detected
                 ▼
         BUCKET_RETRACTING
                 │
                 │ timer >= TIME_BUCKET_RETRACT
                 ▼
        EXTENDO_RETRACTING
                 │
                 │ extendo.atPosition(0)
                 ▼
        ARM_ENTERING_BUCKET ──► deposit.transferFromIntake()
                 │
                 │ timer >= TIME_ENTERING
                 ▼
          COUNTER_ROLLING
                 │
                 │ timer >= TIME_COUNTER_ROLLING
                 ▼
           CLAW_CLOSING
                 │
                 │ timer >= TIME_CLAW_CLOSING
                 ▼
        ARM_EXITING_BUCKET
                 │
                 │ timer >= TIME_EXITING
                 ▼
              STANDBY
```

**Key Points:**
- Non-blocking (timer-based)
- Each state has clear entry/exit conditions
- Hardware updated at end of run()
- Other code continues running

---

## 🔧 Hardware Caching Pattern

```
┌──────────────────────────────────────────────┐
│           OpMode Loop (50 Hz)                │
└───────────────────┬──────────────────────────┘
                    │
        ┌───────────┴───────────┐
        │                       │
        ▼                       ▼
┌───────────────┐      ┌────────────────┐
│  WITHOUT      │      │   WITH         │
│  CACHING      │      │   CACHING      │
└───────┬───────┘      └────────┬───────┘
        │                       │
        ▼                       ▼
┌─────────────────┐    ┌──────────────────┐
│ motor.setPower  │    │CachedDcMotorEx   │
│   (0.5)         │    │  .setPower(0.5)  │
│                 │    │                  │
│ Sends I2C       │    │ if (power ==     │
│ every loop!     │    │    lastPower)    │
│ = 50 msgs/sec   │    │   return; ◄──┐   │
│                 │    │                │  │
│ ❌ SLOW         │    │ motor.set... ──┘  │
└─────────────────┘    │                  │
                       │ ✅ FAST          │
                       │ (only on change) │
                       └──────────────────┘
```

---

## 🌉 EditablePose Bridge Architecture

```
┌───────────────────────────────────────────────────────────────┐
│                    AUTO.JAVA (TeamCode)                        │
├───────────────────────────────────────────────────────────────┤
│                                                                │
│  @Config                                                       │
│  public static EditablePose                                    │
│      scoring = new EditablePose(24, 24, PI/4);                │
│                                                                │
│  Action auto = robot.drivetrain.actionBuilder(pose)           │
│      .strafeToLinearHeading(                                  │
│          scoring.toVector2d(),  ◄────── Converts to RR type   │
│          scoring.heading                                       │
│      )                                                         │
│      .build();                                                 │
│                                                                │
└───────────────────────────────────────────────────────────────┘
                              ▲
                              │
                    COPY PASTE (EXACT VALUES)
                              │
                              ▼
┌───────────────────────────────────────────────────────────────┐
│              MEEPMEEPTESTING.JAVA (Desktop)                    │
├───────────────────────────────────────────────────────────────┤
│                                                                │
│  public static EditablePose                                    │
│      scoring = new EditablePose(24, 24, PI/4);  ◄──SAME!      │
│                                                                │
│  TrajectoryActionBuilder builder =                            │
│      bot.getDrive().actionBuilder(pose)                       │
│      .strafeToLinearHeading(                                  │
│          scoring.toVector2d(),  ◄────── SAME CODE!            │
│          scoring.heading                                       │
│      )                                                         │
│      .build();                                                 │
│                                                                │
│  myBot.runAction(builder);  ◄────── Simulates on desktop      │
│                                                                │
└───────────────────────────────────────────────────────────────┘

KEY INSIGHT: EditablePose exists in BOTH packages as IDENTICAL class
            → Change once → Works everywhere
            → Design visually → Copy code → Test on robot
```

---

## 📦 Module Dependencies

```
┌─────────────────────────────────────────────────────────┐
│                    settings.gradle                      │
│  include ':FtcRobotController'                          │
│  include ':TeamCode'                                    │
│  include ':MeepMeepTesting'                             │
└────────────────────┬────────────────────────────────────┘
                     │
        ┌────────────┼────────────┐
        │            │            │
        ▼            ▼            ▼
┌──────────┐  ┌──────────┐  ┌──────────────┐
│   FTC    │  │ TeamCode │  │  MeepMeep    │
│ Robot    │  │ (Android)│  │  Testing     │
│Controller│  │          │  │  (Java)      │
└──────────┘  └────┬─────┘  └───────┬──────┘
     ▲             │                 │
     │             │                 │
     └─────────────┘                 │
     dependencies on                 │
                                     │
┌───────────────────────────────────┴┐
│         Dependencies:               │
│  • RoadRunner Core 1.0              │
│  • RoadRunner Actions 1.0           │
│  • FTC Dashboard 0.4.16             │
│  • Pinpoint Driver                  │
│  • FTCLib Vision & Core             │
│  • Kotlin stdlib                    │
└─────────────────────────────────────┘
```

---

## 🎯 Localization Systems Comparison

```
┌────────────────────────────────────────────────────────────┐
│                  PINPOINT DRIVE (Recommended)              │
├────────────────────────────────────────────────────────────┤
│  Hardware:                                                 │
│    • GoBilda Pinpoint sensor                               │
│    • 2x odometry pods (integrated)                         │
│    • IMU (integrated)                                      │
│                                                            │
│  Pros:                                                     │
│    ✅ Easiest setup (one sensor)                          │
│    ✅ Built-in sensor fusion                              │
│    ✅ Best accuracy                                       │
│    ✅ Drop-in replacement for MecanumDrive                │
│                                                            │
│  Cons:                                                     │
│    ❌ Costs $75                                           │
│    ❌ Requires mounting height calibration                │
└────────────────────────────────────────────────────────────┘

┌────────────────────────────────────────────────────────────┐
│              TWO DEAD WHEEL LOCALIZER                      │
├────────────────────────────────────────────────────────────┤
│  Hardware:                                                 │
│    • 2x odometry pods (perpendicular)                      │
│    • Control Hub IMU                                       │
│                                                            │
│  Pros:                                                     │
│    ✅ Cheaper (~$30 for pods)                             │
│    ✅ DIY friendly                                        │
│    ✅ Good accuracy                                       │
│                                                            │
│  Cons:                                                     │
│    ❌ Manual IMU orientation setup                        │
│    ❌ More wiring                                         │
│    ❌ Requires mounting precision                         │
└────────────────────────────────────────────────────────────┘

┌────────────────────────────────────────────────────────────┐
│                DRIVE ENCODER LOCALIZER                     │
├────────────────────────────────────────────────────────────┤
│  Hardware:                                                 │
│    • Drive motor encoders                                  │
│    • Control Hub IMU                                       │
│                                                            │
│  Pros:                                                     │
│    ✅ No extra hardware                                   │
│    ✅ Built into MecanumDrive                             │
│    ✅ Free                                                │
│                                                            │
│  Cons:                                                     │
│    ❌ Poor accuracy (wheel slip)                          │
│    ❌ Not recommended for competition                     │
│    ❌ Requires constant tuning                            │
└────────────────────────────────────────────────────────────┘
```

---

## 🔄 Git Workflow (Recommended)

```
master branch
    │
    ├─ Week 1: Setup
    │   • Copy gradle files
    │   • Add RoadRunner dependencies
    │   • Sync and build
    │
    ├─ Week 2: MecanumDrive
    │   • Port MecanumDrive.java
    │   • Update motor names
    │   • Test LocalizationTest
    │
    ├─ Week 3: Robot Structure
    │   • Create Robot.java
    │   • Add utility classes
    │   • Test hardware
    │
    ├─ Week 4: MeepMeep Setup
    │   • Add MeepMeepTesting module
    │   • Create EditablePose
    │   • Design first path
    │
    ├─ Week 5: Subsystems
    │   • Implement Intake FSM
    │   • Implement Deposit FSM
    │   • Test individually
    │
    ├─ Week 6: Autonomous
    │   • Create Auto.java
    │   • Copy from MeepMeep
    │   • Test and iterate
    │
    ├─ Week 7: TeleOp
    │   • Create Tele.java
    │   • Add manual controls
    │   • Driver practice
    │
    └─ Week 8+: Optimization
        • Tune PID gains
        • Optimize cycle times
        • Competition ready!
```

---

## 🎨 Code Organization Principles

```
┌─────────────────────────────────────────────────────────────┐
│                  SEPARATION OF CONCERNS                     │
└─────────────────────────────────────────────────────────────┘

OpMode Layer (Auto.java, Tele.java)
    ↓ commands
Robot Layer (Robot.java)
    ↓ delegates to
Subsystem Layer (Intake.java, Deposit.java, etc.)
    ↓ controls
Hardware Layer (motors, servos, sensors)

NEVER:
  ❌ OpMode directly controls motors
  ❌ Subsystem knows about other subsystems
  ❌ Hardware logic in OpMode

ALWAYS:
  ✅ OpMode calls robot.method()
  ✅ Robot coordinates subsystems
  ✅ Subsystems encapsulate hardware
  ✅ Clear interfaces between layers
```

---

## 📊 Performance Optimization Stack

```
┌───────────────────────────────────────────┐
│         APPLICATION LAYER                 │
│  • Non-blocking state machines            │
│  • Passive motion during driving          │
│  • Parallel actions                       │
└───────────────┬───────────────────────────┘
                │
                ▼
┌───────────────────────────────────────────┐
│         FRAMEWORK LAYER                   │
│  • Cached hardware writes                 │
│  • EditablePose (no conversion overhead)  │
│  • Efficient action composition           │
└───────────────┬───────────────────────────┘
                │
                ▼
┌───────────────────────────────────────────┐
│         COMMUNICATION LAYER               │
│  • Bulk reads (one I2C transaction)       │
│  • Manual cache mode                      │
│  • Minimize sensor polling                │
└───────────────┬───────────────────────────┘
                │
                ▼
┌───────────────────────────────────────────┐
│         HARDWARE LAYER                    │
│  • REV Hub bulk caching                   │
│  • I2C bus optimization                   │
│  • Fast sensors (Pinpoint)                │
└───────────────────────────────────────────┘

Result: ~50 Hz main loop (20ms cycle time)
```

---

## 🧪 Testing Strategy

```
┌────────────────────────────────────────────┐
│         DEVELOPMENT PHASES                 │
└────────────────────────────────────────────┘

Phase 1: UNIT TESTING
├── Test each subsystem alone
├── Use mechanismtest/ OpModes
└── Verify state transitions

Phase 2: INTEGRATION TESTING  
├── Test subsystems together
├── Use Tele.java for manual control
└── Verify no conflicts

Phase 3: LOCALIZATION TESTING
├── Run LocalizationTest
├── Drive in squares/lines
└── Tune lateralInPerTick

Phase 4: PATH TESTING
├── Design in MeepMeep
├── Simple path on robot
└── Check Dashboard trajectory

Phase 5: AUTONOMOUS TESTING
├── Copy from MeepMeep
├── Add subsystem actions
└── Iterate timing

Phase 6: COMPETITION TESTING
├── Full autonomous runs
├── Measure cycle times
└── Optimize for consistency
```

---

**Remember:** This architecture is battle-tested in FTC competitions. Follow the patterns, use the tools, and you'll have a robust, maintainable codebase! 🏆

