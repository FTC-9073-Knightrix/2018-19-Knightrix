package org.firstinspires.ftc.teamcode.TeleOp.Development;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleOp.TeleOpMethods;

@TeleOp(name = "Test Board", group = "Development")

public class TestBoard extends TeleOpMethods {
    public void loop() {
        if (gamepad1.left_stick_y != 0) {
            leftMotor.setPower(gamepad1.left_stick_y);
        }

        if (gamepad1.right_stick_y != 0) {
            rightMotor.setPower(gamepad1.right_stick_y);
        }
    }
}
