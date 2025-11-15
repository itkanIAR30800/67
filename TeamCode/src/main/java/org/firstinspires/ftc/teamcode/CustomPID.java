package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@Autonomous
public class CustomPID extends LinearOpMode {
    public static double AMPLITUDE = 10;
    public static double PHASE = 90;
    public static double FREQUENCY = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();

        waitForStart();
        +
        if (isStopRequested()) {
            return;
        }

        while (opModeIsActive()) {
            telemetry.addData("x", AMPLITUDE * Math.sin(
                    2 * Math.PI * FREQUENCY * getRuntime() + Math.toRadians(PHASE)
            ));
            telemetry.update();

            sleep(20);
        }
    }
    //
//    DcMotor testMotor;
//
//    double integral = 0;
//
//   public static PIDCoefficients testPID = new PIDCoefficients(0, 0, 0);
//
//   FtcDashboard dashboard;
//
//   public static double TARGET_POS = 100;
//
//   ElapsedTime PIDTimer = new ElapsedTime();
//   @Override
//   public void runOpMode(){
//       testMotor = hardwareMap.dcMotor.get("testMotor");
//       testMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//       testMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//       testMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//       dashboard = FtcDashboard.getInstance();
//
//       waitForStart();
//
//       moveTestMotor(TARGET_POS);
//
//   }
//   void moveTestMotor(double targetPosition){
//       double error = testMotor.getCurrentPosition();
//       double lastError = 0;
//       while (Math.abs(error) <= 9){
//           error = testMotor.getCurrentPosition() - targetPosition;
//           double changeInError = lastError - error;
//           integral += changeInError * PIDTimer.time();
//           double derivative = changeInError / PIDTimer.time();
//           double P = testPID.p * error;
//           double I = testPID.i * integral;
//           double D = testPID.d * derivative;
//           testMotor.setPower(P + I + D);
//           error = lastError;
//           PIDTimer.reset();
//       }
//   }
}