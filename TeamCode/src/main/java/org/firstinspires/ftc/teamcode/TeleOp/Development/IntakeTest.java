package org.firstinspires.ftc.teamcode.TeleOp.Development;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeleOp.TeleOpMethods;

/**
 * Created by nicolas on 11/17/18.
 */
@Disabled
@TeleOp(name = "Intake")

public class IntakeTest extends OpMode {
    public CRServo intake;

    public void init() {
        intake = hardwareMap.crservo.get("intake");
    }

    public void loop() {
        if (gamepad1.a) {
            intake.setPower(1);
        } else if (gamepad1.b) {
            intake.setPower(-1);
        } else {
            intake.setPower(0);
        }
    }
}
