package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@Autonomous(name = "CenterCrater", group = "Crater")

public class CenterCrater extends LandingTest {
    public void runOpMode() {
        landing();

        //Get the current position of the robot
        orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
        //Get the current degree of the robot
        angle = orientation.firstAngle;

        // Determine if cube is on LEFT, CENTER or RIGHT
        // ==== CENTER ====
        // Forward to Cube
        gyroMove((int) -angle,0.5, 35,100, "no");

        // Back half way to opening
        gyroMove((int) 180,0.5, 10,0, "no");
        // ==== END CENTER ===


        // LEFT to get out of sampling area
        gyroMove((int) 90,0.8, 80,100, "no");

        // TURN RIGHT w/back to align to wall
        turn(-45, -0.4);

        // Move following wall to drop area
        gyroMove((int) 180,0.8, 120,100, "no");

        // Servo drop
            // Out of wall
            gyroMove((int) -90,1, 15,100, "no");
            //Drop
            marker.setPosition(0.55); //0.55 Down ; 0.20 IN
            // forward
            // left
            gyroMove((int) 45,1, 30,0, "no");
            //ServoUP
            marker.setPosition(0.25); //0.55 Down ; 0.20 IN

        // move to park
        gyroMove((int) 00,1, 150,100, "left");

    }
}
