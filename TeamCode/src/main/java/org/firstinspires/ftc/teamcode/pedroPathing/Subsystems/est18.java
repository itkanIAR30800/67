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

@Autonomous(name = "18 blue", group = "Autonomous")
@Configurable // Panels
public class est18 extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    private ShooterSubSystem shooter;
    public Follower follower; // Pedro Pathing follower instance

    public boolean startedState = false;

    private enum pathState {
        startToShoot,
        shoot1,
        shootToMiddle,
        middleToShoot,
        shoot2,
        ShootToGate1,
        gateToShoot1,
        shoot3,
        ShootToGate2,
        gateToShoot2,
        shoot4,
        ShootToGate3,
        gateToShoot3,
        shoot5,
        shootToFirst,
        firstToShoot,
        shoot6,
        leave


    } // Current autonomous path state (state machine)

    pathState pathstate;
    private Timer opModeTimer;

    public PathChain startToShoot;
    public PathChain shootToMiddle;
    public PathChain middleToShoot;
    public PathChain ShootToGate1;
    public PathChain gateToShoot1;
    public PathChain ShootToGate2;
    public PathChain gateToShoot2;
    public PathChain ShootToGate3;
    public PathChain gateToShoot3;
    public PathChain shootToFirst;
    public PathChain firstToShoot;

    public void buildPaths(Follower follower) {
        startToShoot = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(14.756, 112.044),

                                new Pose(54.901, 100.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(223))

                .build();

        shootToMiddle = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(54.901, 100.000),
                                new Pose(47.373, 63.965),
                                new Pose(24.560, 59.825)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(223), Math.toRadians(180))

                .build();

        middleToShoot = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(24.560, 59.825),

                                new Pose(53.697, 79.044)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(223))

                .build();

        ShootToGate1 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(53.697, 79.044),
                                new Pose(26.934, 49.738),
                                new Pose(15.500, 63.647)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        gateToShoot1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(11.500, 58.647),

                                new Pose(53.157, 79.429)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(148), Math.toRadians(223))

                .build();

        ShootToGate2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(53.157, 79.429),
                                new Pose(26.934, 49.738),
                                new Pose(15.500, 63.647)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        gateToShoot2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(11.500, 58.647),

                                new Pose(53.157, 83.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(148), Math.toRadians(225))

                .build();

        ShootToGate3 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(53.157, 83.000),
                                new Pose(26.934, 49.738),
                                new Pose(11.500, 58.647)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        gateToShoot3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(11.500, 58.647),

                                new Pose(53.198, 79.481)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(148), Math.toRadians(220))

                .build();

        shootToFirst = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(53.198, 79.481),
                                new Pose(45.028, 94.787),
                                new Pose(24.560, 82.706)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(220), Math.toRadians(180))

                .build();

        firstToShoot = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(24.560, 82.706),

                                new Pose(56.586, 106.181)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(240))

                .build();
    }
    ElapsedTime pathTimer;


    void enter(pathState next) {
        pathstate = next;
        startedState = false;
    }

    @Override
    public void init() {
        shooter = new ShooterSubSystem(hardwareMap);
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(15.697, 109.598, Math.toRadians(270)));
        pathTimer = new ElapsedTime();

        buildPaths(follower); // Build paths
        pathstate = pathState.startToShoot;
        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);

//        blue.idNum = 20;
    }

    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();
        // Log values to Panels and Driver Station
        Pose currentPosition = follower.getPose();

        telemetry.addData("pathTimer: ", pathTimer.seconds());
        telemetry.addData("Started State: ", startedState);
        telemetry.addData("TX>   ", shooter.tx);
        panelsTelemetry.debug("Path State", pathstate);
        panelsTelemetry.debug("X", currentPosition.getX());
        panelsTelemetry.debug("Y", currentPosition.getY());
        panelsTelemetry.debug("Heading", currentPosition.getHeading());
        panelsTelemetry.update(telemetry);
    }

    public void autonomousPathUpdate()  {
        switch (pathstate) {
            case startToShoot:
                shooter.flywheelInit();
                if (!startedState) {
                    shooter.flywheelInit();
                    shooter.lockTurret();
                    follower.followPath(startToShoot);
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
                if(pathTimer.seconds() > 2.0) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.shootToMiddle);
                }
                break;
            case shootToMiddle:
                if (!startedState) {
                    follower.followPath(shootToMiddle);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.middleToShoot);
                    startedState = false;
                }
                break;

            case middleToShoot:
                if (!startedState) {
                    follower.followPath(middleToShoot);
                    shooter.intake();
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.shoot2);
                    follower.startTeleOpDrive();
                    startedState = false;
                    shooter.stopIntake();
                }
                break;


            case shoot2:
                turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 1.7) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.ShootToGate1);
                }
                break;
            case ShootToGate1:
                if (!startedState) {
                    //shooter.flywheelInit();
                    follower.followPath(ShootToGate1);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.gateToShoot1);
                    startedState = false;
                }
                break;
            case gateToShoot1:
                if (!startedState) {
                    follower.followPath(gateToShoot1);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    follower.startTeleOpDrive();
                    setPathState(pathState.shoot3);
                    startedState = false;
                }
                break;
            case shoot3:
                turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 2) {
                    shooter.stopShoot();
                    shooter.intake();
                    setPathState(pathState.ShootToGate2);
                }
                break;
            case ShootToGate2:
                if (!startedState) {
//                    shooter.flywheelInit();
                    follower.followPath(ShootToGate2);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.gateToShoot2);
                    startedState = false;
                }
                break;
            case gateToShoot2:
                if (!startedState) {
                    follower.followPath(gateToShoot2);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    follower.startTeleOpDrive();
                    setPathState(pathState.shoot4);
                    startedState = false;
                }
                break;

            case shoot4:
                turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 2.2) {
                    shooter.stopShoot();
                    shooter.intake();
                    setPathState(pathState.ShootToGate3);
                }
                break;
            case ShootToGate3:
                //shooter.flywheelInit();
                if (!startedState) {
                    follower.followPath(ShootToGate3);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    follower.startTeleOpDrive();
                    setPathState(pathState.gateToShoot3);
                    startedState = false;
                }
                break;
            case gateToShoot3:
                if (!startedState) {
                    follower.followPath(gateToShoot3);
//                    shooter.shoot5turret();
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    follower.startTeleOpDrive();
                    setPathState(pathState.shoot5);
                    startedState = false;
                }
                break;
            case shoot5:
                turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 1.7) {
                    shooter.stopShoot();
                    shooter.stopIntake();
                    setPathState(pathState.shootToFirst);
                }
                break;
            case shootToFirst:
                //shooter.flywheelInit();
                if (!startedState) {
                    follower.followPath(shootToFirst);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    follower.startTeleOpDrive();
                    setPathState(pathState.firstToShoot);
                    startedState = false;
                }
                break;
            case firstToShoot:
                if (!startedState) {
                    follower.followPath(firstToShoot);
//                    shooter.shoot5turret();
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    follower.startTeleOpDrive();
                    setPathState(pathState.shoot6);
                    startedState = false;
                }
                break;
            case shoot6:
                turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 1.7) {
                    shooter.stopShoot();
                    shooter.stopIntake();
                }
                break;

        }


    }

    public void setPathState(pathState pState) {
        pathstate = pState;
        startedState = false;
        pathTimer.reset();
        //switch for autonomous path update after reset for stuff that only happens once (such as intake running)
    }
}








