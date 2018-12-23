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

@Autonomous(name="Test Move with Encoders")

public class EncoderTest extends AutoMethods {

    public void runOpMode() {
        initRobot();
        waitForStart();

        say("Encoders ON - Gyro OFF");

        //gyroMove(90, 0.3, 200, 0, "none");
        resetEncoders();
        sleep(500);
        mecanumMove("x", 50, 0.5, 2000);

        resetEncoders();
        sleep(500);
        mecanumMove("y", 50, 0.5, 2000);

        resetEncoders();
        sleep(500);
        mecanumMove("x", -50, 0.5, 2000);

        resetEncoders();
        sleep(500);
        mecanumMove("y", -50, 0.5, 2000);
        //turn(90);
        //turn(-180);



        telemetry.addData("Position", (leftFrontDrive.getCurrentPosition() + rightFrontDrive.getCurrentPosition() + leftBackDrive.getCurrentPosition() + rightBackDrive.getCurrentPosition()) / 4);
        telemetry.update();

    }
}
