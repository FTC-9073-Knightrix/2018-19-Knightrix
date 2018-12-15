package org.firstinspires.ftc.teamcode.Autonomous.Master;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

/**
 * Created by nicolas on 11/21/18.
 */

@Autonomous(name = "Blue Right 1", group = "Master")

public class BlueRight1 extends AutoMethods {
    public void runOpMode() {
        initVuforia();
        initTfod();
        initRobot();
        waitForStart();

        String block = detectBlock();
        say(block);

        while (opModeIsActive() && MagDown.getState()) {
            hangMotor.setPower(-1);
        }
        hangMotor.setPower(0);

        gyroMove(-90,0.5, 8, 200, "no");
        gyroMove(0, 0.7, 30, 200, "no");

        if (block.equals("left")) {
            //move left
            gyroMove(90, 0.5, 40, 200, "no");
            //go forwards
            gyroMove(0, 0.8, 50, 200, "no");
            //turn to be parallel to the wall
            turn(-45, -0.4);
            //move along wall
            gyroMove(0, 1, 55, 200, "left");
            //set marker down
            marker.setPosition(0);
            //turn to face other wall
            turn(-135, -0.4);
            //set marker back up
            marker.setPosition(1);
            //move along long stretch of wall to crater
            gyroMove(0, 1, 175, 200, "left");
            //set down marker
            marker.setPosition(0);
            //turn to set up for TeleOp
            turn(145, -0.3);
        }
        else if (block.equals("right")) {
            //move left
            gyroMove(-90, 0.5, 40, 200, "no");
            //go forwards
            gyroMove(0, 0.8, 70, 200, "no");
            //turn to set up drop of marker
            turn(-45, -0.4);
            //strafe to marker drop off
            gyroMove(90, 0.5, 20, 200, "no");
            //set marker down
            marker.setPosition(0);
            //wait for marker to fall
            sleep(2000);
            //set marker back up
            marker.setPosition(1);
            //turn to face other wall
            turn(-135, -0.4);
            //move along long stretch of wall to crater
            gyroMove(0, 1, 160, 200, "left");
            //set down marker
            marker.setPosition(0);
            //turn to set up for TeleOp
            turn(145, -0.3);
        }
        else {
            //go forwards
            gyroMove(0, 0.8, 90, 200, "no");
            //set marker down
            marker.setPosition(0);
            //turn to set up drop of marker
            turn(-45, -0.4);
            //set marker back up
            marker.setPosition(1);
            //turn to face other wall
            turn(-135, -0.4);
            //move along long stretch of wall to crater
            gyroMove(0, 1, 165, 200, "left");
            //set down marker
            marker.setPosition(0);
            //turn to set up for TeleOp
            turn(145, -0.3);
        }
    }
}
