package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by nicolas on 9/29/18.
 */

public abstract class AutoHardwareMap extends LinearOpMode {

    public ElapsedTime runtime = new ElapsedTime();

    public DcMotor leftFrontDrive;
    public DcMotor rightFrontDrive;
    public DcMotor rightBackDrive;
    public DcMotor leftBackDrive;

    public NavxMicroNavigationSensor navxGyro;
    public Orientation orientation;
    public double angle = 0;
}
