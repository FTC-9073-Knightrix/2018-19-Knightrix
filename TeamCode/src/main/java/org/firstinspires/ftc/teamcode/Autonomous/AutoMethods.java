//Declare the package
package org.firstinspires.ftc.teamcode.Autonomous;

//Import the dependencies needed to run the program
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

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
    public void move(String direction, double power) {

        //If the direction set is forwards, set the directions of the motors appropriately
        if (direction.toLowerCase().equals("front")) {
            leftFrontDrive.setPower(power);
            rightFrontDrive.setPower(power);
            leftBackDrive.setPower(power);
            rightBackDrive.setPower(power);
        }

        //If the direction set is backwards, set the directions of the motors appropriately
        else if (direction.toLowerCase().equals("back")) {
            leftFrontDrive.setPower(-power);
            rightFrontDrive.setPower(-power);
            leftBackDrive.setPower(-power);
            rightBackDrive.setPower(-power);
        }

        //If the direction set is to the left, set the directions of the motors appropriately
        else if (direction.toLowerCase().equals("left")) {
            leftFrontDrive.setPower(-power);
            rightFrontDrive.setPower(power);
            leftBackDrive.setPower(power);
            rightBackDrive.setPower(-power);
        }

        //If the direction set is to the right, set the directions of the motors appropriately
        else if (direction.toLowerCase().equals("right")) {
            leftFrontDrive.setPower(power);
            rightFrontDrive.setPower(-power);
            leftBackDrive.setPower(-power);
            rightBackDrive.setPower(power);
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
}
