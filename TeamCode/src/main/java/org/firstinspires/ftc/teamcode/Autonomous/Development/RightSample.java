package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by nicolas on 1/5/19.
 */

@Autonomous(name = "Right", group = "Test")

public class RightSample extends LandingTest {
    public void runOpMode() {
        landing();

        gyroMove(-45, 0.5, 50, 200, "no");

        turn(-135, -0.3);

        sleep(200);

        //Get the current position of the robot
        orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
        //Get the current degree of the robot
        angle = orientation.firstAngle;

        gyroMove((int) -angle,0.5, 10,0, "no");
    }
}
