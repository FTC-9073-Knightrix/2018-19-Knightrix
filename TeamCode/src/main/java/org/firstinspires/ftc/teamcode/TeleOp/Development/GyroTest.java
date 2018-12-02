//Declare the package
package org.firstinspires.ftc.teamcode.TeleOp.Development;

//Import the dependencies needed to run the program
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.TeleOp.TeleOpMethods;

//Add the program to the index of TeleOp programs and to the Gyro group
@TeleOp(group = "Test", name = "Gyro")

//Create the class declaration, extending TeleOpMethods
public class GyroTest extends TeleOpMethods {

    //Run the entire program from within the loop() method from OpMode
    public void loop() {

        //Refresh the gyroscope value every three loops of the program
        //if (navxCounter == 3) {
            //Get the current position of the robot
        orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
            //Get the current angle of the robot, subtracting it by the previous value recorded
        //gyroDegrees = (int) (orientation.firstAngle - gyroResetValue);
            //Reset the counter
            /*navxCounter = 1;
        }
        else {
            //Add one to the counter
            navxCounter++;
        }*/

        //Add the current angle of the robot to the display
        telemetry.addLine("Gyro Value: " + orientation + "\u00b0");
        telemetry.addData("Left Front", leftFrontDrive.getCurrentPosition());
        telemetry.addData("Right Front", rightFrontDrive.getCurrentPosition());
        telemetry.addData("Left Back", leftBackDrive.getCurrentPosition());
        telemetry.addData("Right Back", rightBackDrive.getCurrentPosition());
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
}
