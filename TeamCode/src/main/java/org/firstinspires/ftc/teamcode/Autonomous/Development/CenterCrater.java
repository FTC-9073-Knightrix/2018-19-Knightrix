package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@Autonomous(name = "Crater_Select", group = "Crater")

public class CenterCrater extends LandingTest {
    public void runOpMode() {
        landing();

        // Forward to Starting Position
        gyroMove((int) 0, 0.5, 15, 0, "no");

        //Get the current position of the robot
        orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
        //Get the current degree of the robot
        angle = orientation.firstAngle;

        sleep(2000);
        int DistanceToWall;

        // Determine if cube is on LEFT, CENTER or RIGHT
        if ( gamepad1.dpad_left ) {
            // === LEFT ==
            gyroMove((int) 45,0.8, 80,0, "no");
            gyroMove((int) 135,0.8, 80,0, "no");
            DistanceToWall = 10;
            // === END LEFT ===
        } else if (gamepad1.dpad_right) {
            // === RIGHT ==
            gyroMove((int) -45,0.8, 80,0, "no");
            gyroMove((int) 135,0.8, 80,0, "no");
            DistanceToWall = 80;
            // === END RIGHT ===
        } else { // "CENTER"
            // ==== CENTER ====
            // Forward to Cube
            gyroMove((int) 0, 0.5, 20, 100, "no");
            // Back half way to opening
            gyroMove((int) 180, 0.5, 15, 0, "no");
            DistanceToWall = 80;
            // ==== END CENTER ===
        }


        // Aligns before leaving sampling area
        if( angle < 0 ) {     // tilted towards crater => turn left
            turn(0,.6);
        } else {
            turn(0, -.6);
        }

        // LEFT to get out of sampling area
        gyroMove((int) 90,0.8, (int) DistanceToWall,100, "no");

        // TURN RIGHT w/back to align to wall
        turn(-45, -0.4);

        // Move following wall to drop area
        gyroMove((int) 0,-0.8, 145,100, "left");

        // Servo drop
            // Out of wall
            gyroMove((int) -90,.8, 15,100, "no");
            //Drop
            marker.setPosition(0.55); //0.55 Down ; 0.20 IN
            // forward
            gyroMove((int) 0,.8, 30,0, "no");
            //ServoUP
            marker.setPosition(0.25); //0.55 Down ; 0.20 IN
            // left
            //gyroMove((int) 45,1, 30,0, "no");

        // Align robot
        //Get the current position of the robot
        orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
        //Get the current degree of the robot
        angle = orientation.firstAngle;

        if( angle < -45 ) {     // tilted towards crater => turn left
            turn(-45,.4);
        } else {
            turn(-45, -.4);
        }

        // move to park
        gyroMove((int) 00,1, 150,100, "left");

        // MISSING:
        // Turn 90 degrees to the right to align with field for TeleOp

    }
}
