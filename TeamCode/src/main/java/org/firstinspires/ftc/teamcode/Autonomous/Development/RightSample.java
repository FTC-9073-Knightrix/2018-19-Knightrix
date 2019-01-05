package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by nicolas on 1/5/19.
 */

@Autonomous(name = "Right", group = "Test")

public class RightSample extends LandingTest {
    public void runOpMode() {
        landing();

        gyroMove(-45, 0.5, 25, 0, "no");
    }
}
