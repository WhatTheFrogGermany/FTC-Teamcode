package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;

/**
 * Created by FTC2 on 24.01.2017.
 */
public class FrogRange extends FrogSensor{
    final static byte ULTRASONIC_REGISTER = 0x04;
    final static byte OPTICAL_REGISTER = 0x05;

    public FrogRange(HardwareMap hardwareMap, String name, int address){
        super(hardwareMap, name, address);
    };
    public int getUltrasonic(){
        return super.readOneByte(0x04);
    }

    public int getOptical(){
        return super.readOneByte(0x05);
    }

}
