package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Intake Artifacts", group="Robot")
public class IntakeArtifacts extends LinearOpMode {
    private DcMotor intakeMotor = null;

    //@Override
    public void runOpMode() throws InterruptedException {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");

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

        }

    }
}
// 5