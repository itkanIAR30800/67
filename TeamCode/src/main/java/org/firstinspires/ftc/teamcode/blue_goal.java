/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/*
 * This OpMode illustrates the concept of driving a path based on time.
 * The code is structured as a LinearOpMode
 *
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: RobotAutoDriveByEncoder;
 *
 *   The desired path in this example is:
 *   - Drive forward for 3 seconds
 *   - Spin right for 1.3 seconds
 *   - Drive Backward for 1 Second
 *
 *  The code is written in a simple form with no optimizations.
 *  However, there are several ways that this type of sequence could be streamlined,
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@Autonomous(name="blue auton", group="Robot")
//@Disabled
public class blue_goal extends LinearOpMode {

    /* Declare OpMode members. *//*
    private DcMotor         leftDrive   = null;
    private DcMotor         rightDrive  = null;*/

    private DcMotor intakeMotor = null; //intake motor

    //shooter variables
    private DcMotor shooterRightMotor = null;
    private DcMotor shooterLeftMotor = null;
    private Servo transferServoLeft = null;
    private Servo transferServoRight = null;
    private Servo transferArm = null;
    int amount_of_motors = 4;
    private DcMotor[]motors = new DcMotor[amount_of_motors];
    private String[]motordirections = {"front_left_drive",
            "back_left_drive",
            "front_right_drive",
            "back_right_drive"};
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     FORWARD_SPEED = 0.52;
    static final double     TURN_SPEED    = 0.5;

    @Override
    public void runOpMode() {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        shooterRightMotor = hardwareMap.get(DcMotor.class, "shooterRightMotor");
        shooterLeftMotor = hardwareMap.get(DcMotor.class, "shooterLeftMotor");
        transferServoRight = hardwareMap.get(Servo.class, "transferServoRight");
        transferServoLeft = hardwareMap.get(Servo.class, "transferServoLeft");
        transferArm = hardwareMap.get(Servo.class, "transferArm");


        // Initialize the drive system variables.
        for (int i = 0; i < amount_of_motors; i++)
        {
            motors[i] = hardwareMap.get(DcMotor.class, motordirections[i]);
        }
//        transferServoRight.setDirection((Servo.Direction.FORWARD));
//        transferServoLeft.setDirection((Servo.Direction.REVERSE));
//        transferServoRight.setPosition(1.0);
//        transferServoLeft.setPosition(0.0); //this is actually right??

        /*leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");*/

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        for (int i = 0; i < 2; i++)
        {
            motors[i].setDirection(DcMotor.Direction.FORWARD);
        }

        for (int i = 2; i < 4; i++)
        {
            motors[i].setDirection(DcMotor.Direction.REVERSE);
        }

        /*leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);*/

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses START)
        waitForStart();

        // Step through each leg of the path, ensuring that the OpMode has not been stopped along the way.

        // Step 1:  back up for 0.75 seconds

        /*leftDrive.setPower(FORWARD_SPEED);
        rightDrive.setPower(FORWARD_SPEED);*/

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.8)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
            for (int i = 0; i < amount_of_motors; i++)
            {
                motors[i].setPower(-FORWARD_SPEED);
            }
        }

        for (int i = 0; i < amount_of_motors; i++)
        {
            motors[i].setPower(0.0);
        }
        sleep(1000);

        //secret step 76: 2 shooting cycles
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 4.0)) {
            telemetry.addData("shooting", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
            //transfer
            shooterLeftMotor.setDirection((DcMotorSimple.Direction.FORWARD));
            shooterRightMotor.setDirection((DcMotorSimple.Direction.REVERSE));
            shooterLeftMotor.setPower(1.0);
            shooterRightMotor.setPower(1.0);
//            transferServoRight.setPosition(1.0);
//            transferServoLeft.setPosition(0.0);
            //push
            sleep(1000);
            transferArm.setDirection((Servo.Direction.REVERSE));
            transferArm.setPosition(1.0);
            //shoot

            telemetry.addData("shots fired", "Leg 1: %4.1f S Elapsed", runtime.seconds());
        }

        // Step 2:  Spin left for 0.5 seconds (strafe)

        /*
        leftDrive.setPower(TURN_SPEED);
        rightDrive.setPower(-TURN_SPEED);*/
//        runtime.reset();
//        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
//            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
//            telemetry.update();
//            for (int i = 0; i < 3; i++)
//            {
//                motors[i].setPower(-TURN_SPEED);
//            }
//
//            for (int i = 2; i < 4; i++)
//            {
//                motors[i].setPower(TURN_SPEED);
//            }
//        }
//        sleep(1000);

        // Step 3:  Drive Backward for 1 Second

        /*
        leftDrive.setPower(-FORWARD_SPEED);
        rightDrive.setPower(-FORWARD_SPEED);*/
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) //TODO: if this is actually strafe, the directions need to be changed for blue goal
        {
            telemetry.addData("Path", "Leg 3: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
            for (int i = 0; i < 3; i++)
            {
                motors[i*3].setPower(-TURN_SPEED);
                motors[i*3].setDirection(DcMotorSimple.Direction.REVERSE);
            }
            for (int i = 1; i < 3; i++)
            {
                motors[i].setPower(TURN_SPEED);
                motors[i].setDirection(DcMotorSimple.Direction.FORWARD);
            }
        }

        // Step 4:  Stop
        for (int i = 0; i < amount_of_motors; i++)
        {
            motors[i].setPower(0);
        }

        /*leftDrive.setPower(0);
        rightDrive.setPower(0);*/
        intakeMotor.setPower(0.0);

        for (int i = 0; i < amount_of_motors; i++) //locks motors
        {
            motors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
