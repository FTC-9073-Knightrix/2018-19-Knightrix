package org.firstinspires.ftc.teamcode.TeleOp.Master;

import org.firstinspires.ftc.teamcode.TeleOp.TeleOpMethods;

/**
 * Created by nicolas on 11/23/18.
 *
 * Modified by Akhil on 11/30/18
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "Master")

public class TeleOp extends TeleOpMethods {
    public void loop () {
        drive();

        // Hanging Robot
        float HangPower = 0;
        if (gamepad2.dpad_up) {
            if (MagDown.getState())   { HangPower =  1;}
        }
        else if (gamepad2.dpad_down) {
            if (MagUp.getState()) { HangPower = -1;}
        }
        hangMotor.setPower(HangPower);

        if (gamepad2.a) {
            intakeHand.setPower(0.7);
        }
        else if (gamepad2.b) {
            intakeHand.setPower(-0.45);
        }
        else {
            intakeHand.setPower(0);
        }

        if (gamepad2.y) {
            intake.setPower(-0.4);
        }
        else if (gamepad2.x) {
            intake.setPower(0.4);
        }
        else {
            intake.setPower(0);
        }

        extendMotor.setPower(gamepad2.left_stick_y);

        dunker.setPower(gamepad2.right_stick_y);

        krispy.setPosition(1 - gamepad2.right_trigger);
    }
}