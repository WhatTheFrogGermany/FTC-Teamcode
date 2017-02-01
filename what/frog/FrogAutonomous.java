package org.firstinspires.ftc.teamcode.what.frog;

/**
 * Created by FTC2 on 01.02.2017.
 */
public class FrogAutonomous extends FrogOpMode {
    int robotX;
    int robotY;

    public void driveToPosition(int x, int y){

    }

    public void driveToHeading(int heading){

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
