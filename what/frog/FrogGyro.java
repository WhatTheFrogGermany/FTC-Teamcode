package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;

/**
 * Created by FTC2 on 24.01.2017.
 */

public class FrogGyro {
    final static int HEADING_REGISTER = 0x04;
    final static int INTEGRATED_Z_REGISTER = 0x06;
    final static int RAW_X_REGISTER = 0x08;
    final static int RAW_Y_REGISTER = 0x0a;
    final static int RAW_Z_REGISTER = 0x0c;
    final static int Z_AXIS_OFFSET_REGISTER = 0x0e;
    final static int Z_AXIS_SCALING_REGISTER = 0x10;

    HardwareMap hardwareMap;
    I2cDevice i2cDevice;
    byte[] readCache;
    I2cDeviceSynch deviceRead;
    int address;
    String name;

    public FrogGyro(HardwareMap hardwareMap, String name, int address){
        this.name = name;
        this.address = address;
        this.hardwareMap = hardwareMap;
    }
    public void init(){
        i2cDevice = hardwareMap.i2cDevice.get(name);
        deviceRead = new I2cDeviceSynchImpl(i2cDevice, I2cAddr.create8bit(address), false);
        deviceRead.engage();
    }

    public int getHeading(){
        readCache = deviceRead.read(HEADING_REGISTER, 2);
        int heading = bytesToInt(readCache);
        return heading;

    }

    public int bytesToInt(byte[] bytes){
        //this converts two bytes (lsb:msb) to an integer
        int val = ((bytes[1] & 0xff) << 8) | (bytes[0] & 0xff);
        return val;
    }




}
