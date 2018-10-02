//Declare the package
package org.firstinspires.ftc.teamcode.Autonomous;

//Import the dependencies needed to run the programs
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

//Create the class declaration, extending LinearOpMode
public abstract class AutoHardwareMap extends LinearOpMode {

    //Create the timer
    public ElapsedTime runtime = new ElapsedTime();

    //Create the four motors
    public DcMotor leftFrontDrive;
    public DcMotor rightFrontDrive;
    public DcMotor rightBackDrive;
    public DcMotor leftBackDrive;

    //Create the gyroscope
    public NavxMicroNavigationSensor navxGyro;
    //Create the orientation variable for the robot position
    public Orientation orientation;
    //Create the angle tracker
    public double angle = 0;
}