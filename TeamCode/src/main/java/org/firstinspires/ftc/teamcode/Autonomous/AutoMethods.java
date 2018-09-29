package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.HardwareMap;

public abstract class AutoMethods extends AutoHardwareMap {
    public void initRobot() {
        leftFrontDrive = hardwareMap.dcMotor.get("LF");
        rightFrontDrive = hardwareMap.dcMotor.get("RF");
        rightBackDrive = hardwareMap.dcMotor.get("RB");
        leftBackDrive = hardwareMap.dcMotor.get("LB");
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        navxGyro = hardwareMap.get(NavxMicroNavigationSensor.class, "gyro");

        while(navxGyro.isCalibrating()) {
            telemetry.addLine("navx Calibrating");
            telemetry.update();
        }
        telemetry.addLine("init complete");
        telemetry.update();
    }

    public void move(String direction, double power) {
        if (direction.toLowerCase().equals("front")) {
            leftFrontDrive.setPower(power);
            rightFrontDrive.setPower(power);
            leftBackDrive.setPower(power);
            rightBackDrive.setPower(power);
        }
        else if (direction.toLowerCase().equals("back")) {
            leftFrontDrive.setPower(-power);
            rightFrontDrive.setPower(-power);
            leftBackDrive.setPower(-power);
            rightBackDrive.setPower(-power);
        }
        else if (direction.toLowerCase().equals("left")) {
            leftFrontDrive.setPower(-power);
            rightFrontDrive.setPower(power);
            leftBackDrive.setPower(power);
            rightBackDrive.setPower(-power);
        }
        else if (direction.toLowerCase().equals("right")) {
            leftFrontDrive.setPower(power);
            rightFrontDrive.setPower(-power);
            leftBackDrive.setPower(-power);
            rightBackDrive.setPower(power);
        }
    }

    public void turn(double degrees) {
        double power = Range.clip(360.0/(double)(degrees),0.2,1);
        orientation = navxGyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
        angle = orientation.firstAngle;
        while (Math.abs(angle - degrees) > 3) {
            if (degrees > angle) {
                leftFrontDrive.setPower(-power);
                rightFrontDrive.setPower(power);
                leftBackDrive.setPower(-power);
                rightBackDrive.setPower(power);
            }
            if (degrees < angle) {
                leftFrontDrive.setPower(power);
                rightFrontDrive.setPower(-power);
                leftBackDrive.setPower(-power);
                rightBackDrive.setPower(power);
            }

            orientation = navxGyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
            angle = orientation.firstAngle;

            telemetry.addLine("Target degree: " + (int)(degrees));
            telemetry.addLine("Current degree: " + (int)(angle));
            telemetry.update();
        }
    }
}
