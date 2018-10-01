package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;


@Autonomous(name="Auto: auto stuff", group="Linear")
@Disabled
public class AutoLinearOffOfGold_Dakota extends LinearOpMode {

    HardwarePushbot robot = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor RightFrontDrive;
    DcMotor LeftFrontDrive;
    DcMotor RightBackDrive;
    DcMotor LeftBackDrive;


    static final double FORWARD_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;

    @Override
    public void runOpMode()

    {
        RightFrontDrive = hardwareMap.dcMotor.get("RightFrontDrive");


        RightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        //LT
        LeftFrontDrive = hardwareMap.dcMotor.get("LeftFrontDrive");


        LeftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        //RB
        RightBackDrive = hardwareMap.dcMotor.get("RightBackDrive");


        RightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        //LB
        LeftBackDrive = hardwareMap.dcMotor.get("LeftBackDrive");
        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        // Step 1:  Drive forward for 3 seconds
        // DO the deploy thingie off the center//
        //this goes forward//
        LeftFrontDrive.setPower(0.5);
        RightBackDrive.setPower(0.5);
        RightFrontDrive.setPower(0.5);
        LeftBackDrive.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        //add sampling feature//
        //turns left 90*//
        RightFrontDrive.setPower(0.5);
        RightBackDrive.setPower(0.5);
        LeftFrontDrive.setPower(-0.5);
        LeftBackDrive.setPower(-0.5);
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        //this goes forward//
        LeftFrontDrive.setPower(0.5);
        RightBackDrive.setPower(0.5);
        RightFrontDrive.setPower(0.5);
        LeftBackDrive.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        //turns left 45*//
        RightFrontDrive.setPower(0.5);
        RightBackDrive.setPower(0.5);
        LeftFrontDrive.setPower(-0.5);
        LeftBackDrive.setPower(-0.5);
        while (opModeIsActive() && (runtime.seconds() < .65)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        //this goes forward//
        LeftFrontDrive.setPower(0.5);
        RightBackDrive.setPower(0.5);
        RightFrontDrive.setPower(0.5);
        LeftBackDrive.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        //does the figurine thing//
        //turns left 180*//
        RightFrontDrive.setPower(0.5);
        RightBackDrive.setPower(0.5);
        LeftFrontDrive.setPower(-0.5);
        LeftBackDrive.setPower(-0.5);
        while (opModeIsActive() && (runtime.seconds() < 2.6)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        //this goes forward//
        LeftFrontDrive.setPower(0.5);
        RightBackDrive.setPower(0.5);
        RightFrontDrive.setPower(0.5);
        LeftBackDrive.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        //turns right 90*//
        RightFrontDrive.setPower(-0.5);
        RightBackDrive.setPower(-0.5);
        LeftFrontDrive.setPower(0.5);
        LeftBackDrive.setPower(0.5);
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        //turns in an arc to the left//
        RightFrontDrive.setPower(0.5);
        RightBackDrive.setPower(0.5);
        LeftFrontDrive.setPower(0.65);
        LeftBackDrive.setPower(0.65);
        while (opModeIsActive() && (runtime.seconds() < 2)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        //deploys deployables//
    }
}
