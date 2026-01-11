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

@Autonomous(name = "eventually franklin will show up to defend his home", group = "Autonomous")
@Configurable // Panels
public class red15noturret extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance

    private ShooterSubSystemRed shooter;
    public Follower follower; // Pedro Pathing follower instance

    public boolean startedState = false;

    private enum pathState {
        goBack2ShootPreload,
        shoot1,
        curveGo2Second,
        intakeSecond,
        go2ShootSecond,
        shoot2,
        go2OpenGate1,
        go2ShootFromGate,
        shoot3,
        curve2First,
        intakeFirst,
        shootFirst,
        shoot4,
        curve2Third,
        intakeThird,
        shootThird,
        shoot5,
        leave
    } // Current autonomous path state (state machine)

    pathState pathstate;
    private Timer opModeTimer;
    public PathChain goBack2ShootPreload;
    public PathChain curveGo2Second;
    public PathChain intakeSecond;
    public PathChain go2ShootSecond;
    public PathChain go2OpenGate1;
    public PathChain go2ShootFromGate;
    public PathChain curve2First;
    public PathChain intakeFirst;
    public PathChain shootFirst;
    public PathChain curve2Third;
    public PathChain intakeThird;
    public PathChain shootThird;
    public PathChain leave;
    public static GoalId red;

    public void buildPaths(Follower follower) {
        goBack2ShootPreload = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(127.076, 113.018),

                                new Pose(99.519, 96.490)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(-90), Math.toRadians(-90))

                .build();

        curveGo2Second = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(99.519, 96.490),
                                new Pose(94.830, 60.817),
                                new Pose(102.391, 59.860)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(-90), Math.toRadians(0))

                .build();

        intakeSecond = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(102.391, 59.860),

                                new Pose(135.396, 59.809)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        go2ShootSecond = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(135.396, 59.809),
                                new Pose(92.350, 59.834),
                                new Pose(90.607, 90.994)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))

                .build();

        go2OpenGate1 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(90.607, 90.994),
                                new Pose(107.807, 46.359),
                                new Pose(131.746, 60.936)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(50))

                .build();

        go2ShootFromGate = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(131.746, 60.936),
                                new Pose(98.142, 69.459),
                                new Pose(95.440, 95.104)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(50), Math.toRadians(100))

                .build();

        curve2First = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(95.440, 95.104),
                                new Pose(92.183, 82.549),
                                new Pose(99.897, 82.662)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(100), Math.toRadians(0))

                .build();

        intakeFirst = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(99.897, 82.662),

                                new Pose(127.390, 82.424)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shootFirst = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(127.390, 82.424),

                                new Pose(105.504, 105.452)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))

                .build();

        curve2Third = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(105.504, 105.452),
                                new Pose(94.055, 36.493),
                                new Pose(102.163, 34.610)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))

                .build();

        intakeThird = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(102.163, 34.610),

                                new Pose(132.000, 34.399)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shootThird = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(132.000, 34.399),

                                new Pose(96.201, 95.832)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))

                .build();

        leave = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(96.201, 95.832),

                                new Pose(90.434, 107.624)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))

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
        follower.setStartingPose(new Pose(127.076, 113.018, Math.toRadians(-90)));
        pathTimer = new ElapsedTime();

        buildPaths(follower); // Build paths
        pathstate = pathState.goBack2ShootPreload;
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
            case goBack2ShootPreload:
                if (!startedState) {
                    follower.followPath(goBack2ShootPreload);
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
                    setPathState(pathState.curveGo2Second);
                }
                break;
            case curveGo2Second:
                if (!startedState) {
                    follower.followPath(curveGo2Second);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.intakeSecond);
                    startedState = false;
                }
                break;
            case intakeSecond:
                if (!startedState) {
                    follower.followPath(intakeSecond);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.go2ShootSecond);
                    startedState = false;
                }
                break;
            case go2ShootSecond:
                if (!startedState) {
                    follower.followPath(go2ShootSecond);
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
                    setPathState(pathState.go2OpenGate1);
                }
                break;
            case go2OpenGate1:
                if (!startedState) {
                    follower.followPath(go2OpenGate1);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.go2ShootFromGate);
                    startedState = false;
                }
                break;
            case go2ShootFromGate:
                if (!startedState) {
                    follower.followPath(go2ShootFromGate);
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
                    setPathState(pathState.shoot3);
                }
                break;
            case curve2First:
                if (!startedState) {
                    follower.followPath(curve2First);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.intakeFirst);
                    startedState = false;
                }
                break;
            case intakeFirst:
                if (!startedState) {
                    follower.followPath(intakeFirst);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.shootFirst);
                    startedState = false;
                }
                break;
            case shootFirst:
                if (!startedState) {
                    follower.followPath(shootFirst);
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
                    setPathState(pathState.curve2Third);
                }
                break;
            case curve2Third:
                if (!startedState) {
                    follower.followPath(curve2Third);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.intakeThird);
                    startedState = false;
                }
                break;
            case intakeThird:
                if (!startedState) {
                    follower.followPath(intakeThird);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.shootThird);
                    startedState = false;
                }
                break;
            case shootThird:
                if (!startedState) {
                    follower.followPath(shootThird);
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








