package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.concurrent.TimeUnit;

@TeleOp
public class AutonMotorTest extends OpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    @Override
    public void init() {
        frontRight = hardwareMap.get(DcMotor.class, "front right");
        frontLeft = hardwareMap.get(DcMotor.class, "front left");

        telemetry.addData("Hardware: ", "Initialized");

        telemetry.addData("Current task:", "Waiting for 5 seconds");

        try {
            TimeUnit.SECONDS.sleep(5);

        }
        catch (Exception e)
        {
            telemetry.addData("Exception:", e.getCause());
        }

        telemetry.addData("Current task:", "Running for 5 seconds");

        frontLeft.setPower(0.5);
        frontRight.setPower(0.5);

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