//Declare the package
package org.firstinspires.ftc.teamcode.Autonomous.Development;

//Import the dependencies needed to run the program
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

//Add the program to the index of Autonomous programs
@Disabled
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
        runtime.reset();

        //While the program is running and the timer is less than 2 seconds
        while (opModeIsActive() && runtime.seconds() < 2) {
            //Move forwards at half speed
            //move("front", 0.5);
        }

        //Pause the robot for 0.2 seconds
        sleep(200);

        //Reset the timer
        runtime.reset();

        //While the program is running and the timer is less than 2 seconds
        while (opModeIsActive() && runtime.seconds() < 2) {
            //Move left at half speed
            //move("left", 0.5);
        }

        //Pause the robot for 0.2 seconds
        sleep(200);

        //Reset the timer
        runtime.reset();

        //While the program is running and the timer is less than 2 seconds
        while (opModeIsActive() && runtime.seconds() < 2) {
            //Move backwards at half speed
            //move("back", 0.5);
        }

        //Pause the robot for 0.2 seconds
        sleep(200);

        //Reset the timer
        runtime.reset();

        //While the program is running and the timer is less than 2 seconds
        while (opModeIsActive() && runtime.seconds() < 2) {
            //Move right at half speed
            //move("right", 0.5);
        }

        //Pause the robot for 1 second
        sleep(1000);

        //Turn the robot 90 degrees to the right
        //turn(90);

        //Pause the robot for 1 second
        sleep(1000);

        //Turn the robot 180 degrees to the left
        //turn(-180);
    }
}