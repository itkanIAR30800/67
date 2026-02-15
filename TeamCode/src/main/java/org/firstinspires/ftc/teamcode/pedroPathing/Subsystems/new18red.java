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

@Autonomous(name = "18 new red", group = "Autonomous")
@Configurable // Panels
public class new18red extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    private ShooterSubSystemRed shooter;
    public Follower follower; // Pedro Pathing follower instance
    public boolean startedState = false;

    private enum pathState {
        startToShoot,
        shoot1,
        shootToMiddle,
        middleToShoot,
        shoot2,
        ShootToGate,
        gateToShoot,
        shoot3,
        shootToGate2,
        gateToShoot2,
        shoot4,
        shootToFirst,
        firstToShoot,
        shoot5,
        shootToLast,
        lastToShoot,
        shoot6,
        leave


    } // Current autonomous path state (state machine)

    pathState pathstate;
    private Timer opModeTimer;

    public PathChain startToShoot;
    public PathChain shootToMiddle;
    public PathChain middleToShoot;
    public PathChain shootToGate;
    public PathChain gateToShoot;
    public PathChain shootToGate2;
    public PathChain gateToShoot2;
    public PathChain shootToFirst;
    public PathChain firstToShoot;
    public PathChain shootToLast;
    public PathChain lastToShoot;

    public void buildPaths(Follower follower) {
        startToShoot = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(144-15.610, 109.659),

                                new Pose(144-55.610, 91.024)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(315))

                .build();
        shootToMiddle = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(144-55.610, 91.024),
                                new Pose(144-59.427, 53.085),
                                new Pose(144-23.244, 51.780)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(315), Math.toRadians(0))

                .build();
        middleToShoot = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(144-23.244, 51.780),
                                new Pose(144-65, 70),
                                new Pose(144-60.561, 90.585)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(315))

                .build();
        shootToGate = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(144-60.561, 90.585),
                                new Pose(144-55.329, 33.146),
                                new Pose(144-11.5, 48.5)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(315), Math.toRadians(45))

                .build();
        gateToShoot = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(144-11.5, 48.5),
                                new Pose(144-70.756, 66.610),
                                new Pose(144-56.268, 85.024)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(315))

                .build();
        shootToGate2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(144-56.268, 85.024),
                                new Pose(144-55.329, 33.146),
                                new Pose(144-10.25, 49)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(315), Math.toRadians(45))

                .build();
        gateToShoot2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(144-10.25 , 49),
                                new Pose(144-70.756, 66.610),
                                new Pose(144-56.268, 82.024)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(315))

                .build();
        shootToFirst = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(144-56.268, 82.024),

                                new Pose(144-20.439, 78.659)
                        )
                ).setTangentHeadingInterpolation()

                .build();
        firstToShoot = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(144-20.439, 78.659),

                                new Pose(144-50.146, 81.780)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(315))

                .build();
        shootToLast = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(144-50.146, 81.780),
                                new Pose(144-58.488, 37.049),
                                new Pose(144-20.415, 26.634)
                        )
                ).setTangentHeadingInterpolation()

                .build();
        lastToShoot = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(144-20.415, 26.634),

                                new Pose(144-53.463, 110.317)
                        )
                ).setTangentHeadingInterpolation()
                .setReversed()
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
        follower.setStartingPose(new Pose(144-15.610, 109.659, Math.toRadians(270)));
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
        panelsTelemetry.addData("speed", shooter.getShooterVelocity());
        panelsTelemetry.update(telemetry);
    }

    public void autonomousPathUpdate()  {
        switch (pathstate) {
            case startToShoot:
                shooter.updateShootAndAlign();
                //shooter.flywheelInit();
                if (!startedState) {
                    //shooter.flywheelInit();
                    shooter.lockTurret();
                    follower.followPath(startToShoot);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(new18red.pathState.shoot1);
                    //shooter.flywheelInit();
                    startedState = false;
                    follower.startTeleOpDrive();
                }
                break;
            case shoot1:
                double turnPower = shooter.updateShootAndAlign();
                shooter.gate();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 1.35) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(new18red.pathState.    shootToMiddle);
                }
                break;
            case     shootToMiddle:
                shooter.updateShootAndAlign();

                if (!startedState) {
                    follower.followPath(    shootToMiddle);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(new18red.pathState.middleToShoot);
                    startedState = false;
                }
                break;

            case middleToShoot:
                shooter.updateShootAndAlign();

                if (!startedState) {
                    follower.followPath(middleToShoot);
                    shooter.intake();
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(new18red.pathState.shoot2);
                    follower.startTeleOpDrive();
                    startedState = false;
                    shooter.stopIntake();
                }
                break;


