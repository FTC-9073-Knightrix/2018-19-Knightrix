package org.firstinspires.ftc.teamcode.Autonomous.Master;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

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

        mecanumMove("y", 5000, 0.4, 2000);

        if (block.equals("left")) {
            mecanumMove("x", 500, -0.2, 2000);
            mecanumMove("y", 500, 0.2, 2000);
            mecanumMove("y", 500, -0.2, 2000);
            mecanumMove("x", 500, 0.4, 2000);
        }
        else if (block.equals("right")) {
            mecanumMove("x", 100, 0.2, 2000);
            mecanumMove("y", 500, 0.4, 2000);
            mecanumMove("y", 500, -0.4, 2000);
            mecanumMove("x", 100, -0.2, 2000);
        }
        else {
            mecanumMove("y", 500, 0.4, 2000);
            mecanumMove("y", 500, -0.4, 2000);
        }

        mecanumMove("x", 500, -0.4, 2000);
        turn(-120);
        mecanumMove("y", 500, 0.4, 2000);
        //drop the marker
        turn(60);
        mecanumMove("y", 1000, 1, 0);
        //extend out arm
    }
}
