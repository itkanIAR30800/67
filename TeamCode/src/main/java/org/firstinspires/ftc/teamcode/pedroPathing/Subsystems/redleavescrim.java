package org.firstinspires.ftc.teamcode.pedroPathing.Subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "run ts boi real fr", group = "Autonomous")
@Configurable // Panels
public class redleavescrim extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance

    private ShooterSubSystemRed shooter;
    public Follower follower; // Pedro Pathing follower instance

    public boolean startedState = false;

    private enum pathState {
        startToShoot,
        shoot1,
        leave,
        leaveStall


    } // Current autonomous path state (state machine)

    pathState pathstate;
    private Timer opModeTimer;

    public PathChain startToShoot;
    public PathChain shootRotate;
    public PathChain leave;
    public PathChain leaveStall;

    public Pose startToShootStart = new Pose(14.756, 112.244);
    public Pose startToShootEnd = new Pose(51.200, 96.533);
    public Pose shootRotateStart = new Pose(51.200, 96.533);
    public Pose shootRotateEnd = new Pose(51.022, 96.356);
    public Pose leaveStart = new Pose(51.022, 96.356);
    public Pose leaveEnd = new Pose(51.378, 71.289);
    public Pose leaveFinalStart = new Pose(51.378, 71.289);
    public Pose leaveFinalEnd = new Pose(51.378, 71.289);



    public void buildPaths(Follower follower) {
        mirrorRed();
        startToShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(startToShootStart, startToShootEnd)
                )
                .setLinearHeadingInterpolation(Math.toRadians(270-90), Math.toRadians(323-90))
                .build();

        shootRotate = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(shootRotateStart, shootRotateEnd)
                )
                .setLinearHeadingInterpolation(Math.toRadians(270-90), Math.toRadians(323-90))
                .build();


        leave = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(leaveStart, leaveEnd)
                )
                .setLinearHeadingInterpolation(Math.toRadians(323-90), Math.toRadians(240-90))
                .build();

        leaveStall = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(leaveFinalStart, leaveFinalEnd)
                )
                .setLinearHeadingInterpolation(Math.toRadians(270-90), Math.toRadians(323-90))
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
        follower.setStartingPose(new Pose((72-14.756+72), (72-112.24444444444444+72), Math.toRadians(270-90)));
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
                    setPathState(pathState.leave);
                }
                break;
            case leave:
                if (!startedState) {
                    follower.followPath(leave);
                    startedState = true;
                }
                if (!follower.isBusy()) {
                    setPathState(pathState.leaveStall);
                    startedState = false;
                }
                break;
            case leaveStall:
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

    public void mirrorRed() {
        startToShootStart = startToShootStart.mirror();
        startToShootEnd = startToShootEnd.mirror();
        shootRotateStart =         shootRotateStart.mirror();
        shootRotateEnd =         shootRotateEnd.mirror();
        leaveStart = leaveStart.mirror();
        leaveEnd = leaveEnd.mirror();
    }
}








