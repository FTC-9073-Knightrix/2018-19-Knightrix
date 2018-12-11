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

        /*intakeHand.setTargetPosition((int)(-650 + (gamepad2.left_trigger * 650)));
        telemetry.addLine("" + (-650 + (gamepad2.left_trigger * 650)));
        telemetry.addData("Position", intakeHand.getCurrentPosition());

        /*if((-650 + (gamepad2.left_trigger * 650) <= -550 && intakeHand.getCurrentPosition() <= -550) || (-650 + (gamepad2.left_trigger * 650) >= -100 && intakeHand.getCurrentPosition() >= -100)) {
            intakeHand.setPower(0);
        }
        else {
            intakeHand.setPower(0.1);
        }*/

        if (gamepad2.a) {
            intakeHand.setPower(0.7);
        }
        else if (gamepad2.b) {
            intakeHand.setPower(-0.45);
        }
        else {
            intakeHand.setPower(0);
        }

        /*
        intakeMotor Program:

        read encoder value

        if (press A) {
            intakeHand.setPower(0.2);
        }
        elseif (press B) {
            intakeHand.setPower(-.2);
        }
        else {
            intakeHand.setPower(0);
        }

        if (encoder value = prior encoder value) {
             intakeHand.setPower(0);
        }

        prior value = read encoder value
        */



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

        krispy.setPosition(1 - gamepad2.right_trigger);
        //telemetry.addData("Servo", 1 - gamepad2.right_trigger);

        //double curFlipPos =
        double intakeHandPosition= intakeHand.getCurrentPosition();
        //telemetry.addData("Position", "" + intakeHandPosition);
        //telemetry.update();
    }
}