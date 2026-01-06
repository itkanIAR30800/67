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

@Autonomous(name = "Pedro Pathing Autonomous3", group = "Autonomous")
@Configurable // Panels
public class pedroautonmotifs extends OpMode {

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

        public PathChain Path1;
        public PathChain Path2;
        public PathChain Path3;
        public PathChain Path4;
        public PathChain Path5;
        public PathChain Path6;
        public PathChain Path7;
        public PathChain Path8;
        public PathChain Path9;
        public PathChain Path10;
        public PathChain Path11;
        public PathChain Path12;
        public PathChain Path13;

        public Paths(Follower follower) {
            Path1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(25.171, 130.537), new Pose(61.854, 90.732))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(323), Math.toRadians(323))
                    .build();

            Path2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(61.854, 90.732),
                                    new Pose(74.927, 58.927),
                                    new Pose(45.268, 59.122)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(323), Math.toRadians(180))
                    .build();

            Path3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(45.268, 59.122), new Pose(4.878, 58.927))
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            Path4 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(4.878, 58.927),
                                    new Pose(62.049, 57.951),
                                    new Pose(62.049, 91.317)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(323))
                    .build();

            Path5 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(62.049, 91.317),
                                    new Pose(31.220, 40.780),
                                    new Pose(8.390, 63.415)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(323), Math.toRadians(135))
                    .build();

            Path6 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(8.390, 63.415),
                                    new Pose(57.756, 44.683),
                                    new Pose(62.439, 90.927)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(323))
                    .build();

            Path7 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(62.439, 90.927), new Pose(42.927, 83.512))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(323), Math.toRadians(180))
                    .build();

            Path8 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(42.927, 83.512), new Pose(16.878, 82.732))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                    .build();

            Path9 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(12.878, 82.732), new Pose(62.049, 90.732))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(323))
                    .build();

            Path10 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(62.049, 90.732), new Pose(53.073, 34.537))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(323), Math.toRadians(180))
                    .build();

            Path11 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(53.073, 34.537), new Pose(7.220, 35.317))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                    .build();

            Path12 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(7.220, 35.317), new Pose(68.098, 19.512))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(305))
                    .build();

            Path13 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(68.098, 19.512), new Pose(14.244, 9.951))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(305), Math.toRadians(180))
                    .build();
        }
    }

    public int autonomousPathUpdate() {
        // Add your state machine Here
         //starts here
            switch (pathState) {
                case 0:
                    follower.followPath(paths.Path1);
                    setPathState(1);
                    break;
                case 1:
                    if (!follower.isBusy()) {
                        follower.followPath(paths.Path2, true);
                        setPathState(2);
                    }
                    break;
                case 2:
                    if (!follower.isBusy()) {
                        follower.followPath(paths.Path3, true);
                        setPathState(3);
                    }
                    break;
                case 3:
                    if (!follower.isBusy()) {
                        follower.followPath(paths.Path4, true);
                        setPathState(4);
                    }
                    break;
                case 4:
                    if (!follower.isBusy()) {
                        follower.followPath(paths.Path5, true);
                        setPathState(5);
                    }
                    break;
                case 5:
                    if (!follower.isBusy()) {
                        follower.followPath(paths.Path6, true);
                        setPathState(6);
                    }
                    break;
                case 6:
                    if (!follower.isBusy()) {
                        follower.followPath(paths.Path7, true);
                        setPathState(7);
                    }
                    break;
                case 7:
                    if (!follower.isBusy()) {
                        follower.followPath(paths.Path8, true);
                        setPathState(8);
                    }
                    break;
                case 8:
                    if (!follower.isBusy()) {
                        follower.followPath(paths.Path9, true);
                        setPathState(9);
                    }
                    break;
                case 9:
                    if (!follower.isBusy()) {
                        follower.followPath(paths.Path10, true);
                        setPathState(10);
                    }
                    break;
                case 10:
                    if (!follower.isBusy()) {
                        follower.followPath(paths.Path11, true);
                        setPathState(11);
                    }
                    break;
                case 11:
                    if (!follower.isBusy()) {
                        follower.followPath(paths.Path12, true);
                        setPathState(12);
                    }
                    break;
                case 12:
                    if (!follower.isBusy()) {
                        follower.followPath(paths.Path13, true);
                        setPathState(13);
                    }
                    break;
                case 13:
                    if (!follower.isBusy()) {
                    //    follower.followPath(paths.endlinetoshootfromfarzone, true);
                        setPathState(-1);
                    }
                    break;
            }
            return pathState;

    }


            // Access paths with paths.pathName
            // Refer to the Pedro Pathing Docs (Auto Example) for an example state machine
            public void setPathState(int pState) {
                pathState = pState;

                pathTimer.reset();
                //switch for autonomous path update after reset for stuff that only happens once (such as intake running)
            }
}