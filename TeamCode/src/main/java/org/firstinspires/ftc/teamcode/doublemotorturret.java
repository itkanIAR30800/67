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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
//shooterRight and shooterLeft, opposite directions
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

@TeleOp(name="2 motor test", group="Robot")
public class doublemotorturret extends LinearOpMode {

    private DcMotor nearMotor = null;
    private DcMotor farMotor = null;
    private ElapsedTime     runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        nearMotor = hardwareMap.get(DcMotor.class, "shooterLeft");
        farMotor = hardwareMap.get(DcMotor.class, "shooterRight");

        nearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        farMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        nearMotor.setDirection(DcMotor.Direction.REVERSE);
        farMotor.setDirection(DcMotor.Direction.REVERSE);



        waitForStart();
        while(opModeIsActive()) {
            double maxPower = 1.0;
            double nearSpeed = gamepad1.left_trigger;
            double farSpeed = gamepad1.right_trigger;

            if (gamepad1.left_trigger > 0f && gamepad1.right_trigger == 0f) //changed to if statements due to driving issue
            {
                telemetry.addData("shooting:", "near");
//                nearSpeed = 0.7;
//                farSpeed = 0.5;
                nearMotor.setPower(nearSpeed);
                farMotor.setPower(nearSpeed);
            }
            else if (gamepad1.right_trigger > 0f && gamepad1.left_trigger == 0f)
            {
                telemetry.addData("shooting:", "far");
//                farSpeed = maxPower;
//                nearSpeed = 0.8;
//
                nearMotor.setPower(farSpeed);
                farMotor.setPower(farSpeed);
            } else
            {
                telemetry.addData("shooting:", "none");
                nearMotor.setPower(0.0);
                farMotor.setPower(0.0);
            }
        }


    }
}
