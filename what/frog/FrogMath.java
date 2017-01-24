package org.firstinspires.ftc.teamcode.what.frog;

/**
 * Created by FTC2 on 24.01.2017.
 */
public class FrogMath {

    public static int bytesToInt(byte[] bytes){
        //this converts two bytes (lsb:msb) to an integer
        int val = ((bytes[1] & 0xff) << 8) | (bytes[0] & 0xff);
        return val;
    }
}
