package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;

/**
 * Created by FTC2 on 24.01.2017.
 */
public class FrogRange {
    HardwareMap hardwareMap;
    I2cDevice i2cDevice;
    byte[] readCache;
    I2cDeviceSynch deviceRead;
    int address;
    String name;
}
