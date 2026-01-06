package org.firstinspires.ftc.teamcode.pedroPathing;

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

import org.firstinspires.ftc.teamcode.pedroPathing.Subsystems.ShooterSubSystem;

@Autonomous(name = "boi pedro1", group = "Autonomous")
@Configurable // Panels
public class finalpedro extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance

    private ShooterSubSystem shooter;
    public Follower follower; // Pedro Pathing follower instance

    public boolean startedState = false;

    private enum pathState {
        startToShoot,
        shoot1,
        shootRotate,
        rotateToFirst,
        firstToZone,
        shoot2,
        shootToMiddle,
        middleToGate,
        gateToZone,
        shoot3,
        shootToLast,
        lastRotate,
        lastToZone,
        rotateShootBoi,
        shoot4,
        zoneToHuman,
        humanRotate,
        humanToZone,
        goalRotate,
        shoot5

    } // Current autonomous path state (state machine)

    pathState pathstate;
    private Timer opModeTimer;

    public PathChain startToShoot;
    public PathChain shootRotate;
    public PathChain rotateToFirst;
    public PathChain firstToZone;
    public PathChain shootToMiddle;
    public PathChain middleToGate;
    public PathChain gateToZone;
    public PathChain shootToLast;
    public PathChain lastRotate;
    public PathChain lastToZone;
    public PathChain rotateShootboi;
    public PathChain zoneTohuman;
    public PathChain humanRotate;
    public PathChain humanToZone;
    public PathChain goalRotate;

    public void buildPaths(Follower follower) {
        startToShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(14.756, 110.044), new Pose(62.222, 83.556))
                )
                .setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(323))
                .build();

        shootRotate = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(62.222, 83.556), new Pose(60.800, 83.556))
                )
                .setLinearHeadingInterpolation(Math.toRadians(323), Math.toRadians(180))
                .build();

        rotateToFirst = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(60.800, 83.556), new Pose(18.133, 83.556))
                )
                .setTangentHeadingInterpolation()
                .build();

        firstToZone = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(18.133, 83.556), new Pose(57.244, 86.222))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(323))
                .setReversed()
                .build();

        shootToMiddle = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(57.244, 86.222),
                                new Pose(77.511, 63.822),
                                new Pose(16.711, 57.778)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        middleToGate = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(16.711, 57.778),
                                new Pose(36.800, 64.889),
                                new Pose(16.711, 69.511)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .build();

        gateToZone = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(16.711, 69.511), new Pose(54.400, 88.178))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(335))
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
        follower.setStartingPose(new Pose(14.755, 110.044, Math.toRadians(270)));
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
                }
                break;
            case shoot1:

                    shooter.updateShoot();
                    if(pathTimer.seconds()>2) {

                        setPathState(pathState.shootRotate);
                    }
                    break;
            case shootRotate:
                if (!startedState) {
                    follower.followPath(shootRotate);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.rotateToFirst);
                    startedState = false;
                }
                break;
            case rotateToFirst:
                if (!startedState) {
                    follower.followPath(rotateToFirst);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.firstToZone);
                    startedState = false;
                }
                break;
            case firstToZone:
                if (!startedState) {
                    follower.followPath(firstToZone);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.shoot2);
                    startedState = false;
                }
                break;

            case shoot2:
                while (pathTimer.seconds() < 2) {
                    shooter.updateShoot();
                }

                    shooter.intake();
                    setPathState(pathState.shootToMiddle);

                    break;
            case shootToMiddle:
                if (!startedState) {
                    follower.followPath(shootToMiddle);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.middleToGate);
                    startedState = false;
                }
                break;
            case middleToGate:
                if (!startedState) {
                    follower.followPath(middleToGate);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.gateToZone);
                    startedState = false;
                }
                break;
            case gateToZone:
                if (!startedState) {
                    follower.followPath(gateToZone);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.shoot3);
                    startedState = false;
                }
                break;
            case shoot3:
                while (pathTimer.seconds() < 2) {
                    shooter.updateShoot();
                }
                shooter.intake();
                setPathState(pathState.shootToLast);
                break;
//            case shootToLast:
//                if (!startedState) {
//                    follower.followPath(shootToLast);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    shooter.stopIntake();
//                    setPathState(pathState.lastRotate);
//                    startedState = false;
//                }
//                break;
//            case lastRotate:
//                if (!startedState) {
//                    follower.followPath(lastRotate);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    setPathState(pathState.lastToZone);
//                    startedState = false;
//                }
//                break;
//            case lastToZone:
//                if (!startedState) {
//                    follower.followPath(lastToZone);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    setPathState(pathState.rotateShootBoi);
//                    startedState = false;
//                }
//                break;
//            case shoot4:
//                while (pathTimer.seconds() < 3) {
//                    shooter.updateShoot();
//                }
//
//                    shooter.intake();
//                    setPathState(pathState.zoneToHuman);
//                break;
//            case zoneToHuman:
//                if (!startedState) {
//                    follower.followPath(zoneTohuman);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    shooter.stopIntake();
//                    setPathState(pathState.humanRotate);
//                    startedState = false;
//                }
//                break;
//            case humanRotate:
//                if (!startedState) {
//                    follower.followPath(humanRotate);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    setPathState(pathState.humanToZone);
//                    startedState = false;
//                }
//                break;
//            case goalRotate:
//                if (!startedState) {
//                    follower.followPath(goalRotate);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    setPathState(pathState.goalRotate);
//                    startedState = false;
//                }
//                break;
//            case shoot5:
//                while (pathTimer.seconds() < 2) {
//                    shooter.updateShoot();
//                }
        }


    }

    public void setPathState(pathState pState) {
        pathstate = pState;
        pathTimer.reset();
        //switch for autonomous path update after reset for stuff that only happens once (such as intake running)
    }
}








