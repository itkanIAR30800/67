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

@Autonomous(name = "Pedro Pathing Autonomous 5", group = "Autonomous")
@Configurable // Panels
public class pedroauton5 extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    public Follower follower; // Pedro Pathing follower instance
    private int pathState; // Current autonomous path state (state machine)
    private Paths paths; // Paths defined in the Paths class

    @Override
    public void init() {
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(72, 8, Math.toRadians(90)));

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
        public PathChain shootRotate;
        public PathChain rotateToFirst;
        public PathChain firstToZone;
        public PathChain zoneRotateShoot1;
        public PathChain shootToMiddle;
        public PathChain middleToGate;
        public PathChain gateToZone;
        public PathChain zoneRotateShoot2;
        public PathChain shootToLast;
        public PathChain lastToZone;
        public PathChain zoneRotateShoot3;
        public PathChain shootToHuman;
        public PathChain humanToZone;
        public PathChain zoneRotateShoot4;

        public Paths(Follower follower) {
            startToShoot = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(26.667, 128.711), new Pose(62.222, 83.556))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(315), Math.toRadians(323))
                    .build();

            shootRotate = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(62.222, 83.556), new Pose(60.800, 83.556))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(323), Math.toRadians(180))
                    .build();

            rotateToFirst = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(60.800, 83.556), new Pose(14.933, 83.733))
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            firstToZone = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(14.933, 83.733), new Pose(56.889, 82.311))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                    .build();

            zoneRotateShoot1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(56.889, 82.311), new Pose(58.311, 80.889))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(323))
                    .build();

            shootToMiddle = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(58.311, 80.889),
                                    new Pose(67.911, 64.711),
                                    new Pose(18.133, 60.267)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(323), Math.toRadians(180))
                    .build();

            middleToGate = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(18.133, 60.267),
                                    new Pose(30.933, 64.533),
                                    new Pose(11.556, 69.156)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                    .build();

            gateToZone = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(11.556, 69.156), new Pose(54.400, 88.178))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                    .build();

            zoneRotateShoot2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(54.400, 88.178), new Pose(56.000, 88.178))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(323))
                    .build();

            shootToLast = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(56.000, 88.178),
                                    new Pose(87.822, 36.800),
                                    new Pose(12.800, 36.089)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(323), Math.toRadians(180))
                    .build();

            lastToZone = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(12.800, 36.089), new Pose(50.489, 92.978))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                    .build();

            zoneRotateShoot3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(50.489, 92.978), new Pose(49.067, 94.222))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(323))
                    .build();

            shootToHuman = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(49.067, 94.222),
                                    new Pose(1.778, 53.511),
                                    new Pose(7.822, 6.044)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            humanToZone = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(7.822, 6.044), new Pose(53.867, 118.933))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(-90), Math.toRadians(-90))
                    .build();

            zoneRotateShoot4 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(53.867, 118.933), new Pose(55.111, 117.333))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(-90), Math.toRadians(323))
                    .build();
        }
    }

    public int autonomousPathUpdate() {
        // Add your state machine Here
        // Access paths with paths.pathName
        // Refer to the Pedro Pathing Docs (Auto Example) for an example state machine
        return pathState;
    }
}