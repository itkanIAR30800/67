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
        shootToFirst,
        firstToShoot,
        shoot4,
        shootToLast,
        lastToShoot,
        shoot5,
        leave


    } // Current autonomous path state (state machine)

    pathState pathstate;
    private Timer opModeTimer;

    public PathChain startToShoot;
    public PathChain shootToMiddle;
    public PathChain middleToShoot;
    public PathChain shootToGate;
    public PathChain gateToShoot;
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
                                new Pose(22.244, 54.780)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(225), Math.toRadians(180))

                .build();
        middleToShoot = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(22.244, 59.780),
                                new Pose(65, 70),
                                new Pose(55.561, 90.585)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(-163), Math.toRadians(225))

                .build();
        shootToGate = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(55.561, 90.585),
                                new Pose(55.329, 37.146),
                                new Pose(11.341, 53.927)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(225), Math.toRadians(145))

                .build();
        gateToShoot = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(11.341, 53.927),
                                new Pose(70.756, 66.610),
                                new Pose(56.268, 85.024)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(150), Math.toRadians(228))

                .build();
        shootToFirst = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(56.268, 85.024),

                                new Pose(20.439, 86.659)
                        )
                ).setTangentHeadingInterpolation()

                .build();
        firstToShoot = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(20.439, 86.659),

                                new Pose(56.146, 84.780)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(225))

                .build();
        shootToLast = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(56.146, 84.780),
                                new Pose(58.488, 37.049),
                                new Pose(25.415, 28.634)
                        )
                ).setTangentHeadingInterpolation()

                .build();
        lastToShoot = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(25.415, 28.634),

                                new Pose(53.463, 111.317)
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
        shooter.flywheelInit();
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
                shooter.flywheelInit();
                if (!startedState) {
                    shooter.flywheelInit();
                    shooter.lockTurret();
                    shooter.intake();
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
                if(pathTimer.seconds() > 2) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.shootToMiddle);
                }
                break;
            case shootToMiddle:
                if (!startedState) {
                    shooter.intake();
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
                if(pathTimer.seconds() > 2.1) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.ShootToGate);
                }
                break;
            case ShootToGate:
                if (!startedState) {
                    //shooter.flywheelInit();
                    follower.followPath(shootToGate);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.gateToShoot);
                    startedState = false;
                }
                break;
            case gateToShoot:
                if (!startedState) {
                    follower.followPath(gateToShoot);
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
                if(pathTimer.seconds() > 2.2) {
                    shooter.stopShoot();
                    shooter.intake();
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
                    setPathState(pathState.shoot4);
                    startedState = false;
                }
                break;
            case shoot4:
                turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 2.1) {
                    shooter.stopShoot();
                    shooter.intake();
                    setPathState(pathState.shootToLast);
                }
                break;
            case shootToLast:
                //shooter.flywheelInit();
                if (!startedState) {
                    follower.followPath(shootToLast);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    follower.startTeleOpDrive();
                    setPathState(pathState.lastToShoot);
                    startedState = false;
                }
                break;
            case lastToShoot:
                if (!startedState) {
                    shooter.stopIntake();
                    follower.followPath(lastToShoot);
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








