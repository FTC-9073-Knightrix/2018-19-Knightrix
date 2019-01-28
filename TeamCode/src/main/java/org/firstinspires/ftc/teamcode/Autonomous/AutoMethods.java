//Declare the package
package org.firstinspires.ftc.teamcode.Autonomous;

//Import the dependencies needed to run the program
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

//Create the class declaration, extending AutoHardwareMap
public abstract class AutoMethods extends AutoHardwareMap {

    //Create the initialization method to run at the start of the programs
    public void initRobot() {

        //Add the motors to the configuration on the phones
        leftFrontDrive = hardwareMap.dcMotor.get("LF");
        rightFrontDrive = hardwareMap.dcMotor.get("RF");
        rightBackDrive = hardwareMap.dcMotor.get("RB");
        leftBackDrive = hardwareMap.dcMotor.get("LB");
        hangMotor = hardwareMap.dcMotor.get("hang");

        marker = hardwareMap.servo.get("marker");

        //Set the direction of the motors
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        //leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        //leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        //Set the mode the motors are going to be running in
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        /*leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);*/

        //Add the gyroscope to the configuration on the phones
        //navxGyro = hardwareMap.get(NavxMicroNavigationSensor.class, "gyro");
        gyro = hardwareMap.get(BNO055IMU.class, "gyro");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        gyro.initialize(parameters);


        range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");
        range2 = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range2");



        // Magnetic Switch
        MagUp = hardwareMap.get(DigitalChannel.class, "MagUp");
        MagUp.setMode(DigitalChannel.Mode.INPUT); // set the digital channel to input.
        MagDown = hardwareMap.get(DigitalChannel.class, "MagDown");
        MagDown.setMode(DigitalChannel.Mode.INPUT); // set the digital channel to input.



        //Wait for the gyroscope to stop calibrating
        /*while(opModeIsActive() && navxGyro.isCalibrating()) {

            //Display on the screen the fact the gyroscope is calibrating
            telemetry.addLine("navx Calibrating");
            //Update telemetry
            telemetry.update();
        }

        //Display on the screen the fact the gyroscope has finished calibrating
        telemetry.addLine("init complete");
        //Update telemetry
        telemetry.update();*/
    }

    //Create the method to move the robot forwards based on direction and power of the motors
    /*public void move(double angle1, double power) {
        if (leftFrontDrive != null && leftBackDrive != null && rightFrontDrive != null && rightBackDrive != null) {
            leftFrontDrive.setPower(Range.clip(power * ((Math.sin((angle1 + 45) / 180 * Math.PI))), -1, 1));
            leftBackDrive.setPower(Range.clip(power * ((Math.sin((angle1 - 45) / 180 * Math.PI))), -1, 1));
            rightFrontDrive.setPower(Range.clip(power * ((Math.sin((angle1 - 45) / 180 * Math.PI))), -1, 1));
            rightBackDrive.setPower(Range.clip(power * ((Math.sin((angle1 + 45) / 180 * Math.PI))), -1, 1));
        }
    }*/

    //Create the move method to move the robot based off the angle, power, and rotation of the robot applied
    public void move (double myangle, float mypower, float myrot) {

        //If none of the motors are null, run each motor to an individual value based off the values inputted from the joystick
        if (leftFrontDrive != null && leftBackDrive != null && rightFrontDrive != null && rightBackDrive != null) {
            leftFrontDrive.setPower(Range.clip((myrot + (mypower * ((Math.sin((myangle + 135) / 180 * Math.PI))))), -1, 1));
            leftBackDrive.setPower(Range.clip((myrot + (mypower * ((Math.sin((myangle + 45) / 180 * Math.PI))))), -1, 1));
            rightFrontDrive.setPower(Range.clip((-myrot + (mypower * ((Math.sin((myangle + 45) / 180 * Math.PI))))), -1, 1));
            rightBackDrive.setPower(Range.clip((-myrot + (mypower * ((Math.sin((myangle + 135) / 180 * Math.PI))))), -1, 1));

            telemetry.addData("LeftFront", leftFrontDrive.getCurrentPosition());
            telemetry.addData("LeftBack", leftBackDrive.getCurrentPosition());
            telemetry.addData("RightFront", rightFrontDrive.getCurrentPosition());
            telemetry.addData("RightBack", rightBackDrive.getCurrentPosition());
        }
    }



