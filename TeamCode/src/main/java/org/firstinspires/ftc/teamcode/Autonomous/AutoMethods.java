package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.HardwareMap;

public abstract class AutoMethods extends HardwareMap {
    public void move(double angle, double power) {
        if (leftFrontDrive != null && leftBackDrive != null && rightFrontDrive != null && rightBackDrive != null) {
            /*if (angle != 0 && angle != 180) {
                leftFrontDrive.setPower(Range.clip(power * Math.sin(angle),-1,1));
                leftBackDrive.setPower(Range.clip(power * Math.sin(angle),-1,1));
                rightFrontDrive.setPower(Range.clip(power * Math.sin(angle),-1,1));
                leftBackDrive.setPower(Range.clip(power * Math.sin(angle),-1,1));
            }
            else {
                if (angle == 0) {
                    leftFrontDrive.setPower();
                }
            }*/
            leftFrontDrive.setPower(Range.clip(power * ((Math.sin((angle + 45) / 180 * Math.PI))), -1, 1));
            leftBackDrive.setPower(Range.clip(power * ((Math.sin((angle - 45) / 180 * Math.PI))), -1, 1));
            rightFrontDrive.setPower(Range.clip(power * ((Math.sin((angle - 45) / 180 * Math.PI))), -1, 1));
            rightBackDrive.setPower(Range.clip(power * ((Math.sin((angle + 45) / 180 * Math.PI))), -1, 1));

            telemetry.addLine("LF: " + leftFrontDrive.getPower());
            telemetry.addLine("RF: " + rightFrontDrive.getPower());
            telemetry.addLine("LB: " + leftBackDrive.getPower());
            telemetry.addLine("RB: " + rightBackDrive.getPower());
        }
    }
}