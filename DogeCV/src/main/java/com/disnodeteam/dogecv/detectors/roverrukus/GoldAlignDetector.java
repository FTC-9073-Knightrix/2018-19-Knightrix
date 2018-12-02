package com.disnodeteam.dogecv.detectors.roverrukus;

import android.util.Log;

import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.DogeCVDetector;
import com.disnodeteam.dogecv.filters.DogeCVColorFilter;
import com.disnodeteam.dogecv.filters.LeviColorFilter;
import com.disnodeteam.dogecv.scoring.DogeCVScorer;
import com.disnodeteam.dogecv.scoring.MaxAreaScorer;
import com.disnodeteam.dogecv.scoring.PerfectAreaScorer;
import com.disnodeteam.dogecv.scoring.RatioScorer;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victo on 9/17/2018.
 */

public class GoldAlignDetector extends DogeCVDetector {

    private Mat workingMat = new Mat();
    private Mat blurredMat  = new Mat();
    private Mat maskYellow = new Mat();
    private Mat hiarchy  = new Mat();
    private Mat structure = new Mat();
    private Size stretchKernal = new Size(10,10);
    private Size newSize = new Size();


    private boolean found = false;
    private boolean aligned = false;
    private double goldXPos = 0;
    private double goldYPos = 0;
    private double xCenterPos = 0;
    private double yCenterPos = 0;
    private double xDistanceFromCenter = 0;
    private double yDistanceFromCenter = 0;
    private double angle = 0;

    public boolean debugAlignment = true;
    public boolean debugContours  = true;
    public boolean stretch        = true;
    public double minArea = 1000;
    public double alignPosOffset = 0;
    public double alignSize = 100;

    public DogeCV.AreaScoringMethod areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA;
    public DogeCVColorFilter yellowFilter   = new LeviColorFilter(LeviColorFilter.ColorPreset.YELLOW);
    public RatioScorer      ratioScorer        = new RatioScorer(1.0, 3);
    public MaxAreaScorer    maxAreaScorer      = new MaxAreaScorer( 0.01);
    public PerfectAreaScorer perfectAreaScorer = new PerfectAreaScorer(5000,0.05);

