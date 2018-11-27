package org.firstinspires.ftc.teamcode.Autonomous.Master;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

/**
 * Created by nicolas on 11/21/18.
 */

@Autonomous(name = "Blue Left 1", group = "Master")

public class BlueLeft1 extends AutoMethods {
    public void runOpMode() {
        initVuforia();
        initTfod();
        initRobot();
        waitForStart();

        //String block = detectBlock();
        String block = "center";
        say(block);
        //drop the robot off the hook
        say("Motors at 100%");

        leftFrontDrive.setPower(-1);
        rightFrontDrive.setPower(1);
        leftBackDrive.setPower(1);
        rightBackDrive.setPower(-1);

        say("Sleeping 2000");
        sleep(2000);

        say("Motors at 20%");
        leftFrontDrive.setPower(-0.2);
        rightFrontDrive.setPower(0.2);
        leftBackDrive.setPower(0.2);
        rightBackDrive.setPower(-0.2);

        sleep(2000);

        say("Motors OFF");
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightBackDrive.setPower(0);

        sleep(2000);

        mecanumMove("y", 200, 0.2, 500);

        if (block.equals("left")) {
            mecanumMove("x", 500, -0.1, 200);
            mecanumMove("y", 500, 1, 200);
            mecanumMove("y", 500, -0.1, 200);
            mecanumMove("x", 500, 1, 200);
        }
        else if (block.equals("right")) {
            mecanumMove("x", 100, 0.5, 200);
            mecanumMove("y", 500, 0.5, 200);
            mecanumMove("y", 500, -0.5, 200);
            mecanumMove("x", 100, -0.5, 200);
        }
        else {
            mecanumMove("y", 500, 0.5, 200);
            mecanumMove("y", 500, -0.5, 200);
        }

        mecanumMove("x", 500, -0.5, 200);
        turn(-120);
        mecanumMove("y", 500, 0.5, 200);
        //drop the marker
        turn(60);
        mecanumMove("y", 1000, 0.5, 0);
        //extend out arm
    }
}
