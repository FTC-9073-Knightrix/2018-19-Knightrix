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

        //while(opModeIsActive()) {
            String block = detectBlock();
            //String block = "center";
            say(block);
            //drop the robot off the hook

        //leftPower(1);
        say("1");
        mecanumMove("y", 200, -1);
        sleep(500);
        say("2");
        mecanumMove("y", 200, 1);
        sleep(500);
        say("3");
        mecanumMove("x", 200, 1);
        sleep(500);
        say("4");
        mecanumMove("x", 200, -1);

        /*while (opModeIsActive() && leftMove(2000)) {
            say("2000 <= " + Math.abs(((leftFrontDrive.getCurrentPosition() + rightBackDrive.getCurrentPosition()) - (rightFrontDrive.getCurrentPosition() + leftBackDrive.getCurrentPosition())) / 4));
        }
        say("Done");

        //boolean movedRobot = false;
        /*resetEncoders();
        while(opModeIsActive() && !robotDone) {
            robotDone = mecanumMove("y", 2000, 1);
            say("2000 vs. " + Math.abs((leftFrontDrive.getCurrentPosition() + rightFrontDrive.getCurrentPosition() + leftBackDrive.getCurrentPosition() + rightBackDrive.getCurrentPosition()) / 4));
        }
        robotDone = false;*/

        /*leftFrontDrive.setPower(1);
        rightFrontDrive.setPower(1);
        leftBackDrive.setPower(1);
        rightBackDrive.setPower(1);*/

            ///////////////////////////////////////mecanumMove("y", 2000, 1);

            /*telemetry.addLine("Moving...");
            telemetry.update();

            if (block.equals("left")) {
                mecanumMove("x", 1000, -0.5);
                mecanumMove("y", 5000, 1);
                mecanumMove("y", 5000, -1);
                mecanumMove("x", 1000, 0.5);
            }
            else if (block.equals("right")) {
                mecanumMove("x", 1000, 0.5);
                mecanumMove("y", 5000, 1);
                mecanumMove("y", 5000, -1);
                mecanumMove("x", 1000, -0.5);
            }
            else if (block.equals("center")) {
                mecanumMove("y", 5000, 1);
                mecanumMove("y", 5000, -1);
            }
            /*else {
                //make a way to drop, turn, and quit the program
                stop();
            }*/

            /*mecanumMove("x", 5000, -1);
            turn(-120);
            mecanumMove("y", 5000, 1);
            //drop the marker
            turn(60);
            mecanumMove("y", 10000, 1);
            //extend out arm
        *///}

    }
}
