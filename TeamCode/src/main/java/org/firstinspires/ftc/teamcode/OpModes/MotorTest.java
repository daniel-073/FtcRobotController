package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp
public class MotorTest extends OpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    @Override
    public void init() {
        frontRight = hardwareMap.get(DcMotor.class, "front right");
        frontLeft = hardwareMap.get(DcMotor.class, "front left");

        telemetry.addData("Hardware: ", "Initialized");
    }

    @Override
    public void loop() {
        if(gamepad1.left_stick_y > 0){
            frontLeft.setPower(0.5);
            frontRight.setPower(0.5);
        }
        frontLeft.setPower(0);
        frontRight.setPower(0);

    }
}