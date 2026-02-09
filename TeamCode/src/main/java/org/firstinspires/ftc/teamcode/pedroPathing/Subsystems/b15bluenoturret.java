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

@Autonomous(name = "actual blue 15", group = "Autonomous")
@Configurable // Panels
public class b15bluenoturret extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    private ShooterSubSystem shooter;
    public Follower follower; // Pedro Pathing follower instance

    public boolean startedState = false;

    private enum pathState {
        startToShoot,
        shoot1,
        shootRotate,
        rotateToFirst,
        firstToGate,
        gateToZone,
        zoneShootRotate,
        shoot2,
        rotateToMiddle,
        middleToZone,
        shoot3,
        zoneToLast,
        lastToZone,
        shoot4,
        shootToHP,
        hpToShoot,
        shoot5,
        leave


    } // Current autonomous path state (state machine)

    pathState pathstate;
    private Timer opModeTimer;

    public PathChain startToShoot;
    public PathChain shootRotate;
    public PathChain rotateToFirst;
    public PathChain firstToGate;
    public PathChain gateToZone;
    public PathChain zoneShootRotate;
    public PathChain rotateToMiddle;
    public PathChain middleToZone;
    public PathChain zoneToLast;
    public PathChain lastToZone;
    public PathChain shootToHP;
    public PathChain hpToShoot;
    public PathChain leave;

//    public static GoalId blue;

    public void buildPaths(Follower follower) {
        startToShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(14.756, 112.044), new Pose(54.222, 100.556))
                )
                .setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(323-90))
                .build();

        shootRotate = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(54.222, 83.556), new Pose(54.222, 83.556))
                )
                .setLinearHeadingInterpolation(Math.toRadians(323-90), Math.toRadians(180))
                .build();

        rotateToFirst = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(54.222, 83.556), new Pose(24.133, 83.556))
                )
                .setTangentHeadingInterpolation()
                .build();

        gateToZone = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(24.133, 83.556), new Pose(48.711, 87.467))
                )
                .setConstantHeadingInterpolation(Math.toRadians(325-90))
                .build();

        zoneShootRotate = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(48.711, 87.467), new Pose(50.711, 89.467))
                )
                .setLinearHeadingInterpolation(Math.toRadians(200), Math.toRadians(323))
                .build();

        rotateToMiddle = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(50.711, 89.467),
                                new Pose(51.9111111111111, 66.48888888888888),
                                new Pose(16.000, 56.5)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();
//        firstToGate = follower
//                .pathBuilder()
//                .addPath(
//                        new BezierCurve(
//                                new Pose(16.000, 54.133),
//                                new Pose(35.866666666666667, 80.19999999999999),
//                                new Pose(15.644, 69)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
//                .build();

        middleToZone = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(16.000, 56.500),
                                new Pose(19.512, 79.311),
                                new Pose(54.044, 87.466)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(235))

                .build();
        zoneToLast = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(54.044, 87.466),
                                new Pose(80.355, 26.555),
                                new Pose(17.344, 35.444)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();
        lastToZone = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(17.344, 35.444), new Pose(45, 95))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(235))
                .build();
        shootToHP = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(45.000, 95.000),
                                new Pose(6.044, 63.822),
                                new Pose(11.733, 12.533)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        hpToShoot = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(11.733, 12.533),

                                new Pose(54.444, 110.156)
                        )
                ).setTangentHeadingInterpolation()
                .setReversed()
                .build();

        leave = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(57.422, 85.156), new Pose(53.333, 63.822))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
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
        shooter = new ShooterSubSystem(hardwareMap);
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(14.755, 112.044, Math.toRadians(270)));
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
        telemetry.addData("speed", shooter.getShooterVelocity());
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
                    shooter.flywheelInit();
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
            case rotateToFirst:
                if (!startedState) {
                    follower.followPath(rotateToFirst);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.gateToZone);
                    startedState = false;
                }
                break;

            case gateToZone:
                if (!startedState) {
                    follower.followPath(gateToZone);
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
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 1.9) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.rotateToMiddle);
                }
                break;
            case rotateToMiddle:
                if (!startedState) {
                    //shooter.flywheelInit();
                    follower.followPath(rotateToMiddle);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.middleToZone);
                    startedState = false;
                }
                break;
            case firstToGate:
                if (!startedState) {
                    follower.followPath(firstToGate);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.middleToZone);
                    startedState = false;
                }
                break;
            case middleToZone:
                if (!startedState) {
                    follower.followPath(middleToZone);
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
                    setPathState(pathState.zoneToLast);
                }
                break;
            case zoneToLast:
                if (!startedState) {
                    follower.followPath(zoneToLast);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.lastToZone);
                    startedState = false;
                }
                break;
            case lastToZone:
                if (!startedState) {
                    follower.followPath(lastToZone);
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
                if(pathTimer.seconds() > 2.4) {
                    shooter.stopShoot();
                    shooter.intake();
                    setPathState(pathState.shootToHP);
                }
                break;
            case shootToHP:
                if (!startedState) {
                    follower.followPath(shootToHP);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                   // follower.startTeleOpDrive();
                    setPathState(pathState.hpToShoot);
                    startedState = false;
                }
                break;
            case hpToShoot:
                if (!startedState) {
                    shooter.stopIntake();
                    follower.followPath(hpToShoot);
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
            case leave:
                if (!startedState) {
                    follower.followPath(zoneToLast);
                    startedState = true;
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








