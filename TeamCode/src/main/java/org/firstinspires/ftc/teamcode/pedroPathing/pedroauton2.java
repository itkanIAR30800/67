package org.firstinspires.ftc.teamcode.pedroPathing;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Tuff pedro", group = "Autonomous")
@Configurable // Panels
public class pedroauton2 extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    public Follower follower; // Pedro Pathing follower instance

    public boolean startedState = false;
    private enum pathState{
        shoot1,
        Wait3,
        intakeMiddle2,
        shoot2,
        gate3,
        shoot3,
        intakeFirst4,
        shoot4,
        intakeLast5,
        shoot5,
        leave6
    } // Current autonomous path state (state machine)
    pathState pathstate;
    private Timer opModeTimer;
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




    public void buildPaths(Follower follower) {
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
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(270), 0.6)
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
                .setReversed()
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

    void enter(pathState next) {
        pathstate = next;
        startedState = false;
    }

    @Override
    public void init() {

        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(24.888888888888886, 130.48888888888888, Math.toRadians(323)));

        buildPaths(follower); // Build paths
        pathstate = pathState.shoot1;
        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);
    }

    @Override
    public void loop() {
        follower.update();
        switch (pathstate) {
            case shoot1:
                if (!startedState) {
                    follower.followPath(shoot1, true);
                    startedState = true;
                }
                if (!follower.isBusy()){
                    pathstate = pathState.intakeMiddle2;
                    startedState = false;
                    }
                break;

            case intakeMiddle2:
                if (!startedState) {
                    follower.followPath(intakeMiddle2, true);
                    startedState = true;
                }
                if (!follower.isBusy()){
                    pathstate = pathState.shoot2;
                    startedState = false;
                }
                break;
            case shoot2:
                if (!startedState) {
                    follower.followPath(shoot2, true);
                    startedState = true;
                }
                if (!follower.isBusy()){
                    pathstate = pathState.gate3;
                    startedState = false;
                }
                break;
            case gate3:
                if (!startedState) {
                    follower.followPath(gate3, true);
                    startedState = true;
                }
                if (!follower.isBusy()){
                    pathstate = pathState.shoot3;
                    startedState = false;
                }
                break;
            case shoot3:
                if (!startedState) {
                    follower.followPath(shoot3, true);
                    startedState = true;
                }
                if (!follower.isBusy()){
                    pathstate = pathState.intakeFirst4;
                    startedState = false;
                }
                break;
            case intakeFirst4:
                if (!startedState) {
                    follower.followPath(intakeFirst4, true);
                    startedState = true;
                }
                if (!follower.isBusy()){
                    pathstate = pathState.shoot4;
                    startedState = false;
                }
                break;
            case shoot4:
                if (!startedState) {
                    follower.followPath(shoot4, true);
                    startedState = true;
                }
                if (!follower.isBusy()){
                    pathstate = pathState.intakeLast5;
                    startedState = false;
                }
                break;
            case intakeLast5:
                if (!startedState) {
                    follower.followPath(intakeLast5, true);
                    startedState = true;
                }
                if (!follower.isBusy()){
                    pathstate = pathState.shoot5;
                    startedState = false;
                }
                break;
            case shoot5:
                if (!startedState) {
                    follower.followPath(shoot5, true);
                    startedState = true;
                }
                if (!follower.isBusy()){
                    pathstate = pathState.leave6;
                    startedState = false;
                }
                break;
            case leave6:
                if (!startedState) {
                    startedState = true;
                }
                if (!follower.isBusy()){
                    startedState = false;
                }
                break;
        }


        // Log values to Panels and Driver Station
        panelsTelemetry.debug("Path State", pathstate);
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
        panelsTelemetry.update(telemetry);
    }
}












