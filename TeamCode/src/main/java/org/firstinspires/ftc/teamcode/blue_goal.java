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
import com.acmerobotics.roadrunner.Pose2d;

@Autonomous(name="blue auton goal", group="Robot")
//@Disabled
public class blue_goal extends LinearOpMode {

    private DcMotor intakeMotor = null; //first intake motor
    private DcMotor intakeTransferMotor = null; //second intake motor
    private DcMotor shooterMotor = null;
    private Servo hoodServo = null;
    private DcMotor turretMotor = null;

    private DcMotor frontLeftDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backRightDrive = null;
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     FORWARD_SPEED = 0.52;
    static final double     TURN_SPEED    = 0.5;

    @Override
    public void runOpMode() {

        hardwareInit();

        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses START)
        waitForStart();

        // Step through each leg of the path, ensuring that the OpMode has not been stopped along the way.

        // Step 1:  back up for 0.75 seconds
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.75)) { //use variable not numbers
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
            frontLeftDrive.setPower(-FORWARD_SPEED);
            backLeftDrive.setPower(-FORWARD_SPEED);
            frontRightDrive.setPower(-FORWARD_SPEED);
            backRightDrive.setPower(-FORWARD_SPEED);
        }

        frontLeftDrive.setPower(0.0);
        backLeftDrive.setPower(0.0);
        frontRightDrive.setPower(0.0);
        backRightDrive.setPower(0.0);
        sleep(1000);

        // Step 2:   2 shooting cycles
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 4.0)) {
            telemetry.addData("shooting", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
            //transfer
            shooterMotor.setDirection((DcMotorSimple.Direction.FORWARD));
            shooterMotor.setPower(1.0);
            sleep(1000);
            //shoot
            telemetry.addData("shots fired", "Leg 1: %4.1f S Elapsed", runtime.seconds());
        }

        // Step 3:  Spin left for 0.5 seconds (strafe)
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
            frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
            backLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
            frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
            backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

            frontLeftDrive.setPower(FORWARD_SPEED);
            frontRightDrive.setPower(FORWARD_SPEED);
            backLeftDrive.setPower(-FORWARD_SPEED);
            backRightDrive.setPower(-FORWARD_SPEED);

            frontLeftDrive.setPower(0.0);
            backLeftDrive.setPower(0.0);
            frontRightDrive.setPower(0.0);
            backRightDrive.setPower(0.0);

        }
        sleep(1000);


        // Step 4:  Stop

        frontLeftDrive.setPower(0.0);
        backLeftDrive.setPower(0.0);
        frontRightDrive.setPower(0.0);
        backRightDrive.setPower(0.0);


        intakeMotor.setPower(0.0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);

        // Step 5: revolve and drive to artifacts cycle 1
        //START OF AMINAS CODE(ADDED SECTION!!)

//        runtime.reset();
//        while (opModeIsActive() && runtime.seconds() < 4.2)
//        {
//            //revolve
//            frontLeftDrive.setPower(-FORWARD_SPEED);
//            backLeftDrive.setPower(-FORWARD_SPEED);
//            frontRightDrive.setPower(FORWARD_SPEED);
//            backRightDrive.setPower(FORWARD_SPEED);
//            frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
//            frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
//            backLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
//            backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
//
//            sleep(800);
//
//            //drive forward to collect artifacts
//            frontLeftDrive.setPower(FORWARD_SPEED);
//            backLeftDrive.setPower(-FORWARD_SPEED);
//            frontRightDrive.setPower(-FORWARD_SPEED);
//            backRightDrive.setPower(FORWARD_SPEED);
//            frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
//            frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
//            backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
//            backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
//
//            sleep(1300);
//
//            //backwards to line
//            frontLeftDrive.setPower(-FORWARD_SPEED);
//            backLeftDrive.setPower(FORWARD_SPEED);
//            frontRightDrive.setPower(FORWARD_SPEED);
//            backRightDrive.setPower(-FORWARD_SPEED);
//            frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
//            frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
//            backLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
//            backRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
//
//            sleep(1300);
//
//            //revolve back to shoot
//            frontLeftDrive.setPower(FORWARD_SPEED);
//            backLeftDrive.setPower(FORWARD_SPEED);
//            frontRightDrive.setPower(-FORWARD_SPEED);
//            backRightDrive.setPower(-FORWARD_SPEED);
//            frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
//            frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
//            backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
//            backRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
//
//            sleep(800);
//
//        }
//
//    }
//
//    private int amountofmotors = 4;
//    private DcMotor[] motors = new DcMotor[amountofmotors];
//
//    private String[] motorstrings = {"frontLeft", "backLeft", "frontRight", "backRight"};
//
//
//    //function that takes in game state to output motor speed
//    public int motorPower()
//    {
//
//        int game_state = 0;
//        double state_speed = 0.0;
//        switch (game_state)
//        {
//            case 0:
//                state_speed = -FORWARD_SPEED;
//                break;
//            case 1:
//                state_speed = FORWARD_SPEED;
//                break;
//            case 2:
//
//
//        }
//        for (int i = 0; i < amountofmotors; i++) {
//            motors[i].setPower(state_speed);
//        }
//
//        return game_state;
    }
    void hardwareInit() {
        //4 motors, 1 servo
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        shooterMotor = hardwareMap.get(DcMotor.class, "shooterMotor");
        intakeTransferMotor = hardwareMap.get(DcMotor.class, "intakeTransferMotor");
        turretMotor = hardwareMap.get(DcMotor.class, "turretMotor");
        hoodServo = hardwareMap.get(Servo.class, "hoodServo");

        //drivetrain motors
        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backRightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
    }
}

