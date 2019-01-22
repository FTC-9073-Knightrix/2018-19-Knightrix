//Declare the package
package org.firstinspires.ftc.teamcode.TeleOp;

//Import the dependencies needed to run the programs
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

//Create the class declaration, extending OpMode
public abstract class TeleOpHardwareMap extends OpMode {

    //Create the four motors
    public DcMotor leftFrontDrive;
    public DcMotor rightFrontDrive;
    public DcMotor rightBackDrive;
    public DcMotor leftBackDrive;

    //public DcMotor extendMotor;
    //public DcMotor dunker;
    //public DcMotor intakeHand;
    public DcMotor hangMotor;

    public DcMotor intake;

    public DcMotor twistMotor;
    public DcMotor spoolMotor;

    //public Servo krispy;
    //public CRServo intakeHand;
    public Servo marker;

    public Servo intakeRelease;
    public int releasePos;

    //Create the intake
    //public DcMotor intake;
    //public Servo spinner;
    //ColorSensor color;
    /*int intakeNum = 0;
    double mineral1 = 0;
    double mineral2 = 0;
    double mineral3 = 0;
    String color1 = "";
    String color2 = "";
    String color3 = "";
    public boolean intakeBool = false;
    public int intakePosition = 0;*/

    //Create the gyroscope
    //public NavxMicroNavigationSensor navxGyro;
    public BNO055IMU gyro;
    //Create the orientation variable for the robot position
    public Orientation orientation;

    //Create the counter variable
    //public int navxCounter = 3;
    //Create the variable for the degrees of the robot
    public int gyroDegrees = 0;
    //Create the variable that will calculate the angle of the joystick
    public int myangle = 0;
    //Create the variable that will calculate the power of the robot
    public float mypower = 0;
    //Create the variable that will keep the previous angle of the robot
    public int gyroResetValue = 0;

    /*public int setTwistPos = 0;
    public int lastTwistPos = 0;
    public String readTwistPos = "none";
    */
    public int setSpoolPos = 0;

    // Create the Magnetic Limit Switches (Digital Inputs)
    public DigitalChannel MagUp;  // Rev Magnetic Switch
    public DigitalChannel MagDown;  // Rev Magnetic Switch


    //Create the variable that will keep track of the left joystick's x value
    public float leftstick_x = 0;
    public float g1_leftstick_x = 0;
    public float g2_leftstick_x = 0;
    //Create the variable that will keep track of the left joystick's y value
    public float leftstick_y = 0;
    public float g1_leftstick_y = 0;
    public float g2_leftstick_y = 0;

    public float g1_rightstick_x = 0;
    public float g2_rightstick_x = 0;

    //Create the variable that tracks the GamePad buttons
    public boolean g2_dpad_down  = false;
    public boolean g2_dpad_up    = false;
    public boolean g2_dpad_right = false;
    public boolean g2_dpad_left  = false;

    // Game pad Bumpers
    public boolean g2_right_bumper = false;
    public boolean g2_left_bumper  = false;


    public boolean slowmode = false;


    //Create the variable that will calculate the rotation of the robot
    public float myrot = 0;

    //Initialize the defined objects
    public void init() {

        //Add the motors to the configuration on the phones
        leftFrontDrive = hardwareMap.dcMotor.get("LF");
        rightFrontDrive = hardwareMap.dcMotor.get("RF");
        rightBackDrive = hardwareMap.dcMotor.get("RB");
        leftBackDrive = hardwareMap.dcMotor.get("LB");
        //liftMotor = hardwareMap.dcMotor.get("lift");
        //extendMotor = hardwareMap.dcMotor.get("extend");
        //intakeMotor = hardwareMap.dcMotor.get("intake");
        //intakeHand = hardwareMap.crservo.get("hand");
        hangMotor = hardwareMap.dcMotor.get("hang");
        //dunker = hardwareMap.dcMotor.get("dunker");
        intake = hardwareMap.dcMotor.get("intake");

        //krispy = hardwareMap.servo.get("krispy");
        marker = hardwareMap.servo.get("marker");

        twistMotor = hardwareMap.dcMotor.get("twist");
        spoolMotor = hardwareMap.dcMotor.get("spool");

        intakeRelease = hardwareMap.servo.get("release");

        //Set the direction of the motors
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);// F
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE); // F
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD); // R
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);  // R
        //Set the mode the motors are going to be running in
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //intakeHand.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //intakeHand.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //twistMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //twistMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        spoolMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        spoolMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Add the gyroscope to the configuration on the phones
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        gyro = hardwareMap.get(BNO055IMU.class, "gyro");
        gyro.initialize(parameters);

        // Magnetic Switch
        MagUp = hardwareMap.get(DigitalChannel.class, "MagUp");
        MagUp.setMode(DigitalChannel.Mode.INPUT); // set the digital channel to input.
        MagDown = hardwareMap.get(DigitalChannel.class, "MagDown");
        MagDown.setMode(DigitalChannel.Mode.INPUT); // set the digital channel to input.


        //Add the intake to the configuration on the phones
        //intake = hardwareMap.dcMotor.get("intake");
        //spinner = hardwareMap.servo.get("spinner");
        //color = hardwareMap.get(ColorSensor.class, "color");
        //intake.setDirection(DcMotor.Direction.REVERSE);
        //color.enableLed(true);

    }
}
