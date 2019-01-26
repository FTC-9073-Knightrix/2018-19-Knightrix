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

        // Forward to Cube
        gyroMove((int) -angle,0.5, 35,100, "no");

        // Back half way to opening
        gyroMove((int) 180,0.5, 10,0, "no");

        // LEFT to get out of sampling area
        gyroMove((int) 90,0.8, 80,100, "no");

        // TURN w/back to align to wall
        turn(0, 0.6);
        sleep(200);
        turn(45, 0.6);
        sleep(200);
        turn(90, 0.6);
        sleep(200);
        turn(0, 0.6);
        sleep(200);
        turn(-45, 0.6);
        sleep(200);
        turn(-90, 0.6);
        sleep(200);

        // Align to wall (?) might not be required
        // Move following wall to drop area
        //gyroMove((int) 135,0.8, 100,100, "left");

        // Servo drop
        // move to park

    }
}
