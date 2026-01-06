package org.firstinspires.ftc.teamcode.pedroPathing;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Pedro Pathing Autonomous", group = "Autonomous")
@Configurable // Panels
public class pedroauton extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    public Follower follower; // Pedro Pathing follower instance
    private int pathState; // Current autonomous path state (state machine)
    ElapsedTime pathTimer;
    private Paths paths; // Paths defined in the Paths class

    @Override
    public void init() {
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        pathTimer = new ElapsedTime();

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(24.888888888888886, 130.48888888888888, Math.toRadians(323)));

        paths = new Paths(follower); // Build paths

        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);
    }

    @Override
    public void loop() {
        follower.update(); // Update Pedro Pathing
        autonomousPathUpdate(); // Update autonomous state machine

        // Log values to Panels and Driver Station
        panelsTelemetry.debug("Path State", pathState);
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
        panelsTelemetry.update(telemetry);
    }

    public static class Paths {

        public PathChain shoot1;
        public double Wait3;
        public PathChain intakeMiddle2;
        public PathChain shoot2;
        public PathChain gate3;
        public PathChain shoot3;
        public PathChain intakeFirst4;
        public PathChain shoot4;
        public PathChain intakeLast5;
        public PathChain shoot5;
        public PathChain leave6;

        public Paths(Follower follower) {
            shoot1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(24.889, 130.489), new Pose(49.067, 111.467))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(323), Math.toRadians(323))
                    .build();

            Wait3 = 1000;

            intakeMiddle2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(49.067, 111.467),
                                    new Pose(61.689, 51.733),
                                    new Pose(11.022, 59.378)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(323), Math.toRadians(180))
                    .build();

            shoot2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(11.022, 59.378),
                                    new Pose(73.956, 77.689),
                                    new Pose(38.044, 102.756)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(270))
                    .setReversed()
                    .build();

            gate3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(38.044, 102.756),
                                    new Pose(46.578, 38.222),
                                    new Pose(11.733, 64.711)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(210))
                    .build();

            shoot3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(11.733, 64.711),
                                    new Pose(65.067, 81.244),
                                    new Pose(47.644, 105.422)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(210), Math.toRadians(290))
                    .setReversed()
                    .build();

            intakeFirst4 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(47.644, 105.422),
                                    new Pose(58.667, 81.244),
                                    new Pose(15.467, 83.378)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(290), Math.toRadians(170))
                    .build();

            shoot4 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(15.467, 83.378),
                                    new Pose(63.644, 84.089),
                                    new Pose(47.467, 104.533)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(170), Math.toRadians(300))
                    .setReversed()
                    .build();

            intakeLast5 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(47.467, 104.533),
                                    new Pose(74.311, 27.911),
                                    new Pose(8.711, 35.911)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(300), Math.toRadians(170))
                    .build();

            shoot5 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(8.711, 35.911),
                                    new Pose(81.067, 54.400),
                                    new Pose(47.822, 104.356)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(170), Math.toRadians(300))
                    .setReversed()
                    .build();

            leave6 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(47.822, 104.356), new Pose(45.689, 68.800))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(300), Math.toRadians(270))
                    .build();
        }
    }


    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(paths.shoot1);
                setPathState(1);
                break;
            case 1:

        /* You could check for
        - Follower State: "if(!follower.isBusy()) {}"
        - Time: "if(pathTimer.getElapsedTimeSeconds() > 1) {}"
        - Robot Position: "if(follower.getPose().getX() > 36) {}"
        */

                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    /* Score Preload */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(paths.intakeMiddle2,true);
                    setPathState(2);
                }
                break;
            case 2:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup1Pose's position */
                if(!follower.isBusy()) {
                    /* Grab Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(paths.shoot2,true);
                    setPathState(3);
                }
                break;
//            case 3:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
//                if(!follower.isBusy()) {
//                    /* Score Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(paths.gate3,true);
//                    setPathState(4);
//                }
//                break;
//            case 4:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup2Pose's position */
//                if(!follower.isBusy()) {
//                    /* Grab Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
//                    follower.followPath(paths.shoot3,true);
//                    setPathState(5);
//                }
//                break;
//            case 5:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
//                if(!follower.isBusy()) {
//                    /* Score Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(paths.intakeFirst4,true);
//                    setPathState(6);
//                }
//                break;
//            case 6:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
//                if(!follower.isBusy()) {
//                    /* Grab Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
//                    follower.followPath(paths.shoot4, true);
//                    setPathState(7);
//                }
//                break;
//            case 7:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
//                if(!follower.isBusy()) {
//                    /* Grab Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
//                    follower.followPath(paths.intakeLast5, true);
//                    setPathState(8);
//                }
//                break;
//            case 8:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
//                if(!follower.isBusy()) {
//                    /* Grab Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
//                    follower.followPath(paths.shoot5, true);
//                    setPathState(9);
//                }
//                break;
//            case 9:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
//                if(!follower.isBusy()) {
//                    /* Grab Sample */
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
//                    follower.followPath(paths.leave6, true);
//                    setPathState(10);
//                }
//                break;
//            case 10:
//                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
//                if(!follower.isBusy()) {
//                    /* Set the state to a Case we won't use or define, so it just stops running an new paths */
//                    setPathState(-1);
//                }
//                break;

        }
    }


/** These change the states of the paths and actions. It will also reset the timers of the individual switches **/
    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.reset();
        //switch for autonomous path update after reset for stuff that only happens once (such as intake running)
    }
    // Access paths with paths.pathName
}