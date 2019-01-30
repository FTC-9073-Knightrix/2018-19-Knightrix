package org.firstinspires.ftc.teamcode.Autonomous.Master;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

@Autonomous(name = "Crater", group = "Master")

public class Crater extends AutoMethods {
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
        gyroMove(60,0.8, 50,0, "no");
        gyroMove(135,0.8, 20,0, "no");
        // === END LEFT ===


        // LEFT to get out of sampling area
        //gyroMove(90,0.8, 10,100, "no");

        // TURN RIGHT w/back to align to wall
        turn(-45, -0.8);

        while (opModeIsActive() && range.cmUltrasonic() > 15) {
            leftFrontDrive.setPower(-0.4);
            leftBackDrive.setPower(0.4);
            rightFrontDrive.setPower(0.4);
            rightBackDrive.setPower(-0.4);
        }

        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);

        // Move following wall to drop area
        gyroMove(0,-0.8, 150,80, "left");

        // Servo drop
        // Out of wall
        gyroMove(-90,.8, 15,100, "no");
        //Drop
        marker.setPosition(0.55); //0.55 Down ; 0.20 IN
        sleep(800);
        marker.setPosition(0.25); //0.55 Down ; 0.20 IN
        // forward
        gyroMove(0,.8, 30,0, "no");
        //ServoUP




        // left
        //gyroMove((int) 45,1, 30,0, "no");


        // move to park
        gyroMove(0,1, 140,70, "left");

        turn(-135, -0.8);

        gyroMove(90, 1, 10, 0, "no");
    }

    private void right() {
        gyroMove(-60,1, 50,0, "no");
        gyroMove(135,1, 20,0, "no");

        // LEFT to get out of sampling area
        gyroMove(90,1, 80,0, "no");

        // TURN RIGHT w/back to align to wall
        turn(-45, -1);

        while (opModeIsActive() && range.cmUltrasonic() > 15) {
            leftFrontDrive.setPower(-0.8);
            leftBackDrive.setPower(0.8);
            rightFrontDrive.setPower(0.8);
            rightBackDrive.setPower(-0.8);
        }

        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);

        // Move following wall to drop area
        gyroMove(0,-1, 150,0, "left");

        // Servo drop
        marker.setPosition(0.55); //0.55 Down ; 0.20 IN
        // Out of wall
        gyroMove(-90,1, 15,0, "no");
        //Drop


        sleep(400);

        marker.setPosition(0.25); //0.55 Down ; 0.20 IN
        // forward
        gyroMove(0,1, 30,0, "no");
        //ServoUP
        //marker.setPosition(0.25); //0.55 Down ; 0.20 IN
        // left
        //gyroMove((int) 45,1, 30,0, "no");



        // move to park
        gyroMove(0,1, 130,0, "left");

        turn(-135,-1);

        gyroMove(90,1, 60,0, "no");
    }

    private void center() {
        gyroMove(0, 0.5, 35, 0, "no");
        // Back half way to opening
        gyroMove((int) 180, 0.5, 25, 0, "no");
        // LEFT to get out of sampling area
        gyroMove(90,0.8, 80,0, "no");

        // TURN RIGHT w/back to align to wall
        turn(-45, -0.8);

        // Move following wall to drop area
        gyroMove(0,-0.8, 145,100, "left");

        // Servo drop
        // Out of wall
        gyroMove(-90,.8, 15,100, "no");
        //Drop
        marker.setPosition(0.55); //0.55 Down ; 0.20 IN

        sleep(800);

        // forward
        gyroMove(0,.8, 30,0, "no");
        //ServoUP
        marker.setPosition(0.25); //0.55 Down ; 0.20 IN
        // left
        //gyroMove((int) 45,1, 30,0, "no");
        // move to park
        gyroMove(0,1, 140,0, "left");

        turn(-135, -0.8);

        gyroMove(90, 1, 10, 0, "no");
    }
}
