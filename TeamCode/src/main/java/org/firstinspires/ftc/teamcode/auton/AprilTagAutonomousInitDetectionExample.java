///*
// * Copyright (c) 2021 OpenFTC Team
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in all
// * copies or substantial portions of the Software.
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// */
//
//package org.firstinspires.ftc.teamcode.auton;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.openftc.apriltag.AprilTagDetection;
//import org.openftc.easyopencv.OpenCvCamera;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//import org.openftc.easyopencv.OpenCvCameraRotation;
//import org.openftc.easyopencv.OpenCvInternalCamera;
//
//import java.util.ArrayList;
//
//
//
//@TeleOp
//public class AprilTagAutonomousInitDetectionExample extends AutonEncoderDriveTest {
//    OpenCvCamera camera;
//
//
//    AprilTagDetectionPipeline aprilTagDetectionPipeline;
//
//    static final double FEET_PER_METER = 3.28084;
//
//
//
//
//    // Lens intrinsics
//    // UNITS ARE PIXELS
//    // NOTE: this calibration is for the C920 webcam at 800x448.
//    // You will need to do your own calibration for other configurations!
//    double fx = 578.272;
//    double fy = 578.272;
//    double cx = 402.145;
//    double cy = 221.506;
//
//    // UNITS ARE METERS
//    double tagsize = 0.166;
//
//    // Tag ID 1,2,3 from the 36h11 family
//    int LEFT = 1;
//    int MIDDLE = 2;
//    int RIGHT = 3;
//
//    AprilTagDetection tagOfInterest = null;
//
//    public void cameraInit()
//    {
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
//        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);
//        camera.setPipeline(aprilTagDetectionPipeline);
//
//    }
//
//    @Override
//    public void init() {
//
//
//        cameraInit();
//
//        MotorInit();
//
////        waitForSeconds(2);
////
////        // Move forward
////        DriveForwardDistance(0.5, 3180);
////
////        waitForSeconds(2);
////
////        //turn right
////        TurnRightDistance(0.5, 1450);
////
////        waitForSeconds(2);
////
////        DriveForwardDistance(0.5, 318);
////
////        waitForSeconds(2);
//
//        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
//            @Override
//            public void onOpened() {
//                camera.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT);
//            }
//
//            public void onError(int errorCode) {
//                // TODO: print something where goes wrong
//                telemetry.addLine("error");
//
//            }
//        });
//
//        telemetry.setMsTransmissionInterval(50);
//    }
//
//        /*
//         * The INIT-loop:
//         *
//         * This REPLACES waitForStart!
//         */
//
//
//        @Override
//        public void loop() {
//        ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();
//
//            if(currentDetections.size() != 0)
//            {
//                boolean tagFound = false;
//
//                for (AprilTagDetection tag : currentDetections)
//                {
//                    if( tag.id == MIDDLE)
//                    {
//                        tagOfInterest = tag;
//                        tagFound = true;
//                        break;
//                    }
//                }
//
//                if(tagFound)
//                {
//                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
//                    tagToTelemetry(tagOfInterest);
//
//                    double yaw = extractYaw(tagOfInterest);
//
//                    if(yaw>0) {
//                        leftTurn();
////                        while (frontRight.isBusy()) {}
////                        waitForSeconds(2);
////                        StopDriving();
//
//             }
//                    else if(yaw<0)
//                    {
//                       rightTurn(1, 100);
////                       waitForSeconds(2);
////                       while (frontLeft.isBusy()) {}
////                       StopDriving();
//
//                    }
//
//
//
//                }
//                else
//                {
//                    telemetry.addLine("Don't see tag of interest :(");
//
//                    if(tagOfInterest == null)
//                    {
//                        telemetry.addLine("(The tag has never been seen)");
//                    }
//                    else
//                    {
//                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
//                        tagToTelemetry(tagOfInterest);
//                    }
//                }
//
//            }
//            else
//            {
//                telemetry.addLine("Don't see tag of interest :(");
//
//                if(tagOfInterest == null)
//                {
//                    telemetry.addLine("(The tag has never been seen)");
//                }
//                else
//                {
//                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
//                    tagToTelemetry(tagOfInterest);
//                }
//
//
//            }
//
//            telemetry.update();
//        }
//
//
//
//
//        /*
//         * The START command just came in: now work off the latest snapshot acquired
//         * during the init loop.
//         */
//
////        /* Update the telemetry */
////        if (tagOfInterest != null) {
////            telemetry.addLine("Tag snapshot:\n");
////            tagToTelemetry(tagOfInterest);
////            telemetry.update();
////        } else {
////            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
////            telemetry.update();
////        }
////
////        /* Actually do something useful */
////        if (tagOfInterest == null) {
////            //default trajectory here if preferred
////        } else if (tagOfInterest.id == LEFT) {
////            //left trajectory
////        } else if (tagOfInterest.id == MIDDLE) {
////            //middle trajectory
////        } else {
////            //right trajectory
////        }
//
//
//
//
//    public void rightTurn(double power, int distance)
//    {
//        frontLeft.setTargetPosition(distance);
//        backLeft.setTargetPosition(distance);
//
//        waitForSeconds(1);
//
//        Drive(power);
//
//        while(frontLeft.isBusy()) {
//
//        }
//
//        StopDriving();
//
//
//    }
//
//
//    public  void leftTurn()
//    {
//            frontRight.setPower(1);
//            backRight.setPower(1);
//    }
//
//
//
//    public double extractYaw(AprilTagDetection detection)
//    {
//        double R00 = detection.pose.R.get(0, 0);
//        double R10 = detection.pose.R.get(1, 0);
//        double R20 = detection.pose.R.get(2, 0);
//        double pitch = Math.atan2(-R20, Math.sqrt(R00 * R00 + R10 * R10));
//        double yaw = Math.atan2(R10 / Math.cos(pitch), R00 / Math.cos(pitch));
//        return yaw;
//    }
//
//    void tagToTelemetry(AprilTagDetection detection) {
//        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
//        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x * FEET_PER_METER));
//        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y * FEET_PER_METER));
//        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z * FEET_PER_METER));
//        telemetry.addLine(String.format("Rotation yaw: %.2f degrees", Math.toDegrees(extractYaw(detection))));
//    }
//}