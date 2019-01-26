package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "CenterMarker", group = "Marker")

public class CenterMarker extends LandingTest {
    public void runOpMode() {
        //BEGIN GO RIGHT

        /*landing();

        gyroMove(0, 0.8, 115, 200, "no");

        turn(-45, -0.3);

        marker.setPosition(1);

        sleep(1500);

        marker.setPosition(0);

        turn(-135, -0.3);

        sleep(200);

        gyroMove(0, 1, 173, 0, "left");

        turn(135, -0.3);

        marker.setPosition(1);

        gyroMove(90, 1, 5, 0, "no");*/

        //BEGIN GO LEFT

        landing();

        gyroMove(0, 0.8, 115, 200, "no");

        turn(-45, -0.3);

        marker.setPosition(1);

        sleep(1500);

        marker.setPosition(0);

        sleep(200);

        gyroMove(180, 1, 173, 0, "left");

        turn(-135, -0.3);

        gyroMove(-90, 1, 5, 0, "no");
    }
}