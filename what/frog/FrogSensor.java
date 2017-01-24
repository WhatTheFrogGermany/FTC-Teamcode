package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;

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

    public FrogSensor(HardwareMap hardwareMap, String name, int address){
        this.name = name;
        this.address = address;
        this.hardwareMap = hardwareMap;
        i2cDevice = hardwareMap.i2cDevice.get(name);
        deviceRead = new I2cDeviceSynchImpl(i2cDevice, I2cAddr.create8bit(address), false);
        deviceRead.engage();
    }

    public int readOneByte(int address){
        if(locked){
            return -1;
        }
        readCache = deviceRead.read(address, 1);
        return readCache[0]&0xFF;
    }

    public int readTwoBytes(int address){
        if(locked){
            return -1;
        }
        readCache = deviceRead.read(address, 2);
        return FrogMath.bytesToInt(readCache);
    }

    public void write(int address, byte[] toWrite){
        deviceRead.write(address, toWrite);
    }
}
