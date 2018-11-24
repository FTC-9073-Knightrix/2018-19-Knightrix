//Declare the package
package org.firstinspires.ftc.teamcode.TeleOp.Development;

//Import the dependencies needed to run the program
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.TeleOp.TeleOpMethods;

//Add the program to the index of TeleOp programs under the Development group
@TeleOp(name = "Test Board", group = "Development")

//Create the class declaration, extending TeleOpMethods
public class TestBoard extends OpMode {

    DcMotor leftFrontDrive;
    //int position = 0;

    public void init() {
        leftFrontDrive = hardwareMap.dcMotor.get("lf");
        //leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    //Run the entire program from within the loop() method from OpMode
    public void loop() {
        /*if (gamepad1.right_stick_y != 0) {
            position += Math.round(gamepad1.right_stick_y * 10);
        }*/

        //Set the power of the left motor to the left joystick y
        //leftFrontDrive.setPower(1);
        leftFrontDrive.setPower(gamepad1.right_stick_y);
        //leftFrontDrive.setTargetPosition(position);
    }
}
