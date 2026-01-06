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

@Autonomous(name = "Zeyad 9 red", group = "Autonomous")
@Configurable // Panels
public class zeyadtuff9ball extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance

    private ShooterSubSystemRed shooter;
    public Follower follower; // Pedro Pathing follower instance

    public boolean startedState = false;

    private enum pathState {
        startToShoot,
       shoot1,
        shootToFirst,
        firstToShoot,
        shoot2,
        shootToLast,
        lastToShoot,
        shoot3,
        shootToLeave,
    } // Current autonomous path state (state machine)

    pathState pathstate;
    private Timer opModeTimer;
    public PathChain startToShoot;
    public PathChain shootToFirst;
    public PathChain firstToShoot;
    public PathChain shootToLast;
    public PathChain lastToShoot;
    public PathChain shootToLeave;

    public void buildPaths(Follower follower) {
        startToShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(129.561, 110.049), new Pose(88.195, 82.927))
                )
                .setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(225))
                .build();

        shootToFirst = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(88.195, 82.927), new Pose(129.000, 82.927))
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        firstToShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(129.000, 82.927), new Pose(88.195, 82.732))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(225))
                .build();

        shootToLast = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(88.195, 82.732),
                                new Pose(73.951, 40.000),
                                new Pose(135.220, 31.805)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(225), Math.toRadians(0))
                .build();

        lastToShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(135.220, 31.805),
                                new Pose(74.146, 40.000),
                                new Pose(89.171, 82.732)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(225))
                .build();

        shootToLeave = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(89.171, 82.732), new Pose(89.756, 60.683))
                )
                .setConstantHeadingInterpolation(Math.toRadians(-80))
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
        follower.setStartingPose(new Pose(129.561, 110.049, Math.toRadians(270)));
        pathTimer = new ElapsedTime();

        buildPaths(follower); // Build paths
        pathstate = pathState.startToShoot;
        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);
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
                    setPathState(pathState.shootToLast);
                }
                break;
            case shootToLast:
                if (!startedState) {
                    follower.followPath(shootToLast);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.lastToShoot);
                    startedState = false;
                }
                break;
            case lastToShoot:
                if (!startedState) {
                    follower.followPath(lastToShoot);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.shoot3);
                    startedState = false;
                }
                break;
            case shoot3:
                 turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 2) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.shootToLeave);
                }
                break;
            case shootToLeave:
                if (!startedState) {
                    follower.followPath(shootToLeave);
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








