//Declare the package
package org.firstinspires.ftc.teamcode.TeleOp;

//Import the dependencies needed to run the program
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

//Create the class declaration, extending TeleOpHardwareMap
public abstract class TeleOpMethods extends TeleOpHardwareMap {

    //Create the move method to move the robot based off the angle, power, and rotation of the robot applied
    public void move (double myangle, float mypower, float myrot) {

        //If none of the motors are null, run each motor to an individual value based off the values inputted from the joystick
        if (leftFrontDrive != null && leftBackDrive != null && rightFrontDrive != null && rightBackDrive != null) {
            leftFrontDrive.setPower(Range.clip((myrot + (mypower * ((Math.sin((myangle + 135) / 180 * 3.141592))))), -1, 1));
            leftBackDrive.setPower(Range.clip((myrot + (mypower * ((Math.sin((myangle + 45) / 180 * 3.141592))))), -1, 1));
            rightFrontDrive.setPower(Range.clip((-myrot + (mypower * ((Math.sin((myangle + 45) / 180 * 3.141592))))), -1, 1));
            rightBackDrive.setPower(Range.clip((-myrot + (mypower * ((Math.sin((myangle + 135) / 180 * 3.141592))))), -1, 1));
        }
    }

    public void drive() {
        //Refresh the gyroscope value every three loops of the program
        if (navxCounter == 3) {
            //Get the current position of the robot
            orientation = navxGyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
            //Get the current angle of the robot, subtracting it by the previous value recorded
            gyroDegrees = (int) (orientation.firstAngle - gyroResetValue);
            //Reset the counter
            navxCounter = 1;
        }
        else {
            //Add one to the counter
            navxCounter++;
        }

        //Add the current angle of the robot to the display
        telemetry.addLine("Gyro Value: " + orientation + "\u00b0");
        //Update telemetry
        telemetry.update();

        //Get the rotation of the robot based off the position of the right joystick x
        myrot = Math.round((gamepad1.right_stick_x / (float) 1) * (float) 100) / (float) 100;

        //Declare the left joystick x
        leftstick_x = gamepad1.left_stick_x;
        //Declare the left joystick y
        leftstick_y = gamepad1.left_stick_y;

        //If the robot's previously recorded angle is within certain boundaries, change the direction of the robot
        if ((gyroResetValue > 45 && gyroResetValue < 135) || (gyroResetValue > 225 && gyroResetValue < 315)) {

            //Set the left joystick y as negative
            leftstick_y = -gamepad1.left_stick_y;
        }
        else {

            //Set the left joystick x as negative
            leftstick_x = -gamepad1.left_stick_x;
        }

        //Calculate the angle of the joystick based off the x and y values
        //If the joystick is in quadrant I or IV
        if ((leftstick_x > 0 && leftstick_y > 0) || (leftstick_x > 0 && leftstick_y < 0)) {
            myangle = (int) (90 + Math.toDegrees(Math.atan(leftstick_y / leftstick_x)));
        }
        //If the joystick is in quadrant II or III
        else if ((leftstick_x < 0 && leftstick_y < 0) || (leftstick_x < 0 && leftstick_y > 0)) {
            myangle = (int) (270 + Math.toDegrees(Math.atan(leftstick_y / leftstick_x)));
        }
        //If the joystick is at the origin
        else if ((leftstick_x == 0 && leftstick_y == 0) || (leftstick_x == 0 && leftstick_y < 0)) {
            myangle = 0;
        }
        //If the joystick is on the x axis and x is positive
        else if (leftstick_x > 0  && leftstick_y == 0) {
            myangle = 90;
        }
        //If the joystick is on the y axis and y is positive
        else if (leftstick_x == 0 && leftstick_y > 0) {
            myangle = 180;
        }
        //If the joystick is on the x axis and the x is negative
        else if (leftstick_x < 0 && leftstick_y == 0) {
            myangle = 270;
        }
        //Determine the power of the motors based off the pythagorean theorem
        mypower = (float) Range.clip(Math.sqrt(leftstick_x*leftstick_x+leftstick_y*leftstick_y),0.0,1.0);

        //Limit the angle to within 0-365
        myangle -= gyroDegrees;
        if (myangle < -359) {
            myangle += 360;
        }

        //Move the robot based off the calculated values
        move(myangle,mypower,myrot);
    }

    public void intake () {
        //reset variables
        if (intakeNum == 0) {
            mineral1 = 0;
            mineral2 = 0;
            mineral3 = 0;
            color1 = "";
            color2 = "";
            color3 = "";
            intakeNum += 1;
        }

        /*//begin scoop
        if (intakeNum == 1) {
            intake.setPower(1);
            intakeNum += 1;
        }

        //detect and save color with degree
        if (intakeNum == 2) {
            if (color.red() > (2 * color.blue())) {
                mineral1 = spinner.getPosition();
                color1 = "yellow";
                intakeNum += 1;
            }
            else if (color.blue() > 0) {
                mineral1 = spinner.getPosition();
                color1 = "white";
                intakeNum += 1;
            }
        }

        //turn 120 deg
        if (intakeNum == 3) {
            spinner.setPosition(120);
            intakeNum += 1;
        }

        //repeat, 2nd time
        //detect and save color with degree
        if (intakeNum == 4) {
            if (color.red() > (2 * color.blue())) {
                mineral2 = spinner.getPosition();
                color2 = "yellow";
                intakeNum += 1;
            }
            else if (color.blue() > 0) {
                mineral2 = spinner.getPosition();
                color2 = "white";
                intakeNum += 1;
            }
        }

        //turn 120 deg
        if (intakeNum == 5) {
            spinner.setPosition(240);
            intakeNum += 1;
        }

        //repeat, 3rd time
        //detect and save color with degree
        if (intakeNum == 6) {
            if (color.red() > (2 * color.blue())) {
                mineral3 = spinner.getPosition();
                color3 = "yellow";
                intakeNum += 1;
            }
            else if (color.blue() > 0) {
                mineral3 = spinner.getPosition();
                color3 = "white";
                intakeNum += 1;
            }
        }

        //calculate odd one out (yellow or white) and remove
        if (intakeNum == 7) {
            if (color1.equals(color2)) {
                //kick out mineral3
                spinner.setPosition(mineral3);
                intake.setPower(-1);
                intakeNum += 1;
            }
            else {
                if (color1.equals(color3)) {
                    //kick out mineral2
                    spinner.setPosition(mineral2);
                    intake.setPower(-1);
                    intakeNum += 1;
                }
                else {
                    //kick out mineral1
                    spinner.setPosition(mineral1);
                    intake.setPower(-1);
                    intakeNum += 1;
                }
            }
        }

        //reset everything
        if (intakeNum == 8) {
            intakeNum = 0;
            intakeBool = false;
        }*/
    }
}
