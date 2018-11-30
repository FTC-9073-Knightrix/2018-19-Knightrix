package org.firstinspires.ftc.teamcode.Autonomous.Master;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

/**
 * Created by nicolas on 11/21/18.
 */

@Autonomous(name = "Red Left 1", group = "Master")

public class RedLeft1 extends AutoMethods {
    public void runOpMode() {
        initVuforia();
        initTfod();
        initRobot();
        waitForStart();

        String block = detectBlock();
        //String block = "center";
        say(block);

        mecanumMove("y", 33, 0.4, 2000);

        if (block.equals("left")) {
            mecanumMove("x", 41, -0.2, 2000);
            mecanumMove("y", 28, 0.2, 2000);
            mecanumMove("y", 28, -0.2, 2000);
            mecanumMove("x", 50, -0.4, 2000);
        }
        else if (block.equals("right")) {
            mecanumMove("x", 41, 0.2, 2000);
            mecanumMove("y", 28, 0.4, 2000);
            mecanumMove("y", 28, -0.4, 2000);
            mecanumMove("x", 138, -0.2, 2000);
        }
        else {
            mecanumMove("y", 28, 0.4, 2000);
            mecanumMove("y", 28, -0.4, 2000);
            mecanumMove("x", 94, -0.4, 2000);
        }

        turn(-45);
        mecanumMove("y", 200, -0.4, 2000);
        //drop the marker
        turn(90);
        marker.setPosition(90);
        sleep(500);
        marker.setPosition(0);

        turn(-90);
        mecanumMove("y", 250, 0.5, 0);
        turn(-90);
        marker.setPosition(90);
        //extend out arm
    }
}