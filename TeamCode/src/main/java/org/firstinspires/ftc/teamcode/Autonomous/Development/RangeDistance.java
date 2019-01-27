package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

/**
 * Created by nicolas on 12/3/18.
 */

@Autonomous(name="Range")

public class RangeDistance extends AutoMethods {

    public void runOpMode() {

        initRobot();

        //range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");

        waitForStart();

        //gyroMove(90, 0.5, 500, 0, "back");

        while(opModeIsActive()) {
            telemetry.addData("Optical", range2.cmOptical());
            telemetry.addData("Ultrasonic", range2.cmUltrasonic());
            telemetry.update();
        }
    }
}
