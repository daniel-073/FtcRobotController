package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.concurrent.TimeUnit;

@TeleOp
public class AutonEncoderDriveTest extends OpMode{
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    public void init() {
        MotorInit();

        //23.56cm for one revolution (530)
        //140cm forward then turn 90 degrees and go forward 70cm
        DriveForwardDistance(0.5, 3180);

        WaitFiveSeconds();

        //90 degree right turn
        TurnRightDistance(0.5, 1450);

        WaitFiveSeconds();

        DriveForwardDistance(0.5, 1590);

        WaitFiveSeconds();

        DriveForwardDistance(0.5, -1590);

        WaitFiveSeconds();

        TurnRightDistance(0.5, -1450);

        WaitFiveSeconds();

        DriveForwardDistance(0.5, -3180);

    }

    public void WaitFiveSeconds() {
        try {
            TimeUnit.SECONDS.sleep(2);

        }
        catch (Exception e)
        {
            telemetry.addData("Exception:", e.getCause());
        }
    }
    public void Drive(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }
    public void StopDriving() {
        Drive(0);
    }


    public void DriveForwardDistance(double power, int distance) {
        MotorConfig();

        frontLeft.setTargetPosition(distance);
        backLeft.setTargetPosition(distance);
        frontRight.setTargetPosition(distance);
        backRight.setTargetPosition(-distance);

        RunMotors();

        Drive(power);

        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {}

        StopDriving();

    }

    public void TurnRightDistance(double power, int distance) {
        MotorConfig();

        frontLeft.setTargetPosition(distance);
        backLeft.setTargetPosition(distance);
        frontRight.setTargetPosition(-distance);
        backRight.setTargetPosition(distance);

        RunMotors();

        Drive(power);

        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {}

        StopDriving();
    }

    public boolean EncodersAreNegative() {
        return (frontLeft.getCurrentPosition() < 0 && frontRight.getCurrentPosition() < 0 && backLeft.getCurrentPosition() < 0 && backRight.getCurrentPosition() < 0);
    }

    public void MotorConfig() {
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void MotorInit() {
        frontRight = hardwareMap.get(DcMotor.class, "front right");
        backRight = hardwareMap.get(DcMotor.class, "back right");
        frontLeft = hardwareMap.get(DcMotor.class, "front left");
        backLeft = hardwareMap.get(DcMotor.class, "back left");

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void RunMotors() {
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    @Override
    public void loop() {}



}

