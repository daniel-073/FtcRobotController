package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.concurrent.TimeUnit;

@TeleOp
public class AutonTurnTest extends OpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    @Override
    public void init() {
        frontRight = hardwareMap.get(DcMotor.class, "front right");
        frontLeft = hardwareMap.get(DcMotor.class, "front left");
        backRight = hardwareMap.get(DcMotor.class, "back right");
        backLeft = hardwareMap.get(DcMotor.class, "back left");

        telemetry.addData("Hardware: ", "Initialized");

        frontLeft.setPower(-0.8);
        frontRight.setPower(0.8);

        try {
            TimeUnit.SECONDS.sleep(5);

        }
        catch (Exception e)
        {
            telemetry.addData("Exception:", e.getCause());
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
    }
    @Override
    public void loop() {}

}