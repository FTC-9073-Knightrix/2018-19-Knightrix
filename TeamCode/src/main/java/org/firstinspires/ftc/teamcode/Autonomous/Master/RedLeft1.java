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

        mecanumMove("y", 33, 0.4, 1000);

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
            mecanumMove("y", 28, 0.4, 1000);
            mecanumMove("y", 23, -0.4, 1000);
            mecanumMove("x", 94, 0.4, 1000);
        }

        turn(-45);
        mecanumMove("y", 110, -0.4, 1000);
        //drop the marker
        turn(-45);
        mecanumMove("x", 20, -0.4, 1000);
        turn(-45);
        mecanumMove("y", 50, 0.4, 1000);
        turn(-45);
        mecanumMove("x", 20, 0.4, 1000);
        //marker.setPosition(90);
        //sleep(500);
        //marker.setPosition(0);

        turn(-45);
        mecanumMove("y", 120, 0.4, 1000);
        turn(-45);
        //marker.setPosition(90);
        //extend out arm
    }
}