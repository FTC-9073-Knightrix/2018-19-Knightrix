package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

/**
 * Created by nicolas on 10/29/18.
 */

@Autonomous(name="Without Encoders")

public class RunWithoutEncoders extends LinearOpMode {

    DcMotor motor1;

    public void runOpMode() {
        motor1 = hardwareMap.dcMotor.get("m1");
        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        float encoderbaseline = motor1.getCurrentPosition();
        float encodervalue = motor1.getCurrentPosition();
        double mytime = getRuntime();
        double priortime = mytime;
        float priorencodervalue = encodervalue;
        waitForStart();

        motor1.setPower(0.2);

        double timer = getRuntime();

        while(opModeIsActive()) {
            encodervalue = motor1.getCurrentPosition();
            mytime = getRuntime();

            telemetry.addData("Encoder:       ", encodervalue - encoderbaseline);
            telemetry.addData("Avg Speed:     ", (encodervalue - encoderbaseline)/(getRuntime() - timer));
            telemetry.addData("Instant Speed: ", (encodervalue - priorencodervalue)/(mytime - priortime));
            telemetry.update();

            sleep(2000);

            priortime = mytime;
            priorencodervalue = encodervalue;
        }
    }
}
