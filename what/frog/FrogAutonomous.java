package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FTC2 on 01.02.2017.
 */
public class FrogAutonomous extends FrogOpMode {
    public final static short HILDE_FRONT = 0;
    public final static short FRANZ_FRONT = 1;
    public final static short BEACON_FRONT = 2;
    public final static short GABI_FRONT = 3;

    short front = GABI_FRONT;

    int robotX;
    int robotY;

    FrogMotor frontRightDrive;
    FrogMotor frontLeftDrive;
    FrogMotor backLeftDrive;
    FrogMotor backRightDrive;

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

    public void resetHeading(){
        bottomGyro.reset();
        topGyro.reset();
    }
    public int getFront(){
        return front;
    }
    public void pushBlueBeacon(){

    }

    public void pushRedBeacon(){

    }

    public void shootBall(){

    }

    public void changeDirection(int direction){
        if(direction == GABI_FRONT){

        }
    }
}