    //Create the method to turn the robot based on the degree value set and the current position of the robot
    public void turn(double degrees, double power) {

        //Create a variable power of the motor that gets slower the closer the robot is to the set degree
        //double power = 0.3;

        //Get the current position of the robot
        orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
        //Get the current degree of the robot
        angle = orientation.firstAngle;

        //While the difference between the target angle and current angle is greater than three degrees
        while (opModeIsActive() && Math.abs(degrees - angle) > 1) {
            //If the target degree is greater than the current angle of the robot, turn right
            if (Math.abs(degrees - angle) < 25) {
                leftFrontDrive.setPower(-power/4);
                rightFrontDrive.setPower(power/4);
                leftBackDrive.setPower(-power/4);
                rightBackDrive.setPower(power/4);
            }
            else {
                leftFrontDrive.setPower(-power);
                rightFrontDrive.setPower(power);
                leftBackDrive.setPower(-power);
                rightBackDrive.setPower(power);
            }

            //If the target degree is greater than the current angle of the robot, turn left
            /*if (degrees > angle) {
                leftFrontDrive.setPower(power);
                rightFrontDrive.setPower(-power);
                leftBackDrive.setPower(power);
                rightBackDrive.setPower(-power);
            }*/

            //Get the current position of the robot
            orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
            //Get the current degree of the robot
            angle = orientation.firstAngle;

            //Display the target degree the robot is going to move to on the screen
            telemetry.addLine("Target degree: " + (int)(degrees));
            //Display the current degree of the robot on the screen
            telemetry.addLine("Current degree: " + (int)(angle));
            //Update telemetry
            telemetry.update();
        }

        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightBackDrive.setPower(0);
    }

    // Move using RangeSensor
    /*public void LeftRangeMove(int direction, double power, int distance, int wait){
        // Need to convert to Linear OpMode
        // Set Variables:
        int WallDistance = 15;       // Constant Distance from Wall
        int WallDistanceOffset = 10; // Constant Range from wall to adjust direction
        int AngleCorrection = 90;    // Maximum angle to adjust when outside WallDistanceOffset margins

        // Get Variables:
        int RangeValue = 5;   // CHANGE with real sensor reading

        // Calculate new direction to move
        int Newdirection = direction + (Range.clip((RangeValue-WallDistance)/WallDistanceOffset,-1,1) * AngleCorrection);

        // Move!
        gyroMove(Newdirection,power, distance, wait, true);
    }*/



