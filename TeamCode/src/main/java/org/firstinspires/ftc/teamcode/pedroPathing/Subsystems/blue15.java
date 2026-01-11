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

@Autonomous(name = "blue 15 boiiiii", group = "Autonomous")
@Configurable // Panels
public class blue15 extends OpMode {

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
        shootToGateIntake,
        gateIntakeToShoot,
        shoot3,
        shootToFirst,
        firstToShoot,
        shoot4,
        shootToLast,
        lastToShootLeave,
    } // Current autonomous path state (state machine)

    pathState pathstate;
    private Timer opModeTimer;
    public PathChain startToShoot;
    public PathChain shootToMiddle;
    public PathChain middleToShoot;
    public PathChain shootToGateIntake;
    public PathChain gateIntakeToShoot;
    public PathChain shootToFirst;
    public PathChain firstToShoot;
    public PathChain shootToLast;
    public PathChain lastToShootLeave;

//    public static GoalId blue;

    public void buildPaths(Follower follower) {
        startToShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(14.634, 108.293), new Pose(48.976, 92.488))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(220))
                .build();

        shootToMiddle = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(48.976, 92.488),
                                new Pose(53.854, 59.902),
                                new Pose(12.488, 58.732)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        middleToShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(12.488, 58.732),
                                new Pose(32.780, 61.463),
                                new Pose(55.610, 93.854)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        shootToGateIntake = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(55.610, 93.854),
                                new Pose(43.512, 39.024),
                                new Pose(11.902, 60.293)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        gateIntakeToShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(11.902, 60.293),
                                new Pose(28.098, 50.927),
                                new Pose(55.220, 93.659)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        shootToFirst = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(55.220, 93.659), new Pose(15.024, 83.707))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-123), Math.toRadians(180))
                .build();

        firstToShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(15.024, 83.707), new Pose(55.220, 93.659))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(-123))
                .setReversed()
                .build();

        shootToLast = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(55.220, 93.659),
                                new Pose(62.244, 31.415),
                                new Pose(13.268, 37.659)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        lastToShootLeave = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(13.268, 37.659), new Pose(51.317, 110.244))
                )
                .setTangentHeadingInterpolation()
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
        follower.setStartingPose(new Pose(14.634, 108.293, Math.toRadians(180)));
        pathTimer = new ElapsedTime();

        buildPaths(follower); // Build paths
        pathstate = pathState.startToShoot;
        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);

//        blue.idNum = 24;
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
            case startToShoot:
                if (!startedState) {
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
                    follower.followPath(shootToMiddle);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.middleToShoot);
                    startedState = false;
                }
                break;
            case middleToShoot:
                if (!startedState) {
                    follower.followPath(middleToShoot);
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
                    setPathState(pathState.shootToGateIntake);
                }
                break;
            case shootToGateIntake:
                if (!startedState) {
                    follower.followPath(shootToGateIntake);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.gateIntakeToShoot);
                    startedState = false;
                }
                break;
            case gateIntakeToShoot:
                if (!startedState) {
                    follower.followPath(gateIntakeToShoot);
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
                    setPathState(pathState.shootToFirst);
                }
                break;
            case shootToFirst:
                if (!startedState) {
                    follower.followPath(shootToFirst);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.firstToShoot);
                    startedState = false;
                }
                break;
            case firstToShoot:
                if (!startedState) {
                    follower.followPath(firstToShoot);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.shoot4);
                    startedState = false;
                    follower.startTeleOpDrive();
                }
                break;
            case shoot4:
                turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 2) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.shootToLast);
                }
                break;
            case shootToLast:
                if (!startedState) {
                    follower.followPath(shootToLast);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.lastToShootLeave);
                    startedState = false;
                }
                break;
            case lastToShootLeave:
                if (!startedState) {
                    follower.followPath(lastToShootLeave);
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








