package org.firstinspires.ftc.teamcode.pedroPathing.Subsystems;
import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


import com.qualcomm.hardware.limelightvision.LLResult;
import java.util.List;


@TeleOp(name="positional test", group="Robot")
public class tuff extends LinearOpMode {

    private Servo turret = null;




    //logic
    public void runOpMode() throws InterruptedException {
        turret = hardwareMap.get(Servo.class, "turret");

        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.square){turret.setPosition(0.0);
            telemetry.addData("0.0", "position");
            telemetry.update();}
            if(gamepad1.circle){turret.setPosition(0.3);
                telemetry.addData("0.3", "position");
                telemetry.update();}
            if(gamepad1.triangle){turret.setPosition(0.5);
                telemetry.addData("0.5", "position");
                telemetry.update();}
            if(gamepad1.cross){turret.setPosition(0.7);
                telemetry.addData("0.7", "position");
                telemetry.update();}
            if(gamepad1.dpad_up){turret.setPosition(1.0);
                telemetry.addData("1.0", "position");
                telemetry.update();}



            }



        }
    }


