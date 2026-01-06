package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Field Centric Code", group="Robot")
public class FieldCentricMecanumTeleOp extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("leftFrontMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("leftBackMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("rightFrontMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("rightBackMotor");
        DcMotor intake = hardwareMap.dcMotor.get("intakeMotor");
        
        DcMotor shooting = hardwareMap.dcMotor.get("shootingMotor");
        DcMotor turret = hardwareMap.dcMotor.get("turretMotor");
        DcMotor transfer = hardwareMap.dcMotor.get("intakeMotor2");
        Servo hood = hardwareMap.servo.get("hoodServo");

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        imu = hardwareMap.get(IMU.class, "imu");
    // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
    // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
            max = Math.max(max, Math.abs(backLeftPower));
            max = Math.max(max, Math.abs(backRightPower));

            if (max > 1.0) {

                leftFrontPower /= max;
                leftBackPower /= max;
                rightFrontPower /= max;
                rightBackPower /= max;

//                for (int i = 0; i < amount_of_motors; i++)
//                {
//                    powers[i] /= max;
//                }
            }


            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            if (gamepad1.left_trigger > 0f && gamepad1.right_trigger == 0f) //changed to if statements due to driving issue
            {
                intake.setDirection((DcMotorSimple.Direction.REVERSE));
                intake.setPower(1.0); //last run intake was at .5, changed it
            } else if (gamepad1.right_trigger > 0f && gamepad1.left_trigger == 0f)
            {
                intake.setDirection((DcMotorSimple.Direction.FORWARD));
                intake.setPower(1.0);
            } else
            {
                intake.setPower(0.0);

//                for (int i = 0; i < amount_of_motors; i++) //locks motors
//                {
//                    motors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//                }
            }

            if (gamepad1.right_bumper) //shoot forward
            {
                shooting.setDirection(DcMotor.Direction.FORWARD);
                shooting.setPower(1.0);

            }
//            if (gamepad1.left_bumper)
//            {
//                hood.setDirection((Servo.Direction.FORWARD));
//                hood.setPosition(-0.67);
//            }
            else
            {

                shooting.setPower(0.0);
            }

            if (gamepad1.x)
            {
                shooting.setPower(0.0);


            }
//            else if (!gamepad1.right_bumper && !gamepad1.left_bumper)
//            {
//                shooterLeftMotor.setPower(0.0);
//                shooterRightMotor.setPower(0.0);
//            }
            if (gamepad1.dpad_up && !gamepad1.dpad_down)
            {
                hood.setPosition(1.0);
                hood.setDirection((Servo.Direction.REVERSE));
            }
            else if (gamepad1.dpad_down && !gamepad1.dpad_up)
            {
                hood.setPosition(-1.0);
                hood.setDirection((Servo.Direction.FORWARD));

            }
            else
            {
                hood.setPosition(0.0);


            }
        }
    }
}