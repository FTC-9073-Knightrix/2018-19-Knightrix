package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by nicolas on 1/5/19.
 */

@Autonomous(name = "Left", group = "Test")

public class LeftSample extends LandingTest {
    public void runOpMode() {
        landing();

        gyroMove(45, 0.5, 25, 0, "no");
    }
}
