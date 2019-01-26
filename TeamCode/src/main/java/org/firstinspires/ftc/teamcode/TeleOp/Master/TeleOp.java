package org.firstinspires.ftc.teamcode.TeleOp.Master;

import org.firstinspires.ftc.teamcode.TeleOp.TeleOpMethods;
//import org.opencv.core.Range;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
/**
 * Created by nicolas on 11/23/18.
 *
 * Modified by Akhil on 11/30/18
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "Master")

public class TeleOp extends TeleOpMethods {
    public void loop () {
        drive();

        marker.setPosition(0.55);

        if (gamepad1.y) {
            slowmode = false;
        }
        else if (gamepad1.x) {
            slowmode = true;
        }

        // Hanging Robot
        float HangPower = 0;
        if (gamepad2.dpad_down || gamepad1.dpad_down) {
            if (MagDown.getState())   { HangPower =  1;}
        }
        else if (gamepad2.dpad_up || gamepad1.dpad_up) {
            if (MagUp.getState()) { HangPower = -1;}
        }
        hangMotor.setPower(HangPower);

        // ####### Define Twist Motor #########
        //if run to pos != pos & pos != old pos
        /*twistMotor.setPower(1);
        telemetry.addData("Pos", twistMotor.getCurrentPosition());
        telemetry.addData("Set", setTwistPos);
        telemetry.addData("Var", lastTwistPos);


        setTwistPos = setTwistPos + (int)(gamepad2.left_stick_y*100);
        twistMotor.setTargetPosition(setTwistPos);
        */

        double twistPower;
        double twist = -gamepad2.left_stick_y;
        twistPower = Range.clip(twist,-1.0,1.0);
        twistMotor.setPower(twistPower);
        twistMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);




        spoolMotor.setPower(1);
        setSpoolPos = setSpoolPos + (int)(gamepad2.right_stick_y*100);
        if(setSpoolPos <= 0){
            setSpoolPos = 0;
        }
        spoolMotor.setTargetPosition(setSpoolPos);



        telemetry.addData("SPos", spoolMotor.getCurrentPosition());
        telemetry.addData("SSet", setSpoolPos);




        /*if (!readTwistPos.equals("down") && gamepad2.left_stick_y < 0) {
            readTwistPos = "none";
            setTwistPos = twistMotor.getCurrentPosition() + 20;
            twistMotor.setTargetPosition(setTwistPos);
            if (twistMotor.getCurrentPosition() <= lastTwistPos) {
                twistMotor.setTargetPosition(twistMotor.getCurrentPosition());
                readTwistPos = "down";
            }
        }
        else if (!readTwistPos.equals("up") && gamepad2.left_stick_y > 0) {
            readTwistPos = "none";
            setTwistPos = twistMotor.getCurrentPosition() - 20;
            twistMotor.setTargetPosition(setTwistPos);
            if (twistMotor.getCurrentPosition() >= lastTwistPos) {
                twistMotor.setTargetPosition(twistMotor.getCurrentPosition());
                readTwistPos = "up";
            }
        }
        else {
            twistMotor.setTargetPosition(twistMotor.getCurrentPosition());
        }

        lastTwistPos = twistMotor.getCurrentPosition();*/

        // ####### Define Twist Motor END #########



        if (gamepad2.right_bumper) {
            intake.setPower(1);
        }
        else if (gamepad2.left_bumper) {
            intake.setPower(-1);
        }
        else {
            intake.setPower(0);
        }

        intakeRelease.setPosition(1-(gamepad2.left_trigger*.57));

        telemetry.addData("Release", gamepad2.left_trigger);

        /*extendMotor.setPower(gamepad2.left_stick_y);

        dunker.setPower(-gamepad2.right_stick_y);

        krispy.setPosition(1 - gamepad2.right_trigger);*/

        telemetry.update();
    }
}