package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "Encoder Test ")
public class EncoderTest extends OpMode {
    DcMotor frontLeft;
    double ticks = 2786.2;
    double newTarget;
    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "front left");
        telemetry.addData("Hardware: ", "Initialized");
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            encoder(2);
        }
        telemetry.addData("Motor Ticks: ", frontLeft.getCurrentPosition());
        if(gamepad1.b){
            tracker();
        }

    }
    public void encoder(int turnage){
        newTarget = ticks/turnage;
        frontLeft.setTargetPosition((int)newTarget);
        frontLeft.setPower(0.3);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void tracker(){
        frontLeft.setTargetPosition(0);
        frontLeft.setPower(0.8);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}