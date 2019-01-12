//Declare the package
package org.firstinspires.ftc.teamcode.Autonomous;

//Import the dependencies needed to run the programs
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

//Create the class declaration, extending LinearOpMode
public abstract class AutoHardwareMap extends LinearOpMode {

    //Create the timer
    public ElapsedTime runtime = new ElapsedTime();

    //Create the four motors
    public DcMotor leftFrontDrive;
    public DcMotor rightFrontDrive;
    public DcMotor rightBackDrive;
    public DcMotor leftBackDrive;
    public DcMotor hangMotor;

    public Servo marker;

    //public Camera camera;

    //Create the gyroscope
    //public NavxMicroNavigationSensor navxGyro;
    public BNO055IMU gyro;
    //Create the orientation variable for the robot position
    public Orientation orientation;
    //Create the angle tracker
    public double angle = 0;

    public ModernRoboticsI2cRangeSensor range;
    public ModernRoboticsI2cRangeSensor range2;
    public DistanceSensor range3; //

    //amount of clicks per cm
    public final double ENCDISTANCE = 35.4736842105;

    public DigitalChannel MagUp;  // Rev Magnetic Switch
    public DigitalChannel MagDown;  // Rev Magnetic Switch

    public static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    public static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    public static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    public static final String VUFORIA_KEY = "AYl7ALf/////AAABmYRUZlrZD0yrpIpwu/lCqI404R8xljtOfAC2CqbtCpS8cdQOxNeRkjlt2adr+rxsSRHbQ8BerXEveMhl/jmXPM8GaYKri+E1j+LZplYraYHpWu6YXtceQQ24UIRIDKtRXkzUY9Hp5QY+Zrie7bELZTAm21wWSNLtF1XPd2XyEUa0Lw96c5BpczME5VhEQdVCfHA1TLnOEsQutrw173uOiZvvKdeEV1bGeRAlfkcf99ffKvTlVMZORlMCksWeKcatZRoC/zBXphIJMETpgYZCkmy59usG/JxL6rQrAjXHaLM60fzvLm2E9Fl5guhDrydctoHye8+Zla8Uh0tFAqF14YaElg2mmhAci59Nq2c1hher";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    public VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the Tensor Flow Object
     * Detection engine.
     */
    public TFObjectDetector tfod;
}