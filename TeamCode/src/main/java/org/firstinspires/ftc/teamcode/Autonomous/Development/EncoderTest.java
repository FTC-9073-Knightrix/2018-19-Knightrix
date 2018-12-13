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


        //gyroMove(0, 0.3, 200, 0, "none");
        //mecanumMove("y", 50, 0.3, 0);
        turn(90);
        turn(-180);



        telemetry.addData("Position", (leftFrontDrive.getCurrentPosition() + rightFrontDrive.getCurrentPosition() + leftBackDrive.getCurrentPosition() + rightBackDrive.getCurrentPosition()) / 4);
        telemetry.update();

    }
}
