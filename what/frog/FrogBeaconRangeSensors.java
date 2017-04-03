package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by FTC2 on 03.04.2017.
 */
public class FrogBeaconRangeSensors {
    FrogRange leftRange;
    FrogRange rightRange;

    int oldLeft;
    int oldRight;

    ElapsedTime maxNoRangeTimeLeft;
    ElapsedTime maxNoRangeTimeRight;
    int maxNoRangeInterval = 1000;

    public FrogBeaconRangeSensors(FrogRange leftRange, FrogRange rightRange){
        this.leftRange = leftRange;
        this.rightRange = rightRange;

        maxNoRangeTimeLeft = new ElapsedTime();
        maxNoRangeTimeRight = new ElapsedTime();
    }

    public int getLeft(){
        int value = leftRange.getUltrasonic();
        if(value == 255){
            if(maxNoRangeTimeLeft.milliseconds() > maxNoRangeInterval){
                return 255;
            } else {
                return oldLeft;
            }
        } else {
            maxNoRangeTimeLeft.reset();
            oldLeft = value;
            return value;
        }
    }

    public int getRight(){
        int value = rightRange.getUltrasonic();
        if(value == 255){
            if(maxNoRangeTimeRight.milliseconds() > maxNoRangeInterval){
                return 255;
            } else {
                return oldRight;
            }
        } else {
            maxNoRangeTimeRight.reset();
            oldRight = value;
            return value;
        }
    }
}