    @Override
    public Mat process(Mat input) {
        if(input.channels() < 0 || input.cols() <= 0){
            Log.e("DogeCV", "Bad INPUT MAT!");

        }
        input.copyTo(workingMat);
        input.release();

        Imgproc.GaussianBlur(workingMat,workingMat,new Size(5,5),0);
        yellowFilter.process(workingMat.clone(),maskYellow);

        List<MatOfPoint> contoursYellow = new ArrayList<>();

        Imgproc.findContours(maskYellow, contoursYellow, hiarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        Imgproc.drawContours(workingMat,contoursYellow,-1,new Scalar(230,70,70),2);


        Rect bestRect = null;
        double bestDiffrence = Double.MAX_VALUE;

        for(MatOfPoint cont : contoursYellow){
            double score = calculateScore(cont);


            // Get bounding rect of contour
            Rect rect = Imgproc.boundingRect(cont);
            Imgproc.rectangle(workingMat, rect.tl(), rect.br(), new Scalar(0,0,255),2);

            if(score < bestDiffrence){
                bestDiffrence = score;
                bestRect = rect;
            }
        }

        //to calculate green bar for the x position
        double alignX = (getAdjustedSize().width / 2) + alignPosOffset;
        double alignXMin = alignX - (alignSize / 2);
        double alignXMax = alignX +(alignSize / 2);
        double xPos = 0;

        //to calculate green bar for the y position
        double alignY = (getAdjustedSize().height / 2) + alignPosOffset;
        double alignYMin = alignY - (alignSize / 2);
        double alignYMax = alignY +(alignSize / 2);
        double yPos = 0;


        if(bestRect != null){
            Imgproc.rectangle(workingMat, bestRect.tl(), bestRect.br(), new Scalar(255,0,0),4);
            Imgproc.putText(workingMat, "Chosen", bestRect.tl(),0,1,new Scalar(255,255,255));

            xPos = bestRect.x + (bestRect.width / 2);
            goldXPos = xPos;
            yPos = bestRect.y + (bestRect.height / 2);
            goldYPos = yPos;

            // creates circle of radius 5 in the middle of the gold block
            Imgproc.circle(workingMat, new Point(xPos,yPos), 5, new Scalar(0,255,0),2);

            // calculate coordinate for the center of image
            xCenterPos = getAdjustedSize().width/2;
            yCenterPos = getAdjustedSize().height/2;

            // calculate distance from center of cube to center of image
            xDistanceFromCenter = xCenterPos - xPos;
            yDistanceFromCenter = yCenterPos - yPos;

            // calculates the angle
            angle = Math.atan2((yDistanceFromCenter),(xDistanceFromCenter));

            // calculates the distance to the center using pythagorean theorem
            double distance = Math.sqrt((Math.pow(xDistanceFromCenter,2))+(Math.pow(yDistanceFromCenter,2)));

            //  determines if the circle is in the alignment area
            if(xPos < alignXMax && xPos > alignXMin){
                if(yPos < alignYMax && yPos > alignYMin) {
                    aligned = true;
                }
            } else {
                aligned = false;
            }

            // draws yellow line in the axis where cube is located
            Imgproc.line(workingMat,new Point(xPos, getAdjustedSize().height), new Point(xPos, getAdjustedSize().height - 30),new Scalar(255,255,0), 2);
            Imgproc.line(workingMat,new Point(getAdjustedSize().width, yPos), new Point(getAdjustedSize().width - 30, yPos),new Scalar(255,255,0), 2);

            Imgproc.putText(workingMat,"Current X: " + bestRect.x,new Point(10,getAdjustedSize().height - 10),0,0.5, new Scalar(255,255,255),1);
            found = true;
        }else{
            found = false;
            aligned = false;
        }
        if(debugAlignment) {
            // draws line for y
            Imgproc.line(workingMat, new Point(alignXMin, getAdjustedSize().height), new Point(alignXMin, getAdjustedSize().height - 40), new Scalar(0, 255, 0), 2);
            Imgproc.line(workingMat, new Point(alignXMax, getAdjustedSize().height), new Point(alignXMax, getAdjustedSize().height - 40), new Scalar(0, 255, 0), 2);

            // draws line for x
            Imgproc.line(workingMat, new Point(getAdjustedSize().width, alignYMin), new Point(getAdjustedSize().width - 40, alignYMin), new Scalar(0, 255, 0), 2);
            Imgproc.line(workingMat, new Point(getAdjustedSize().width, alignYMax), new Point(getAdjustedSize().width - 40, alignYMax), new Scalar(0, 255, 0), 2);

        }



        Imgproc.putText(workingMat,"Result: " + aligned,new Point(10,getAdjustedSize().height - 30),0,1, new Scalar(255,255,0),1);



        Imgproc.putText(workingMat,"DogeCV 2018.0 Gold Align: " + getAdjustedSize().toString() + " - " + speed.toString() ,new Point(5,30),0,0.5,new Scalar(0,255,255),2);

        return workingMat;

    }

    @Override
    public void useDefaults() {
        addScorer(ratioScorer);
        if(areaScoringMethod == DogeCV.AreaScoringMethod.MAX_AREA){
            addScorer(maxAreaScorer);
        }

        if (areaScoringMethod == DogeCV.AreaScoringMethod.PERFECT_AREA){
            addScorer(perfectAreaScorer);
        }

    }

    public boolean getAligned(){
        return aligned;
    }

    public double getXPosition(){
        return goldXPos;
    }

    public double getYPosition(){
        return goldYPos;
    }

    public double getYCenterPosition(){
        return yCenterPos;
    }

    public double getXCenterPosition(){
        return xCenterPos;
    }

    public double getXDistanceFromCenter(){
        return xDistanceFromCenter;
    }

    public double getYDistanceFromCenter(){
        return yDistanceFromCenter;
    }

    public double getAngle(){
        return (((angle*180)/Math.PI)+180);
    }

    public boolean isFound() {
        return found;
    }
}
