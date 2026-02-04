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

@Autonomous(name="tuff test motor", group="Robot")
//@Disabled
public class tuff2 extends LinearOpMode {

    /* Declare OpMode members. *//*
    private DcMotor         leftDrive   = null;
    private DcMotor         rightDrive  = null;*/


    int amount_of_motors = 4;
    private DcMotor[]motors = new DcMotor[amount_of_motors];
    private DcMotor frontLeftDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backRightDrive = null;

    private DcMotor intakeMotor = null;
    private DcMotor shootingMotor = null;

    private ElapsedTime     runtime = new ElapsedTime();

    static final double     FORWARD_SPEED = 0.52;
    static final double     TURN_SPEED    = 0.5;

    @Override
    public void runOpMode() {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        shootingMotor = hardwareMap.get(DcMotor.class, "shootingMotor");

        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backRightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");


        //TODO: determine what shooting velocity and angle is  optimal for shooter
        //TODO: read and take notes on the chatgpt thing + the websites from aaban


        //intake
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeMotor.setPower(0.5);
        sleep(3000);
        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        sleep(3000);
        intakeMotor.setPower(0.0);

        sleep(1000);

        shootingMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        shootingMotor.setPower(0.5);
        sleep(3000);
        shootingMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        sleep(3000);
        shootingMotor.setPower(0.0);

        waitForStart();




        // Initialize the drive system variables.
//        for (int i = 0; i < amount_of_motors; i++)
//        {
//            motors[i] = hardwareMap.get(DcMotor.class, motordirections[i]);
//        }

//        frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
//        backLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
//        frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
//        backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
//
//        frontLeftDrive.setPower(1.0);
//        backLeftDrive.setPower(-1.0);
//        frontRightDrive.setPower(1.0);
//        backRightDrive.setPower(-1.0);
//        sleep(2000);
//        frontLeftDrive.setPower(0.0);
//        backLeftDrive.setPower(0.0);
//        frontRightDrive.setPower(0.0);
//        backRightDrive.setPower(0.0);
    }
}
