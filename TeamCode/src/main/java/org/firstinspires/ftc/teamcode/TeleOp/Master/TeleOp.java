package org.firstinspires.ftc.teamcode.TeleOp.Master;

import org.firstinspires.ftc.teamcode.TeleOp.TeleOpMethods;

/**
 * Created by nicolas on 11/23/18.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "Master")

public class TeleOp extends TeleOpMethods {
    public void loop () {
        drive();

        if (gamepad2.dpad_up) {
            liftMotor.setPower(1);
        }
        else if (gamepad2.dpad_down) {
            liftMotor.setPower(-1);
        }
        else {
            liftMotor.setPower(0);
        }

        if (gamepad2.dpad_right) {
            extendMotor.setPower(1);
        }
        else if (gamepad2.dpad_left) {
            extendMotor.setPower(-1);
        }
        else {
            extendMotor.setPower(0);
        }

        if (gamepad2.a) {
            intakeMotor.setPower(1);
        }
        else if (gamepad2.b) {
            intakeMotor.setPower(-1);
        }
        else {
            intakeMotor.setPower(0);
        }

        if (gamepad2.y) {
            intakeHand.setPower(0.7);
        }
        else if (gamepad2.x) {
            intakeHand.setPower(-0.7);
        }

        krispy.setPosition(gamepad2.right_trigger * 270);

        telemetry.addData("Position", "" + intakePosition);
        telemetry.addData("Encoder", "" + intakeHand.getCurrentPosition());
        telemetry.update();
    }
}