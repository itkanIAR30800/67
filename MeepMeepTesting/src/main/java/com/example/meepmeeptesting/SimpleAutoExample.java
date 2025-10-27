package com.example.meepmeeptesting;

import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

/**
 * SIMPLEST MEEPMEEP EXAMPLE - DECODE Game
 *
 * Realistic autonomous starting from Blue GOAL side:
 * 1. Start at Blue GOAL (large launch zone)
 * 2. Move forward to score pre-loaded artifacts
 * 3. Leave the launch line (3 pts)
 * 4. Navigate to spike mark to collect artifact
 * 5. Return to score in GOAL
 * 6. Return to BASE for end-game bonus
 *
 * How to use:
 * 1. Right-click this file → Run 'SimpleAutoExample.main()'
 * 2. Watch the robot follow the path in the visualization
 * 3. Once satisfied, copy the poses and trajectory to SimpleAuto.java in TeamCode
 */
public class SimpleAutoExample {

    // STEP 1: DEFINE YOUR POSES
    // Field coordinates: (0,0) is center
    // Blue GOAL is at BOTTOM LEFT (-60, -60), Red GOAL is at TOP RIGHT (60, 60)
    public static EditablePose
        start = new EditablePose(-36, -60, Math.toRadians(90)),      // Blue GOAL side, large launch zone, facing UP
        scorePos = new EditablePose(-36, -36, Math.toRadians(90)),   // Move forward (up) to score
        spikeNear = new EditablePose(-48, -24, Math.toRadians(90)),  // Near spike mark (audience side)
        scoreAgain = new EditablePose(-36, -36, Math.toRadians(90)), // Return to score position
        base = new EditablePose(-60, -60, Math.toRadians(45));       // BASE zone for end-game (bottom left corner)

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set these to match your MecanumDrive.PARAMS
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        // STEP 2: BUILD YOUR TRAJECTORY
        // This demonstrates a realistic DECODE autonomous routine
        myBot.runAction(myBot.getDrive().actionBuilder(start.toPose2d())
                // Score pre-loaded artifacts in GOAL
                .strafeToLinearHeading(scorePos.toVector2d(), scorePos.heading)
                .waitSeconds(0.5)  // Simulate shooting/scoring action

                // Leave launch line (earns 3 pts)
                .strafeToLinearHeading(spikeNear.toVector2d(), spikeNear.heading)
                .waitSeconds(0.5)  // Simulate picking up artifact from spike mark

                // Return to score the collected artifact
                .strafeToLinearHeading(scoreAgain.toVector2d(), scoreAgain.heading)
                .waitSeconds(0.5)  // Simulate scoring again

                // Return to BASE zone for end-game points
                .strafeToLinearHeading(base.toVector2d(), base.heading)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}

