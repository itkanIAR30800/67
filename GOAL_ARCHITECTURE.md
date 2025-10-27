# Goal Architecture — Simple Version 

This project helps a robot drive by combining three main parts:

- `MeepMeepTesting` — a computer simulator where you draw and test robot paths.
- `TeamCode` — the robot's brain. This is the code that actually runs on the robot.
- `FtcRobotController` — the Android app that talks to the robot hardware.

How it works (easy steps)
1. Make and test a path in `MeepMeepTesting`.
2. Copy the position and path info into `TeamCode`.
3. Put `TeamCode` on the robot and run it with `FtcRobotController`.
4. Watch and adjust until it works well.

What each part of `TeamCode` does (short)
- control — small helpers (like PID controllers) that keep the robot steady.
- opmode — the starting programs you press on the phone: one for autonomous, one for driver control.
- roadrunner — code that turns paths into motor actions (helps the robot move along a planned route).
- subsystem — parts of the robot (drivetrain, intake, lift) that have simple start/run functions.
- hardware — low-level wrappers for motors and sensors.

Important ideas (simple)
- Actions: small tasks the robot can do (move, intake, throw). You can chain them together.
- EditablePose: a simple data object used in both the simulator and the robot so paths match.
- Hardware caching: only send commands to motors when values change to avoid slow communication.

Files to look at first
- `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/opmode/Auto.java`
- `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/opmode/Tele.java`
- `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/roadrunner/PinpointDrive.*`
- `MeepMeepTesting/src/main/java/com/example/meepmeeptesting/EditablePose.java`

That is all you need to start: make a path in the simulator, copy it to the robot code, and run it on the robot.
