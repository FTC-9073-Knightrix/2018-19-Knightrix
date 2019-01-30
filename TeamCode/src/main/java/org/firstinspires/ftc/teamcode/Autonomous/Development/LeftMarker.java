package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name = "LeftMarker", group = "Marker")
@Disabled

public class LeftMarker extends LandingTest {
    public void runOpMode() {
        landing();

        gyroMove(60, 0.8, 80, 0, "no");

        turn(-45, -0.8);

        gyroMove(0, 0.8, 90, 0, "no");

        marker.setPosition(0.55);

        sleep(800);

        marker.setPosition(0.2);

        gyroMove(0, -1, 50, 0, "no");

        turn(135, -0.8);

        while (opModeIsActive() && range2.cmUltrasonic() > 15) {
            leftFrontDrive.setPower(0.4);
            leftBackDrive.setPower(-0.4);
            rightFrontDrive.setPower(-0.4);
            rightBackDrive.setPower(0.4);
        }

        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);

        //gyroMove(0, 0.3, 20, 0, "right");

        gyroMove(0, 0.8, 130, 0, "right");
    }
}