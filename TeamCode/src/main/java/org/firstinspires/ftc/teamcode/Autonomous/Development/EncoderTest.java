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
        leftFrontDrive = hardwareMap.dcMotor.get("LF");
        rightFrontDrive = hardwareMap.dcMotor.get("RF");
        rightBackDrive = hardwareMap.dcMotor.get("RB");
        leftBackDrive = hardwareMap.dcMotor.get("LB");

        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        if (opModeIsActive()) {

            resetEncoders();

            mecanumMove("y", 20000, 1);

            telemetry.addData("Left Front", leftFrontDrive.getCurrentPosition());
            telemetry.addData("Right Front", rightFrontDrive.getCurrentPosition());
            telemetry.addData("Left Back", leftBackDrive.getCurrentPosition());
            telemetry.addData("Right Back", rightBackDrive.getCurrentPosition());

            resetEncoders();

            mecanumMove("x", 10000,1);

            telemetry.addData("Left Front", leftFrontDrive.getCurrentPosition());
            telemetry.addData("Right Front", rightFrontDrive.getCurrentPosition());
            telemetry.addData("Left Back", leftBackDrive.getCurrentPosition());
            telemetry.addData("Right Back", rightBackDrive.getCurrentPosition());

            resetEncoders();

            mecanumMove("rot", 5000, 0.5);

            telemetry.addData("Left Front", leftFrontDrive.getCurrentPosition());
            telemetry.addData("Right Front", rightFrontDrive.getCurrentPosition());
            telemetry.addData("Left Back", leftBackDrive.getCurrentPosition());
            telemetry.addData("Right Back", rightBackDrive.getCurrentPosition());

            resetEncoders();

            mecanumMove("rot", 5000, -0.5);

            telemetry.addData("Left Front", leftFrontDrive.getCurrentPosition());
            telemetry.addData("Right Front", rightFrontDrive.getCurrentPosition());
            telemetry.addData("Left Back", leftBackDrive.getCurrentPosition());
            telemetry.addData("Right Back", rightBackDrive.getCurrentPosition());
        }
    }
}

// LF = x + y + rot
// RF = -x + y - rot
// LR = -x + y + rot
// RR = x + y - rot
//
// (LF + RR) - (RF + LR) = (2x + 2y) - (-2x + 2y)
// => (LF + RR) - (RF + LR) = 4x
// => x = ((LF + RR) - (RF + LR))/4
//
// LF + RF + LR + RR = 4y
// => y = (LF + RF + LR + RR)/4
//
// (LF + LR) - (RF + RR) = (2y + 2rot) - (2y - 2rot)
// => (LF + LR) - (RF + RR) = 4rot
// => rot = ((LF + LR) - (RF + RR))/4

// => x = ((LF + RR) - (RF + LR))/4
// => y = (LF + RF + LR + RR)/4
// => rot = ((LF + LR) - (RF + RR))/4

