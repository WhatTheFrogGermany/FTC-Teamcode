package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

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

    double power;

    ArrayList<FrogAction> actionList;
    int actionIndex = 0;

    int robotX;
    int robotY;
    int heading;

    FrogMotor frontRightDrive;
    FrogMotor frontLeftDrive;
    FrogMotor backLeftDrive;
    FrogMotor backRightDrive;

    ElapsedTime waitTime;
    int waitMilliSecs;

    public void initWait(int milliSecs){
        waitTime = new ElapsedTime();
        waitMilliSecs = milliSecs;
    }

    public boolean waiting(){
        if(waitTime.milliseconds() > waitMilliSecs){
            return false;
        } else {
            return true;
        }
    }
    public void driveToPosition(int x, int y){

    }

    public void setDriveTolerances(double tolerance){
        frontRightDrive.setTolerance(tolerance);
        frontLeftDrive.setTolerance(tolerance);
        backLeftDrive.setTolerance(tolerance);
        backRightDrive.setTolerance(tolerance);
    }
    public void initDriveToHeading(int heading){
        this.heading = heading;
    }
    public void driveToHeading(){
        if(drivingToHeading()) {
            int difference = heading - getHeading();
            if(difference > 180){
                difference = 360 - difference;
            }else if (difference < -180){
                difference = difference + 360;
            }
            power = ((difference) * 0.005556 * 0.3);
            telemetry.addData("power", power);
            telemetry.addData("heading", heading);
            telemetry.addData("gyro val", getHeading());

            frontRightDrive.setPower(power);
            frontLeftDrive.setPower(-power);
            backLeftDrive.setPower(-power);
            backRightDrive.setPower(power);

        } else {
            stopDrive();
        }


    }

    public boolean drivingToHeading(){
        return (Math.abs(heading - getHeading()) > 5);
    }

    public void stopDrive(){
        frontRightDrive.reset();
        frontLeftDrive.reset();
        backLeftDrive.reset();
        backRightDrive.reset();
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
        //int result = Math.round(((360-topGyro.getHeading())+bottomGyro.getHeading())/2);
        //telemetry.addData("result", result);
        return bottomGyro.getHeading();
    }

    public void resetHeading(){
        bottomGyro.reset();
        topGyro.reset();
    }
    public int getFront(){
        return front;
    }

    public void pushBlueBeacon(){
        if (leftBeaconColor.blue() > leftBeaconColor.red()) {
            leftBeaconServo.setPosition(1);
        }
        else if (rightBeaconColor.blue() > rightBeaconColor.red()){
            rightBeaconServo.setPosition(0);
        }

    }

    public void pushRedBeacon(){
        if (leftBeaconColor.red() > leftBeaconColor.blue()) {
            leftBeaconServo.setPosition(1);
        }
        else if (rightBeaconColor.red() > rightBeaconColor.blue()) {
            rightBeaconServo.setPosition(0);
        }
    }

    public void resetServo (){

        rightBeaconServo.setPosition(1);
        leftBeaconServo.setPosition(0);

    }

    public void initShootBall(){
        wildeHildeMotor.initRotateRounds(1,1);
    }

    public void shootBall(){
        wildeHildeMotor.driveToPosition();
    }

    public boolean shooting(){
        return wildeHildeMotor.drivingToPosition;
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
        gabiBlockServo.setPosition(0.15);
    }
}
