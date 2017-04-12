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
        degrees = Math.toDegrees(Math.asin(Math.abs(y)/length));
        if(y < 0){
            if(x < 0){
                degrees += 180;
                degrees = degreesInCircle((int)degrees);
            } else {
                degrees += (90-degrees)*2;
            }
        } else{
            if(x < 0){
                degrees += (180 - degrees)*2;
            }
        }
        if(y < 0){
            degrees += 180;
        }
        double[] result = {length, degrees};
        return result;
    }

    public static int degreesInCircle(int degrees){
        while(degrees > 360){
            degrees -= 360;
        }
        while(degrees < 0){
            degrees -= 360;
        }
        return degrees;
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

    public static double checkSmaller(double val, double limit){
        if (val > limit){
            val = limit;
        }
        if (val < -limit){
            val = -limit;
        }
        return val;
    }
}
