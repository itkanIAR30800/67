# FTC DECODE Weekend Plan (Sat & Sun)

Team-facing plan aligned with Brogan M. Pratt's fundamentals-first philosophy. No Road Runner, no FTCLib, no MeepMeep.

## Purpose / Goals
- Build fundamentals: custom PID, state machines, clean telemetry.
- Set up FTC Dashboard for live tuning and visualization.
- Integrate Pinpoint odometry to get accurate Pose2d.
- Visualize robot pose/shape on the field view to debug paths.
- Ship one small, reliable state-machine autonomous and one tuned mechanism.
- Everyone creates a branch and opens one PR.

---

## Pre-session Study (Software; open to all)
Overarching resources (watch in order of need):
- Learn Java for Robotics (FTC) – Brogan Pratt (playlist): https://www.youtube.com/playlist?list=PLRHdgFNRLyaPiZ5rvINwMmGMHEIL9usla
- Robot Building Tutorials – Brogan Pratt (playlist): https://www.youtube.com/playlist?list=PLRHdgFNRLyaM6TmNVVHKqVnnyntDDYDNt
- FTC DECODE (game-specific) – Brogan Pratt (playlist): https://www.youtube.com/playlist?list=PLRHdgFNRLyaNX9714HfqBoMCusDBnfXbw

### 1) Understand PID Control Theory
- Video (general): https://www.youtube.com/watch?v=6OH-wOsVVjg
- Docs (GM0 – Control Loops): https://gm0.org/en/latest/docs/software/concepts/control-loops.html
- Docs (CTRL ALT FTC – PID): https://www.ctrlaltftc.com/the-pid-controller

### 2) Implement Custom PID in FTC
- Video (FTC-focused): https://www.youtube.com/watch?v=zrMFEw8qxLc

### 3) Use FTC Dashboard for Tuning Prep
- Docs (FTC Dashboard): https://acmerobotics.github.io/ftc-dashboard/
- Video (@Config for live tuning): https://www.youtube.com/watch?v=vhLF6NVXLXI

### 4) Learn & Apply PID Tuning Methods
- Docs (CTRL ALT FTC – Ziegler–Nichols and more):
  https://www.ctrlaltftc.com/the-pid-controller/tuning-methods-of-a-pid-controller#ziegler-nichols-tuning

### 5) Set Up Pinpoint Odometry (no motion-planning libs)
- Product page: https://www.gobilda.com/pinpoint-odometry-computer-imu-sensor-fusion-for-2-wheel-odometry/
- Driver code: https://github.com/goBILDA-Official/FtcRobotController-Add-Pinpoint/blob/goBILDA-Odometry-Driver/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/GoBildaPinpointDriver.java
- Example OpMode: https://github.com/goBILDA-Official/FtcRobotController-Add-Pinpoint/blob/goBILDA-Odometry-Driver/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/SensorGoBildaPinpointExample.java
- Pose2d reference (class API): https://acmerobotics.github.io/road-runner/core/0.4.5/javadoc/com/acmerobotics/roadrunner/geometry/Pose2d.html

### 6) Visualize Paths with FTC Dashboard (field view)
- Field view docs (drawing, overlay): https://acmerobotics.github.io/ftc-dashboard/fieldview

### 7) Organize Code & Build Autonomous (State Machines)
- Video (state machines – Brogan Pratt): https://www.youtube.com/watch?v=RweqIqouYqM
- Video (methods/functions – Brogan Pratt): https://www.youtube.com/watch?v=UG6iSvgXxYg

### Git workflow (study before Sat)
- GitHub Flow quickstart: https://docs.github.com/en/get-started/quickstart/github-flow
- About pull requests: https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests
- FTC Docs – Fork & Clone from GitHub (Android Studio): https://ftc-docs.firstinspires.org/en/latest/programming_resources/tutorial_specific/android_studio/fork_and_clone_github_repository/Fork-and-Clone-From-GitHub.html
- YouTube – Android Studio Git basics (fork/clone/PRs): https://www.youtube.com/watch?v=F8Z-sSXklOg
- Review PRs using Android Studio (article): https://medium.com/@andresguedes/how-to-review-pull-requests-using-android-studio-c42589b2332c

---

