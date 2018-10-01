package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

/**
 * Created by nicolas on 9/29/18.
 */

@Autonomous(name = "Box")

public class AutoTest extends AutoMethods {
    public void runOpMode() {
        initRobot();
        waitForStart();

        runtime.reset();

        while (opModeIsActive() && runtime.seconds() < 2) {
            move("front", 0.5);
        }

        sleep(200);

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2) {
            move("left", 0.5);
        }

        sleep(200);

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2) {
            move("back", 0.5);
        }

        sleep(200);

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2) {
            move("right", 0.5);
        }

        sleep(1000);

        turn(90);

        sleep(1000);

        turn(-180);
    }
}
