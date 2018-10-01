package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by nicolas on 9/29/18.
 */

public abstract class TeleOpHardwareMap extends OpMode {

    public DcMotor leftFrontDrive;
    public DcMotor rightFrontDrive;
    public DcMotor rightBackDrive;
    public DcMotor leftBackDrive;

    public NavxMicroNavigationSensor navxGyro;
    public Orientation orientation;
    public int navxCounter = 3;
    public int gyroDegrees = 0;
    public int myangle = 0;
    public float mypower = 0;
    public int gyroResetValue = 0;
    public float leftstick_x;
    public float leftstick_y;
    public float myrot = 0;

    public void init() {
        leftFrontDrive = hardwareMap.dcMotor.get("LF");
        rightFrontDrive = hardwareMap.dcMotor.get("RF");
        rightBackDrive = hardwareMap.dcMotor.get("RB");
        leftBackDrive = hardwareMap.dcMotor.get("LB");
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);

        navxGyro = hardwareMap.get(NavxMicroNavigationSensor.class, "gyro");

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void init_loop() {
        if(navxGyro.isCalibrating()) {
            telemetry.addLine("navx Calibrating");
        }
        else {
            telemetry.addLine("init complete");
        }
        telemetry.update();
    }
}
