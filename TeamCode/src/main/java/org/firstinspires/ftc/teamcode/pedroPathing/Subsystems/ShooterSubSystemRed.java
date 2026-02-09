package org.firstinspires.ftc.teamcode.pedroPathing.Subsystems;

//import static org.firstinspires.ftc.teamcode.pedroPathing.Subsystems.balltestnew12.blue;
//import static org.firstinspires.ftc.teamcode.pedroPathing.Subsystems.zeyadtuff9ball.red;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


import java.util.List;

public class ShooterSubSystemRed{
    double MAX_DRIVE_POWER = 1;
    double tx = 0, ty = 0, ta = 0, id = 0;
    double rotate = 0;
    boolean aligned;
    LLResult result;
    double previousTxError = 0;
    private boolean hasTarget =false;
    private int tolerance = 20;
    private int targetVelocity = 1400;
    private DcMotorEx intake = null;
    private DcMotorEx transfer, leftShooter, rightShooter;

    private DcMotor leftBack, leftFront, rightBack, rightFront;
    private Servo transferGate;
        private CRServo turret;
    private boolean shootingSafe = false;

    private com.qualcomm.hardware.limelightvision.Limelight3A limelight;




    public ShooterSubSystemRed(HardwareMap hardwareMap) {
        turret = hardwareMap.get(CRServo.class, "turret");
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        transfer = hardwareMap.get(DcMotorEx.class, "transfer");
        leftShooter = hardwareMap.get(DcMotorEx.class, "shooterLeft");
        rightShooter = hardwareMap.get(DcMotorEx.class, "shooterRight");
        transferGate = hardwareMap.get(Servo.class, "gate");
        leftShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftShooter.setDirection(DcMotor.Direction.REVERSE);
        rightShooter.setDirection(DcMotor.Direction.FORWARD);
        transfer.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.REVERSE);

//
//        leftBack = hardwareMap.get(DcMotor.class, "back_left_drive");
//        leftFront = hardwareMap.get(DcMotor.class, "front_left_drive");
//        rightBack = hardwareMap.get(DcMotor.class, "back_right_drive");
//        rightFront = hardwareMap.get(DcMotor.class, "front_right_drive");
//        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
//        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
//        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
//        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);

        limelight = hardwareMap.get(com.qualcomm.hardware.limelightvision.Limelight3A.class, "limelight");
        limelight.setPollRateHz(10);
        limelight.start();
        limelight.pipelineSwitch(0);
    }

    public void intake() {
        closeGate();
        intake.setPower(1);
        transfer.setPower(-1);
    }
//    public void shoot5turret() {
//        turret.setPosition(0);
//    }

    void runTransfer() {
        transfer.setPower(-1);
        intake.setPower(1);
    }

    void lockTurret() {
        turret.setPower(0.01);
    }
    public void flywheelInit() {
        leftShooter.setPower(0.42);
        rightShooter.setPower(0.42);
    }

    public void updateShoot() {

        if (targetVelocity - getShooterVelocity() < tolerance * 2) {
            runTransfer();
        } else {
            stopIntake();
        }
        if (getShooterVelocity() < targetVelocity) {
            leftShooter.setPower(1);
            rightShooter.setPower(1);
        } else {
            leftShooter.setPower(0);
            rightShooter.setPower(0);
        }
        if (targetVelocity - getShooterVelocity() < tolerance) {
            openGate();
        }
    }

    public void update_Limelight() {
        result = limelight.getLatestResult();
        aligned = false;
        if (result != null && result.isValid()) {
            List<LLResultTypes.FiducialResult> tags = result.getFiducialResults();
            if (tags != null) {
                for (LLResultTypes.FiducialResult tag : tags) {
                    id = tag.getFiducialId();
                    if (id == 24) {
                        hasTarget = true;
                        tx = tag.getTargetXDegrees();
                        ty = tag.getTargetYDegrees();
                        ta = tag.getTargetArea();
                    }


                }
            }
        }
    }

    public double updateShootAndAlign() {
        // Alignment stuff
        update_Limelight();
        boolean aligned = false;
//        if (result != null && result.isValid()) {
//            List<LLResultTypes.FiducialResult> tags = result.getFiducialResults();
//            if (tags != null) {
//                for (LLResultTypes.FiducialResult tag : tags) {
//                    id = tag.getFiducialId();
//                    if (id == 20) {
//                        hasTarget = true;
//                        tx = tag.getTargetXDegrees();
//                        ty = tag.getTargetYDegrees();
//                        ta = tag.getTargetArea();
//                    }
//
//                }
//            }
////            double alignmentTolerence = 1;
////            if (Math.abs(tx) > alignmentTolerence) {
////                rotate = calculateAimAssistPower(0, hasTarget);
////            } else {
////                rotate = 0;
////                aligned = true;
////            }
////          //  leftFront.setPower(frontLeftPower);
////            rightFront.setPower(frontRightPower);
////            leftBack.setPower(backLeftPower);
////            rightBack.setPower(backRightPower);
//
//
//        }

        if (targetVelocity - getShooterVelocity() < tolerance * 2) {
            runTransfer();
        } else {
            stopIntake();
        }
        if (getShooterVelocity() < targetVelocity) {
            leftShooter.setPower(1);
            rightShooter.setPower(1);
        } else {
            leftShooter.setPower(0);
            rightShooter.setPower(0);
        }
        if (Math.abs(targetVelocity - getShooterVelocity()) < tolerance) {
            openGate(); //its saying the velocity is right (shooter is at the right velocity)
        }


        return calculateAimAssistPower(0, hasTarget);
    }


    public void stopShoot() {
        leftShooter.setPower(0.0);
        rightShooter.setPower(0.0);
    }

    public void tuff() {
        System.out.println("tuff");
    }

    public void stopIntake() {
        intake.setPower(0.0);
        transfer.setPower(0.0);
    }

    public double getShooterVelocity() {
        return leftShooter.getVelocity();
    }

    public void openGate() {
        transferGate.setPosition(0.65);
    }

    public void closeGate() {
        transferGate.setPosition(0.85);
    }

    public double calculateAimAssistPower(double targetTx, boolean isBlue) {
        if (!hasTarget) {
            previousTxError = 0.0; // Reset derivative term when no target
            return 0.0;
        }


        double error = targetTx - tx;

        // Calculate derivative term (rate of change of error)
        double derivative = error - previousTxError;
        previousTxError = error; // Store for next iteration

        // PDF control: Proportional + Derivative + Feedforward
        double kp = 0.002;
        double kd = 0;
        double kf = 0.1;

        // Feedforward term: small constant power to help overcome static friction
        double feedforward = Math.signum(error) * kf;

        double drivePower = (error * kp) + (derivative * kd) + feedforward;

        // Clamp drive power
        if (drivePower > MAX_DRIVE_POWER) {
            drivePower = MAX_DRIVE_POWER;
        }
        if (drivePower < -MAX_DRIVE_POWER) {
            drivePower = -MAX_DRIVE_POWER;
        }

        if(Math.abs(targetTx - tx) < 0.5) {
            drivePower = 0.0;
        }
        return drivePower;
    }
}