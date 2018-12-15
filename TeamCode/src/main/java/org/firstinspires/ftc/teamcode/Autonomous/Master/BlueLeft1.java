package org.firstinspires.ftc.teamcode.Autonomous.Master;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

/**
 * Created by nicolas on 11/21/18.
 */

@Autonomous(name = "Blue Left", group = "Master")

public class BlueLeft1 extends AutoMethods {
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
        }
        else if (block.equals("right")) {
            //move left
            gyroMove(-90, 0.5, 40, 200, "no");
        }

        //go forwards
        gyroMove(0, 0.8, 10, 200, "no");
        //turn to set up for TeleOp
        turn(-135, -0.3);
        //move diagonally
        gyroMove(135, 0.4, 5, 0, "no");
    }
}
