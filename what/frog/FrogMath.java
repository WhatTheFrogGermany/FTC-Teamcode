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

    public static int[] calculateVector(int x1, int y1, int x2, int y2){
        int[] vector = {x2-x1, y2-y1};
        return vector;
    }

    public static double[] xyToPolar(int x, int y){
        double length;
        double degrees;
        length = Math.sqrt(x*x + y*y);
        degrees = Math.toDegrees(Math.atan(y/x));
        double[] result = {length, degrees};
        return result;
    }

    public static double checkSmallerOne(double val){
        if (val > 1){
            val = 1;
        }
        if (val < -1){
            val = -1;
        }
        return val;
    }
}
