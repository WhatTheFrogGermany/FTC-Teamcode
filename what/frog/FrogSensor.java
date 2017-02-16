package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by FTC2 on 24.01.2017.
 */
public class FrogSensor {
    HardwareMap hardwareMap;
    I2cDevice i2cDevice;
    byte[] readCache;
    I2cDeviceSynch deviceRead;
    int address;
    String name;
    boolean locked = false;
    int milliSecondsWait = 250;
    ElapsedTime waitTime;
    boolean isValueNew = false;

    public FrogSensor(HardwareMap hardwareMap, String name, int address){
        this.name = name;
        this.address = address;
        this.hardwareMap = hardwareMap;
        i2cDevice = hardwareMap.i2cDevice.get(name);
        deviceRead = new I2cDeviceSynchImpl(i2cDevice, I2cAddr.create8bit(address), false);
        deviceRead.engage();
        waitTime = new ElapsedTime();
    }

    public int readOneByte(int address){
        if(locked){
            return -2;
        }
        if(waitTime.milliseconds() > milliSecondsWait) {
            waitTime.reset();
            readCache = deviceRead.read(address, 1);
            isValueNew = true;
            return readCache[0] & 0xFF;
        } else {
            isValueNew = false;
            return -1;
        }
    }

    public int readTwoBytes(int address){
        if(locked){
            return -2;
        }
        if(waitTime.milliseconds() > milliSecondsWait){
            waitTime.reset();
            readCache = deviceRead.read(address, 2);
            isValueNew = true;
            return FrogMath.bytesToInt(readCache);
        } else {
            isValueNew = false;
            return -1;
        }

    }

    public boolean getIsValueNew(){return isValueNew;}

    public void write(int address, byte[] toWrite){
        deviceRead.write(address, toWrite);
    }
}