    // Move for a number of clicks based on the Gyro, Power/Speed, and desired direction of the robot
    public void gyroMove(int direction, double power, int distance, int wait, String wallFollow){

        // SET Values
        // Set desired power level
        // Set desired direction
        // Set number of clicks to move
        // Set current encoder position as baseline

        // Do while (active && number of clicks lower than destination clicks
        // Read Gyro Position
        // move(myangle,mypower,myrot);
        // End Loop



        //direction = angle, 0 forwards, -179/179 backwards, 270 left, 90 right
        //power = power/speed running
        //distance = centimeters to move
        //wait = time to wait after moving

        // Determine robot orientation (0, 45, 90 degrees, etc)
        // Need to test with -179/+179 degrees in the extreme
        orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
        int StartingOrientation = (int) orientation.firstAngle;

        /////////StartingOrientation = Math.round(StartingOrientation / 45) * 45;

        resetEncoders();
        distance *= ENCDISTANCE; //converts cm to encoder rotations
        while(opModeIsActive() &&  (distance > (Math.abs(leftFrontDrive.getCurrentPosition()) + Math.abs(rightFrontDrive.getCurrentPosition()) + Math.abs(leftBackDrive.getCurrentPosition()) + Math.abs(rightBackDrive.getCurrentPosition())) / 4)) {
            // orientation / gyroDegrees = roobot true orientation direction
            // direction = direction we want the robot to move
            // myrot = course correction between actual heading and desired heading

            orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
            int gyroDegrees = (int) orientation.firstAngle;

            if (gyroDegrees < -359) {
                gyroDegrees += 360;
            }

            int CorrectionDegrees = (StartingOrientation - gyroDegrees);
            float myrot = (float)(CorrectionDegrees / 180.0) * -1;


            if (wallFollow.equals("right")) {
                // Set Variables:
                float distanceFromSide = 3;
                float WallDistance = 8 + distanceFromSide;       // Constant Distance from Wall
                float WallDistanceOffset = 10; // Constant Range from wall to adjust direction
                float AngleCorrection = 90;    // Maximum angle to adjust when outside WallDistanceOffset margins

                // Get Variables:
                double RangeValue = range2.cmUltrasonic();   // CHANGE with real sensor reading

                // Calculate new direction to move
                int newDirection;
                // Calculate new direction to move
                if (power >= 0) {
                    newDirection = direction - (int) (Range.clip((RangeValue - WallDistance) / WallDistanceOffset, -1, 1) * AngleCorrection);
                }
                else {
                    newDirection = direction + (int) (Range.clip((RangeValue - WallDistance) / WallDistanceOffset, -1, 1) * AngleCorrection);
                }
                telemetry.addData("New Direction", newDirection);
                telemetry.addData("Range", range2.cmUltrasonic());

                move(newDirection, (float) power, myrot);
            }
            else if (wallFollow.equals("left")) {
                // Set Variables:
                float distanceFromSide = 0;
                float WallDistance = 8 + distanceFromSide;       // Constant Distance from Wall
                float WallDistanceOffset = 10; // Constant Range from wall to adjust direction
                float AngleCorrection = 90;    // Maximum angle to adjust when outside WallDistanceOffset margins

                // Get Variables:
                double RangeValue = range.cmUltrasonic();   // CHANGE with real sensor reading

                int newDirection;
                // Calculate new direction to move
                if (power >= 0) {
                    newDirection = direction + (int) (Range.clip((RangeValue - WallDistance) / WallDistanceOffset, -1, 1) * AngleCorrection);
                }
                else {
                    newDirection = direction - (int) (Range.clip((RangeValue - WallDistance) / WallDistanceOffset, -1, 1) * AngleCorrection);
                }
                telemetry.addData("New Direction", newDirection);
                telemetry.addData("Range", range.cmUltrasonic());

                move(newDirection, (float) power, myrot);
            }
            else if (wallFollow.equals("back")) {
                // Set Variables:
                float WallDistance = 38;       // Constant Distance from Wall
                float WallDistanceOffset = 10; // Constant Range from wall to adjust direction
                float AngleCorrection = 90;    // Maximum angle to adjust when outside WallDistanceOffset margins

                // Get Variables:
                double RangeValue = range3.getDistance(DistanceUnit.CM);

                // Calculate new direction to move
                int newDirection;
                // Calculate new direction to move
                if (power >= 0) {
                    newDirection = direction + (int) (Range.clip((RangeValue - WallDistance) / WallDistanceOffset, -1, 1) * AngleCorrection);
                }
                else {
                    newDirection = direction - (int) (Range.clip((RangeValue - WallDistance) / WallDistanceOffset, -1, 1) * AngleCorrection);
                }
                telemetry.addData("New Direction", newDirection);
                telemetry.addData("Range", range3.getDistance(DistanceUnit.CM));

                move(newDirection, (float) power, myrot);
            }
            else {
                move(direction, (float) power, myrot);
            }


            if (MagUp.getState() == true) {
                telemetry.addData("Switch UP ON","");
            } else {
                telemetry.addData("Switch UP OFF","");
            }
            if (MagDown.getState() == true) {
                telemetry.addData("Switch Down ON","");
            } else {
                telemetry.addData("Switch Down OFF","");
            }

            // DEBUG with Telemetry
            telemetry.addData("Directin Goal: ", direction);
            telemetry.addData("Gyro Position: ", gyroDegrees);
            telemetry.addData("Correction :   ",  CorrectionDegrees );
            telemetry.addData("MyRot -1/+1 :  ",  myrot);

            /*telemetry.addData("LF Rot:   ", Math.abs( leftFrontDrive.getCurrentPosition()));
            telemetry.addData("LF Power: ", Math.sin((direction + 135) / 180.0 * Math.PI));
            telemetry.addData("RF Rot:   ", Math.abs(rightFrontDrive.getCurrentPosition()));
            telemetry.addData("RF Power: ", Math.sin((direction + 45) / 180.0 * Math.PI));
            telemetry.addData("LB Rot:   ", Math.abs(  leftBackDrive.getCurrentPosition()));
            telemetry.addData("LB Power: ", Math.sin((direction + 45) / 180.0 * Math.PI));
            telemetry.addData("RB Rot:   ", Math.abs( rightBackDrive.getCurrentPosition()));
            telemetry.addData("RB Power: ", Math.sin((direction + 135) / 180.0 * Math.PI));*/

            telemetry.addData("Position", (
                    Math.abs(leftFrontDrive.getCurrentPosition()) +
                            Math.abs(rightFrontDrive.getCurrentPosition()) +
                            Math.abs(leftBackDrive.getCurrentPosition()) +
                            Math.abs(rightBackDrive.getCurrentPosition()) )/ 4);
            telemetry.update();


        }

        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightBackDrive.setPower(0);

        sleep(wait);

    }


