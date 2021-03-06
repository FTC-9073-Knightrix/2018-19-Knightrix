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

    public void getController() {
        //Gamepad
        g1_leftstick_x = gamepad1.left_stick_x;
        g2_leftstick_x = gamepad2.left_stick_x / 5;

        g1_leftstick_y = gamepad1.left_stick_y;
        g2_leftstick_y = gamepad2.left_stick_y / 5;

        g1_rightstick_x = gamepad1.right_stick_x;
        g2_rightstick_x = gamepad2.right_stick_x / 5;


        //Gamepad buttons
        g2_dpad_down = gamepad2.dpad_down;
        g2_dpad_up   = gamepad2.dpad_up;
        g2_dpad_right= gamepad2.dpad_right;
        g2_dpad_left = gamepad2.dpad_left;

        //Gamepad bumpers
        g2_right_bumper = gamepad2.right_bumper;
        g2_left_bumper  = gamepad2.left_bumper;
    }

    public void drive() {
        getController();
        //Refresh the gyroscope value every three loops of the program
        //if (navxCounter == 3) {
            //Get the current position of the robot
        orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
            //Get the current angle of the robot, subtracting it by the previous value recorded
        //gyroDegrees = (int) (orientation.firstAngle - gyroResetValue);
        gyroDegrees = (int) orientation.firstAngle;
            //Reset the counter
            //navxCounter = 1;
        //}
        //else {
            //Add one to the counter
        //    navxCounter++;
        //}

        //Add the current angle of the robot to the display
        //telemetry.addLine("Gyro Value: " + orientation.firstAngle + "\u00b0");
        //Update telemetry
        //telemetry.update();

        //Get the rotation of the robot based off the position of the right joystick x

        if (g1_rightstick_x != 0) {
            myrot = Math.round(g1_rightstick_x * (float) 100) / (float) 100;
        }
        else {
            myrot = Math.round(g2_rightstick_x * (float) 100) / (float) 100;
        }

        if (g1_leftstick_x != 0) {
            //Declare the left joystick x
            leftstick_x = -g1_leftstick_x;
        }
        else {
            leftstick_x = -g2_leftstick_x;
        }
        if (g1_leftstick_y != 0) {
            //Declare the left joystick y
            leftstick_y = g1_leftstick_y;
        }
        else {
            leftstick_y = g2_leftstick_y;
        }

        //If the robot's previously recorded angle is within certain boundaries, change the direction of the robot
        /*if ((gyroResetValue > 45 && gyroResetValue < 135) || (gyroResetValue > 225 && gyroResetValue < 315)) {

            //Set the left joystick y as negative
            leftstick_y = -gamepad1.left_stick_y;
        }
        else {

            //Set the left joystick x as negative
            leftstick_x = -gamepad1.left_stick_x;
        }*/

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

        //telemetry.addData("Power", leftstick_y);

        //telemetry.update();

        move(myangle,mypower,myrot);
    }
}
