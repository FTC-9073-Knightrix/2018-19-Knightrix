package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

/**
 * Created by nicolas on 1/12/19.
 */

@Autonomous(name = "Lift Down")
public class LiftDown extends AutoMethods {
    public void runOpMode() {
        initRobot();
        waitForStart();

        while (opModeIsActive() && MagDown.getState()) {
            hangMotor.setPower(1);
        }
        hangMotor.setPower(0);
    }
}
