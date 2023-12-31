package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


public class driver extends OpMode {
    DcMotor motor;
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    public Servo servo;
    @Override
    public void init() {

        motor = hardwareMap.get(DcMotor.class, "frontLeft");
        motor1 = hardwareMap.get(DcMotor.class, "frontRight");
        motor2 = hardwareMap.get(DcMotor.class, "backLeft");
        motor3 = hardwareMap.get(DcMotor.class, "backRight");
        servo = hardwareMap.get(Servo.class, "servo");
    }

    @Override
    public void loop() {
        float y = gamepad1.left_stick_y;


        if (gamepad1.left_stick_y>0) {
            motor.setPower(y);
            motor1.setPower(y);
            motor2.setPower(-y);
            motor3.setPower(-y);
        }

        if(gamepad1.left_stick_y<0) {
            motor.setPower(-y);
            motor1.setPower(-y);
            motor2.setPower(y);
            motor3.setPower(y);
        }


        while(gamepad1.a) {
            servo.setPosition(0.5);
        }
        servo.setPosition(0);


    }
}
