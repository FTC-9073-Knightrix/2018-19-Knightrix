//Declare the package
package org.firstinspires.ftc.teamcode.TeleOp.Development;

//Import the dependencies needed to run the program
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.TeleOp.TeleOpMethods;

//Add the program to the index of TeleOp programs and to the Gyro group
@TeleOp(group = "Test", name = "Gyro Values")

//Create the class declaration, extending TeleOpMethods
public class GyroValues extends OpMode {

    /*public Orientation orientation;
    public int gyroDegrees = 0;
    public NavxMicroNavigationSensor navxGyro;*/

    BNO055IMU gyro;
    Orientation orientation;

    public void init() {
        //navxGyro = hardwareMap.get(NavxMicroNavigationSensor.class, "gyro");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;

        gyro = hardwareMap.get(BNO055IMU.class, "gyro");
        gyro.initialize(parameters);
    }

    //Run the entire program from within the loop() method from OpMode
    public void loop() {

        //Get the current position of the robot
        /*orientation = navxGyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);

        //Add the current angle of the robot to the display
        telemetry.addLine("Gyro Value: " + orientation + "\u00b0");
        //Update telemetry
        telemetry.update();*/

        orientation = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        telemetry.addData("Gyro", orientation.firstAngle);
        telemetry.update();
    }
}
