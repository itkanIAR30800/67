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

@Autonomous(name = "FINAL RED run ts boi", group = "Autonomous")
@Configurable // Panels
public class REALredleavescrim extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance

    private ShooterSubSystemRed shooter;
    public Follower follower; // Pedro Pathing follower instance

    public boolean startedState = false;

    private enum pathState {
        leave,
        stall


    } // Current autonomous path state (state machine)

    pathState pathstate;
    private Timer opModeTimer;

    public PathChain leave;
    public PathChain stall;

    public Pose leaveStart = new Pose(51.022, 96.356);
    public Pose leaveEnd = new Pose(51.378, 71.289);

    public Pose stallStart = new Pose();
    public Pose stallEnd = new Pose();



    public void buildPaths(Follower follower) {
        mirrorRed();
        leave = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(leaveStart, leaveEnd)
                )
                .setLinearHeadingInterpolation(Math.toRadians(323 - 90), Math.toRadians(180 - 90))
                .build();

        stall = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(stallStart, stallEnd)
                )
                .setTangentHeadingInterpolation()
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
        follower.setStartingPose(new Pose((72-23.46666666666667+72), 127.11111111111111, Math.toRadians(90-90)));
        pathTimer = new ElapsedTime();

        buildPaths(follower); // Build paths
        pathstate = pathState.leave;
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
            case leave:
                double turnPower = shooter.updateShootAndAlign();
                follower.setTeleOpDrive(0, 0, turnPower);
                if(pathTimer.seconds() > 2) {
                    shooter.intake();
                    shooter.stopShoot();
                    setPathState(pathState.leave);
                }
                break;

            case stall:
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
        leaveStart = leaveStart.mirror();
        leaveEnd = leaveEnd.mirror();
        stallStart = stallStart.mirror();
        stallEnd = stallEnd.mirror();
    }
}








