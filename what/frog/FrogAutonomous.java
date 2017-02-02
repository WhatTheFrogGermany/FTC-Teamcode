package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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
        int encoderValue = cmDistance; //for now. I'll later calculate something using the stuff I measure
        frontRightDrive.initDriveToPosition(encoderValue, 5000, 1);
        frontLeftDrive.initDriveToPosition(encoderValue, 5000, 1);
        backLeftDrive.initDriveToPosition(encoderValue, 5000, 1);
        backRightDrive.initDriveToPosition(encoderValue, 5000, 1);

        while(frontRightDrive.drivingToPosition || frontLeftDrive.drivingToPosition || backLeftDrive.drivingToPosition || backRightDrive.drivingToPosition){
            frontRightDrive.driveToPosition();
            frontLeftDrive.driveToPosition();
            backLeftDrive.driveToPosition();
            backRightDrive.driveToPosition();
        }
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
            frontRightDrive = aOmni;
            frontLeftDrive = bOmni;
            backLeftDrive = cOmni;
            backRightDrive = dOmni;
        }
        if(direction == HILDE_FRONT){
            frontRightDrive = bOmni;
            frontLeftDrive = cOmni;
            backLeftDrive = dOmni;
            backRightDrive = aOmni;
        }
        if(direction == FRANZ_FRONT){
            frontRightDrive = cOmni;
            frontLeftDrive = dOmni;
            backLeftDrive = aOmni;
            backRightDrive = bOmni;
        }
        if(direction == BEACON_FRONT){
            frontRightDrive = dOmni;
            frontLeftDrive = aOmni;
            backLeftDrive = bOmni;
            backRightDrive = cOmni;
        }

        frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
    }
}
