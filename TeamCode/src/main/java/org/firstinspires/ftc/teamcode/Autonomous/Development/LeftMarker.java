package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "LeftMarker", group = "Marker")

public class LeftMarker extends LandingTest {
    public void runOpMode() {
        landing();

        gyroMove(60, 0.5, 70, 0, "no");

        turn(-45, -0.3);

        gyroMove(0, 0.5, 80, 0, "no");

        marker.setPosition(0.55);

        sleep(800);

        marker.setPosition(0.2);

        gyroMove(0, -1, 80, 0, "no");

        turn(135, -0.3);

        gyroMove(0, 0.3, 20, 0, "right");

        gyroMove(0, 0.8, 80, 0, "right");
    }
}