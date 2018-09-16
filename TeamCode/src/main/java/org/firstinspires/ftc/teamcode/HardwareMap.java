package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public abstract class HardwareMap extends OpMode {
    //Motors
    public DcMotor leftMotor;
    public DcMotor rightMotor;

    public void init() {
        leftMotor = hardwareMap.dcMotor.get("Left Motor");
        rightMotor = hardwareMap.dcMotor.get("Right Motor");

        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void init_loop() {

    }
}