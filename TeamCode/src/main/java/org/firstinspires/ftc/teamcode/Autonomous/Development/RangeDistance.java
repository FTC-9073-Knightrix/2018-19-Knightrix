package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by nicolas on 12/3/18.
 */

@Autonomous(name="Range")

public class RangeDistance extends LinearOpMode {

    ModernRoboticsI2cRangeSensor range;

    public void runOpMode() {

        range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");

        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("Optical", range.cmOptical());
            telemetry.addData("Ultrasonic", range.cmUltrasonic());
            telemetry.update();
        }
    }
}
