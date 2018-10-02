//Declare the package
package org.firstinspires.ftc.teamcode.TeleOp;

//Import the dependencies needed to run the program
import com.qualcomm.robotcore.util.Range;

//Create the class declaration, extending TeleOpHardwareMap
public abstract class TeleOpMethods extends TeleOpHardwareMap {

    //Create the move method to move the robot based off the angle, power, and rotation of the robot applied
    public void move (double myangle, float mypower, float myrot) {

        //If none of the motors are null, run each motor to an individual value based off the values inputted from the joystick
        if (leftFrontDrive != null && leftBackDrive != null && rightFrontDrive != null && rightBackDrive != null) {
            leftFrontDrive.setPower(Range.clip(myrot + (mypower * ((Math.sin((myangle + 135) / 180 * 3.141592)))), -1, 1));
            leftBackDrive.setPower(Range.clip(myrot + (mypower * ((Math.sin((myangle + 45) / 180 * 3.141592)))), -1, 1));
            rightFrontDrive.setPower(Range.clip(-myrot + (mypower * ((Math.sin((myangle + 45) / 180 * 3.141592)))), -1, 1));
            rightBackDrive.setPower(Range.clip(-myrot + (mypower * ((Math.sin((myangle + 135) / 180 * 3.141592)))), -1, 1));
        }
    }
}
