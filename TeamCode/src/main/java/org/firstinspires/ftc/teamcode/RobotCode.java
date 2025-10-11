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

        for (int i = 0; i < amount_of_motors; i++)
        {
            motors[i] = hardwareMap.get(DcMotor.class, motordirections[i]);
        }

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

        for (int i = 0; i < 2; i++) //left joystick is making robot go backwards when it is pressed forwards
        {
            motors[i].setDirection(DcMotor.Direction.REVERSE);
        }

        for (int i = 2; i < amount_of_motors; i++)
        {
            motors[i].setDirection(DcMotor.Direction.FORWARD);
        }
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
            double lateral =  gamepad1.left_stick_x;
            double yaw     =  gamepad1.right_stick_x;

            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double frontLeftPower  = axial + lateral + yaw;
            double frontRightPower = axial - lateral - yaw;
            double backLeftPower   = axial - lateral + yaw;
            double backRightPower  = axial + lateral - yaw;

            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
            max = Math.max(max, Math.abs(backLeftPower));
            max = Math.max(max, Math.abs(backRightPower));

            double[] powers = {frontLeftPower,
                    backLeftPower,
                    frontRightPower,
                    backRightPower
            };

            if (max > 1.0) {

                for (int i = 0; i < amount_of_motors; i++)
                {
                    powers[i] /= max;
                }
            }

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

            // Send calculated power to wheels
            for (int i = 0; i < amount_of_motors; i++)
            {
                motors[i].setPower(powers[i]);
            }

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", frontLeftPower, frontRightPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", backLeftPower, backRightPower);
            telemetry.update();
            //END DRIVETRAIN -----------------------------------------------------------------------
            //START INTAKE -------------------------------------------------------------------------
            if (gamepad1.left_trigger > 0f && gamepad1.right_trigger == 0f) //changed to if statements due to driving issue
            {
                intakeMotor.setDirection((DcMotorSimple.Direction.FORWARD));
                intakeMotor.setPower(0.8); //last run intake was at .5, changed it
            } else if (gamepad1.right_trigger > 0f && gamepad1.left_trigger == 0f)
            {
                intakeMotor.setDirection((DcMotorSimple.Direction.REVERSE));
                intakeMotor.setPower(0.8);
            } else
            {
                intakeMotor.setPower(0.0);
                for (int i = 0; i < amount_of_motors; i++) //locks motors
                {
                    motors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                }
            }

//            while(gamepad1.left_trigger > 0f && gamepad1.right_trigger == 0f)
//            {
//                intakeMotor.setDirection((DcMotorSimple.Direction.FORWARD));
//                intakeMotor.setPower(0.8); //last run intake was at .5, changed it
//
//            }
//
//            while(gamepad1.right_trigger > 0f && gamepad1.left_trigger == 0f)
//            {
//                intakeMotor.setDirection((DcMotorSimple.Direction.REVERSE));
//                intakeMotor.setPower(0.8);
//
//            }
//
//            while(gamepad1.right_trigger == 0f && gamepad1.left_trigger == 0f)
//            {
//                intakeMotor.setPower(0.0);
//
//            }
            //END INTAKE -------------------------------------------------------------------------
            //START SHOOTER ----------------------------------------------------------------------
            if (gamepad1.right_bumper) //shoot forward
            {
                shooterLeftMotor.setDirection((DcMotorSimple.Direction.FORWARD));
                shooterRightMotor.setDirection((DcMotorSimple.Direction.REVERSE));
                shooterLeftMotor.setPower(1);
                shooterRightMotor.setPower(1);
            }
            if (gamepad1.left_bumper)
            {
                transferArm.setDirection((Servo.Direction.FORWARD));
                transferArm.setPosition(-0.67);
            } else
            {
                transferArm.setDirection((Servo.Direction.REVERSE));
                transferArm.setPosition(0.0);
                shooterLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                shooterRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }

//            else if (!gamepad1.right_bumper && !gamepad1.left_bumper)
//            {
//                shooterLeftMotor.setPower(0.0);
//                shooterRightMotor.setPower(0.0);
//            }
            if (gamepad1.dpad_up && !gamepad1.dpad_down)
            {
                transferServoRight.setPosition(1.0);
                transferServoLeft.setPosition(1.0);
                transferServoRight.setDirection((Servo.Direction.REVERSE));
                transferServoLeft.setDirection((Servo.Direction.FORWARD));
            } else if (gamepad1.dpad_down && !gamepad1.dpad_up)
            {
                transferServoRight.setPosition(-1.0);
                transferServoLeft.setPosition(-1.0);
                transferServoRight.setDirection((Servo.Direction.FORWARD));
                transferServoLeft.setDirection((Servo.Direction.REVERSE));
            } else
            {
                transferServoRight.setPosition(0.0);
                transferServoLeft.setPosition(0.0);
            }





//            while(gamepad1.right_bumper && !gamepad1.left_bumper)  //shooters need to build speed. r1 is making it shoot forward, but in the code, leftmotor is set to go forward for right?
//            {
//                shooterLeftMotor.setDirection((DcMotorSimple.Direction.FORWARD));
//                shooterRightMotor.setDirection((DcMotorSimple.Direction.REVERSE));
//                transferServoRight.setPosition(1.0);
//                transferServoLeft.setPosition(1.0);
//                transferServoRight.setDirection((Servo.Direction.REVERSE));
//                transferServoLeft.setDirection((Servo.Direction.FORWARD));
//
//               // transferArm.setPosition(0.5);
//                //transferArm.setDirection((Servo.Direction.FORWARD));
//
//                shooterLeftMotor.setPower(1.0);
//                shooterRightMotor.setPower(1.0);
//            }
//
//            while(gamepad1.left_bumper && !gamepad1.right_bumper) //transfers need to be crservos (continuous rotation) encoder is needed for that, as of now they are not running at all
//            {
//
//                //transferServoRight.setPosition(0.0);
//                //transferServoLeft.setPosition(0.0);
//                transferServoRight.setDirection((Servo.Direction.FORWARD));
//                transferServoLeft.setDirection((Servo.Direction.REVERSE));
//
////                transferArm.setPosition(0.0);
////                transferArm.setDirection((Servo.Direction.REVERSE));
//
//            }
            //END SHOOTER ----------------------------------------------------------------------

        }

    }

}
//intake backspin (prob hardware) *******
//shooter is shooting on l1 (supposed to be r1) ********
//at the same time, button to turn off intake *****
//transfer servos are not triggering at all
//drive


//all of these are buttons, should run when holding(true) and stop when released(false)
//right trigger intake forward
//left trigger intake reverse
//right bumper shoot forward
//left bumper transfer forward
// transfer backward
