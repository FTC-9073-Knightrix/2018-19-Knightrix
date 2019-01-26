package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "RightMarker", group = "Marker")

public class RightMarker extends LandingTest {
    public void runOpMode() {
        landing();

        gyroMove(-45, 0.5, 70, 200, "no");

        turn(-135, -0.3);

        gyroMove(0, -0.5, 50, 200, "left");

        marker.setPosition(0.55);

        sleep(1000);

        marker.setPosition(0.2);

        gyroMove(-90, 0.5, 20, 200, "no");

        turn(135, -0.3);

        gyroMove(0, 0.5, 140, 200, "right");
    }
}
