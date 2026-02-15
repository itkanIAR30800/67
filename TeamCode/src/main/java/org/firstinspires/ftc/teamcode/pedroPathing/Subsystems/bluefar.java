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

@Autonomous(name = "blue far", group = "Autonomous")
@Configurable // Panels
public class bluefar extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    private ShooterSubSystemFarBlue shooter;
    public Follower follower; // Pedro Pathing follower instance
    public boolean startedState = false;

    private enum pathState {
        startToShoot,
        shoot1,
        shootToLast,
        lastToShoot,
        shoot2,
        ShootToHP,
        hpToBackUp,
        backUpToHP,
        hpToShoot,
        shoot3,
        shootToTunnel,
        tunnelToShoot,
        shoot4,
        shootToHP2,
        hpToShoot2,
        shoot5,
        leave,

    } // Current autonomous path state (state machine)

    pathState pathstate;
    private Timer opModeTimer;

    public PathChain startToShoot;
    public PathChain shootToLast;
    public PathChain lastToShoot;
    public PathChain shootToHP;
    public PathChain hpToBackUp;
    public PathChain backUpToHP;
    public PathChain hpToShoot;
    public PathChain shootToTunnel;
    public PathChain tunnelToShoot;
    public PathChain shootToHP2;
    public PathChain hpToShoot2;
    public PathChain leave;

    public void buildPaths(Follower follower){
        startToShoot = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(40.585, 9.171),

                                new Pose(50.537, 17.073)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(205))

                .build();

        shootToLast = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(50.537, 17.073),
                                new Pose(53.659, 36.878),
                                new Pose(24.878, 35.585)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(205), Math.toRadians(180))

                .build();

        lastToShoot = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(24.878, 35.585),

                                new Pose(50.707, 17.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(205))

                .build();

        shootToHP = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(50.707, 17.000),

                                new Pose(11.341, 11.610)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        hpToBackUp = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(11.341, 11.610),

                                new Pose(15.829, 12.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(-172), Math.toRadians(-172))

                .build();

        backUpToHP = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(15.829, 12.000),

                                new Pose(11.122, 11.439)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(-172), Math.toRadians(-172))

                .build();

        hpToShoot = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(11.122, 11.439),

                                new Pose(50.951, 13.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(-172), Math.toRadians(205))

                .build();

        shootToTunnel = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(50.951, 13.000),
                                new Pose(11.988, 0.439),
                                new Pose(10.878, 39.561)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        tunnelToShoot = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(10.878, 39.561),

                                new Pose(51.049, 13.195)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(205))

                .build();

        shootToHP2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(51.049, 13.195),

                                new Pose(9.341, 10.659)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        hpToShoot2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(9.341, 10.659),

                                new Pose(50.659, 16.780)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(174), Math.toRadians(205))

                .build();

        leave = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(50.659, 16.780),

                                new Pose(34.390, 12.976)
                        )
                ).setConstantHeadingInterpolation(Math.toRadians(205))

                .build();
    }
    ElapsedTime pathTimer;


    void enter(pathState next) {
        pathstate = next;
        startedState = false;
    }

    @Override
    public void init() {
        shooter = new ShooterSubSystemFarBlue(hardwareMap);
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
                    setPathState(bluefar.pathState.shoot1);
                    //shooter.flywheelInit();
                    startedState = false;
                    follower.startTeleOpDrive();
                }
                break;
            case shoot1:
                double turnPower = shooter.updateShootAndAlign();
                shooter.gate();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 1.6) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(bluefar.pathState.    shootToLast);
                }
                break;
            case     shootToLast:
                shooter.updateShootAndAlign();

                if (!startedState) {
                    follower.followPath(    shootToLast);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.lastToShoot);
                    startedState = false;
                }
                break;

            case lastToShoot:
                shooter.updateShootAndAlign();

                if (!startedState) {
                    follower.followPath(lastToShoot);
                    shooter.intake();
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(bluefar.pathState.shoot2);
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
                if(pathTimer.seconds() > 1.3) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(bluefar.pathState.ShootToHP);
                }
                break;
            case ShootToHP:
                shooter.updateShootAndAlign();
                if (!startedState) {
                    follower.followPath(shootToHP);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.hpToBackUp);
                    startedState = false;

                }
                break;
            case hpToBackUp:
                shooter.stopIntake();
                if (!startedState) {
                    follower.followPath(hpToBackUp);
                    shooter.updateShootAndAlign();
                        startedState = true;
                }
                if (!follower.isBusy()) {
                    follower.startTeleOpDrive();
                    setPathState(pathState.backUpToHP);
                    startedState = false;
                }
                break;
            case backUpToHP:
                shooter.updateShootAndAlign();
                if (!startedState) {
                    follower.followPath(backUpToHP);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.hpToShoot);
                    startedState = false;

                }
                break;
            case hpToShoot:
                shooter.updateShootAndAlign();
                if (!startedState) {
                    follower.followPath(hpToShoot);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(bluefar.pathState.shoot3);
                    startedState = false;

                }
                break;

            case shoot3:
                turnPower = shooter.updateShootAndAlign();
                shooter.gate();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 1.3) {
                    shooter.stopShoot();
                    shooter.intake();
                    setPathState(pathState.shootToTunnel);
                }
                break;
            case shootToTunnel:
                shooter.updateShootAndAlign();

                if (!startedState) {
                    follower.followPath(shootToTunnel);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.tunnelToShoot);
                    startedState = false;
                }
                break;
            case tunnelToShoot:
                shooter.updateShootAndAlign();
                shooter.stopIntake();
                if (!startedState) {
                    follower.followPath(tunnelToShoot);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    follower.startTeleOpDrive();
                    setPathState(bluefar.pathState.shoot4);
                    startedState = false;
                }
                break;

            case shoot4:
                turnPower = shooter.updateShootAndAlign();
                shooter.gate();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 1.3) {
                    shooter.stopShoot();
                    shooter.intake();
                    setPathState(bluefar.pathState.shootToHP2);
                }
                break;
            case shootToHP2:
                shooter.updateShootAndAlign();

                if (!startedState) {
                    follower.followPath(shootToHP2);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    // follower.startTeleOpDrive();
                    setPathState(bluefar.pathState.hpToShoot2);
                    shooter.stopIntake();
                    startedState = false;
                }
                break;
            case hpToShoot2:


                if (!startedState) {
                    shooter.stopIntake();
                    follower.followPath(hpToShoot2);
                    shooter.updateShootAndAlign();
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    follower.startTeleOpDrive();
                    setPathState(bluefar.pathState.shoot5);
                    startedState = false;
                }
                break;
            case shoot5:
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