## Before the Session (Do this)
- Watch the PID video and skim GM0 Control Loops; skim CTRL ALT FTC on PID. See: [Pre-session 1](#1-understand-pid-control-theory).
- Watch the FTC-focused PID implementation video. See: [Pre-session 2](#2-implement-custom-pid-in-ftc).
- Skim FTC Dashboard docs and the @Config tuning video. See: [Pre-session 3](#3-use-ftc-dashboard-for-tuning-prep).
- Read GitHub Flow and About Pull Requests. See: [Git workflow](#git-workflow-study-before-sat).
- Install Android Studio, clone the team repo, open the project, and build once on your laptop. Make sure you can run a sample OpMode.
- Set up Git on your laptop and sign in to GitHub so you can push a branch during the session.
- Optional but recommended: Watch Brogan’s State Machines and Methods/Functions videos. See: [Pre-session 7](#7-organize-code--build-autonomous-state-machines).
- Optional: Read Pinpoint product page and skim the driver/example code. See: [Pre-session 5](#5-set-up-pinpoint-odometry-no-motion-planning-libs).

---

## DECODE game constraints to respect (this weekend)
- Launching allowed only when inside or overlapping your alliance LAUNCH LINE; never launch elsewhere.
- Do not contact ARTIFACTS on the RAMP (own or opponent) except via your own GATE operation; avoid opponent protected zones.
- LEAVE in AUTO: ensure the robot is no longer over any LAUNCH LINE by end of AUTO for 3 pts.
- Stay on your alliance half to reduce risk of protected-zone contact penalties.

---

## Hardware readiness notes
- Drivetrain ready: prioritize drivetrain tuning first (heading hold and straight-drive speed). This gives immediate, visible improvements and helps every later feature.
- Launcher ready: optional velocity tuning after drivetrain, time permitting.
- Intake only: skip; revisit later. Focus this weekend on drivetrain + pose + state machine.

---

## Session Plan (Sat/Sun 2–5pm)
We’ll execute the first pass of the 7-point plan. Keep code simple, visible, and tunable.

### Saturday
1) Repo & Branching
- Everyone creates a personal branch (e.g., `<firstname>`), builds the app, and pushes.
- Resources: [GitHub Flow](https://docs.github.com/en/get-started/quickstart/github-flow); [About PRs](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests); [FTC Docs – Fork & Clone](https://ftc-docs.firstinspires.org/en/latest/programming_resources/tutorial_specific/android_studio/fork_and_clone_github_repository/Fork-and-Clone-From-GitHub.html); [Android Studio Git basics](https://www.youtube.com/watch?v=F8Z-sSXklOg); [Review PRs in Android Studio](https://medium.com/@andresguedes/how-to-review-pull-requests-using-android-studio-c42589b2332c).

2) FTC Dashboard Setup
- Add Dashboard dependency/initialization; confirm connect via Wi‑Fi.
- Add a minimal OpMode that sends telemetry numbers and a Graph.
- Resources: [FTC Dashboard docs](https://acmerobotics.github.io/ftc-dashboard/); [@Config tuning video](https://www.youtube.com/watch?v=vhLF6NVXLXI).

3) Custom PID Class
- Implement a simple PID class (Kp, Ki, Kd; optional kG feedforward) with clamp, integral windup guard, and reset().
- Add @Config static fields for gains; surface target/current/error/output via telemetry.
- Resources: [PID video](https://www.youtube.com/watch?v=6OH-wOsVVjg); [GM0 Control Loops](https://gm0.org/en/latest/docs/software/concepts/control-loops.html); [CTRL ALT FTC – PID](https://www.ctrlaltftc.com/the-pid-controller); [@Config tuning video](https://www.youtube.com/watch?v=vhLF6NVXLXI).

4) Tuning Pass #1 (drivetrain first)
- HeadingHold: maintain a target heading using IMU yaw; tune Kp first, then Ki/Kd if needed. Test at standstill and while gently pushing the robot.
- DriveStraight: command a constant forward speed using encoder average; hold heading using HeadingHold in parallel; tune speed loop gains.
- Optional: TurnToHeading with a simple state machine (ENTER: set target; EXECUTE: apply heading PID; EXIT: when |error| < threshold for N ms).
- Resources: FTC Docs – IMU: https://ftc-docs.firstinspires.org/programming_resources/imu/imu.html and https://gm0.org/ro/latest/docs/software/tutorials/mecanum-drive.html ; FTC Docs – Encoders: https://gm0.org/en/latest/docs/software/tutorials/encoders.html ; [@Config tuning video](https://www.youtube.com/watch?v=vhLF6NVXLXI); [PID video](https://www.youtube.com/watch?v=6OH-wOsVVjg); [GM0 Control Loops](https://gm0.org/en/latest/docs/software/concepts/control-loops.html).

Deliverable (Sat):
- Dashboard connected; custom PID class; drivetrain heading hold and/or straight-drive speed tuned and plotted on Dashboard.

### Sunday
1) Pinpoint Integration
- Add GoBildaPinpointDriver.java; configure the device in RC app.
- Set TICKS_PER_MM per your odometry pods, verify encoder directions (X forward, Y left), CCW heading positive.
- Run the example OpMode; push robot by hand; validate X/Y/Heading telemetry makes sense.
- Resources: [Pinpoint product](https://www.gobilda.com/pinpoint-odometry-computer-imu-sensor-fusion-for-2-wheel-odometry/); [Pinpoint driver](https://github.com/goBILDA-Official/FtcRobotController-Add-Pinpoint/blob/goBILDA-Odometry-Driver/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/GoBildaPinpointDriver.java); [Pinpoint example](https://github.com/goBILDA-Official/FtcRobotController-Add-Pinpoint/blob/goBILDA-Odometry-Driver/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/SensorGoBildaPinpointExample.java); [Pose2d API](https://acmerobotics.github.io/road-runner/core/0.4.5/javadoc/com/acmerobotics/roadrunner/geometry/Pose2d.html).

2) Field Visualization
- In a looped OpMode, get Pose2d and draw a rectangle representing the robot on Dashboard field view using fieldOverlay().
- Confirm the drawn pose tracks physical movement and orientation.
- Resources: [Field view docs](https://acmerobotics.github.io/ftc-dashboard/fieldview); [Pose2d API](https://acmerobotics.github.io/road-runner/core/0.4.5/javadoc/com/acmerobotics/roadrunner/geometry/Pose2d.html).

3) Simple State‑Machine Auto (DECODE-aware)
- Build a minimal autonomous using an enum of states. Example: LEAVE_LAUNCH_LINE → TURN_TO_GOAL_HEADING → (optional) LAUNCH_PRELOAD → IDLE.
- Use Pinpoint pose for decisions (e.g., stop when X ≈ target). Only LAUNCH when inside/overlapping LAUNCH LINE. Use drivetrain PID for heading/drive control; use launcher velocity PID only if it was tuned and the robot is inside the launch line.
- Resources: [State machines video](https://www.youtube.com/watch?v=RweqIqouYqM); [Methods/Functions video](https://www.youtube.com/watch?v=UG6iSvgXxYg); [DECODE constraints](#decode-game-constraints-to-respect-this-weekend).

Deliverable (Sun):
- Pinpoint pose verified; robot shape rendered on field view; one short, reliable DECODE-aware state‑machine auto.

---

## Later Sessions (Backlog)
- Launcher velocity PID tuning and feedforward; integrate into state machine with safety interlocks (launch-line check).
- Build a reusable state‑machine template (enter/execute/exit) and 2+ autonomous routines.
- Add heading hold and turn‑to‑angle PID using IMU yaw (refine with rate limiting and anti-windup).
- Add a light waypoint follower (no external libs): P controllers on position and heading, with rate limiting and saturations; use Pinpoint for feedback.
- Create a quick calibration OpMode to validate TICKS_PER_MM and encoder polarities for odometry pods.
- Improve logging: record gains, steady‑state error, overshoot, settling time.

---

## Done when (Weekend)
- Custom PID class implemented and tuned (drivetrain heading and/or straight drive) with Dashboard graphs.
- FTC Dashboard connected and showing live telemetry/graphs.
- Pinpoint integrated; pose values validated against real motion.
- Field view shows a robot rectangle moving consistently with real position.
- One short, reliable state‑machine autonomous runs start‑to‑finish and respects DECODE launch/ramp constraints.
- Each student opened at least one PR.

## Quick References
- PID video: https://www.youtube.com/watch?v=6OH-wOsVVjg
- GM0 – Control Loops: https://gm0.org/en/latest/docs/software/concepts/control-loops.html
- CTRL ALT FTC – PID: https://www.ctrlaltftc.com/the-pid-controller
- FTC Dashboard docs: https://acmerobotics.github.io/ftc-dashboard/
- @Config tuning video: https://www.youtube.com/watch?v=vhLF6NVXLXI
- Tuning methods (Z–N): https://www.ctrlaltftc.com/the-pid-controller/tuning-methods-of-a-pid-controller#ziegler-nichols-tuning
- FTC Docs – IMU: https://ftc-docs.firstinspires.org/programming_resources/imu/imu.html and https://gm0.org/ro/latest/docs/software/tutorials/mecanum-drive.html
- FTC Docs – Encoders: https://gm0.org/en/latest/docs/software/tutorials/encoders.html
- Pinpoint product: https://www.gobilda.com/pinpoint-odometry-computer-imu-sensor-fusion-for-2-wheel-odometry/
- Pinpoint driver: https://github.com/goBILDA-Official/FtcRobotController-Add-Pinpoint/blob/goBILDA-Odometry-Driver/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/GoBildaPinpointDriver.java
- Pinpoint example: https://github.com/goBILDA-Official/FtcRobotController-Add-Pinpoint/blob/goBILDA-Odometry-Driver/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/SensorGoBildaPinpointExample.java
- Pose2d API: https://acmerobotics.github.io/road-runner/core/0.4.5/javadoc/com/acmerobotics/roadrunner/geometry/Pose2d.html
- Field view docs: https://acmerobotics.github.io/ftc-dashboard/fieldview
- GitHub Flow: https://docs.github.com/en/get-started/quickstart/github-flow
- About PRs: https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests
- codearobot.org (HW→SW primer): https://www.codearobot.org/
