package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.SampleRevBlinkinLedDriver;

@TeleOp
public class driver extends OpMode {
    DcMotor front_left;
    DcMotor front_right;
    DcMotor back_left;
    DcMotor back_right;
    DcMotor linear_slide;

    DcMotor intake;

    DcMotor hanger;

    Servo plane_launcher;

    Servo hanger_left;

    Servo hanger_right;

    Servo front_claw;

    Servo back_claw;

//    public Servo servo;

    @Override
    public void init() {
        front_left = hardwareMap.get(DcMotor.class, "front left");
        front_right = hardwareMap.get(DcMotor.class, "front right");
        back_left = hardwareMap.get(DcMotor.class, "back left");
        back_right = hardwareMap.get(DcMotor.class, "back right");
        linear_slide = hardwareMap.get(DcMotor.class, "linear slide");
        intake = hardwareMap.get(DcMotor.class, "intake");
        hanger = hardwareMap.get(DcMotor.class, "hanger");

        plane_launcher = hardwareMap.get(Servo.class, "plane_launcher");
        hanger_left = hardwareMap.get(Servo.class, "hanger_left");
        hanger_right = hardwareMap.get(Servo.class, "hanger_right");
        front_claw = hardwareMap.get(Servo.class, "front_claw");
        back_claw = hardwareMap.get(Servo.class, "back_claw");

//        servo = hardwareMap.get(Servo.class, "servo");
    }

    public void drive(double y) {
        front_left.setPower(y);
        front_right.setPower(y);
        back_left.setPower(-y);
        back_right.setPower(-y);

    }

    public void rotate(double x) {
        front_right.setPower(-x);
        back_right.setPower(x);
        front_left.setPower(x);
        back_left.setPower(-x);

    }

    public void parallel(double v) {
        front_right.setPower(v);
        back_right.setPower(-v);
        front_left.setPower(-v);
        back_left.setPower(v);
    }


    @Override
    public void loop() {
        // motion control
        float y = gamepad1.left_stick_y;
        float x = gamepad1.left_stick_x;
        float v = gamepad1.right_stick_x;

        drive(y);
        rotate(x);
        parallel(v);

        // linear slide control
        if (gamepad1.a) {
            linear_slide.setPower(0.5);
        }

        if (gamepad1.b) {
            linear_slide.setPower(-0.5);
        }

        // intake control
        if (gamepad1.x) {
            intake.setPower(0.5);
        }

        // hanger control
        if (gamepad1.left_bumper) {
            hanger.setPower(0.5);
        }

        if (gamepad1.right_bumper) {
            hanger.setPower(-0.5);
        }
    }
}
