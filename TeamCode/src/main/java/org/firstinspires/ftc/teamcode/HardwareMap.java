package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


public abstract class HardwareMap extends OpMode {

    DcMotor leftFrontDrive;
    DcMotor rightFrontDrive;
    DcMotor rightBackDrive;
    DcMotor leftBackDrive;


    NavxMicroNavigationSensor navGyro;
    Orientation orientation;
    int navxCounter = 3;
    int gyroDegrees = 0;


    boolean auto = false;

    boolean Vuforia_Init = false;
    VuforiaLocalizer vuforia;
    VuforiaTrackable relicTemplate = null;
    String pictograph = null;

    VuforiaLocalizer vuforiaLocalizer;
    VuforiaLocalizer.Parameters parameters;
    VuforiaTrackables visionTargets;
    VuforiaTrackable target;
    VuforiaTrackableDefaultListener listener;

    OpenGLMatrix lastKnownLocation;
    OpenGLMatrix phoneLocation;


    public void init() {
        leftFrontDrive = hardwareMap.dcMotor.get("LF");
        rightFrontDrive = hardwareMap.dcMotor.get("RF");
        rightBackDrive = hardwareMap.dcMotor.get("RB");
        leftBackDrive = hardwareMap.dcMotor.get("LB");
        navGyro = hardwareMap.get(NavxMicroNavigationSensor.class, "gyro");


        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = "Af2vuDn/////AAAAGXe946hBZkSxhA2XTKJ9Hp8yBAj3UI6Kjy/SeKPMhY8gynJA1+/uvoTP9vJzgR1qyu7JvC1YieE5WDEMAo/v0OD4NOKVXVmxDphz024lZpnf+vKZ03nz30t1wEk50Jv+hy9drTZBr5WSScrf9okUG3IMZ4h5EGyg8X7b0TYS6oN5HxM5XX6+AfnKMimI4olRAsKJN0xF2HhIHchHa3TKWoEhPLwA3Pr3YYtbjjSh6TucVd6SyM6X4yXmnAONYikfV2k2AII8IIGTpzUsFu6xbID4q22rU0CleajBa1GyDO35haGER/93+AStVd1XHKVileLTDgvhvNNfajoJPpA7ef2TVXUvQVbe3duqlqhfhfza";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);


        VuforiaTrackables roverRuckusTrackables = this.vuforia.loadTrackablesFromAsset("roverRuckus");






    }

    public void init_loop() {
        if(navGyro.isCalibrating()) {
            telemetry.addLine("navx Calibrating");

        }



    }
}