//Declare the package
package org.firstinspires.ftc.teamcode.TeleOp.Development;

//Import the dependencies needed to run the program
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.TeleOp.TeleOpMethods;

//Add the program to the index of TeleOp programs under the Development group
@TeleOp(name = "Test Board", group = "Development")

//Create the class declaration, extending TeleOpMethods
public class TestBoard extends TeleOpMethods {

    //Run the entire program from within the loop() method from OpMode
    public void loop() {

        //Set the power of the left motor to the left joystick y
        leftFrontDrive.setPower(gamepad1.left_stick_y);

        //Set the power of the right motor to the right joystick y
        rightFrontDrive.setPower(gamepad1.right_stick_y);
    }
}
