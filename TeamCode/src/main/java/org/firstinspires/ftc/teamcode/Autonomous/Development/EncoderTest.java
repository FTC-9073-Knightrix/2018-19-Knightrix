package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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
        mecanumMove("y", 5000, 0.3, 0);
        //2500 = 72.3
        ///25 = 26.2
        //telemetry.addData("Position", (leftFrontDrive.getCurrentPosition() + rightFrontDrive.getCurrentPosition() + leftBackDrive.getCurrentPosition() + rightBackDrive.getCurrentPosition()) / 4);
        //telemetry.update();
        telemetry.addData("Left Front", leftFrontDrive.getCurrentPosition());
        telemetry.addData("Right Front", rightFrontDrive.getCurrentPosition());
        telemetry.addData("Left Back", leftBackDrive.getCurrentPosition());
        telemetry.addData("Right Back", rightBackDrive.getCurrentPosition());
        telemetry.update();
    }
}
