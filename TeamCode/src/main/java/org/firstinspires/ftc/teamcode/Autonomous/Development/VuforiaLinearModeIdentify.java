package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.R;

public class VuforiaLinearModeIdentify {
    @Autonomous(name="VuforiaAutonomus", group="Linear Opmode")
//@Disabled
    public class VuforiaLinearMode extends LinearOpMode {
        VuforiaLocalizer vuforiaLocalizer;
        VuforiaLocalizer.Parameters parameters;
        VuforiaTrackables visionTargets;
        VuforiaTrackable target;
        VuforiaTrackable target1;
        VuforiaTrackable target2;
        VuforiaTrackable target3;
        VuforiaTrackableDefaultListener listener;

        OpenGLMatrix lastKnownLocation;
        OpenGLMatrix phoneLocation;

        public static final String VUFORIA_KEY = "Af2vuDn/////AAAAGXe946hBZkSxhA2XTKJ9Hp8yBAj3UI6Kjy/SeKPMhY8gynJA1+/uvoTP9vJzgR1qyu7JvC1YieE5WDEMAo/v0OD4NOKVXVmxDphz024lZpnf+vKZ03nz30t1wEk50Jv+hy9drTZBr5WSScrf9okUG3IMZ4h5EGyg8X7b0TYS6oN5HxM5XX6+AfnKMimI4olRAsKJN0xF2HhIHchHa3TKWoEhPLwA3Pr3YYtbjjSh6TucVd6SyM6X4yXmnAONYikfV2k2AII8IIGTpzUsFu6xbID4q22rU0CleajBa1GyDO35haGER/93+AStVd1XHKVileLTDgvhvNNfajoJPpA7ef2TVXUvQVbe3duqlqhfhfza";//insert vuforia key

        @Override
        public void runOpMode() throws InterruptedException {

            setupVuforia();
            lastKnownLocation = createMatrix(0,0,0,0,0,0);
            waitForStart();
            visionTargets.activate();
            
            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {
                OpenGLMatrix latestlocation = listener.getUpdatedRobotLocation();

                if(latestlocation != null)
                    lastKnownLocation = latestlocation;

                telemetry.addData("Tracking " + target.getName(), listener.isVisible());
                telemetry.addData("Last Known Location", formatMatrix(lastKnownLocation));
                telemetry.update();
                idle();
            }
        }

        public void setupVuforia() {
            parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
            parameters.vuforiaLicenseKey = VUFORIA_KEY;
            parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
            vuforiaLocalizer = ClassFactory.createVuforiaLocalizer(parameters);

            visionTargets = vuforiaLocalizer.loadTrackablesFromAsset("RoverRuckus");

            target = visionTargets.get(0);
            target.setName("BluePerimeter_target");
            target1 = visionTargets.get(0);
            target1.setName("RedPerimeter_target");
            target2 = visionTargets.get(0);
            target2.setName("FrontPerimeter_target");
            target3 = visionTargets.get(0);
            target3.setName("BackPerimeter_target");
            target.setLocation(createMatrix(0,0,0,0,0,0));

            phoneLocation = createMatrix(0,0,0,0,0,0);

            listener = (VuforiaTrackableDefaultListener) target.getListener();
            listener.setPhoneInformation(phoneLocation, parameters.cameraDirection );
        }

        public OpenGLMatrix createMatrix(float x, float y, float z, float u, float v, float w) {
            return OpenGLMatrix.translation(x, y, z).
                    multiplied(Orientation.getRotationMatrix(
                            AxesReference.EXTRINSIC, AxesOrder.XYX, AngleUnit.DEGREES, u, v, w));

        }
        public String formatMatrix(OpenGLMatrix matrix){
            return matrix.formatAsTransform();
        }



    }
}