    public void mecanumMove(String direction, int value, double power, int wait) {
        resetEncoders();
        //say("113: mecanumMove");
        value *= ENCDISTANCE;
        //say("115: value");
        boolean done = false;

        //Get the current position of the robot
        orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
        //Get the current degree of the robot
        angle = orientation.firstAngle;

        //say("120: boolean done");
        if (direction.equals("x")) {
            while (opModeIsActive() && !done) {
                orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
                //say("" + value + " > " + Math.abs(((leftFrontDrive.getCurrentPosition() + rightBackDrive.getCurrentPosition()) - (rightFrontDrive.getCurrentPosition() + leftBackDrive.getCurrentPosition())) / 4));
                if (value > Math.abs(((leftFrontDrive.getCurrentPosition() + rightBackDrive.getCurrentPosition()) - (rightFrontDrive.getCurrentPosition() + leftBackDrive.getCurrentPosition())) / 4)) {

                        leftFrontDrive.setPower(power);
                        rightFrontDrive.setPower(-power);
                        leftBackDrive.setPower(-power);
                        rightBackDrive.setPower(power);

                }
                else {
                    leftFrontDrive.setPower(0);
                    rightFrontDrive.setPower(0);
                    leftBackDrive.setPower(0);
                    rightBackDrive.setPower(0);
                    done = true;
                }
            }
        }
        else if (direction.equals("y")) {
            //say("141: direction y");
            while (opModeIsActive() && !done) {
                orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
                //say("140: while opModeIsActive()");
                //say("" + value + " > " + Math.abs((leftFrontDrive.getCurrentPosition() + rightFrontDrive.getCurrentPosition() + leftBackDrive.getCurrentPosition() + rightBackDrive.getCurrentPosition()) / 4));
                    if (value > Math.abs((leftFrontDrive.getCurrentPosition() + rightFrontDrive.getCurrentPosition() + leftBackDrive.getCurrentPosition() + rightBackDrive.getCurrentPosition()) / 4)) {
                    //say("144: if value");
                            leftFrontDrive.setPower(-power);
                            rightFrontDrive.setPower(-power);
                            leftBackDrive.setPower(-power);
                            rightBackDrive.setPower(-power);
                }
                else {
                    //say("152: else");
                    leftFrontDrive.setPower(0);
                    rightFrontDrive.setPower(0);
                    leftBackDrive.setPower(0);
                    rightBackDrive.setPower(0);
                    done = true;
                }
                telemetry.addData("Left Front", leftFrontDrive.getCurrentPosition());
                    telemetry.addData("Right Front", rightFrontDrive.getCurrentPosition());
                    telemetry.addData("Left Back", leftBackDrive.getCurrentPosition());
                    telemetry.addData("Right Back", rightBackDrive.getCurrentPosition());
                    telemetry.update();
            }
        }
        sleep(wait);
    }

