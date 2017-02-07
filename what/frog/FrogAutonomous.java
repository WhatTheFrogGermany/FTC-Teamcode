package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.ArrayList;

/**
 * Created by FTC2 on 01.02.2017.
 */
public class FrogAutonomous extends FrogOpMode {
    public final static short HILDE_FRONT = 0;
    public final static short FRANZ_FRONT = 1;
    public final static short BEACON_FRONT = 2;
    public final static short GABI_FRONT = 3;

    short front = GABI_FRONT;

    ArrayList<FrogAction> actionList;
    int actionIndex = 0;

    int robotX;
    int robotY;

    FrogMotor frontRightDrive;
    FrogMotor frontLeftDrive;
    FrogMotor backLeftDrive;
    FrogMotor backRightDrive;

    public void driveToPosition(int x, int y){

    }

    public void driveToHeading(int heading){
        while(Math.abs(heading - getHeading()) > 2 ){
            if(heading - getHeading() > 0){
                frontRightDrive.setPower(0.3);
                frontLeftDrive.setPower(-0.3);
                backLeftDrive.setPower(-0.3);
                backRightDrive.setPower(0.3);
            } else {
                frontRightDrive.setPower(-0.3);
                frontLeftDrive.setPower(0.3);
                backLeftDrive.setPower(0.3);
                backRightDrive.setPower(-0.3);
            }
        }

    }

    public void initDriveDistance(int cmDistance){
        int encoderValue = cmDistance; //for now. I'll later calculate something using the stuff I measure
        frontRightDrive.initDriveToPosition(encoderValue, 5000, 1);
        frontLeftDrive.initDriveToPosition(encoderValue, 5000, 1);
        backLeftDrive.initDriveToPosition(encoderValue, 5000, 1);
        backRightDrive.initDriveToPosition(encoderValue, 5000, 1);
    }

    public void driveDistance(){
        frontRightDrive.driveToPosition();
        frontLeftDrive.driveToPosition();
        backLeftDrive.driveToPosition();
        backRightDrive.driveToPosition();
    }

    public boolean drivingToPosition(){
        return (frontRightDrive.drivingToPosition || frontLeftDrive.drivingToPosition || backLeftDrive.drivingToPosition || backRightDrive.drivingToPosition);
    }

    public void resetDrive(){
        frontRightDrive.reset();
        frontLeftDrive.reset();
        backLeftDrive.reset();
        backRightDrive.reset();
    }
    public int getHeading(){
        int result = (360-topGyro.getHeading()+bottomGyro.getHeading())/2;
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

    public void changeDirection(short direction){
        if(direction == GABI_FRONT){
            frontRightDrive = aOmni;
            frontLeftDrive = bOmni;
            backLeftDrive = cOmni;
            backRightDrive = dOmni;
            front = direction;
        }
        if(direction == HILDE_FRONT){
            frontRightDrive = bOmni;
            frontLeftDrive = cOmni;
            backLeftDrive = dOmni;
            backRightDrive = aOmni;
            front = direction;
        }
        if(direction == FRANZ_FRONT){
            frontRightDrive = cOmni;
            frontLeftDrive = dOmni;
            backLeftDrive = aOmni;
            backRightDrive = bOmni;
            front = direction;
        }
        if(direction == BEACON_FRONT){
            frontRightDrive = dOmni;
            frontLeftDrive = aOmni;
            backLeftDrive = bOmni;
            backRightDrive = cOmni;
            front = direction;
        }

        frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void addAction(FrogAction action){
        actionList.add(action);
    }

    public void nextAction(){
        actionIndex++;
    }

    @Override
    public void init() {
        super.init();
        actionList = new ArrayList<>();
    }

    @Override
    public void loop() {
        super.loop();
        if(actionIndex < actionList.size()) {
            actionList.get(actionIndex).action();
        }
    }
}
