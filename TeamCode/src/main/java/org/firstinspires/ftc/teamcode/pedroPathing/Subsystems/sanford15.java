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

@Autonomous(name = "15 sanford blue", group = "Autonomous")
@Configurable // Panels
public class sanford15 extends OpMode {

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
                                new Pose(15.610, 109.659),

                                new Pose(55.610, 91.024)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(225))

                .build();
        shootToMiddle = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(55.610, 91.024),
                                new Pose(60.427, 54.085),
                                new Pose(23.244, 51.780)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(225), Math.toRadians(180))

                .build();
        middleToShoot = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(23.244, 51.780),
                                new Pose(65, 70),
                                new Pose(60.561, 90.585)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(-163), Math.toRadians(235))

                .build();
        shootToGate = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(60.561, 90.585),
                                new Pose(55.329, 33.146),
                                new Pose(12.25, 51)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(230), Math.toRadians(132))

                .build();
        gateToShoot = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(12.25, 51),
                                new Pose(70.756, 66.610),
                                new Pose(56.268, 85.024)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(132), Math.toRadians(233))

                .build();
        shootToGate2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(56.268, 85.024),
                                new Pose(55.329, 33.146),
                                new Pose(12.55, 52)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(226), Math.toRadians(132))

                .build();
        gateToShoot2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(12.55, 52),
                                new Pose(70.756, 66.610),
                                new Pose(56.268, 85.024)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(132), Math.toRadians(233))

                .build();
        shootToFirst = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(56.268, 85.024),

                                new Pose(23.439, 81.659)
                        )
                ).setTangentHeadingInterpolation()

                .build();
        firstToShoot = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(23.439, 81.659),

                                new Pose(50.146, 84.780)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(225))

                .build();
        shootToLast = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(50.146, 84.780),
                                new Pose(58.488, 37.049),
                                new Pose(20.415, 26.634)
                        )
                ).setTangentHeadingInterpolation()

                .build();
        lastToShoot = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(20.415, 26.634),

                                new Pose(53.463, 105.317)
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
        shooter = new ShooterSubSystem(hardwareMap);
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(15.610, 109.659, Math.toRadians(270)));
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
        telemetry.addData("speed", shooter.getShooterVelocity());
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
                    shooter.updateShootAndAlign();
                    shooter.lockTurret();
                    follower.followPath(startToShoot);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(sanford15.pathState.shoot1);
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
                    setPathState(sanford15.pathState.    shootToMiddle);
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
                    setPathState(sanford15.pathState.middleToShoot);
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
                    setPathState(sanford15.pathState.shoot2);
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
                    setPathState(sanford15.pathState.ShootToGate);
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
                        setPathState(sanford15.pathState.gateToShoot);
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
                    setPathState(sanford15.pathState.shoot3);
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
                    setPathState(sanford15.pathState.shootToGate2);
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
                        setPathState(sanford15.pathState.gateToShoot2);
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
                    setPathState(sanford15.pathState.shoot4);
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
                    setPathState(sanford15.pathState.shootToFirst);
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
                    setPathState(sanford15.pathState.firstToShoot);
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
                    setPathState(sanford15.pathState.shoot5);
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
                    setPathState(sanford15.pathState.shootToLast);
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
                    setPathState(sanford15.pathState.lastToShoot);
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
                    setPathState(sanford15.pathState.shoot6);
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








