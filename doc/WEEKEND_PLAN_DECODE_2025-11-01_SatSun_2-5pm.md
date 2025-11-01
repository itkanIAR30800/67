# FTC DECODE Weekend Plan (Sat & Sun)

Team-facing plan aligned with Brogan M. Pratt's fundamentals-first philosophy. No Road Runner, no FTCLib, no MeepMeep.

---

## Before the Session (Do this)
## Before Saturday
- Read GitHub Flow and About Pull Requests: https://docs.github.com/en/get-started/quickstart/github-flow, https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests
- Install Android Studio, clone the team repo, open the project, and build once on your laptop. Make sure you can run a sample OpMode.
- Set up Git on your laptop and sign in to GitHub so you can push a branch during the session.
- Skim FTC Dashboard docs and the @Config tuning video: https://acmerobotics.github.io/ftc-dashboard/, https://www.youtube.com/watch?v=vhLF6NVXLXI

## Before Sunday
- Watch the PID video and skim GM0 Control Loops; skim CTRL ALT FTC on PID. Links: https://www.youtube.com/watch?v=6OH-wOsVVjg, https://gm0.org/en/latest/docs/software/concepts/control-loops.html, https://www.ctrlaltftc.com/the-pid-controller
- Watch the FTC-focused PID implementation video: https://www.youtube.com/watch?v=zrMFEw8qxLc
- State Machines and Methods/Functions videos: https://www.youtube.com/watch?v=RweqIqouYqM, https://www.youtube.com/watch?v=bRZ0UDDiIWj0
- 
## Later (optional)
- Optional: Pinpoint product page and driver/example code: https://www.gobilda.com/pinpoint-odometry-computer-imu-sensor-fusion-for-2-wheel-odometry/, https://github.com/goBILDA-Official/FtcRobotController-Add-Pinpoint/blob/goBILDA-Odometry-Driver/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/GoBildaPinpointDriver.java, https://github.com/goBILDA-Official/FtcRobotController-Add-Pinpoint/blob/goBILDA-Odometry-Driver/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/SensorGoBildaPinpointExample.java

