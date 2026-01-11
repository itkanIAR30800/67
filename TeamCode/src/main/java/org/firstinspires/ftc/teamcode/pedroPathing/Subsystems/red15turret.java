package org.firstinspires.ftc.teamcode.pedroPathing.Subsystems;

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
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "i drink soda i eat pizza", group = "Autonomous")
@Configurable // Panels
public class red15turret extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance

    private ShooterSubSystemRed shooter;
    public Follower follower; // Pedro Pathing follower instance

    public boolean startedState = false;

    private enum pathState {
        shootPreLoad,
        shoot1,
        intakeMidSpike,
        shootMidSpike,
        shoot2,
        openIntakeGate,
        shootGate,
        shoot3,
        intakeNearSpike,
        shootNearSpike,
        shoot4,
        intakeLastSpike,
        shootLastSpike,
        shoot5,
        leave
    } // Current autonomous path state (state machine)

    pathState pathstate;
    private Timer opModeTimer;
    public PathChain shootPreLoad;
    public PathChain intakeMidSpike;
    public PathChain shootMidSpike;
    public PathChain openIntakeGate;
    public PathChain shootGate;
    public PathChain intakeNearSpike;
    public PathChain shootNearSpike;
    public PathChain intakeLastSpike;
    public PathChain shootLastSpike;
    public PathChain leave;
    public static GoalId red;

    public void buildPaths(Follower follower) {
        shootPreLoad = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(128.756, 111.001),

                                new Pose(101.032, 99.851)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(140))

                .build();

        intakeMidSpike = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(101.032, 99.851),
                                new Pose(93.928, 51.585),
                                new Pose(129.728, 59.075)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shootMidSpike = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(129.728, 59.075),
                                new Pose(86.648, 63.039),
                                new Pose(100.985, 99.567)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(12), Math.toRadians(140))

                .build();

        openIntakeGate = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(100.985, 99.567),
                                new Pose(100.817, 46.880),
                                new Pose(131.985, 60.734)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shootGate = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(131.985, 60.734),
                                new Pose(87.543, 39.487),
                                new Pose(100.739, 99.628)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(30), Math.toRadians(140))

                .build();

        intakeNearSpike = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(100.739, 99.628),
                                new Pose(107.373, 80.888),
                                new Pose(127.385, 83.086)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shootNearSpike = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(127.385, 83.086),

                                new Pose(100.881, 99.728)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(8), Math.toRadians(140))

                .build();

        intakeLastSpike = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(100.881, 99.728),
                                new Pose(89.559, 30.581),
                                new Pose(130.729, 35.266)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shootLastSpike = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(130.729, 35.266),

                                new Pose(100.919, 99.327)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(7), Math.toRadians(140))

                .build();

        leave = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(100.919, 99.327),

                                new Pose(95.151, 111.874)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(140), Math.toRadians(140))

                .build();
    }

    ElapsedTime pathTimer;


    void enter(pathState next) {
        pathstate = next;
        startedState = false;
    }

    @Override
    public void init() {
        shooter = new ShooterSubSystemRed(hardwareMap);
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(128.756, 111.001, Math.toRadians(90)));
        pathTimer = new ElapsedTime();

        buildPaths(follower); // Build paths
        pathstate = pathState.shootPreLoad;
        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);

        red.idNum = 20;
    }

    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();
        // Log values to Panels and Driver Station
        Pose currentPosition = follower.getPose();
        panelsTelemetry.debug("Path State", pathstate);
        panelsTelemetry.debug("X", currentPosition.getX());
        panelsTelemetry.debug("Y", currentPosition.getY());
        panelsTelemetry.debug("Heading", currentPosition.getHeading());
        panelsTelemetry.update(telemetry);
    }

    public void autonomousPathUpdate()  {
        switch (pathstate) {
            case shootPreLoad:
                if (!startedState) {
                    follower.followPath(shootPreLoad);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.shoot1);
                    startedState = false;
                    follower.startTeleOpDrive();
                }
                break;
            case shoot1:
                double turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 2) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.intakeMidSpike);
                }
                break;
            case intakeMidSpike:
                if (!startedState) {
                    follower.followPath(intakeMidSpike);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.shootMidSpike);
                    startedState = false;
                }
                break;
            case shootMidSpike:
                if (!startedState) {
                    follower.followPath(shootMidSpike);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.shoot2);
                    follower.startTeleOpDrive();
                    startedState = false;
                }
                break;
            case shoot2:
                turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 2) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.openIntakeGate);
                }
                break;
            case openIntakeGate:
                if (!startedState) {
                    follower.followPath(openIntakeGate);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.shootGate);
                    startedState = false;
                }
                break;
            case shootGate:
                if (!startedState) {
                    follower.followPath(shootGate);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.shoot3);
                    follower.startTeleOpDrive();
                    startedState = false;
                }
                break;
            case shoot3:
                turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 2) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.intakeNearSpike);
                }
                break;
            case intakeNearSpike:
                if (!startedState) {
                    follower.followPath(intakeNearSpike);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.shootNearSpike);
                    startedState = false;
                }
                break;
            case shootNearSpike:
                if (!startedState) {
                    follower.followPath(shootNearSpike);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.shoot4);
                    startedState = false;
                }
                break;
            case shoot4:
                turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 2) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.intakeLastSpike);
                }
                break;
            case intakeLastSpike:
                if (!startedState) {
                    follower.followPath(intakeLastSpike);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.shootLastSpike);
                    startedState = false;
                }
                break;
            case shootLastSpike:
                if (!startedState) {
                    follower.followPath(shootLastSpike);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.shoot5);
                    startedState = false;
                }
                break;
            case shoot5:
                turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 2) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.leave);
                }
                break;
            case leave:
                if (!startedState) {
                    follower.followPath(leave);
                    startedState = true;
                }
                break;
        }


    }

    public void setPathState(pathState pState) {
        pathstate = pState;
        pathTimer.reset();
        //switch for autonomous path update after reset for stuff that only happens once (such as intake running)
    }
}








