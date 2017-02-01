package org.firstinspires.ftc.teamcode.what.frog;

/**
 * Created by FTC2 on 01.02.2017.
 */
public class FrogAutonomous extends FrogOpMode {
    public final static short HILDE_FRONT = 0;
    public final static short FRANZ_FRONT = 1;
    public final static short BEACON_FRONT = 2;
    public final static short LIFT_FRONT = 3;

    int robotX;
    int robotY;

    short front;
    public void driveToPosition(int x, int y){

    }

    public void driveToHeading(int heading){

    }

    public void driveDistance(int cmDistance){

    }

    public int getHeading(){
        int result = (topGyro.getHeading()+360-bottomGyro.getHeading())/2;
        return result;
    }

    public void pushBlueBeacon(){

    }

    public void pushRedBeacon(){

    }

    public void shootBall(){

    }
}
