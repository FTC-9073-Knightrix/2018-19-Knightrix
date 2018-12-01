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

        if (gamepad2.dpad_down) {
            liftMotor.setPower(1);
        }
        else if (gamepad2.dpad_up) {
            liftMotor.setPower(-1);
        }
        else {
            liftMotor.setPower(0);
        }

        if (gamepad2.dpad_right) {
            extendMotor.setPower(0.5);
        }
        else if (gamepad2.dpad_left) {
            extendMotor.setPower(-0.5);
        }
        else {
            extendMotor.setPower(0);
        }

        if (gamepad2.right_bumper) {
            intakeMotor.setPower(1);
        }
        else if (gamepad2.left_bumper) {
            intakeMotor.setPower(-1);
        }
        else {
            intakeMotor.setPower(0);
        }

        intakeHand.setTargetPosition((int)(-650 + (gamepad2.left_trigger * 650)));
        telemetry.addLine("" + (-650 + (gamepad2.left_trigger * 650)));
        telemetry.addData("Position", intakeHand.getCurrentPosition());
        intakeHand.setPower(0.1);

        /*if (gamepad2.a) {
            intakeHand.setPower(.1);
            intakeHand.setTargetPosition(0);
        }
        else if (gamepad2.b) {
            intakeHand.setPower(.1);
            intakeHand.setTargetPosition(700);
        }
        else if (gamepad2.y){
            intakeHand.setPower(.1);
            intakeHand.setTargetPosition(1200);
        }*/

        krispy.setPosition(gamepad2.right_trigger);
        telemetry.addData("Servo", gamepad2.right_trigger);

        //double curFlipPos =
        double intakeHandPosition= intakeHand.getCurrentPosition();
        telemetry.addData("Position", "" + intakeHandPosition);
        telemetry.update();
    }
}