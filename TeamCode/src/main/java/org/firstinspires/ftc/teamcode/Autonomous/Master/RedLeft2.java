package org.firstinspires.ftc.teamcode.Autonomous.Master;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

/**
 * Created by nicolas on 11/21/18.
 */

@Autonomous(name = "Red Left 2", group = "Master")

public class RedLeft2 extends AutoMethods {
    public void runOpMode() {
        initVuforia();
        initTfod();
        initRobot();
        waitForStart();

        String block = detectBlock();
        say(block);

        //drop the robot off the hook

        mecanumMove("y", 200, 0.5, 500);

        if (block.equals("left")) {
            mecanumMove("x", 100, -0.5, 200);
            mecanumMove("y", 500, 0.5, 200);
            mecanumMove("y", 500, -0.5, 200);
            mecanumMove("x", 100, 0.5, 200);
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

        turn(45);
        mecanumMove("y",500,-0.5, 200);
        turn(0);
        mecanumMove("y", 500, -0.5, 200);
        turn(-45);
        mecanumMove("x", 500, -0.5, 200);
        mecanumMove("y", 500, 0.5, 200);
        //drop the marker
        mecanumMove("y", 1000, -0.5, 0);
        //extend out arm
    }
}
