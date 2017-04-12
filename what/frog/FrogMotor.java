package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by FTC2 on 27.01.2017.
 */

public class FrogMotor extends DcMotorImpl {
    double gearRatio = 1; //1/20 should put in the value 20
    int encoderPPR = 7; //default for NeveRestMotors
    public boolean drivingToPosition = false;

    int targetPosition = 0;
    public int lastTargetPosition = 0;
    int stoppingInterval;
    public double normalMotorPower;
    public double motorCurrentPower;
    double tolerance = 0.01;

    ElapsedTime waitTime;

    public FrogMotor(DcMotorController controller, int port){
        super(controller, port);
    }

    public FrogMotor(DcMotor dcMotor){
        super(dcMotor.getController(), dcMotor.getPortNumber());
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
        this.stoppingInterval = Math.abs(stoppingInterval);
        this.normalMotorPower = Math.abs(normalMotorPower);
        waitTime = new ElapsedTime();
    }

    public void setTolerance(double tolerance){
        this.tolerance = tolerance;
    }

    public void driveToPosition(){
        if(drivingToPosition) {
            if(Math.abs(targetPosition - getCurrentPosition()) > stoppingInterval) {
                if((targetPosition - getCurrentPosition()) > 0){
                    setPower(normalMotorPower);
                } else {
                    setPower(-normalMotorPower);
                }

            } else {
                int distance = targetPosition - getCurrentPosition();
                double scale = (double)distance / stoppingInterval;
                motorCurrentPower = normalMotorPower * scale;
                setPower(motorCurrentPower);

                if(Math.abs(motorCurrentPower) < tolerance){
                    if(waitTime.milliseconds() > 500){
                        lastTargetPosition = targetPosition;
                        drivingToPosition = false;
                        setPower(0);
                    }
                } else {
                    waitTime.reset();
                }
            }
        }
    }

    public void initRotateRounds(double rounds, double motorPower){
        if(!drivingToPosition) {
            int targetPosition = lastTargetPosition + Math.round((int) (rounds * encoderPPR * gearRatio));
            int stoppingInterval = Math.round((int) (encoderPPR * gearRatio / 5));
            initDriveToPosition(targetPosition, stoppingInterval, motorPower);
        }
    }

    public void stopDrivingToPosition(){
        drivingToPosition = false;
    }

    public void reset(){
        setPower(0);
        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMode(RunMode.RUN_USING_ENCODER);;
        drivingToPosition = false;
        lastTargetPosition = 0;
    }
}
