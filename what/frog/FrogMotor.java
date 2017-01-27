package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorImpl;

/**
 * Created by FTC2 on 27.01.2017.
 */

public class FrogMotor extends DcMotorImpl {
    double gearRatio = 1; //1/20 should put in the value 20
    int encoderPPR = 7; //default for NeveRestMotors
    boolean drivingToPosition = false;

    int targetPosition = 0;
    int stoppingInterval;
    int stopStartPosition;
    double normalMotorPower;
    double motorCurrentPower;

    public FrogMotor(DcMotorController controller, int port){
        super(controller, port);
    }

    public void setGearRatio(double ratio){
        gearRatio = ratio;
    }

    public void setEncoderPPR(int ppr){
        encoderPPR = ppr;
    }

    public void initDriveToPosition(int targetPosition, int stoppingInterval, double normalMotorPower){
        drivingToPosition = true;
        this.targetPosition = targetPosition;
        this.stoppingInterval = stoppingInterval;
        this.normalMotorPower = normalMotorPower;
    }

    public void driveToPosition(){
        if(drivingToPosition) {
            if (getCurrentPosition() < stopStartPosition) {
                setPower(normalMotorPower);
            } else {
                int distance = targetPosition - getCurrentPosition();
                double scale = (double) distance / stoppingInterval;
                motorCurrentPower = normalMotorPower * scale;
                setPower(motorCurrentPower);
                if (motorCurrentPower < 0.01) {
                    drivingToPosition = false;
                }
            }
        }
    }

    public void initRotateRounds(double rounds, double motorPower){
        int targetPosition = Math.round((int)(rounds * encoderPPR * gearRatio));
        int stoppingInterval = Math.round((int)(encoderPPR * gearRatio));
        initDriveToPosition(targetPosition, stoppingInterval, motorPower);
    }

}