    public void resetEncoders() {
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //sleep(1000);

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();



        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        //parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "camera");


        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    public void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);

        tfodParameters.minimumConfidence = 0.40;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
        tfod.activate();
    }

    public String detectBlock() {
        //double getTime = getRuntime();
        //while (opModeIsActive() && getRuntime() - getTime <= 3) {
            if (tfod != null) {
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    float goldPos = -1;
                    float silvPos = -1;
                    float silv2Pos = -1;
                    float width = 0;
                    for (Recognition recognition : updatedRecognitions) {
                        width = recognition.getImageWidth();
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldPos = recognition.getLeft();
                        }
                        else if (silvPos == -1) {
                            silvPos = recognition.getLeft();
                        }
                        else {
                            silv2Pos = recognition.getLeft();
                        }
                    }

                    //DETECT TWO, ASSUME RIGHT TWO
                    if (silv2Pos == -1) {
                        if (goldPos < silvPos) {
                            return "center";
                        } else {
                            return "right";
                        }
                    }

                    //DETECT THREE
                    else {
                        if (goldPos < silvPos) {
                            if (goldPos < silv2Pos) {
                                return "left";
                            } else {
                                return "center";
                            }
                        } else {
                            if (goldPos > silv2Pos) {
                                return "right";
                            } else {
                                return "center";
                            }
                        }
                    }


                    /*if (goldPos == -1) {
                        return "right";
                    }
                    else if (silvPos == -1) {
                        if (goldPos < width / 3) {
                            return "left";
                        }
                        else if (goldPos < width / 3 * 2) {
                            return "center";
                        }
                        else {
                            return "right";
                        }
                    }
                    else if (goldPos > silvPos && silv2Pos == -1) {
                        return "center";
                    }
                    else if (goldPos > silvPos && goldPos > silv2Pos) {
                        return "right";
                    }
                    else if (goldPos < silvPos && goldPos > silv2Pos) {
                        return "center";
                    }
                    else if (goldPos < silvPos && goldPos < silv2Pos) {
                        return "left";
                    }
                    else {
                        return "left";
                    }
                    /*
                    say("# Object Detected: " + updatedRecognitions.size());
                    if (updatedRecognitions.size() == 3) {
                        int goldMineralX = -1;
                        int silverMineral1X = -1;
                        int silverMineral2X = -1;
                        for (Recognition recognition : updatedRecognitions) {
                            if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                goldMineralX = (int) recognition.getLeft();
                            } else if (silverMineral1X == -1) {
                                silverMineral1X = (int) recognition.getLeft();
                            } else {
                                silverMineral2X = (int) recognition.getLeft();
                            }
                        }
                        if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                            if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                                return "left";
                            }
                            else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                return "right";
                            }
                            else {
                                return "center";
                            }
                        }
                    }
                    else {
                        if
                    }*/
                }
                return "center";
            }
            return "center";
        //}
        //return "center";
    }

    public void say(String text) {
        telemetry.addLine(text);
        telemetry.update();
    }
}
