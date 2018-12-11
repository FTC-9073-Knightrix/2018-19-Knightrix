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
        //mecanumMove("y", 50, 0.3, 0);

        gyroMove(00, 0.3, 100, 1000); //129 cm
        //turn(90);
        sleep(1000);

        gyroMove(45, 0.3, 100, 1000); //129 cm
        sleep(1000);

        gyroMove(90, 0.3, 100, 1000); //129 cm
        sleep(1000);

        gyroMove(-45, 0.3, 100, 1000); //129 cm
        sleep(1000);
        //2500 = 72.3
        ///25 = 26.2
        telemetry.addData("Position", (leftFrontDrive.getCurrentPosition() + rightFrontDrive.getCurrentPosition() + leftBackDrive.getCurrentPosition() + rightBackDrive.getCurrentPosition()) / 4);
        telemetry.update();
    }
}
