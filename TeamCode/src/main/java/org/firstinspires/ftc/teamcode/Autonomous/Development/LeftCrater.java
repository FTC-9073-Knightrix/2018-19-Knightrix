package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@Autonomous(name = "LeftCrater")
@Disabled

public class LeftCrater extends LandingTest {
    public void runOpMode() {
        landing();

        // Forward to Starting Position
        //gyroMove(0, 0.5, 15, 0, "no");

        // === LEFT ==
        gyroMove(60,0.8, 50,0, "no");
        gyroMove(135,0.8, 20,0, "no");
            // === END LEFT ===


        // LEFT to get out of sampling area
        //gyroMove(90,0.8, 10,100, "no");

        // TURN RIGHT w/back to align to wall
        turn(-45, -0.8);

        while (opModeIsActive() && range.cmUltrasonic() > 15) {
            leftFrontDrive.setPower(-0.4);
            leftBackDrive.setPower(0.4);
            rightFrontDrive.setPower(0.4);
            rightBackDrive.setPower(-0.4);
        }

        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);

        // Move following wall to drop area
        gyroMove(0,-0.8, 150,80, "left");

        // Servo drop
        // Out of wall
        gyroMove(-90,.8, 15,100, "no");
        //Drop
        marker.setPosition(0.55); //0.55 Down ; 0.20 IN
        sleep(800);
        marker.setPosition(0.25); //0.55 Down ; 0.20 IN
        // forward
        gyroMove(0,.8, 30,0, "no");
        //ServoUP




        // left
        //gyroMove((int) 45,1, 30,0, "no");


        // move to park
        gyroMove(0,1, 140,70, "left");

        turn(-135, -0.8);

        gyroMove(90, 1, 10, 0, "no");

        // MISSING:
        // Turn 90 degrees to the right to align with field for TeleOp

    }
}
