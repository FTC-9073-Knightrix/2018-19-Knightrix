/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.Autonomous.Development;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Autonomous.AutoMethods;

/*
1.Downgrades image size
2.Applies yellow filter
3.Draws box around biggest continuous yellow it finds
4.Calculates x and y position of the middle of the box
5.Calculates whether middle position is within a certain pixel range


IsAligned = true or false depending on phones alignemnt to gold particle.
alignPositionOffset = changes y Position of the pixels(Two green lines)

lowest value doesn't go pass 600 if the downscale method defines it by reducing the size of image.
AlignMin and ALignMax define the area where the alignment value is true.



 */

@Autonomous(name="Walking to the Gold", group="Auto")

public class NoRobotGold extends LinearOpMode {

    private GoldAlignDetector detector;

    public void runOpMode() {


        double pos1;
        boolean bool1;

        telemetry.addData("Status", "Auto - Distance to the gold");

        detector = new GoldAlignDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();

        // Optional Tuning
        detector.alignSize = 100; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        /* detects cloesest object(most area) even when similar particles surround it, does not detect depth so this is why this happens
         * at a flat plane view */
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005;

        detector.ratioScorer.weight = 5;
        detector.ratioScorer.perfectRatio = 1.0;

        detector.enable();

        waitForStart();

        while (opModeIsActive()) {

            if (detector.isFound()) {
                if (detector.getXPosition() < 80) {
                    telemetry.addLine("TURN LEFT");
                } else if (detector.getXPosition() > 400) {
                    telemetry.addLine("TURN RIGHT");
                } else {
                    double left = Range.clip((20 / detector.getYPosition() - (detector.getXDistanceFromCenter() / detector.getYPosition())), 0, 1);
                    double right = Range.clip((20 / detector.getYPosition()) + (detector.getXDistanceFromCenter() / detector.getYPosition()), 0, 1);

                    double frontPower = Range.clip(left+right,-1,1);
                    double sidePower = Range.clip(left-right,-1,1);

                    telemetry.addData("Angle", Math.toDegrees(Math.atan(frontPower/sidePower)));

                    if (Math.abs(frontPower) > Math.abs(sidePower)) {
                        telemetry.addData("Power", Math.abs(frontPower));
                    }
                    else {
                        telemetry.addData("Power", Math.abs(sidePower));
                    }
                }
                telemetry.update();
            } else {
                telemetry.addLine("STOP");
                telemetry.update();
            }
        }
    }
}