//            case zoneShootRotate:
//                if (!startedState) {
//                    follower.followPath(zoneShootRotate);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    shooter.stopIntake();
//                    setPathState(pathState.shoot2);
//                    follower.startTeleOpDrive();
//                    startedState = false;
//                }
//                break;

            case shoot2:
                turnPower = shooter.updateShootAndAlign();
                shooter.gate();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 0.93) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(new18red.pathState.ShootToGate);
                }
                break;
            case ShootToGate:
                shooter.updateShootAndAlign();
                if (!startedState) {
                    follower.followPath(shootToGate);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    if(pathTimer.seconds() > 3.2) {
                        setPathState(new18red.pathState.gateToShoot);
                        startedState = false;
                    }
                }
                break;
            case gateToShoot:
                shooter.stopIntake();
                if (!startedState) {
                    follower.followPath(gateToShoot);
                    if(pathTimer.seconds() > 1){
                        shooter.updateShootAndAlign();
                    }
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    follower.startTeleOpDrive();
                    setPathState(new18red.pathState.shoot3);
                    startedState = false;
                }
                break;
            case shoot3:
                turnPower = shooter.updateShootAndAlign();
                shooter.gate();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 0.93) {
                    shooter.stopShoot();
                    shooter.intake();
                    setPathState(new18red.pathState.shootToGate2);
                }
                break;
            case shootToGate2:
                shooter.updateShootAndAlign();
                if (!startedState) {
                    follower.followPath(shootToGate2);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    if(pathTimer.seconds() > 3.2) {
                        setPathState(new18red.pathState.gateToShoot2);
                        startedState = false;
                    }
                }
                break;
            case gateToShoot2:
                if (!startedState) {
                    shooter.updateShootAndAlign();
                    follower.followPath(gateToShoot2);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    follower.startTeleOpDrive();
                    setPathState(new18red.pathState.shoot4);
                    startedState = false;
                }
                break;
            case shoot4:
                turnPower = shooter.updateShootAndAlign();
                shooter.gate();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 0.93) {
                    shooter.stopShoot();
                    shooter.intake();
                    setPathState(new18red.pathState.shootToFirst);
                }
                break;
            case shootToFirst:
                shooter.updateShootAndAlign();

                if (!startedState) {
                    follower.followPath(shootToFirst);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(new18red.pathState.firstToShoot);
                    startedState = false;
                }
                break;
            case firstToShoot:
                shooter.updateShootAndAlign();
                shooter.stopIntake();
                if (!startedState) {
                    follower.followPath(firstToShoot);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    follower.startTeleOpDrive();
                    setPathState(new18red.pathState.shoot5);
                    startedState = false;
                }
                break;

            case shoot5:
                turnPower = shooter.updateShootAndAlign();
                shooter.gate();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 0.93) {
                    shooter.stopShoot();
                    shooter.intake();
                    setPathState(new18red.pathState.shootToLast);
                }
                break;
            case shootToLast:
                shooter.updateShootAndAlign();

                if (!startedState) {
                    follower.followPath(shootToLast);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    // follower.startTeleOpDrive();
                    setPathState(new18red.pathState.lastToShoot);
                    shooter.stopIntake();
                    startedState = false;
                }
                break;
            case lastToShoot:


                if (!startedState) {
                    shooter.stopIntake();
                    follower.followPath(lastToShoot);
                    if (pathTimer.seconds() > 1){
                        shooter.updateShootAndAlign();
                    }
//                    shooter.shoot5turret();
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    follower.startTeleOpDrive();
                    setPathState(new18red.pathState.shoot6);
                    startedState = false;
                }
                break;
            case shoot6:
                turnPower = shooter.updateShootAndAlign();
                shooter.gate();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 10) {
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








