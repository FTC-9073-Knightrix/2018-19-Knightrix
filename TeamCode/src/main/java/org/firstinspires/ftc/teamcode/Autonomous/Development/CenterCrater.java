package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@Autonomous(name = "CenterCrater")
@Disabled

public class CenterCrater extends LandingTest {
    public void runOpMode() {
        landing();

        // Forward to Starting Position
        gyroMove(0, 0.5, 35, 0, "no");
            // Back half way to opening
            gyroMove((int) 180, 0.5, 25, 0, "no");
            // LEFT to get out of sampling area
        gyroMove(90,0.8, 80,0, "no");

        // TURN RIGHT w/back to align to wall
        turn(-45, -0.8);

        // Move following wall to drop area
        gyroMove(0,-0.8, 145,100, "left");

        // Servo drop
        // Out of wall
        gyroMove(-90,.8, 15,100, "no");
        //Drop
        marker.setPosition(0.55); //0.55 Down ; 0.20 IN

        sleep(800);

        // forward
        gyroMove(0,.8, 30,0, "no");
        //ServoUP
        marker.setPosition(0.25); //0.55 Down ; 0.20 IN
        // left
        //gyroMove((int) 45,1, 30,0, "no");
        // move to park
        gyroMove(0,1, 140,0, "left");

        turn(-135, -0.8);

        gyroMove(90, 1, 10, 0, "no");

        // MISSING:
        // Turn 90 degrees to the right to align with field for TeleOp

    }
}
