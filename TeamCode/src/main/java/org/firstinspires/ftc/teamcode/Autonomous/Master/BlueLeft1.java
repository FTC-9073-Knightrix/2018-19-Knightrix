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

        String block = detectBlock();
        //String block = "center";
        say(block);

        mecanumMove("y", 33, 0.4, 100);

        if (block.equals("left")) {
            mecanumMove("x", 35, 0.4, 100);
            mecanumMove("y", 28, 0.7, 100);
            mecanumMove("y", 21, -0.7, 100);
            mecanumMove("x", 50, 0.6, 100);
        }
        else if (block.equals("right")) {
            mecanumMove("x", 41, -0.6, 100);
            mecanumMove("y", 28, 0.7, 100);
            mecanumMove("y", 21, -0.7, 100);
            mecanumMove("x", 138, 0.6, 100);
        }
        else {
            mecanumMove("y", 28, 0.7, 100);
            mecanumMove("y", 21, -0.7, 100);
            mecanumMove("x", 94, 0.6, 100);
        }

        turn(-45);
        mecanumMove("y", 115, -0.7, 100);
        turn(-45);
        mecanumMove("x", 20, -0.4, 100);
        marker.setPosition(0.1);
        sleep(500);
        marker.setPosition(0.05);
        turn(-45);
        mecanumMove("y", 65, 0.7, 100);
        turn(-45);
        mecanumMove("x", 32, 0.4, 100);
        //drop the marker
        turn(-45);
        mecanumMove("y", 120, 0.7, 0);
        //extend out arm
    }
}
