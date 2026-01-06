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

@Autonomous(name = "run ts boi", group = "Autonomous")
@Configurable // Panels
public class othertuffred12 extends OpMode {

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
        gateToZone,
        zoneShootRotate,
        shoot2,
        rotateToMiddle,
        middleToZone,
        shoot3,
        zoneToLast,
        lastToZone,
        shoot4,
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
    public PathChain leave;

    public Pose startToShootStart = new Pose(14.756, 112.044);
    public Pose startToShootEnd = new Pose(62.222, 83.556);
    public Pose shootRotateStart = new Pose(62.222, 83.556);
    public Pose shootRotateEnd = new Pose(62.222, 83.556);
    public Pose rotateToFirstStart = new Pose(62.222, 83.556);
    public Pose rotateToFirstEnd = new Pose(18.133, 83.556);
    public Pose firstToGate1 = new Pose(18.133, 83.556);
    public Pose firstToGate2 = new Pose(29.867, 75.200);
    public Pose firstToGate3 = new Pose(15.644, 75);
    public Pose gateToZoneStart = new Pose(16.889, 75.200);
    public Pose gateToZoneEnd = new Pose(48.711, 87.467);
    public Pose zoneShootRotateStart = new Pose(48.711, 87.467);
    public Pose zoneShootRotateEnd = new Pose(48.711, 87.467);
    public Pose rotateToMiddle1 = new Pose(48.711, 87.467);
    public Pose rotateToMiddle2 = new Pose(66.488, 57.066);
    public Pose rotateToMiddle3 = new Pose(11.733333333333334, 55);
    public Pose middleToZoneStart = new Pose(11.733333333333334, 55);
    public Pose middleToZoneEnd = new Pose(54.044, 87.466);
    public Pose zoneToLast1 = new Pose(54.044, 87.466);
    public Pose zoneToLast2 = new Pose(80.355, 27.555);
    public Pose zoneToLast3 = new Pose(10.844, 36.444);
    public Pose lastToZoneStart = new Pose(10.844, 36.444);
    public Pose lastToZoneEnd = new Pose(57.422, 85.155);
    public Pose leaveStart = new Pose(57.422, 85.156);
    public Pose leaveEnd = new Pose(53.333, 63.822);

    public void buildPaths(Follower follower) {
        mirror();
        startToShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(startToShootStart, startToShootEnd)
                )
                .setLinearHeadingInterpolation(Math.toRadians(270 - 90), Math.toRadians(323- 90))
                .build();

        shootRotate = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(shootRotateStart, shootRotateEnd)
                )
                .setLinearHeadingInterpolation(Math.toRadians(323- 90), Math.toRadians(180-90))
                .build();

        rotateToFirst = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(rotateToFirstStart, rotateToFirstEnd)
                )
                .setTangentHeadingInterpolation()
                .build();
        firstToGate = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                firstToGate1,
                                firstToGate2,
                                firstToGate3
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        gateToZone = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(gateToZoneStart, gateToZoneEnd)
                )
                .setLinearHeadingInterpolation(Math.toRadians(180- 90), Math.toRadians(200- 90))
                .build();

        zoneShootRotate = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(zoneShootRotateStart, zoneShootRotateEnd)
                )
                .setLinearHeadingInterpolation(Math.toRadians(200- 90), Math.toRadians(323- 90))
                .build();


//        firstToGate = follower
//                .pathBuilder()
//                .addPath(
//                        new BezierCurve(
//                                new Pose(18.133, 83.556),
//                                new Pose(29.866666666666667, 75.19999999999999),
//                                new Pose(15.644, 70.222)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
//                .build();
//
//        gateToZone = follower
//                .pathBuilder()
//                .addPath(
//                        new BezierLine(new Pose(15.644, 70.222), new Pose(48.711, 87.467))
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(325))
//                .build();
//
//        zoneShootRotate = follower
//                .pathBuilder()
//                .addPath(
//                        new BezierLine(new Pose(48.711, 87.467), new Pose(48.711, 87.467))
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(330))
//                .build();

        rotateToMiddle = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                rotateToMiddle1,
                                rotateToMiddle2,
                                rotateToMiddle3
                        )
                )
                .setTangentHeadingInterpolation()
                .build();


        middleToZone = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(middleToZoneStart, middleToZoneEnd)
                )
                .setLinearHeadingInterpolation(Math.toRadians(180- 90), Math.toRadians(323+180- 90))
                .setReversed()
                .build();

        zoneToLast = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                zoneToLast1,
                                zoneToLast2,
                                zoneToLast3
                        )
                )
                .setTangentHeadingInterpolation()
                .build();
        lastToZone = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(lastToZoneStart, lastToZoneEnd)
                )
                .setLinearHeadingInterpolation(Math.toRadians(180- 90), Math.toRadians(327 +180- 90))
                .setReversed()
                .build();
        leave = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(leaveStart, leaveEnd)
                )
                .setConstantHeadingInterpolation(Math.toRadians(180- 90))
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
        follower.setStartingPose(new Pose(129.245, 112.044, Math.toRadians(270-90)));
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
                    shooter.stopIntake();
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
                if(pathTimer.seconds() > 2) {
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
                if(pathTimer.seconds() > 2) {
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
                if(pathTimer.seconds() > 2) {
                    shooter.stopShoot();
                    shooter.stopIntake();
                }
                break;
            case leave:
                if (!startedState) {
                    follower.followPath(leave);
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

    public void mirror() {

        startToShootStart = startToShootStart.mirror();
        startToShootEnd = startToShootEnd.mirror();
        shootRotateStart =         shootRotateStart.mirror();
        shootRotateEnd =         shootRotateEnd.mirror();
        rotateToFirstStart = rotateToFirstStart.mirror();
        rotateToFirstEnd = rotateToFirstEnd.mirror();
        firstToGate1 = firstToGate1.mirror();
        firstToGate2 = firstToGate2.mirror();
        firstToGate3 = firstToGate3.mirror();
        gateToZoneStart = gateToZoneStart.mirror();
        gateToZoneEnd = gateToZoneEnd.mirror();
        gateToZoneStart = gateToZoneStart.mirror();
        gateToZoneEnd = gateToZoneEnd.mirror();
        zoneShootRotateStart = zoneShootRotateStart.mirror();
        zoneShootRotateEnd = zoneShootRotateEnd.mirror();
        rotateToMiddle1 = rotateToMiddle1.mirror();
        rotateToMiddle2 = rotateToMiddle2.mirror();
        rotateToMiddle3 = rotateToMiddle3.mirror();
        middleToZoneStart = middleToZoneStart.mirror();
        middleToZoneEnd = middleToZoneEnd.mirror();
        zoneToLast1 = zoneToLast1.mirror();
        zoneToLast2 = zoneToLast2.mirror();
        zoneToLast3 = zoneToLast3.mirror();
        lastToZoneStart = lastToZoneStart.mirror();
        lastToZoneEnd = lastToZoneEnd.mirror();
        leaveStart = leaveStart.mirror();
        leaveEnd = leaveEnd.mirror();

    }
}








