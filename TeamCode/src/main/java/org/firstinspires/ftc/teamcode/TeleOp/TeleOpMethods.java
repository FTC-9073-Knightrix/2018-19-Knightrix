package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.HardwareMap;

public abstract class TeleOpMethods extends HardwareMap {
    public void move (double myangle, float mypower, float myrot) {
        if (leftFrontDrive != null && leftBackDrive != null && rightFrontDrive != null && rightBackDrive != null) {
            leftFrontDrive.setPower(Range.clip(myrot + (mypower * ((Math.sin((myangle + 135) / 180 * 3.141592)))), -1, 1));
            leftBackDrive.setPower(Range.clip(myrot + (mypower * ((Math.sin((myangle + 45) / 180 * 3.141592)))), -1, 1));
            rightFrontDrive.setPower(Range.clip(-myrot + (mypower * ((Math.sin((myangle + 45) / 180 * 3.141592)))), -1, 1));
            rightBackDrive.setPower(Range.clip(-myrot + (mypower * ((Math.sin((myangle + 135) / 180 * 3.141592)))), -1, 1));
        }
    }
}
