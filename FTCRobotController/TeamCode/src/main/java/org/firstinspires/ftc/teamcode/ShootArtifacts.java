package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Shoot Artifacts", group="Robot")
public class ShootArtifacts extends LinearOpMode {
    private DcMotor shooterRightMotor = null;
    private DcMotor shooterLeftMotor = null;
    /*private Servo transferServoLeft = null;
    private Servo transferServoRight = null;
    private Servo transferArm = null;*/

    //@Override
    public void runOpMode() throws InterruptedException {
        shooterRightMotor = hardwareMap.get(DcMotor.class, "shooterRightMotor");
        shooterLeftMotor = hardwareMap.get(DcMotor.class, "shooterLeftMotor");
        /*transferServoRight = hardwareMap.get(Servo.class, "transferServoRight");
        transferServoLeft = hardwareMap.get(Servo.class, "transferServoLeft");
        transferArm = hardwareMap.get(Servo.class, "transferArm");*/

        waitForStart();

        while (opModeIsActive())
        {
            while(gamepad1.right_bumper && !gamepad1.left_bumper)
            {
                shooterLeftMotor.setDirection((DcMotorSimple.Direction.REVERSE));
                shooterRightMotor.setDirection((DcMotorSimple.Direction.FORWARD));
            /*transferServoRight.setPosition(1.0);
            transferServoLeft.setPosition(1.0);
            transferServoRight.setDirection((Servo.Direction.FORWARD));
            transferServoLeft.setDirection((Servo.Direction.FORWARD));

            transferArm.setPosition(0.5);
            transferArm.setDirection((Servo.Direction.FORWARD));*/

                shooterLeftMotor.setPower(1.0);
                shooterRightMotor.setPower(1.0);
            }

            while(gamepad1.left_bumper && !gamepad1.right_bumper)
            {
                shooterLeftMotor.setDirection((DcMotorSimple.Direction.FORWARD));
                shooterRightMotor.setDirection((DcMotorSimple.Direction.REVERSE));
            /*transferServoRight.setPosition(0.0);
            transferServoLeft.setPosition(0.0);
            transferServoRight.setDirection((Servo.Direction.REVERSE));
            transferServoLeft.setDirection((Servo.Direction.REVERSE));

            transferArm.setPosition(0.0);
            transferArm.setDirection((Servo.Direction.REVERSE));*/

                shooterLeftMotor.setPower(1.0);
                shooterRightMotor.setPower(1.0);
            }


        }





    }
}
// 5

