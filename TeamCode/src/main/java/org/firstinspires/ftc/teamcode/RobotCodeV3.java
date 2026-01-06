package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="Main TeleOp", group="Robot")
public class RobotCodeV3 extends LinearOpMode {
    int defaultShooterVel = 1400;
    int tolerance = 50;
    int defaultTargetVel = 1400;
    private Servo transferGate = null;
    private DcMotor transfer = null;
    private DcMotor intake = null;

    private DcMotorEx nearMotor = null;
    private DcMotorEx farMotor = null;

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightFront = null;
    private DcMotor rightBack = null;



    //logic
    public void runOpMode() throws InterruptedException{
        boolean shooting = false;
        transferGate = hardwareMap.get(Servo.class, "gate");
        transfer = hardwareMap.get(DcMotor.class, "transfer");
        intake = hardwareMap.get(DcMotor.class, "intake");

        nearMotor = hardwareMap.get(DcMotorEx.class, "shooterLeft");
        farMotor = hardwareMap.get(DcMotorEx.class, "shooterRight");
        nearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        farMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        nearMotor.setDirection(DcMotor.Direction.REVERSE);
        farMotor.setDirection(DcMotor.Direction.REVERSE);


        leftBack = hardwareMap.get(DcMotor.class, "back_left_drive");
        leftFront = hardwareMap.get(DcMotor.class, "front_left_drive");
        rightBack = hardwareMap.get(DcMotor.class, "back_right_drive");
        rightFront = hardwareMap.get(DcMotor.class, "front_right_drive");


        // ########################################################################################
        // !!!            IMPORTANT Drive Information. Test your motor directions.            !!!!!
        // ########################################################################################
        // Most robots need the motors on one side to be reversed to drive forward.
        // The motor reversals shown here are for a "direct drive" robot (the wheels turn the same direction as the motor shaft)
        // If your robot has additional gear reductions or uses a right-angled drive, it's important to ensure
        // that your motors are turning in the correct direction.  So, start out with the reversals here, BUT
        // when you first test your robot, push the left joystick forward and observe the direction the wheels turn.
        // Reverse the direction (flip FORWARD <-> REVERSE ) of any wheel that runs backward
        // Keep testing until ALL the wheels move the robot forward when you push the left joystick forward.

        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);


        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive())
        {
            //START DRIVETRAIN -----------------------------------------------------------------------
            double max;

            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.left_stick_x*1.5;
            double yaw     =  gamepad1.right_stick_x;

            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double denominator = Math.max(Math.abs(axial) + Math.abs(lateral) + Math.abs(yaw), 1);
            double frontLeftPower = (axial + lateral + yaw) / denominator;
            double backLeftPower = (axial - lateral + yaw) / denominator;
            double frontRightPower = (axial - lateral - yaw) / denominator;
            double backRightPower = (axial + lateral - yaw) / denominator;

            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
//            max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
//            max = Math.max(max, Math.abs(backLeftPower));
//            max = Math.max(max, Math.abs(backRightPower));
//
//            if (max > 1.0) {
//
//               frontLeftPower /= max;
//                backLeftPower /= max;
//                frontRightPower /= max;
//                backRightPower /= max;
//            }


            // This is test code:
            //
            // Uncomment the following code to test your motor directions.
            // Each button should make the corresponding motor run FORWARD.
            //   1) First get all the motors to take to correct positions on the robot
            //      by adjusting your Robot Configuration if necessary.
            //   2) Then make sure they run in the correct direction by modifying the
            //      the setDirection() calls above.
            // Once the correct motors move in the correct direction re-comment this code.

            /*
            frontLeftPower  = gamepad1.x ? 1.0 : 0.0;  // X gamepad
            backLeftPower   = gamepad1.a ? 1.0 : 0.0;  // A gamepad
            frontRightPower = gamepad1.y ? 1.0 : 0.0;  // Y gamepad
            backRightPower  = gamepad1.b ? 1.0 : 0.0;  // B gamepad
            */

            leftFront.setPower(frontLeftPower);
            leftBack.setPower(backLeftPower);
            rightFront.setPower(frontRightPower);
            rightBack.setPower(backRightPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", frontLeftPower, frontRightPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", backLeftPower, backRightPower);
            telemetry.update();
            //END DRIVETRAIN -----------------------------------------------------------------------
            //START INTAKE -------------------------------------------------------------------------
//            double maxPower = 1.0;
//            double nearSpeed = gamepad1.left_trigger;
//            double farSpeed = gamepad1.right_trigger;
//
//            if (/*robot is in certain zone*/) {
//                shooting = true;
//            }
//
//            if (shooting) {
//                if (gamepad1.left_trigger > 0f && gamepad1.right_trigger == 0f) //changed to if statements due to driving issue
//                {
//                    telemetry.addData("shooting:", "near");
////                nearSpeed = 0.7;
////                farSpeed = 0.5;
//                    nearMotor.setPower(nearSpeed);
//                    farMotor.setPower(nearSpeed);
//                    transfer.setPower(1.0);
//                }
//                else if (gamepad1.right_trigger > 0f && gamepad1.left_trigger == 0f)
//                {
//                    telemetry.addData("shooting:", "far");
////                farSpeed = maxPower;
////                nearSpeed = 0.8;
////
//                    nearMotor.setPower(farSpeed);
//                    farMotor.setPower(farSpeed);
//                    transfer.setPower(1.0);
//                }
//            } else
//            {
//                telemetry.addData("shooting:", "none");
//                nearMotor.setPower(0.0);
//                farMotor.setPower(0.0);
//                transfer.setPower(0.0);
//            }

            boolean shootButton = gamepad1.a;

            if(shootButton) {
                //bang bang controller
                if(nearMotor.getVelocity() > defaultTargetVel) {
                    nearMotor.setPower(0.0);
                    farMotor.setPower(0.0);
                } else {
                    nearMotor.setPower(1.0);
                    farMotor.setPower(1.0);
                }
            } else {
                nearMotor.setPower(0.0);
                farMotor.setPower(0.0);
            }

            if (gamepad1.left_trigger > 0.05) {
                intake.setPower(1.0); //intake in
                transfer.setPower(1.0); //intake in
                transferGate.setPosition(0.0); //BLOCKKK
                //manual intake controls
            } else if(shootButton && defaultShooterVel - nearMotor.getVelocity() < tolerance) {
                intake.setPower(1.0); //intake in
                transfer.setPower(1.);
                transferGate.setPosition(1.0); // UNBLOCK
            }else {
                intake.setPower(0.0);
                transfer.setPower(0.0);
                transferGate.setPosition(0.0); //BLOCK
            }

            //intake system
            if (gamepad1.left_trigger > 0.05) {
                intake.setPower(1.0);
                transfer.setPower(1.0);
//                transferGate.setPosition(0.67);
            } else if(gamepad1.right_trigger > 0.05) {
                intake.setPower(-1.0);
                transfer.setPower(-1.0);
            } else {
                intake.setPower(0.0);
                transfer.setPower(0.0);
//                transferGate.setPosition(0.0);
            }



            //END INTAKE -------------------------------------------------------------------------
            //START SHOOTER ----------------------------------------------------------------------

        }

    }

}
