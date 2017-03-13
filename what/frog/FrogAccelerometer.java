package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by FTC2 on 13.03.2017.
 */
public class FrogAccelerometer extends FrogSensor {
    final static int ACCL_X_REGISTER = 0x06;
    final static int ACCL_Y_REGISTER = 0x08;
    final static int ACCL_Z_REGISTER = 0x0A;
    final static int COMMAND_REGISTER = 0x03;
    final static int ACCL_SCALE_REGISTER = 0x20;

    final static int WRITE_EEPROM_COMMAND = 0x57;

    public FrogAccelerometer(HardwareMap hardwareMap, String name, int address){
        super(hardwareMap, name, address);
    }

    public int getAcclX(){
        return readTwoBytes(ACCL_X_REGISTER);
    }

    public int getAcclY(){
        return readTwoBytes(ACCL_Y_REGISTER);
    }

    public int getAcclZ(){
        return readTwoBytes(ACCL_Z_REGISTER);
    }

    public void setAcclScale(double scale){
        byte beforePoint = (byte)Math.floor(scale);
        byte afterPoint = (byte)((scale - beforePoint) * 256);
        byte[] toWrite = {beforePoint, afterPoint};
        write(ACCL_SCALE_REGISTER, toWrite);
        write(COMMAND_REGISTER, new byte[]{WRITE_EEPROM_COMMAND});
    }


}
