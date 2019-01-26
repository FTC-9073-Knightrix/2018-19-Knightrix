package org.firstinspires.ftc.teamcode.Autonomous.Development;

public class LeftMarker extends LandingTest {
    public void runOpMode() {
        landing();

        gyroMove(45, 0.5, 70, 200, "no");

        turn(-45, -0.3);

        gyroMove(0, 0.5, 30, 200, "no");

        marker.setPosition(0.55);

        sleep(1000);

        marker.setPosition(0.2);

        gyroMove(0, -0.8, 23, 0, "no");

        gyroMove(0, -0.8, 140, 200, "left");

        turn(135, -0.3);
    }
}