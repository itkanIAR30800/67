

        package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


import com.qualcomm.hardware.limelightvision.LLResult;


@TeleOp(name="strafe troubleshoot", group="Robot")
public class strafetroubleshoot extends LinearOpMode {
    private com.qualcomm.hardware.limelightvision.Limelight3A limelight;

    private int tolerance = 50;
    private int targetVelocity = 2000;

    private int idleVelo = 200;
    private CRServo turret = null;
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
    double closed = 0.85;
    double open = 0.4; //TODO: change ts to be faster

    Gamepad lastGamepad1 = new Gamepad();
    Gamepad currentGamepad1 = new Gamepad();


    double initX = 14.755;
    double initY = 112.044;
    double kP = 0.02;          // power per degree (start small)
    double maxPower = 0.35;    // keep it tame
    double deadbandDeg = 15.0;  // stop when within 1 degree
    double maxDeltaPerLoop = 0.05; // slew limit on power changes
    double lastPower = 0.0;




    //logic
    public void runOpMode() throws InterruptedException {
        turret = hardwareMap.get(CRServo.class, "turret");
        transferGate = hardwareMap.get(Servo.class, "gate");
        transfer = hardwareMap.get(DcMotor.class, "transfer");
        intake = hardwareMap.get(DcMotor.class, "intake");

//        turret.setPosition(0.9);

        nearMotor = hardwareMap.get(DcMotorEx.class, "shooterLeft");
        farMotor = hardwareMap.get(DcMotorEx.class, "shooterRight");
        nearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        farMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        nearMotor.setDirection(DcMotor.Direction.REVERSE);
        farMotor.setDirection(DcMotor.Direction.FORWARD);

        transfer.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.REVERSE);

        leftBack = hardwareMap.get(DcMotor.class, "back_left_drive");
        leftFront = hardwareMap.get(DcMotor.class, "front_left_drive");
        rightBack = hardwareMap.get(DcMotor.class, "back_right_drive");
        rightFront = hardwareMap.get(DcMotor.class, "front_right_drive");
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);


        limelight = hardwareMap.get(com.qualcomm.hardware.limelightvision.Limelight3A.class, "limelight");
        //SIX SEVENNNNNNNNNNNN
        limelight.setPollRateHz(10);
        limelight.start();
        limelight.pipelineSwitch(0);
        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();
        transferGate.setPosition(closed);
        while (opModeIsActive()) {


            LLResult result = limelight.getLatestResult();

            double ta = 0, tx = 0, ty = 0;
            boolean hasTarget = result != null && result.isValid();
            if (hasTarget) {
                ta = result.getTa();
                tx = result.getTx();
                ty = result.getTy();
                telemetry.addData("value tx:", tx);
                telemetry.addData("value ty:", ty);
                telemetry.addData("value ta:", ta);
                telemetry.addData("speed", nearMotor.getVelocity());
                telemetry.update();
            }
            //START DRIVETRAIN ----------------------------------------------------------------------

            double max;
            double drive = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double strafe = -gamepad1.left_stick_x * 1.5;
            double rotate = gamepad1.right_stick_x;
            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.


            if (gamepad1.right_trigger > 0.05) {
                intake.setPower(1.0);
                transfer.setPower(-1.0);
                transferGate.setPosition(closed);

            } else if (gamepad1.left_trigger > 0.05) {
                intake.setPower(-1.0);
                transfer.setPower(1.0);
            } else {
                intake.setPower(0.0);
                transfer.setPower(0.0);
            }

            if (gamepad1.dpad_left) {
                turret.setPower(-0.2);
            } else if (gamepad1.dpad_right) {
                turret.setPower(0.2);
            } else {
                turret.setPower(0.0);
            }

            lastGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);
            boolean leftBumper = !lastGamepad1.left_bumper && currentGamepad1.left_bumper;
            boolean rightBumper = !lastGamepad1.right_bumper && currentGamepad1.right_bumper;
            // Update current with newest data


