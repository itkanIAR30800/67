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

@Autonomous(name = "360 no scope", group = "Autonomous")
@Configurable // Panels
public class bluefarnospike extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance

    private ShooterSubSystemRed shooter;
    public Follower follower; // Pedro Pathing follower instance

    public boolean startedState = false;

    private enum pathState {
        startToShoot,
        shoot1,
        shootToHuman1,
        humanToShoot1,
        shoot2,
        shootToHuman2,
        humanToShoot2,
        shoot3,
        shootToHuman3,
        humanToShoot3,
        shoot4,
        shootToLeave
    } // Current autonomous path state (state machine)

    pathState pathstate;
    private Timer opModeTimer;
    public PathChain startToShoot;
    public PathChain shootToHuman1;
    public PathChain humanToShoot1;
    public PathChain shootToHuman2;
    public PathChain humanToShoot2;
    public PathChain shootToHuman3;
    public PathChain humanToShoot3;
    public PathChain shootToLeave;
    public static GoalId blue;

    public void buildPaths(Follower follower) {
        startToShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(57.756, 9.366), new Pose(59.317, 22.244))
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(115))
                .build();

        shootToHuman1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(59.317, 22.244), new Pose(11.122, 10.732))
                )
                .setTangentHeadingInterpolation()
                .build();

        humanToShoot1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(11.122, 10.732), new Pose(59.317, 22.439))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-167), Math.toRadians(115))
                .build();

        shootToHuman2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(59.317, 22.439), new Pose(11.122, 10.732))
                )
                .setTangentHeadingInterpolation()
                .build();

        humanToShoot2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(11.122, 10.732), new Pose(59.317, 22.439))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-167), Math.toRadians(115))
                .build();

        shootToHuman3 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(59.317, 22.439), new Pose(11.122, 10.732))
                )
                .setTangentHeadingInterpolation()
                .build();

        humanToShoot3 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(11.122, 10.732), new Pose(59.317, 22.439))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-167), Math.toRadians(115))
                .build();

        shootToLeave = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(59.317, 22.439), new Pose(51.512, 35.317))
                )
                .setLinearHeadingInterpolation(Math.toRadians(115), Math.toRadians(115))
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
        follower.setStartingPose(new Pose(57.756, 9.366, Math.toRadians(90)));
        pathTimer = new ElapsedTime();

        buildPaths(follower); // Build paths
        pathstate = pathState.startToShoot;
        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);

        blue.idNum = 20;
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
                    setPathState(pathState.shootToHuman1);
                }
                break;
            case shootToHuman1:
                if (!startedState) {
                    follower.followPath(shootToHuman1);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.humanToShoot1);
                    startedState = false;
                }
                break;
            case humanToShoot1:
                if (!startedState) {
                    follower.followPath(humanToShoot1);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.shoot2);
                    startedState = false;
                }
                break;
            case shoot2:
                turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 2) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.shootToHuman2);
                }
                break;
            case shootToHuman2:
                if (!startedState) {
                    follower.followPath(shootToHuman2);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.humanToShoot2);
                    startedState = false;
                }
                break;
            case humanToShoot2:
                if (!startedState) {
                    follower.followPath(humanToShoot2);
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
                    setPathState(pathState.shootToHuman3);
                }
                break;
            case shootToHuman3:
                if (!startedState) {
                    follower.followPath(shootToHuman3);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.humanToShoot3);
                    startedState = false;
                }
                break;
            case humanToShoot3:
                if (!startedState) {
                    follower.followPath(humanToShoot3);
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








