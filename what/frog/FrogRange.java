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

    int oldUltrasonic = 0;

    public FrogRange(HardwareMap hardwareMap, String name, int address){
        super(hardwareMap, name, address);
    };
    public int getUltrasonic(){
        int value = super.readOneByte(ULTRASONIC_REGISTER);
        if(value == -1){
            return oldUltrasonic;
        } else {
            oldUltrasonic = value;
            return value;
        }
    }

    public int getOptical(){
        return super.readOneByte(0x05);
    }

}
