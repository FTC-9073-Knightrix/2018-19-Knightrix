package org.firstinspires.ftc.teamcode.Autonomous.Master;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

@Autonomous(name = "Marker", group = "Master")

public class Marker extends AutoMethods {
    public void runOpMode() {
        initVuforia();
        initTfod();
        initRobot();
        waitForStart();

        String block = detectBlock();
        say(block);

        marker.setPosition(0.2);

        while (opModeIsActive() && MagUp.getState()) {
            hangMotor.setPower(-1);
        }
        hangMotor.setPower(0);

        gyroMove(-90, 0.8, 4, 500, "no");
        gyroMove(0, 0.8, 20, 0, "no");

        if (block.equals("left")) {
            left();
        }
        else if (block.equals("right")) {
            right();
        }
        else {
            center();
        }
    }

    private void left() {
        gyroMove(60, 0.8, 80, 0, "no");

        turn(-45, -0.8);

        gyroMove(0, 0.8, 90, 0, "no");

        marker.setPosition(0.55);

        sleep(800);

        marker.setPosition(0.2);

        gyroMove(0, -1, 50, 0, "no");

        turn(135, -0.8);

        while (opModeIsActive() && range2.cmUltrasonic() > 15) {
            leftFrontDrive.setPower(0.4);
            leftBackDrive.setPower(-0.4);
            rightFrontDrive.setPower(-0.4);
            rightBackDrive.setPower(0.4);
        }

        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);

        //gyroMove(0, 0.3, 20, 0, "right");

        gyroMove(0, 0.8, 130, 0, "right");

        while (opModeIsActive() && range2.cmUltrasonic() > 11) {
            leftFrontDrive.setPower(0.8);
            leftBackDrive.setPower(-0.8);
            rightFrontDrive.setPower(-0.8);
            rightBackDrive.setPower(0.8);
        }

        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);

        orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
        angle = orientation.firstAngle;

        if (angle > 135) {
            turn(135, -0.8);
        }
        else {
            turn(135, 0.8);
        }
    }

    private void right() {
        gyroMove(-60, 0.8, 50, 0, "no");

        turn(-135, -1);

        // If distance to the wall is bigger than 20 cm, then move sideways to get closer to the wall
        while (opModeIsActive() && range.cmUltrasonic() > 20) {
            leftFrontDrive.setPower(-0.4);
            leftBackDrive.setPower(0.4);
            rightFrontDrive.setPower(0.4);
            rightBackDrive.setPower(-0.4);
        }

        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);

        gyroMove(0, -0.8, 40, 0, "none");

        gyroMove(0, -0.8, 50, 0, "left");

        // Moves from prior 8 cm from wall to at least 15 cm from wall
        while (opModeIsActive() && range.cmUltrasonic() < 15) {
            leftFrontDrive.setPower(0.4);
            leftBackDrive.setPower(-0.4);
            rightFrontDrive.setPower(-0.4);
            rightBackDrive.setPower(0.4);
        }

        // Drops the marker
        marker.setPosition(0.55);

        while (opModeIsActive() && range.cmUltrasonic() < 25) {
            leftFrontDrive.setPower(0.4);
            leftBackDrive.setPower(-0.4);
            rightFrontDrive.setPower(-0.4);
            rightBackDrive.setPower(0.4);
        }

        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);

        //marker.setPosition(0.55);

        sleep(500);

        // Lifts the marker
        marker.setPosition(0.2);

        //gyroMove(-90, 0.5, 20, 200, "no");

        /*while (opModeIsActive() && range.cmUltrasonic() < 25) {
            leftFrontDrive.setPower(0.4);
            leftBackDrive.setPower(-0.4);
            rightFrontDrive.setPower(-0.4);
            rightBackDrive.setPower(0.4);
        }

        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);*/

        turn(135, -1);

        while (opModeIsActive() && range2.cmUltrasonic() > 15) {
            leftFrontDrive.setPower(0.4);
            leftBackDrive.setPower(-0.4);
            rightFrontDrive.setPower(-0.4);
            rightBackDrive.setPower(0.4);
        }

        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);

        //sleep(1000);

        gyroMove(0, 0.8, 180, 0, "right");

        while (opModeIsActive() && range2.cmUltrasonic() > 11) {
            leftFrontDrive.setPower(0.8);
            leftBackDrive.setPower(-0.8);
            rightFrontDrive.setPower(-0.8);
            rightBackDrive.setPower(0.8);
        }

        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);

        orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
        angle = orientation.firstAngle;

        if (angle > 135) {
            turn(135, -0.8);
        }
        else {
            turn(135, 0.8);
        }
    }

    private void center() {
        gyroMove(0, 0.8, 110, 200, "no");

        turn(-45, -1);

        marker.setPosition(0.55);

        sleep(1000);

        marker.setPosition(0.2);

        gyroMove(0, -0.8, 23, 0, "no");

        gyroMove(0, -0.8, 100, 200, "left");

        turn(135, -1);

        gyroMove(0, 0.8, 50, 200, "right");

        while (opModeIsActive() && range2.cmUltrasonic() > 11) {
            leftFrontDrive.setPower(0.8);
            leftBackDrive.setPower(-0.8);
            rightFrontDrive.setPower(-0.8);
            rightBackDrive.setPower(0.8);
        }

        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);

        orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
        angle = orientation.firstAngle;

        if (angle > 135) {
            turn(135, -0.8);
        }
        else {
            turn(135, 0.8);
        }
    }
}
