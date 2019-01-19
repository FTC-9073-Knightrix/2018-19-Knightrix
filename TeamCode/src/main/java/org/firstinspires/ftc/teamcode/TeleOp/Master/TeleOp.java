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

        marker.setPosition(0.1);

        if (gamepad1.y) {
            slowmode = false;
        }
        else if (gamepad1.x) {
            slowmode = true;
        }

        // Hanging Robot
        float HangPower = 0;
        if (gamepad2.dpad_down) {
            if (MagDown.getState())   { HangPower =  1;}
        }
        else if (gamepad2.dpad_up) {
            if (MagUp.getState()) { HangPower = -1;}
        }
        hangMotor.setPower(HangPower);

        // ####### Define Twist Motor #########
        //if run to pos != pos & pos != old pos
        twistMotor.setPower(0.8);
        telemetry.addData("Pos", twistMotor.getCurrentPosition());
        telemetry.addData("Set", setTwistPos);
        telemetry.addData("Var", lastTwistPos);


        /*setTwistPos = setTwistPos + (int)(gamepad2.left_stick_y*10);
        twistMotor.setTargetPosition(setTwistPos);*/


        if (gamepad2.left_stick_y < 0) { // go down
            setTwistPos = twistMotor.getCurrentPosition() + 20;
            twistMotor.setTargetPosition(setTwistPos);
            if (twistMotor.getCurrentPosition() <= lastTwistPos) {
                twistMotor.setPower(0);
            }
        }
        else if (gamepad2.left_stick_y > 0) {
            setTwistPos = twistMotor.getCurrentPosition() - 20;
            twistMotor.setTargetPosition(setTwistPos);
            if (twistMotor.getCurrentPosition() >= lastTwistPos) {
                twistMotor.setPower(0);
            }
        }
        else {
            twistMotor.setTargetPosition(twistMotor.getCurrentPosition());
        }

        lastTwistPos = twistMotor.getCurrentPosition();

        // ####### Define Twist Motor END #########



        /*if (gamepad2.right_bumper) {
            intakeHand.setPower(1);
        }
        else if (gamepad2.left_bumper) {
            intakeHand.setPower(-1);
        }
        else {
            intakeHand.setPower(0);
        }

        if (gamepad2.x) {
            intake.setPower(-0.4);
        }
        else if (gamepad2.y) {
            intake.setPower(0.7);
        }
        else {
            intake.setPower(0);
        }

        extendMotor.setPower(gamepad2.left_stick_y);

        dunker.setPower(-gamepad2.right_stick_y);

        krispy.setPosition(1 - gamepad2.right_trigger);*/

        telemetry.update();
    }
}