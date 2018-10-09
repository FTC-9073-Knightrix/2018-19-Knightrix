package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.HardwareMap;

public abstract class AutoMethods extends HardwareMap {
    public void move(double angle, double power) {
        if (leftFrontDrive != null && leftBackDrive != null && rightFrontDrive != null && rightBackDrive != null) {
            leftFrontDrive.setPower(Range.clip(power * ((Math.sin((angle + 45) / 180 * Math.PI))), -1, 1));
            leftBackDrive.setPower(Range.clip(power * ((Math.sin((angle - 45) / 180 * Math.PI))), -1, 1));
            rightFrontDrive.setPower(Range.clip(power * ((Math.sin((angle - 45) / 180 * Math.PI))), -1, 1));
            rightBackDrive.setPower(Range.clip(power * ((Math.sin((angle + 45) / 180 * Math.PI))), -1, 1));
        }
    }
}