package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

/**
 * SIMPLEST AUTONOMOUS EXAMPLE - DECODE Game
 *
 * This matches SimpleAutoExample.java in MeepMeepTesting
 *
 * Autonomous sequence:
 * 1. Start at Blue GOAL (large launch zone)
 * 2. Score pre-loaded artifacts in GOAL
 * 3. Leave launch line (3 pts)
 * 4. Collect artifact from spike mark
 * 5. Return to score again
 * 6. Park at BASE (10 pts if fully in BASE)
 *
 * How to use:
 * 1. Copy the pose values from SimpleAutoExample.java in MeepMeepTesting
 * 2. Copy the trajectory code from MeepMeepTesting
 * 3. Deploy to robot and run
 * 4. Tune values in FTC Dashboard if needed (http://192.168.43.1:8080/dash)
 */
@Config
@Autonomous(name = "Blue GOAL Auto", group = "DECODE")
public class SimpleAuto extends LinearOpMode {

    // STEP 1: PASTE POSES FROM MEEPMEEP HERE
    // These match SimpleAutoExample.java in MeepMeepTesting
    // Field coordinates: (0,0) is center
    // Blue GOAL is at BOTTOM LEFT (-60, -60), Red GOAL is at TOP RIGHT (60, 60)
    public static EditablePose
        start = new EditablePose(-36, -60, Math.toRadians(90)),      // Blue GOAL side, large launch zone, facing UP
        scorePos = new EditablePose(-36, -36, Math.toRadians(90)),   // Move forward (up) to score
        spikeNear = new EditablePose(-48, -24, Math.toRadians(90)),  // Near spike mark (audience side)
        scoreAgain = new EditablePose(-36, -36, Math.toRadians(90)), // Return to score position
        base = new EditablePose(-60, -60, Math.toRadians(45));       // BASE zone for end-game (bottom left corner)

    @Override
    public void runOpMode() {
        // Initialize the drive with the starting pose
        MecanumDrive drive = new MecanumDrive(hardwareMap, start.toPose2d());

        // Wait for the driver to press START
        waitForStart();

        if (isStopRequested()) return;

        // STEP 2: PASTE TRAJECTORY FROM MEEPMEEP HERE
        // This matches the exact trajectory from SimpleAutoExample.java
        // Add .stopAndAdd() calls for your subsystem actions (intake, shooter, etc.)
        Action auto = drive.actionBuilder(start.toPose2d())
                // Score pre-loaded artifacts in GOAL
                .strafeToLinearHeading(scorePos.toVector2d(), scorePos.heading)
                // .stopAndAdd(robot.shoot())  // TODO: Add your scoring action here
                .waitSeconds(0.5)

                // Leave launch line (earns 3 pts)
                .strafeToLinearHeading(spikeNear.toVector2d(), spikeNear.heading)
                // .stopAndAdd(robot.intake())  // TODO: Add your intake action here
                .waitSeconds(0.5)

                // Return to score the collected artifact
                .strafeToLinearHeading(scoreAgain.toVector2d(), scoreAgain.heading)
                // .stopAndAdd(robot.shoot())  // TODO: Add your scoring action here
                .waitSeconds(0.5)

                // Return to BASE zone for end-game points (10 pts if fully in BASE)
                .strafeToLinearHeading(base.toVector2d(), base.heading)
                .build();

        // Run the autonomous sequence
        Actions.runBlocking(auto);
    }
}

