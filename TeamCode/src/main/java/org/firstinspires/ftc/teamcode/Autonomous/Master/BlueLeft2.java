package org.firstinspires.ftc.teamcode.Autonomous.Master;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

/**
 * Created by nicolas on 11/21/18.
 */

public class BlueLeft2 extends AutoMethods {
    public void runOpMode() {
        initVuforia();
        initTfod();

        waitForStart();

        if(opModeIsActive()) {
            String block = detectBlock();

            //drop the robot off the hook

            mecanumMove("y", 2000, 1);

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
            else {
                mecanumMove("y", 5000, 1);
                mecanumMove("y", 5000, -1);
            }
            /*else {
                //make a way to drop, turn, and quit the program
                stop();
            }*/

            turn(45);
            mecanumMove("y",5000,-1);
            turn(-45);
            mecanumMove("y", 5000, -1);
            turn(-45);
            mecanumMove("x", 5000, -1);
            mecanumMove("y", 5000, 1);
            //drop the marker
            mecanumMove("y", 10000, -1);
            //extend out arm
        }
    }
}
