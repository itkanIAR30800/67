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

@Autonomous(name = "12 jiar red qual", group = "Autonomous")
@Configurable // Panels
public class jiarredqual extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    private ShooterSubSystemRed shooter;
    public Follower follower; // Pedro Pathing follower instance

    public boolean startedState = false;

    private enum pathState {
        startToShoot,
        shoot1,
        shootRotate,
        rotateToFirst,
        firstToGate,
        firstToZone,
        zoneShootRotate,
        shoot2,
        rotateToMiddle,
        middleToGate,
        gateToZone,
        shoot3,
        shootToGateIntake,
        wait,
        gateIntakeToShoot,
        shoot4,
        leave


    } // Current autonomous path state (state machine)

    pathState pathstate;
    private Timer opModeTimer;

    public PathChain startToShoot;
    public PathChain shootRotate;
    public PathChain rotateToFirst;
    public PathChain firstToGate;
    public PathChain firstToZone;
    public PathChain zoneShootRotate;
    public PathChain rotateToMiddle;
    public PathChain middleToGate;
    public PathChain gateToZone;
    public PathChain shootToGateIntake;
    public PathChain gateIntakeToShoot;
    public PathChain leave;

//    public static GoalId blue;

    public void buildPaths(Follower follower) {
        startToShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(129.244, 112.044), new Pose(89.778, 83))
                )
                .setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(180-323))
                .build();

        shootRotate = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(89.778, 83), new Pose(89.778, 83))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180-323+(-180)), Math.toRadians(180-180 ))
                .build();

        rotateToFirst = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(89.778, 83), new Pose(122.867, 82.556))
                )
                .setTangentHeadingInterpolation()
                .build();
        firstToGate = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(89.778, 83),
                                new Pose(97, 67.48888888888888),
                                new Pose(128.750, 75))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
        firstToZone = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(122.867, 82.556), new Pose(95.289, 87.467))
                )
                .setConstantHeadingInterpolation(Math.toRadians(30+180))
                .build();

        zoneShootRotate = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(95.289, 87.467), new Pose(95.289, 87.467))
                )
                .setLinearHeadingInterpolation(Math.toRadians(30+180), Math.toRadians(30-90))
                .build();

        rotateToMiddle = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(95.289, 87.467),
                                new Pose(92.0889, 65.48888888888888),
                                new Pose(128.750, 54)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();
        middleToGate = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(128.750, 54),
                                new Pose(97, 67.48888888888888),
                                new Pose(128.750, 75)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
        gateToZone = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(128.750, 75), new Pose(89.956, 105.466))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180-180), Math.toRadians(180-323))
                .build();
        shootToGateIntake = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(89.956, 87.466),
                                new Pose(93.223, 30.533),
                                new Pose(129.556, 59.756)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        gateIntakeToShoot = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(129.556, 59.756),
                                new Pose(105.722, 48.722),
                                new Pose(89.3111111111111, 113.88888888888889)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(31), Math.toRadians(208))

                .build();
        leave = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(89.578, 85.156), new Pose(90.667, 63.822))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180-180))
                .build();
//        lastToZone = follower
//                .pathBuilder()
//                .addPath(
//                        new BezierLine(
//                                new Pose(11.022, 35.911),
//                                new Pose(58.311, 103.644)
//                        )
//                )
//                .setConstantHeadingInterpolation(110)
//                .setReversed()
//                .build();

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
        follower.setStartingPose(new Pose(129.244, 112.044, Math.toRadians(270)));
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
//                    shooter.lockTurret();
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
                if(pathTimer.seconds() > 3) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.rotateToFirst);
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
                    setPathState(pathState.firstToGate);
                    startedState = false;
                }
                break;
            case firstToGate:
                if (!startedState) {
                    follower.followPath(firstToGate);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.firstToZone);
                    startedState = false;
                }
                break;
            case firstToZone:
                if (!startedState) {
                    follower.followPath(firstToZone);
                    shooter.flywheelInit();
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


            case zoneShootRotate:
                if (!startedState) {
                    follower.followPath(zoneShootRotate);
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
                if(pathTimer.seconds() > 3) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.rotateToMiddle);
                }
                break;
            case rotateToMiddle:
                if (!startedState) {
                    follower.followPath(rotateToMiddle);
                    startedState = true;
                }
                if (!follower.isBusy()) {
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
                    follower.startTeleOpDrive();
                    setPathState(pathState.shoot3);
                    startedState = false;
                }
                break;

            case shoot3:
                turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 3) {
                    shooter.stopShoot();
                    shooter.intake();
                    setPathState(pathState.shootToGateIntake);
                }
                break;
//            case shootToGateIntake:
//                if (!startedState) {
//                    follower.followPath(shootToGateIntake);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    shooter.flywheelInit();
//                    setPathState(pathState.wait);
//                    startedState = false;
//                }
//                break;
//            case wait:
//                if (!startedState) {
//                    shooter.intake();
//                    if(pathTimer.seconds() > 3)
//                        setPathState(pathState.gateIntakeToShoot);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    follower.startTeleOpDrive();
//                    setPathState(pathState.gateIntakeToShoot);
//                    startedState = false;
//                }
//            case gateIntakeToShoot:
//                if (!startedState) {
//                    follower.followPath(gateIntakeToShoot);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    follower.startTeleOpDrive();
//                    setPathState(pathState.shoot4);
//                    startedState = false;
//                }
//                break;
//            case shoot4:
//                turnPower = shooter.updateShootAndAlign();
//                follower.setTeleOpDrive(0, 0, turnPower);
//                if(pathTimer.seconds() > 3) {
//                    shooter.stopShoot();
//                    shooter.intake();
//                    setPathState(pathState.leave);
//                }
//                break;
//            case leave:
//                if (!startedState) {
//                    follower.followPath(leave);
//                    startedState = true;
//                }
//                break;

        }


    }

    public void setPathState(pathState pState) {
        pathstate = pState;
        startedState = false;
        pathTimer.reset();
        //switch for autonomous path update after reset for stuff that only happens once (such as intake running)
    }
}
