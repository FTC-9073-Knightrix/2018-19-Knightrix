package org.firstinspires.ftc.teamcode.Autonomous.Master;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

/**
 * Created by nicolas on 11/21/18.
 */

@Autonomous(name = "Red Right 1", group = "Master")

public class RedRight1 extends AutoMethods {
    public void runOpMode() {
        initVuforia();
        initTfod();
        initRobot();
        waitForStart();

        //String block = detectBlock();
        //say(block);
        //drop the robot off the hook

        while (opModeIsActive() && MagDown.getState()) {
            hangMotor.setPower(-1);
        }
        hangMotor.setPower(0);

        gyroMove(-90,0.3, 8, 200, "no");

        while (opModeIsActive() && MagUp.getState()) {
            hangMotor.setPower(1);
        }
        hangMotor.setPower(0);

        gyroMove(90,0.3, 8, 200, "no");

        /*
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

        mecanumMove("x", 500, -0.5, 200);
        turn(-120);
        mecanumMove("y", 500, 0.5, 200);
        //drop the marker
        turn(60);
        mecanumMove("y", 1000, 0.5, 0);
        //extend out arm
        */
    }
}
