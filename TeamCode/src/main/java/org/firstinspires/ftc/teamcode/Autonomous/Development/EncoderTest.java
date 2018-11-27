package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

/**
 * Created by nicolas on 10/29/18.
 */

@Autonomous(name="encoders")

public class EncoderTest extends AutoMethods {

    public void runOpMode() {
        initRobot();
        waitForStart();
        mecanumMove("y", 500, 0.3, 0);
    }
}