//            if (leftBumper || rightBumper) {
//                if (hasTarget) {
//                    telemetry.addData("tx", Math.abs(tx)); //after adding ts: limelight blinking when it sees target but not detecting
//                    telemetry.update(); //bc ts not printing to telemetry
//                    double tolerance2 = 1.0;
//                    double kp = 0.01;
//                    if (Math.abs(tx) > tolerance2) {
//                        if (Math.abs(tx) < 10) {
//                            kp = 0.01;
//                        }
//                        turret.setPower(kp * tx);
//                        telemetry.addData("tx", tx);
//                        telemetry.update();
//                    } else {
//                        turret.setPower(0.0);
//                        //TODO: add auto shoot
//                    }
//                } else {
//                    if (gamepad1.left_bumper) {
//                        turret.setPower(-0.23);
//                    } else if (gamepad1.right_bumper) {
//                        turret.setPower(0.23);
//                    } else {
//                        turret.setPower(0.0);
//                    }
//                }
//            } else {
//                nearMotor.setPower(0.2);
//                farMotor.setPower(0.8);
//            }

            targetVelocity = calcVelo(ty, ta);
            boolean shoot = gamepad1.square;
            boolean aligned = false;
            double currentVelocity = 0;
            if (shoot) { //TODO: goali
                intake.setPower(1);
                if (targetVelocity < 1800)
                    transfer.setPower(-1);
                else
                    transfer.setPower(-0.6);


                if(hasTarget) {
                    double alignmentTolerence = 1;
                    double error = tx; /// because our target is 0 so tx - 0 is tx
                    double kp = 0.01;
                    double kf = 0.1;
                    if (Math.abs(tx) > alignmentTolerence) {
                        rotate = kp * error + kf * Math.signum(error);
                    } else {
                        rotate = 0;
                        aligned = true;
                    }

                    if (!gamepad1.left_bumper) {
                        turret.setPower(0);
                        lastPower = 0;
                    } else {
                        // Read limelight
                        ta = result.getTa();
                        tx = result.getTx();
                        ty = result.getTy();// area
                        // 0/1 if you can

                        // preferred
                        // fallback if tv not available:
                        // boolean valid = ta > 0.1;

//                        double powerCmd = 0.0;
//
//                            if (Math.abs(tx) <= deadbandDeg) {
//                                powerCmd = 0.0;
//                                aligned = true;
//                                telemetry.addData("aligned?","six seven");
//                                powerCmd = slew(powerCmd, lastPower, maxDeltaPerLoop);
//                                turret.setPower(powerCmd);
//                                lastPower = powerCmd;
//                            } else {
//                                powerCmd = clamp(kP * (-tx), -maxPower, maxPower);
//                                telemetry.addData("aligning","six seven");
//                                powerCmd = slew(powerCmd, lastPower, maxDeltaPerLoop);
//                                turret.setPower(-powerCmd);
//                                lastPower = powerCmd;
//                            }
//
//                            powerCmd = 0.0;

                        // Slew limit


                        // CAN_SHOOT example:

                    }
                }

                currentVelocity = nearMotor.getVelocity();

                if (nearMotor.getVelocity() > targetVelocity) {
                    nearMotor.setPower(0);
                    farMotor.setPower(0);
                } else {
                    nearMotor.setPower(1);
                    farMotor.setPower(1);

                }
                boolean shootingSafe = false;
                /// this is not really a tolerance at this point it's basically if we're meeting the target or higher
                /// we're depending on the bang-bang + flywheel weight to roughly maintain the velocity
                /// feel free to give it a push (increasing or decreasing this constant to the side of tolerance in this condition to shoot higher or lower for all values)
                /// the lower transfer speed for the far zone is to space the artifacts more and give
                /// more time for the flywheel to ramp up as we didn't have enough weight on the flywheel
                /// at the time of writing this code.
                /// the other commented part underneath was basically another trial to control when
                /// to shoot without having to open and close the broken gate that we had
                /// you might consider adding the gate back? although lowkey I am very optimistic about
                /// not having to use the gate in between the artifacts but now that we changed the gate
                /// it can open faster after ramping up the speed of the flywheel
                /// (the other gate would take more time to open because it was bent upward nd would
                /// take more force and more effort to turn and therefore more time kinda)
                /// good luck :D

                if (targetVelocity - currentVelocity < tolerance - 20) {
                    shootingSafe = true;
                    //  transfer.setPower(1);
                }

                // if(targetVelocity - currentVelocity < tolerance * 2){
                //     transfer.setPower(1);
                // }
                // else{
                //     transfer.setPower(0);
                // }

                if (aligned && shootingSafe) {
                    transferGate.setPosition(open);
                    // transfer.setPower(1);
                }

            } else if (!shoot) {
                nearMotor.setPower(0.5);
                farMotor.setPower(0.5);
            } //else {
//                nearMotor.setPower(0);
//                farMotor.setPower(0);
//                //  transferGate.setPosition(closed);
//            }


            double frontLeftPower = (drive + strafe + rotate);
            double backLeftPower = (drive - strafe + rotate);
            double frontRightPower = (drive - strafe - rotate);
            double backRightPower = (drive + strafe - rotate);
            leftFront.setPower(frontLeftPower);
            rightFront.setPower(frontRightPower);
            leftBack.setPower(backLeftPower);
            rightBack.setPower(backRightPower);
        }
    }

    int calcVelo(double ty, double ta) {

        if (ty >= 15.0) {
            return 1375;
        }

        if (ty >= 12.0) {
            return 1375;
        }

        // ---------- NEAR ----------
        if (ty >= 9.0) {
            return 1450;
        }

        if (ty >= 6.0) {
            return 1475;
        }

        if (ty >= 4.0) {
            return 1575;
        }

        if (ty >= 3.0) {
            return 1600;
        }

        if (ty >= 2.5) {
            return 1665;
        }

        if (ty >= 1.65) {
            return 1650;
        }

        if (ty >= 1.4) {
            return 1700;
        }

        // ---------- FAR ----------
        if (ty >= 0.5) {
            return 1750;
        }

        if (ty >= 0.1) {
            return 1825;
        }

        // ---------- VERY FAR / BELOW TARGET ----------
        return 2000;
    }
}



// todo: write your code here
