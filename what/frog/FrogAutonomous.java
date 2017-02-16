package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;

/**
 * Created by FTC2 on 01.02.2017.
 */
public class FrogAutonomous extends FrogOpMode {

    double power;

    ArrayList<FrogAction> actionList;
    int actionIndex = 0;

    int robotX = 0;
    int robotY = 0;
    int heading;
    int rangeDistance;

    ElapsedTime waitTime;
    int waitMilliSecs;

    public void setRobotXY(int x, int y){
        robotX = x;
        robotY = y;
    }
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
    public void initDriveToPosition(int x, int y){

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
        int encoderValue = cmDistance * 10; //for now. I'll later calculate something using the stuff I measure
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

    public void resetHeading(){
        bottomGyro.reset();
        topGyro.reset();
    }
    public int getFront(){
        return front;
    }

    public void driveToWall(int distance){
        telemetry.addData("leftBeaconRange", leftBeaconRange.getUltrasonic());
        telemetry.addData("rightBeaconRange", rightBeaconRange.getUltrasonic());
        rangeDistance = distance;

        double leftPower = (leftBeaconRange.getUltrasonic() - distance) *0.005;
        double rightPower = (rightBeaconRange.getUltrasonic() - distance) * 0.005;
        leftPower = FrogMath.checkSmallerOne(leftPower);
        rightPower = FrogMath.checkSmallerOne(rightPower);

        frontLeftDrive.setPower(leftPower);
        backLeftDrive.setPower(leftPower);

        frontRightDrive.setPower(rightPower);
        backRightDrive.setPower(rightPower);
    }

    public boolean drivingToWall(){
        if (leftBeaconRange.getUltrasonic() == 255 && rightBeaconRange.getUltrasonic() == 255)
        {
            telemetry.addData("Fehlermeldung bei BeaconSensor", leftBeaconRange);
            return false;
        }
        return (Math.abs(rangeDistance - leftBeaconRange.getUltrasonic()) > 1) || (Math.abs(rangeDistance - rightBeaconRange.getUltrasonic()) > 1);
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
        leftBeaconServo.setPosition(0);
        rightBeaconServo.setPosition(1);
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
