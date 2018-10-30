package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by nicolas on 10/29/18.
 */

@Autonomous(name="encoders")

public class EncoderTest extends OpMode {
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    //DcMotor motor4;

    public void init() {
        motor1 = hardwareMap.dcMotor.get("m1");
        motor2 = hardwareMap.dcMotor.get("m2");
        motor3 = hardwareMap.dcMotor.get("m3");
        //motor4 = hardwareMap.dcMotor.get("m4");

        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor1.setTargetPosition(20000);
        motor2.setTargetPosition(20000);
        motor3.setTargetPosition(20000);
        //motor4.setTargetPosition(3000);
    }

    public void loop() {
        motor1.setPower(-1);
        motor2.setPower(-1);
        motor3.setPower(-1);
        //motor4.setPower(1);

        telemetry.addData("Encoder 1", motor1.getCurrentPosition());
        telemetry.addData("Encoder 2", motor2.getCurrentPosition());
        telemetry.addData("Encoder 3", motor3.getCurrentPosition());
    }
}
