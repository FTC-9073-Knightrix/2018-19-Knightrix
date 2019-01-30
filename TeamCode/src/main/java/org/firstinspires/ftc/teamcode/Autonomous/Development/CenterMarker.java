package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name = "CenterMarker", group = "Marker")
@Disabled

public class CenterMarker extends LandingTest {
    public void runOpMode() {
        landing();

        gyroMove(0, 0.8, 110, 200, "no");

        turn(-45, -0.3);

        marker.setPosition(0.55);

        sleep(1000);

        marker.setPosition(0.2);

        gyroMove(0, -0.8, 23, 0, "no");

        gyroMove(0, -0.8, 140, 200, "left");

        turn(135, -0.3);
    }
}