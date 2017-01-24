package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;

/**
 * Created by FTC2 on 24.01.2017.
 */

public class FrogGyro extends FrogSensor {
    final static int HEADING_REGISTER = 0x04;
    final static int INTEGRATED_Z_REGISTER = 0x06;
    final static int RAW_X_REGISTER = 0x08;
    final static int RAW_Y_REGISTER = 0x0a;
    final static int RAW_Z_REGISTER = 0x0c;
    final static int Z_AXIS_OFFSET_REGISTER = 0x0e;
    final static int Z_AXIS_SCALING_REGISTER = 0x10;

    public FrogGyro(HardwareMap hardwareMap, String name, int address){
        super(hardwareMap, name, address);
    }

    public int getHeading(){
        return readTwoBytes(HEADING_REGISTER);
    }

    public int getIntegratedZ(){
        return readTwoBytes(INTEGRATED_Z_REGISTER);
    }

    public int getRawX(){
        return readTwoBytes(RAW_X_REGISTER);
    }

    public int getRawY(){
        return readTwoBytes(RAW_Y_REGISTER);
    }

    public int getRawZRegister(){
        return  readTwoBytes(RAW_Z_REGISTER);
    }
}
