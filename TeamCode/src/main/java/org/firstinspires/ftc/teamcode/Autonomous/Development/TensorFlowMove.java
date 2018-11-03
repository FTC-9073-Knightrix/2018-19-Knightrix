package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by nicolas on 11/3/18.
 */

public abstract class TensorFlowMove extends LinearOpMode {
    public void left(double seconds) {
        while (opModeIsActive()) {
            telemetry.addLine("LEFT");
            telemetry.addData("Time to recognize", seconds);
            telemetry.update();
        }
    }
    public void center(double seconds) {
        while (opModeIsActive()) {
            telemetry.addLine("CENTER");
            telemetry.addData("Time to recognize", seconds);
            telemetry.update();
        }
    }
    public void right(double seconds) {
        while (opModeIsActive()) {
            telemetry.addLine("RIGHT");
            telemetry.addData("Time to recognize", seconds);
            telemetry.update();
        }
    }
}
