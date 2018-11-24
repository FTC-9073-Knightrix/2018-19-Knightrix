package org.firstinspires.ftc.teamcode.Autonomous.Master;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

/**
 * Created by nicolas on 11/21/18.
 */

@Autonomous(name = "Red Right 1", group = "Master")

public class RedRight1 extends AutoMethods {
    public void runOpMode() {
        initVuforia();
        initTfod();

        waitForStart();

        if(opModeIsActive()) {
            String block = detectBlock();

            //drop the robot from the hook

            mecanumMove("y", 2000, 1);

            if (block.equals("left")) {
                mecanumMove("x", 1000, -0.5);
                mecanumMove("y", 5000, 1);
                mecanumMove("x", 1000, 0.5);
            }
            else if (block.equals("right")) {
                mecanumMove("x", 1000, 0.5);
                mecanumMove("y", 5000, 1);
                mecanumMove("x", 1000, -0.5);
            }
            else {
                mecanumMove("y", 5000, 1);
            }
            /*else {
                //make a way to drop, turn, and quit the program
                stop();
            }*/
            mecanumMove("y", 2000, 1);
            //drop the marker
            turn(120);
            mecanumMove("y", 10000,1);
            //extend out arm
        }
    }
}
