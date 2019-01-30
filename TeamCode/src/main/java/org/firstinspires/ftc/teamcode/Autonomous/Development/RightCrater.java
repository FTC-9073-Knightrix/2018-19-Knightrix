package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@Autonomous(name = "RightCrater")
@Disabled

public class RightCrater extends LandingTest {
    public void runOpMode() {
        landing();

        // Forward to Starting Position
        //gyroMove(0, 0.5, 15, 0, "no");


        // === RIGHT ==
        gyroMove(-60,1, 50,0, "no");
        gyroMove(135,1, 20,0, "no");

        // LEFT to get out of sampling area
        gyroMove(90,1, 80,0, "no");

        // TURN RIGHT w/back to align to wall
        turn(-45, -1);

        while (opModeIsActive() && range.cmUltrasonic() > 15) {
            leftFrontDrive.setPower(-0.8);
            leftBackDrive.setPower(0.8);
            rightFrontDrive.setPower(0.8);
            rightBackDrive.setPower(-0.8);
        }

        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);

        // Move following wall to drop area
        gyroMove(0,-1, 150,0, "left");

        // Servo drop
        marker.setPosition(0.55); //0.55 Down ; 0.20 IN
        // Out of wall
        gyroMove(-90,1, 15,0, "no");
        //Drop


        sleep(400);

        marker.setPosition(0.25); //0.55 Down ; 0.20 IN
        // forward
        gyroMove(0,1, 30,0, "no");
        //ServoUP
        //marker.setPosition(0.25); //0.55 Down ; 0.20 IN
        // left
        //gyroMove((int) 45,1, 30,0, "no");



        // move to park
        gyroMove(0,1, 130,0, "left");

        turn(-135,-1);

        gyroMove(90,1, 60,0, "no");
        // MISSING:
        // Turn 90 degrees to the right to align with field for TeleOp

    }
}
