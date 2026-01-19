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

@Autonomous(name = "red far no spike 67", group = "Autonomous")
@Configurable // Panels
public class redfarnospike extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance

    private ShooterSubSystemRed shooter;
    public Follower follower; // Pedro Pathing follower instance

    public boolean startedState = false;

    private enum pathState {
        shootPreLoad,
        shoot1,
        goHumanPlayerIntakeOne,
        shootIntakeOne,
        shoot2,
        goHumanPlayerIntakeTwo,
        shootIntakeTwo,
        shoot3,
        goHumanPlayerIntakeThree,
        shootIntakeThree,
        shoot4,
        leave
    } // Current autonomous path state (state machine)

    pathState pathstate;
    private Timer opModeTimer;
    public PathChain shootPreLoad;
    public PathChain goHumanPlayerIntakeOne;
    public PathChain shootIntakeOne;
    public PathChain goHumanPlayerIntakeTwo;
    public PathChain shootIntakeTwo;
    public PathChain goHumanPlayerIntakeThree;
    public PathChain shootIntakeThree;
    public PathChain leave;
//    public static GoalId red;
    public void buildPaths(Follower follower) {
        shootPreLoad = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(87.589, 7.664),

                                new Pose(85.909, 19.197)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(70))

                .build();

        goHumanPlayerIntakeOne = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(85.909, 19.197),

                                new Pose(134.925, 8.632)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shootIntakeOne = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(134.925, 8.632),

                                new Pose(86.076, 19.310)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(-12), Math.toRadians(70))

                .build();

        goHumanPlayerIntakeTwo = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(86.076, 19.310),

                                new Pose(135.021, 13.401)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shootIntakeTwo = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(135.021, 13.401),

                                new Pose(86.172, 19.177)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(-8), Math.toRadians(70))

                .build();

        goHumanPlayerIntakeThree = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(86.172, 19.177),

                                new Pose(134.680, 20.800)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shootIntakeThree = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(134.680, 20.800),

                                new Pose(85.961, 19.266)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(2), Math.toRadians(70))

                .build();

        leave = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(85.961, 19.266),

                                new Pose(86.064, 29.439)
                        )
                ).setTangentHeadingInterpolation()

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
        follower.setStartingPose(new Pose(87.589, 7.664, Math.toRadians(90)));
        pathTimer = new ElapsedTime();

        buildPaths(follower); // Build paths
        pathstate = pathState.shootPreLoad;
        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);

//        red.idNum = 20;
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
            case shootPreLoad:
                if (!startedState) {
                    follower.followPath(shootPreLoad);
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
                    setPathState(pathState.goHumanPlayerIntakeOne);
                }
                break;
            case goHumanPlayerIntakeOne:
                if (!startedState) {
                    follower.followPath(goHumanPlayerIntakeOne);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.shootIntakeOne);
                    startedState = false;
                }
                break;
            case shootIntakeOne:
                if (!startedState) {
                    follower.followPath(shootIntakeOne);
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
                    setPathState(pathState.goHumanPlayerIntakeTwo);
                }
                break;
            case goHumanPlayerIntakeTwo:
                if (!startedState) {
                    follower.followPath(goHumanPlayerIntakeTwo);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.shootIntakeTwo);
                    startedState = false;
                }
                break;
            case shootIntakeTwo:
                if (!startedState) {
                    follower.followPath(shootIntakeTwo);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.shoot3);
                    startedState = false;
                    follower.startTeleOpDrive();
                }
                break;
            case shoot3:
                turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 2) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.goHumanPlayerIntakeThree);
                }
                break;
            case goHumanPlayerIntakeThree:
                if (!startedState) {
                    follower.followPath(goHumanPlayerIntakeThree);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.shootIntakeThree);
                    startedState = false;
                }
                break;
            case shootIntakeThree:
                if (!startedState) {
                    follower.followPath(shootIntakeThree);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    shooter.stopIntake();
                    setPathState(pathState.shoot4);
                    follower.startTeleOpDrive();
                    startedState = false;
                }
                break;
            case shoot4:
                turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 2) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.leave);
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
        pathTimer.reset();
        //switch for autonomous path update after reset for stuff that only happens once (such as intake running)
    }
}








