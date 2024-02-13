package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.concurrent.TimeUnit;
import java.util.*;

@TeleOp
public class AutonEncoderDriveTest extends OpMode{
//    DcMotor frontLeft;
//    DcMotor frontRight;
//    DcMotor backLeft;
//    DcMotor backRight;

    HashMap<String, DcMotor> MotorMap = new HashMap<String, DcMotor>();



    public void init() {
        MotorInit();

        //23.56cm for one revolution (530)
        //140cm forward then turn 90 degrees and go forward 70cm
        TurnDistance  (0.5, 3180, "forward");

        waitForSeconds(5);

        //90 degree right turn
        TurnDistance(0.5, 1450, "right");

        waitForSeconds(5);

        TurnDistance(0.5, 1590, "forward");

        waitForSeconds(5);

        TurnDistance(0.5, -1590, "backward");

        waitForSeconds(5);

    }

    public void waitForSeconds(long seconds)
    {
        try {
            TimeUnit.SECONDS.sleep(seconds);

        } catch (Exception e) {
            telemetry.addData("Exception:", e.getCause());
        }

    }

    public void Drive(double power) {
        for (Map.Entry<String, DcMotor> entry : MotorMap.entrySet()) {
            entry.getValue().setPower(power);
        }
    }
    public void StopDriving() {
        Drive(0);
    }


//    public void DriveForwardDistance(double power, int distance) {
//        MotorConfig();
//
//        frontLeft.setTargetPosition(distance);
//        backLeft.setTargetPosition(distance);
//        frontRight.setTargetPosition(distance);
//        backRight.setTargetPosition(-distance);
//
//        RunMotors();
//
//        Drive(power);
//
//        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
//            // block this function until all of wheels are done.
//
//        }
//
//        StopDriving();
//
//    }

    public void TurnRight(int distance)
    {
    }


    public void TurnLeft(int distance)
    {

        for (Map.Entry<String, DcMotor> entry : MotorMap.entrySet()) {
            if (entry.getKey() == "back right") {
                entry.getValue().setTargetPosition(-distance);
            }

            else if (entry.getKey() == "back left") {
                entry.getValue().setTargetPosition(distance);
            }

            else if(entry.getKey() == "front right"){
                entry.getValue().setTargetPosition(distance);
            }

            else if (entry.getKey()== "front left") {
                entry.getValue().setTargetPosition(-distance);
            }

        }
    }
    public void GoForward(int distance)
    {
        // TODO:
        for (Map.Entry<String, DcMotor> entry : MotorMap.entrySet()) {
            if (entry.getKey() == "back right") {
                entry.getValue().setTargetPosition(distance);
            }

            else if (entry.getKey() == "back left") {
                entry.getValue().setTargetPosition(distance);
            }

            else if(entry.getKey() == "front right"){
                entry.getValue().setTargetPosition(distance);
            }

            else if (entry.getKey()== "front left") {
                entry.getValue().setTargetPosition(distance);
            }

        }


    }

    public void GoBackward(int distance)
    {
        // TODO:
    }

    public void TurnDistance(double power, int distance, String direction) {
        MotorConfig();

        switch (direction) {
            case "right":
                TurnRight(distance);
                break;
            case "left":
                TurnLeft(distance);
                break;
            case "forward":
                GoForward(distance);
                break;
            case "backward":
                GoBackward(distance);
                break;
            default:
                throw new RuntimeException();
        }

        RunMotors();

        Drive(power);

        /*while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {

        }
        */


        StopDriving();
    }

    public boolean EncodersAreNegative() {
        Boolean nagative = true;
        for (Map.Entry<String, DcMotor> entry : MotorMap.entrySet()) {
           nagative = nagative && entry.getValue().getCurrentPosition() < 0;
            }
        return nagative;
    }

    public void MotorConfig() {

   SetMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void SetMode(DcMotor.RunMode mode)
    {
        for (Map.Entry<String, DcMotor> entry : MotorMap.entrySet()) {
            entry.getValue().setMode(mode);
        }
    }
    public void MotorInit() {
        MotorMap.put("front right", hardwareMap.get(DcMotor.class, "front right"));
        MotorMap.put("front left", hardwareMap.get(DcMotor.class, "front left") );
        MotorMap.put("back left", hardwareMap.get(DcMotor.class,   "back left"));
        MotorMap.put("back right", hardwareMap.get(DcMotor.class, "back right"));

        SetMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void RunMotors() {

        SetMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void loop() {}



}

