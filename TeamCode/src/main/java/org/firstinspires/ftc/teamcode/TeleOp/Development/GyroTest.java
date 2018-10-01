package org.firstinspires.ftc.teamcode.TeleOp.Development;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.TeleOp.TeleOpMethods;

/**
 * Created by nicolas on 9/24/18.
 */

@TeleOp(group = "Test", name = "Gyro")

public class GyroTest extends TeleOpMethods {

    public void loop() {
        if (navxCounter == 3) {
            orientation = navxGyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
            gyroDegrees = (int) (orientation.firstAngle);
            navxCounter = 1;
        }
        else {
            navxCounter++;
        }

        telemetry.addLine("Gyro Value: " + orientation + "\u00b0");

        float myrot = Math.round((gamepad1.right_stick_x / (float) 1) * (float) 100) / (float) 100;

        float leftstick_x = gamepad1.left_stick_x;
        float leftstick_y = gamepad1.left_stick_y;

        if ((leftstick_x > 0 && leftstick_y > 0) || (leftstick_x > 0 && leftstick_y < 0)) {//quadrant down/right
            myangle = (int) (90 + Math.toDegrees(Math.atan(leftstick_y / leftstick_x))); //180 to 90}
        }
        else if((leftstick_x < 0 && leftstick_y < 0) || (leftstick_x < 0 && leftstick_y > 0)) { //quadrant up/left
            myangle = (int) (270 + Math.toDegrees(Math.atan(leftstick_y / leftstick_x))); //270-180
        }
        else if((leftstick_x == 0 && leftstick_y == 0) || (leftstick_x == 0 && leftstick_y < 0)) //(0,0)
            myangle = 0;
        else if(leftstick_x > 0  && leftstick_y == 0) //(1,0)
            myangle = 90;
        else if(leftstick_x == 0 && leftstick_y > 0) //(0,1)
            myangle = 180;
        else if(leftstick_x < 0 && leftstick_y == 0) //(-1,0)
            myangle = 270;
        // 2- Determines power based on Pythagorean Theorem (mypower)
        mypower = (float) Range.clip(Math.sqrt(leftstick_x*leftstick_x+leftstick_y*leftstick_y),0.0,1.0);
        // 3- Limits angle value to be in 360 degrees range
        //myangle = myangle - angle that the gyro is at (gyroDegrees)
        myangle -= gyroDegrees;
        if (myangle < -359) {
            myangle += 360;
        }

        // 4- MOVE robot
        move(myangle,mypower,myrot);
    }
}
