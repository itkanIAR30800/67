package org.firstinspires.ftc.teamcode.pedroPathing.Subsystems;//package org.firstinspires.ftc.teamcode.pedroPathing.Subsystems;
//
//import static java.lang.Thread.sleep;
//
//import com.bylazar.configurables.annotations.Configurable;
//import com.bylazar.telemetry.PanelsTelemetry;
//import com.bylazar.telemetry.TelemetryManager;
//import com.pedropathing.follower.Follower;
//import com.pedropathing.geometry.BezierCurve;
//import com.pedropathing.geometry.BezierLine;
//import com.pedropathing.geometry.Pose;
//import com.pedropathing.paths.PathChain;
//import com.pedropathing.util.Timer;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
//import org.firstinspires.ftc.teamcode.pedroPathing.Subsystems.ShooterSubSystem;
//
//@Autonomous(name = "12 piece NEW", group = "Autonomous")
//@Configurable // Panels
//public class khadijah9red extends OpMode {
//
//    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
//
//    private ShooterSubSystem shooter;
//    public Follower follower; // Pedro Pathing follower instance
//
//    public boolean startedState = false;
//
//    private enum pathState {
//        startToShoot,
//        shoot1,
//        shootCurveToFirst,
//        curveToFirst,
//        firstToShoot,
//        shoot2,
//        shootStraightTothird,
//        curveToThird,
//        curveToIntakeThird,
//        shootThird,
//
//        shoot3,
//        leave,
//
//
//    } // Current autonomous path state (state machine)
//
//    pathState pathstate;
//    private Timer opModeTimer;
//
//    public PathChain startToShoot;
//    public PathChain shootCurveToFirst;
//    public PathChain curveToFirst;
//    public PathChain firstToShoot;
//    public PathChain shootStraightTothird;
//    public PathChain curveToThird;
//    public PathChain curveToIntakeThird;
//    public PathChain shootThird;
//    public PathChain leave;
//
//    public void buildPaths(Follower follower) {
//        startToShoot = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(127.412, 112.177),
//
//                                new Pose(101.825, 102.497)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(-90), Math.toRadians(-120))
//
//                .build();
//
//        shootCurveToFirst = follower.pathBuilder().addPath(
//                        new BezierCurve(
//                                new Pose(101.825, 102.497),
//                                new Pose(89.056, 92.119),
//                                new Pose(101.657, 84.518)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(-120), Math.toRadians(0))
//
//                .build();
//
//        curveToFirst = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(101.657, 84.518),
//
//                                new Pose(127.450, 83.985)
//                        )
//                ).setTangentHeadingInterpolation()
//
//                .build();
//
//        firstToShoot = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(127.450, 83.985),
//
//                                new Pose(86.072, 85.799)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-120))
//
//                .build();
//
//        shootStraightTothird = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(86.072, 85.799),
//
//                                new Pose(88.071, 48.585)
//                        )
//                ).setTangentHeadingInterpolation()
//
//                .build();
//
//        curveToThird = follower.pathBuilder().addPath(
//                        new BezierCurve(
//                                new Pose(88.071, 48.585),
//                                new Pose(87.733, 33.573),
//                                new Pose(103.373, 33.131)
//                        )
//                ).setTangentHeadingInterpolation()
//
//                .build();
//
//        curveToIntakeThird = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(103.373, 33.131),
//
//                                new Pose(126.977, 34.124)
//                        )
//                ).setTangentHeadingInterpolation()
//
//                .build();
//
//        shootThird = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(126.977, 34.124),
//
//                                new Pose(90.977, 89.694)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-120))
//
//                .build();
//
//        leave = follower.pathBuilder().addPath(
//                        new BezierLine(
//                                new Pose(90.977, 89.694),
//
//                                new Pose(88.210, 105.518)
//                        )
//                ).setLinearHeadingInterpolation(Math.toRadians(-120), Math.toRadians(-120))
//
//                .build();
//    }
//
//    ElapsedTime pathTimer;
//
//
//    void enter(pathState next) {
//        pathstate = next;
//        startedState = false;
//    }
//
//    @Override
//    public void init() {
//        shooter = new ShooterSubSystem(hardwareMap);
//        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
//        follower = Constants.createFollower(hardwareMap);
//        follower.setStartingPose(new Pose(14.755, 112.044, Math.toRadians(270)));
//        pathTimer = new ElapsedTime();
//
//        buildPaths(follower); // Build paths
//        pathstate = pathState.startToShoot;
//        panelsTelemetry.debug("Status", "Initialized");
//        panelsTelemetry.update(telemetry);
//    }
//
//    @Override
//    public void loop() {
//        follower.update();
//        autonomousPathUpdate();
//        // Log values to Panels and Driver Station
//        Pose currentPosition = follower.getPose();
//
//        telemetry.addData("pathTimer: ", pathTimer.seconds());
//        telemetry.addData("Started State: ", startedState);
//        telemetry.addData("TX>   ", shooter.tx);
//        panelsTelemetry.debug("Path State", pathstate);
//        panelsTelemetry.debug("X", currentPosition.getX());
//        panelsTelemetry.debug("Y", currentPosition.getY());
//        panelsTelemetry.debug("Heading", currentPosition.getHeading());
//        panelsTelemetry.update(telemetry);
//    }
//    startToShoot,
//    shoot1,
//    shootCurveToFirst,
//    curveToFirst,
//    firstToShoot,
//    shoot2,
//    shootStraightTothird,
//    curveToThird,
//    curveToIntakeThird,
//    shootThird,
//    shoot3,
//    leave,
//    public void autonomousPathUpdate()  {
//        switch (pathstate) {
//            case startToShoot:
//                if (!startedState) {
//                    follower.followPath(startToShoot);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    setPathState(pathState.shoot1);
//                    startedState = false;
//                    follower.startTeleOpDrive();
//                }
//                break;
//            case shoot1:
//                double turnPower = shooter.updateShootAndAlign();
//                follower.setTeleOpDrive(0, 0, turnPower);
//                if (pathTimer.seconds() > 2) {
//                    shooter.intake();
//                    shooter.stopShoot();
//                    setPathState(pathState.shootCurveToFirst);
//                }
//                break;
//            case shootCurveToFirst:
//                if (!startedState) {
//                    follower.followPath(shootCurveToFirst);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    setPathState(pathState.curveToFirst);
//                    startedState = false;
//                }
//                break;
//            case curveToFirst:
//                if (!startedState) {
//                    follower.followPath(curveToFirst);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    shooter.stopIntake();
//                    setPathState(pathState.firstToShoot);
//                    startedState = false;
//                }
//                break;
//            case firstToShoot:
//                if (!startedState) {
//                    follower.followPath(firstToShoot);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    setPathState(pathState.shoot2);
//                    startedState = false;
//                }
//                break;
//
//            case shoot2:
//                double turnPower = shooter.updateShootAndAlign();
//                follower.setTeleOpDrive(0, 0, turnPower);
//                if (pathTimer.seconds() > 2) {
//                    shooter.intake();
//                    shooter.stopShoot();
//                    setPathState(pathState.shootStraightTothird);
//                }
//                break;
//
//            case shootStraightTothird:
//                if (!startedState) {
//                    follower.followPath(shootStraightTothird);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    setPathState(pathState.curveToThird);
//                    startedState = false;
//                }
//                break;
//
//            case curveToThird:
//                if (!startedState) {
//                    follower.followPath(curveToThird);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    setPathState(pathState.curveToIntakeThird);
//                    startedState = false;
//                }
//                break;
//            case curveToIntakeThird:
//                if (!startedState) {
//                    follower.followPath(curveToIntakeThird);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    setPathState(pathState.shootThird);
//                    startedState = false;
//                }
//                break;
//            case shootThird:
//                if (!startedState) {
//                    follower.followPath(shootThird);
//                    startedState = true;
//                }
//                if (!follower.isBusy()) {
//                    follower.startTeleOpDrive();
//                    setPathState(pathState.shoot3);
//                    startedState = false;
//                }
//                break;
//
//            case shoot3:
//                turnPower = shooter.updateShootAndAlign();
//                follower.setTeleOpDrive(0, 0, turnPower);
//                if (pathTimer.seconds() > 2) {
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
//
//
//        }
//
//
//    }
//
//    public void setPathState(pathState pState) {
//        pathstate = pState;
//        startedState = false;
//        pathTimer.reset();
//        //switch for autonomous path update after reset for stuff that only happens once (such as intake running)
//    }
//}
//
//
//
//
//
//
//
//
