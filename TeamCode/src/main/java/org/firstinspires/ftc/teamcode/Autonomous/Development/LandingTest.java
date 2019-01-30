package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

/**
 * Created by nicolas on 1/5/19.
 */

@Autonomous(name = "Landing", group = "Test")
@Disabled

public class LandingTest extends AutoMethods {
    public void landing() {
        initRobot();
        waitForStart();

        marker.setPosition(0.2);

        while (opModeIsActive() && MagUp.getState()) {
            hangMotor.setPower(-1);
        }
        hangMotor.setPower(0);

        gyroMove(-90, 0.8, 4, 500, "no");
        gyroMove(0, 0.8, 20, 0, "no");
    }

    public void runOpMode() {
        initRobot();
        waitForStart();

        marker.setPosition(0.2);

        while (opModeIsActive() && MagUp.getState()) {
            hangMotor.setPower(-1);
        }
        hangMotor.setPower(0);

        gyroMove(-90, 0.8, 4, 500, "no");
        gyroMove(0, 0.8, 20, 0, "no");
    }
}
