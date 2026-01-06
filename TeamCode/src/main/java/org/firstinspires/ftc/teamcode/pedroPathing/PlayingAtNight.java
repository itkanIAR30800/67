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

@Autonomous(name = "pls speed i need this", group = "Autonomous")
@Configurable // Panels
public class PlayingAtNight extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    public Follower follower; // Pedro Pathing follower instance
    private int pathState; // Current autonomous path state (state machine)
    private Paths paths; // Paths defined in the Paths class
    ElapsedTime pathTimer = new ElapsedTime();

    @Override
    public void init() {
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(24.888888888888886, 130.48888888888888, Math.toRadians(323)));

        paths = new Paths(follower); // Build paths

        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);
    }

    @Override
    public void loop() {
        follower.update(); // Update Pedro Pathing
        pathState = autonomousPathUpdate(); // Update autonomous state machine

        // Log values to Panels and Driver Station
        panelsTelemetry.debug("Path State", pathState);
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
        panelsTelemetry.update(telemetry);
    }

    public static class Paths {

        public PathChain startToShoot;
        public PathChain shootToMiddle;
        public PathChain middleToShoot;
        public PathChain shootToIntake;
        public PathChain intakeToGate;
        public PathChain gateToShoot;
        public PathChain shootToFirst;
        public PathChain firstToShoot;
        public PathChain shootToLast;
        public PathChain lastToShoot;
        public PathChain shootToEnd;

        public Paths(Follower follower) {
            startToShoot = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(27.378, 131.733), new Pose(58.133, 103.644))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(323), Math.toRadians(323))
                    .build();

            shootToMiddle = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(58.133, 103.644),
                                    new Pose(58.489, 66.667),
                                    new Pose(16.356, 59.733)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(323), Math.toRadians(180))
                    .build();

            middleToShoot = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(16.356, 59.733),
                                    new Pose(62.578, 60.089),
                                    new Pose(59.022, 102.222)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(320))
                    .build();

            shootToIntake = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(56.533, 104.000), new Pose(43.022, 58.844))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(320), Math.toRadians(180))
                    .build();

            intakeToGate = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(43.022, 58.844),
                                    new Pose(25.956, 50.133),
                                    new Pose(9.956, 62.756)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))
                    .build();


            gateToShoot = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(11.022, 61.867),
                                    new Pose(56.178, 61.867),
                                    new Pose(65.778, 116.089)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(320))
                    .build();

            shootToFirst = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(65.778, 116.089),
                                    new Pose(69.867, 91.911),
                                    new Pose(15.289, 84.267)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(320), Math.toRadians(180))
                    .build();

            firstToShoot = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(15.289, 84.267),
                                    new Pose(44.089, 83.911),
                                    new Pose(59.022, 101.867)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(320))
                    .build();

            shootToLast = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(59.022, 101.867),
                                    new Pose(67.200, 41.244),
                                    new Pose(17.778, 36.444)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(320), Math.toRadians(180))
                    .build();

            lastToShoot = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(17.778, 36.444), new Pose(59.378, 17.956))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(305))
                    .build();

            shootToEnd = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(59.378, 17.956), new Pose(16.889, 9.067))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(305), Math.toRadians(180))
                    .build();
        }
    }

    public int autonomousPathUpdate() {
        // Add your state machine Here

        // Access paths with paths.pathName
        // Refer to the Pedro Pathing Docs (Auto Example) for an example state machine
        switch (pathState) {
            case 0:
                follower.followPath(paths.startToShoot);
                setPathState(1);
                break;
            case 1:
                if (!follower.isBusy()) {
                    follower.followPath(paths.shootToMiddle, true);
                    setPathState(2);
                }
                break;
            case 2:
                if (!follower.isBusy()) {
                    follower.followPath(paths.middleToShoot, true);
                    setPathState(3);
                }
                break;
            case 3:
                if (!follower.isBusy()) {
                    follower.followPath(paths.shootToIntake, true);
                    setPathState(4);
                }
                break;
            case 4:
                if (!follower.isBusy()) {
                    follower.followPath(paths.intakeToGate, true);
                    setPathState(5);
                }
                break;
            case 5:
                if (!follower.isBusy()) {
                    follower.followPath(paths.gateToShoot, true);
                    setPathState(6);
                }
                break;
            case 6:
                if (!follower.isBusy()) {
                    follower.followPath(paths.shootToFirst, true);
                    setPathState(7);
                }
                break;
            case 7:
                if (!follower.isBusy()) {
                    follower.followPath(paths.firstToShoot, true);
                    setPathState(8);
                }
                break;
            case 8:
                if (!follower.isBusy()) {
                    follower.followPath(paths.shootToLast, true);
                    setPathState(9);
                }
                break;
            case 9:
                if (!follower.isBusy()) {
                    follower.followPath(paths.lastToShoot, true);
                    setPathState(10);
                }
                break;
            case 10:
                if (!follower.isBusy()) {
                    follower.followPath(paths.shootToEnd, true);
                    setPathState(11);
                }
                break;
            case 11:
                if (!follower.isBusy()) {
                    //    follower.followPath(paths.endlinetoshootfromfarzone, true);
                    setPathState(-1);
                }
                break;
        }
        return pathState;
        //switch for autonomous path update after reset for stuff that only happens once (such as intake running)

    }
    public void setPathState(int pState) {
        pathState = pState;

        pathTimer.reset();
        //switch for autonomous path update after reset for stuff that only happens once (such as intake running)
    }
}