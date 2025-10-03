package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="Robot Code", group="Robot")
public class RobotCode extends LinearOpMode {

    private DcMotor intakeMotor = null; //intake motor

    //shooter variables
    private DcMotor shooterRightMotor = null;
    private DcMotor shooterLeftMotor = null;
    private Servo transferServoLeft = null;
    private Servo transferServoRight = null;
    private Servo transferArm = null;

    //drivetrain variables
    // Declare OpMode members for each of the 4 motors.
    private ElapsedTime runtime = new ElapsedTime();
    int amount_of_motors = 4;
    private DcMotor[] motors = new DcMotor[amount_of_motors];
    private String[] motordirections = {"front_left_drive",
            "back_left_drive",
            "front_right_drive",
            "back_right_drive"};


    //logic
    public void runOpMode() throws InterruptedException{
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        shooterRightMotor = hardwareMap.get(DcMotor.class, "shooterRightMotor");
        shooterLeftMotor = hardwareMap.get(DcMotor.class, "shooterLeftMotor");
        transferServoRight = hardwareMap.get(Servo.class, "transferServoRight");
        transferServoLeft = hardwareMap.get(Servo.class, "transferServoLeft");
        transferArm = hardwareMap.get(Servo.class, "transferArm");

        waitForStart();

        while (opModeIsActive())
        {
            while(gamepad1.right_trigger > 0f && gamepad1.left_trigger == 0f)
            {
                intakeMotor.setDirection((DcMotorSimple.Direction.FORWARD));
                intakeMotor.setPower(0.5);
            }

            while(gamepad1.left_trigger > 0f && gamepad1.right_trigger == 0f)
            {
                intakeMotor.setDirection((DcMotorSimple.Direction.REVERSE));
                intakeMotor.setPower(0.5);
            }

            while(gamepad1.right_bumper && !gamepad1.left_bumper)
            {
                shooterLeftMotor.setDirection((DcMotorSimple.Direction.REVERSE));
                shooterRightMotor.setDirection((DcMotorSimple.Direction.FORWARD));
                transferServoRight.setPosition(1.0);
                transferServoLeft.setPosition(1.0);
                transferServoRight.setDirection((Servo.Direction.FORWARD));
                transferServoLeft.setDirection((Servo.Direction.FORWARD));

                transferArm.setPosition(0.5);
                transferArm.setDirection((Servo.Direction.FORWARD));

                shooterLeftMotor.setPower(1.0);
                shooterRightMotor.setPower(1.0);
            }

            while(gamepad1.left_bumper && !gamepad1.right_bumper)
            {
                shooterLeftMotor.setDirection((DcMotorSimple.Direction.FORWARD));
                shooterRightMotor.setDirection((DcMotorSimple.Direction.REVERSE));
                transferServoRight.setPosition(0.0);
                transferServoLeft.setPosition(0.0);
                transferServoRight.setDirection((Servo.Direction.REVERSE));
                transferServoLeft.setDirection((Servo.Direction.REVERSE));

                transferArm.setPosition(0.0);
                transferArm.setDirection((Servo.Direction.REVERSE));

                shooterLeftMotor.setPower(1.0);
                shooterRightMotor.setPower(1.0);
            }

        }

    }

}