## Didn't do software before?
- Anyone else just joining software can do before of the above:
    - [Code a Robot](https://www.codearobot.org/) – hardware to software primer
    - Watch Java for Robotics (FTC) playlist: https://www.youtube.com/playlist?list=PLRHdgFNRLyaPiZ5rvINwMmGMHEIL9usla
---

## Saturday Session (2–5 pm)
- Repo & Branching
    - Everyone creates a personal branch (e.g., <firstname>), builds the app, pushes, and opens a PR.
    - Resources: [GitHub Flow](https://docs.github.com/en/get-started/quickstart/github-flow); [About PRs](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests); [FTC Docs – Fork & Clone](https://ftc-docs.firstinspires.org/en/latest/programming_resources/tutorial_specific/android_studio/fork_and_clone_github_repository/Fork-and-Clone-From-GitHub.html); [Android Studio Git basics](https://www.youtube.com/watch?v=F8Z-sSXklOg); [Review PRs in Android Studio](https://medium.com/@andresguedes/how-to-review-pull-requests-using-android-studio-c42589b2332c).
- FTC Dashboard Setup
    - Add Dashboard dependency/initialization; confirm connect via Wi‑Fi.
    - Add a minimal OpMode that sends telemetry numbers and a graph.
    - Resources: [FTC Dashboard docs](https://acmerobotics.github.io/ftc-dashboard/); [@Config tuning video](https://www.youtube.com/watch?v=vhLF6NVXLXI).

## Sunday Session (2–5 pm)
- Custom PID Class
    - Implement a simple PID class (Kp, Ki, Kd; optional kG feedforward) with clamp, integral windup guard, and reset().
    - Add @Config static fields for gains; surface target/current/error/output via telemetry.
    - Resources: [PID video](https://www.youtube.com/watch?v=6OH-wOsVVjg); [GM0 Control Loops](https://gm0.org/en/latest/docs/software/concepts/control-loops.html); [CTRL ALT FTC – PID](https://www.ctrlaltftc.com/the-pid-controller); [@Config tuning video](https://www.youtube.com/watch?v=vhLF6NVXLXI). Also see GitHub Flow and About PRs: https://docs.github.com/en/get-started/quickstart/github-flow, https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests.
- Tuning Pass #1 (drivetrain first)
    - HeadingHold: maintain a target heading using IMU yaw; tune Kp first, then Ki/Kd if needed. Test at standstill and while gently pushing the robot.
    - DriveStraight: command a constant forward speed using encoder average; hold heading using HeadingHold in parallel; tune speed loop gains.
    - Optional: TurnToHeading with a simple state machine (ENTER: set target; EXECUTE: apply heading PID; EXIT: when |error| < threshold for N ms).
    - Resources: FTC Dashboard docs: https://acmerobotics.github.io/ftc-dashboard/ and @Config tuning video: https://www.youtube.com/watch?v=vhLF6NVXLXI.

## Later Sessions (Backlog)
- Pinpoint Integration
    - Add GoBildaPinpointDriver.java; configure the device in RC app.
    - Set TICKS_PER_MM per your odometry pods, verify encoder directions (X forward, Y left), CCW heading positive.
    - Run the example OpMode; push robot by hand; validate X/Y/Heading telemetry makes sense.
    - Resources: [Pinpoint product](https://www.gobilda.com/pinpoint-odometry-computer-imu-sensor-fusion-for-2-wheel-odometry/); [Pinpoint driver](https://github.com/goBILDA-Official/FtcRobotController-Add-Pinpoint/blob/goBILDA-Odometry-Driver/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/GoBildaPinpointDriver.java); [Pinpoint example](https://github.com/goBILDA-Official/FtcRobotController-Add-Pinpoint/blob/goBILDA-Odometry-Driver/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/SensorGoBildaPinpointExample.java); [Pose2d API](https://acmerobotics.github.io/road-runner/core/0.4.5/javadoc/com/acmerobotics/roadrunner/geometry/Pose2d.html). Also see: IMU https://ftc-docs.firstinspires.org/programming_resources/imu/imu.html, Mecanum/IMU example https://gm0.org/ro/latest/docs/software/tutorials/mecanum-drive.html, Encoders https://gm0.org/en/latest/docs/software/tutorials/encoders.html.
- Field Visualization
    - In a looped OpMode, get Pose2d and draw a rectangle representing the robot on Dashboard field view using fieldOverlay().
    - Confirm the drawn pose tracks physical movement and orientation.
    - Resources: [Field view docs](https://acmerobotics.github.io/ftc-dashboard/fieldview); [Pose2d API](https://acmerobotics.github.io/road-runner/core/0.4.5/javadoc/com/acmerobotics/roadrunner/geometry/Pose2d.html).
- Simple State‑Machine Auto (DECODE‑aware)
    - Build a minimal autonomous using an enum of states. Example: LEAVE_LAUNCH_LINE → TURN_TO_GOAL_HEADING → (optional) LAUNCH_PRELOAD → IDLE.
    - Use Pinpoint pose for decisions (e.g., stop when X ≈ target). Only LAUNCH when inside/overlapping LAUNCH LINE. Use drivetrain PID for heading/drive control; use launcher velocity PID only if it was tuned and the robot is inside the launch line.
    - Resources: [State machines video](https://www.youtube.com/watch?v=RweqIqouYqM); [Methods/Functions video](https://www.youtube.com/watch?v=bRZ0UDiIWj0); [DECODE constraints](#decode-game-constraints-to-respect-this-weekend). Also see: Pinpoint product https://www.gobilda.com/pinpoint-odometry-computer-imu-sensor-fusion-for-2-wheel-odometry/, driver code https://github.com/goBILDA-Official/FtcRobotController-Add-Pinpoint/blob/goBILDA-Odometry-Driver/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/GoBildaPinpointDriver.java, example OpMode https://github.com/goBILDA-Official/FtcRobotController-Add-Pinpoint/blob/goBILDA-Odometry-Driver/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/SensorGoBildaPinpointExample.java, Pose2d API reference https://acmerobotics.github.io/road-runner/core/0.4.5/javadoc/com/acmerobotics/roadrunner/geometry/Pose2d.html.
- Deliverables (milestones)
    - Dashboard connected; custom PID class; drivetrain heading hold and/or straight‑drive speed tuned and plotted on Dashboard.
    - Pinpoint pose verified; robot shape rendered on field view; one short, reliable DECODE‑aware state‑machine auto.
- Additional backlog items
    - Launcher velocity PID tuning and feedforward; integrate into state machine with safety interlocks (launch‑line check).
    - Build a reusable state‑machine template (enter/execute/exit) and 2+ autonomous routines.
    - Add heading hold and turn‑to‑angle PID using IMU yaw (refine with rate limiting and anti‑windup).
    - Create a quick calibration OpMode to validate TICKS_PER_MM and encoder polarities for odometry pods.
    - Improve logging: record gains, steady‑state error, overshoot, settling time.

---

## Quick References

### Learn Java for Robotics (FTC) – Brogan Pratt (playlist)
https://www.youtube.com/playlist?list=PLRHdgFNRLyaPiZ5rvINwMmGMHEIL9usla

### Robot Building Tutorials – Brogan Pratt (playlist)
https://www.youtube.com/playlist?list=PLRHdgFNRLyaM6TmNVVHKqVnnyntDDYDNt

### FTC DECODE (game-specific) – Brogan Pratt (playlist)
https://www.youtube.com/playlist?list=PLRHdgFNRLyaNX9714HfqBoMCusDBnfXbw

### Code a Robot (HW→SW primer)
https://www.codearobot.org/

### PID video (general)
https://www.youtube.com/watch?v=6OH-wOsVVjg

### GM0 – Control Loops
https://gm0.org/en/latest/docs/software/concepts/control-loops.html

### CTRL ALT FTC – PID
https://www.ctrlaltftc.com/the-pid-controller

### FTC-focused PID video
https://www.youtube.com/watch?v=zrMFEw8qxLc

### Tuning methods (Z–N)
https://www.ctrlaltftc.com/the-pid-controller/tuning-methods-of-a-pid-controller#ziegler-nichols-tuning

### FTC Dashboard docs
https://acmerobotics.github.io/ftc-dashboard/

### @Config tuning video
https://www.youtube.com/watch?v=vhLF6NVXLXI

### FTC Docs – IMU
https://ftc-docs.firstinspires.org/programming_resources/imu/imu.html

### GM0 – Mecanum Drive (IMU example)
https://gm0.org/ro/latest/docs/software/tutorials/mecanum-drive.html

### FTC Docs – Encoders
https://gm0.org/en/latest/docs/software/tutorials/encoders.html

### Pinpoint product page
https://www.gobilda.com/pinpoint-odometry-computer-imu-sensor-fusion-for-2-wheel-odometry/

### Pinpoint driver code
https://github.com/goBILDA-Official/FtcRobotController-Add-Pinpoint/blob/goBILDA-Odometry-Driver/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/GoBildaPinpointDriver.java

### Pinpoint example OpMode
https://github.com/goBILDA-Official/FtcRobotController-Add-Pinpoint/blob/goBILDA-Odometry-Driver/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/SensorGoBildaPinpointExample.java

### Pose2d API reference
https://acmerobotics.github.io/road-runner/core/0.4.5/javadoc/com/acmerobotics/roadrunner/geometry/Pose2d.html

### Field view docs
https://acmerobotics.github.io/ftc-dashboard/fieldview

### State machines video – Brogan Pratt
https://www.youtube.com/watch?v=RweqIqouYqM

### Methods/functions video – Brogan Pratt
https://www.youtube.com/watch?v=bRZ0UDiIWj0

### GitHub Flow quickstart
https://docs.github.com/en/get-started/quickstart/github-flow

### About pull requests
https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests

### FTC Docs – Fork & Clone from GitHub
https://ftc-docs.firstinspires.org/en/latest/programming_resources/tutorial_specific/android_studio/fork_and_clone_github_repository/Fork-and-Clone-From-GitHub.html

### YouTube – Android Studio Git basics
https://www.youtube.com/watch?v=F8Z-sSXklOg

### Review PRs using Android Studio (article)
https://medium.com/@andresguedes/how-to-review-pull-requests-using-android-studio-c42589b2332c

### Study quick picks (grouped)
- PID basics: video https://www.youtube.com/watch?v=6OH-wOsVVjg, GM0 https://gm0.org/en/latest/docs/software/concepts/control-loops.html, CTRL ALT FTC https://www.ctrlaltftc.com/the-pid-controller
- Implement Custom PID in FTC: https://www.youtube.com/watch?v=zrMFEw8qxLc
- Dashboard tuning prep: docs https://acmerobotics.github.io/ftc-dashboard/, @Config video https://www.youtube.com/watch?v=vhLF6NVXLXI
- PID tuning methods (Z–N): https://www.ctrlaltftc.com/the-pid-controller/tuning-methods-of-a-pid-controller#ziegler-nichols-tuning
- Pinpoint odometry setup: product https://www.gobilda.com/pinpoint-odometry-computer-imu-sensor-fusion-for-2-wheel-odometry/, driver https://github.com/goBILDA-Official/FtcRobotController-Add-Pinpoint/blob/goBILDA-Odometry-Driver/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/GoBildaPinpointDriver.java, example https://github.com/goBILDA-Official/FtcRobotController-Add-Pinpoint/blob/goBILDA-Odometry-Driver/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/SensorGoBildaPinpointExample.java, Pose2d API https://acmerobotics.github.io/road-runner/core/0.4.5/javadoc/com/acmerobotics/roadrunner/geometry/Pose2d.html
- Field view: https://acmerobotics.github.io/ftc-dashboard/fieldview
- Organize code & build autonomous: state machines https://www.youtube.com/watch?v=RweqIqouYqM, methods/functions https://www.youtube.com/watch?v=bRZ0UDDiIWj0
- Git workflow (study before Sat): GitHub Flow https://docs.github.com/en/get-started/quickstart/github-flow, About PRs https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests, Fork & Clone https://ftc-docs.firstinspires.org/en/latest/programming_resources/tutorial_specific/android_studio/fork_and_clone_github_repository/Fork-and-Clone-From-GitHub.html, Android Studio Git basics https://www.youtube.com/watch?v=F8Z-sSXklOg, Review PRs https://medium.com/@andresguedes/how-to-review-pull-requests-using-android-studio-c42589b2332c


## DECODE game constraints to respect (this weekend)
- Launching allowed only when inside or overlapping your alliance LAUNCH LINE; never launch elsewhere.
- Do not contact ARTIFACTS on the RAMP (own or opponent) except via your own GATE operation; avoid opponent protected zones.
- LEAVE in AUTO: ensure the robot is no longer over any LAUNCH LINE by end of AUTO for 3 pts.
- Stay on your alliance half to reduce risk of protected-zone contact penalties.