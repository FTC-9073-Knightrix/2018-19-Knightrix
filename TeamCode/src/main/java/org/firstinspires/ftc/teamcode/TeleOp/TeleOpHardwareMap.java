//Declare the package
package org.firstinspires.ftc.teamcode.TeleOp;

//Import the dependencies needed to run the programs
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

//Create the class declaration, extending OpMode
public abstract class TeleOpHardwareMap extends OpMode {

    //Create the four motors
    public DcMotor leftFrontDrive;
    public DcMotor rightFrontDrive;
    public DcMotor rightBackDrive;
    public DcMotor leftBackDrive;

    //Create the gyroscope
    public NavxMicroNavigationSensor navxGyro;
    //Create the orientation variable for the robot position
    public Orientation orientation;
    //Create the counter variable
    public int navxCounter = 3;
    //Create the variable for the degrees of the robot
    public int gyroDegrees = 0;
    //Create the variable that will calculate the angle of the joystick
    public int myangle = 0;
    //Create the variable that will calculate the power of the robot
    public float mypower = 0;
    //Create the variable that will keep the previous angle of the robot
    public int gyroResetValue = 0;
    //Create the variable that will keep track of the left joystick's x value
    public float leftstick_x = 0;
    //Create the variable that will keep track of the left joystick's y value
    public float leftstick_y = 0;
    //Create the variable that will calculate the rotation of the robot
    public float myrot = 0;

    //Initialize the defined objects
    public void init() {

        //Add the motors to the configuration on the phones
        leftFrontDrive = hardwareMap.dcMotor.get("LF");
        rightFrontDrive = hardwareMap.dcMotor.get("RF");
        rightBackDrive = hardwareMap.dcMotor.get("RB");
        leftBackDrive = hardwareMap.dcMotor.get("LB");
        //Set the direction of the motors
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);

        //Set the mode the motors are going to be running in
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //Add the gyroscope to the configuration on the phones
        navxGyro = hardwareMap.get(NavxMicroNavigationSensor.class, "gyro");
    }

    //Wait for the gyroscope to stop calibrating
    public void init_loop() {

        //If the gyroscope is calibrating
        if(navxGyro.isCalibrating()) {

            //Display the fact the gyroscope is still calibrating on screen
            telemetry.addLine("navx Calibrating");
        }

        //If the gyroscope has finished calibrating
        else {

            //Display the fact the gyroscope has finished calibrating on screen
            telemetry.addLine("init complete");
        }

        //Update the telemetry
        telemetry.update();
    }
}
