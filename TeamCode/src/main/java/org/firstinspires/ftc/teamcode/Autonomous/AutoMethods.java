//Declare the package
package org.firstinspires.ftc.teamcode.Autonomous;

//Import the dependencies needed to run the program
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
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
        //Set the direction of the motors
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        //Set the mode the motors are going to be running in
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //Add the gyroscope to the configuration on the phones
        navxGyro = hardwareMap.get(NavxMicroNavigationSensor.class, "gyro");

        //Wait for the gyroscope to stop calibrating
        while(navxGyro.isCalibrating()) {

            //Display on the screen the fact the gyroscope is calibrating
            telemetry.addLine("navx Calibrating");
            //Update telemetry
            telemetry.update();
        }

        //Display on the screen the fact the gyroscope has finished calibrating
        telemetry.addLine("init complete");
        //Update telemetry
        telemetry.update();
    }

    //Create the method to move the robot forwards based on direction and power of the motors
    public void move(double angle1, double power) {
        if (leftFrontDrive != null && leftBackDrive != null && rightFrontDrive != null && rightBackDrive != null) {
            leftFrontDrive.setPower(Range.clip(power * ((Math.sin((angle1 + 45) / 180 * Math.PI))), -1, 1));
            leftBackDrive.setPower(Range.clip(power * ((Math.sin((angle1 - 45) / 180 * Math.PI))), -1, 1));
            rightFrontDrive.setPower(Range.clip(power * ((Math.sin((angle1 - 45) / 180 * Math.PI))), -1, 1));
            rightBackDrive.setPower(Range.clip(power * ((Math.sin((angle1 + 45) / 180 * Math.PI))), -1, 1));
        }
    }

    //Create the method to turn the robot based on the degree value set and the current position of the robot
    public void turn(double degrees) {

        //Create a variable power of the motor that gets slower the closer the robot is to the set degree
        double power = Range.clip(360.0/(double)(degrees),0.2,1);

        //Get the current position of the robot
        orientation = navxGyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
        //Get the current degree of the robot
        angle = orientation.firstAngle;

        //While the difference between the target angle and current angle is greater than three degrees
        while (Math.abs(angle - degrees) > 3) {

            //If the target degree is greater than the current angle of the robot, turn right
            if (degrees > angle) {
                leftFrontDrive.setPower(-power);
                rightFrontDrive.setPower(power);
                leftBackDrive.setPower(-power);
                rightBackDrive.setPower(power);
            }

            //If the target degree is greater than the current angle of the robot, turn left
            if (degrees < angle) {
                leftFrontDrive.setPower(power);
                rightFrontDrive.setPower(-power);
                leftBackDrive.setPower(-power);
                rightBackDrive.setPower(power);
            }

            //Get the current position of the robot
            orientation = navxGyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
            //Get the current degree of the robot
            angle = orientation.firstAngle;

            //Display the target degree the robot is going to move to on the screen
            telemetry.addLine("Target degree: " + (int)(degrees));
            //Display the current degree of the robot on the screen
            telemetry.addLine("Current degree: " + (int)(angle));
            //Update telemetry
            telemetry.update();
        }
    }

    public void mecanumMove(String direction, int value, double power) {
        resetEncoders();
        if (direction.equals("x")) {
            while (value > Math.abs(((leftFrontDrive.getCurrentPosition() + rightBackDrive.getCurrentPosition()) - (rightFrontDrive.getCurrentPosition() + leftBackDrive.getCurrentPosition())) / 4)) {
                leftFrontDrive.setPower(power);
                rightFrontDrive.setPower(power);
                leftBackDrive.setPower(power);
                rightBackDrive.setPower(power);
            }
        }
        else if (direction.equals("y")) {
            while (value > Math.abs((leftFrontDrive.getCurrentPosition() + rightFrontDrive.getCurrentPosition() + leftBackDrive.getCurrentPosition() + rightBackDrive.getCurrentPosition()) / 4)) {
                leftFrontDrive.setPower(-power);
                rightFrontDrive.setPower(power);
                leftBackDrive.setPower(power);
                rightBackDrive.setPower(-power);
            }
        }
        else {
            while (value > Math.abs(((leftFrontDrive.getCurrentPosition() + leftBackDrive.getCurrentPosition()) - (rightFrontDrive.getCurrentPosition() + rightBackDrive.getCurrentPosition())) / 4)) {
                leftFrontDrive.setPower(power);
                rightFrontDrive.setPower(-power);
                leftBackDrive.setPower(power);
                rightBackDrive.setPower(-power);
            }
        }
    }

    public void resetEncoders() {
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    public void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    public String detectBlock() {
        boolean found = false;

        if (tfod != null) {
            tfod.activate();
        }

        double time = getRuntime();

        while (!found && getRuntime() - time < 5) {
            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    telemetry.addData("# Object Detected", updatedRecognitions.size());
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
                                //telemetry.addData("Gold Mineral Position", "Left");
                                found = true;
                                tfod.shutdown();
                                return "left";
                            }
                            else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                //telemetry.addData("Gold Mineral Position", "Right");
                                found = true;
                                tfod.shutdown();
                                return "right";
                            }
                            else {
                                //telemetry.addData("Gold Mineral Position", "Center");
                                found = true;
                                tfod.shutdown();
                                return "center";
                            }
                        }
                    }
                    telemetry.update();
                }
            }
        }
        return "";
    }
}
