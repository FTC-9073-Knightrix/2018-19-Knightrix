//Declare the package
package org.firstinspires.ftc.teamcode.Autonomous.Development;

//Import the dependencies needed to run the program
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

//Add the program to the index of Autonomous programs

@Autonomous(name = "Box")

//Create the class declaration, extending AutoMethods
public class AutoTest extends AutoMethods {

    //Begin the program with the runOpMode() method of LinearOpMode
    public void runOpMode() {

        //Run our defined method initRobot() to initialize devices
        initRobot();

        //Wait for the driver to press start on the phone
        waitForStart();

        //Reset the timer
        //runtime.reset();

        //While the program is running and the timer is less than 2 seconds
        //while (opModeIsActive() && runtime.seconds() < 5) {
            //Move forwards at half speed
            gyroMove(0, 0.5, 300, 0, "no");
        //}
        say("" + (leftFrontDrive.getCurrentPosition() + rightFrontDrive.getCurrentPosition() + leftBackDrive.getCurrentPosition() + rightBackDrive.getCurrentPosition()) / 4);

        sleep(5000);
    }
}