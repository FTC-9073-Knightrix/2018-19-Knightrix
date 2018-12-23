package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

@Autonomous(name = "Test_Move with Gyro")

public class GyroTest extends AutoMethods {
    public void runOpMode() {
        initRobot();
        waitForStart();

        say("Encoders ON - Gyro ON");

        //gyroMove(90, 0.3, 200, 0, "none");
        resetEncoders();
        sleep(500);
        gyroMove(0,0.5, 50, 2000, "no");
        gyroMove(90,0.5, 50, 2000, "no");
        gyroMove(180,0.5, 50, 2000, "no");
        gyroMove(270,0.5, 50, 2000, "no");


    }

}
