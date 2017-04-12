package org.firstinspires.ftc.teamcode.navigation;

/**
 * Created by FTCAdministrator on 04.03.2016.
 */

import android.bluetooth.BluetoothA2dp;

public enum Color {
    //set values of colors
    RED (1,1,1,1,1),
    BLUE (1,2,2,3,2),
    GREY (1,1,1,1,2);
    // set interval in which the colors are
    int redmin;
    int redmax;
    int greenmin;
    int greenmax;
    int bluemin;
    int bluemax;
    int alphamin; //shade
    int alphamax;

    Color(int rmin, int rmax, int gmin, int gmax, int bmin, int bmax, int amin, int amax){
        this.redmin = rmin;
        this.redmax = rmax;
        this.greenmin = gmin;
        this.greenmax = gmax;
        this.bluemin = bmin;
        this.bluemax = bmax;
        this.alphamin = amin;
        this.alphamax = amax;

    }

    Color(int r, int g, int b, int a, int intv){  //provide rgb and alpha values and a intv that is substracted and added
        this.redmin = r - intv;
        this.redmax = r + intv;
        this.greenmin = r -intv;
        this.greenmax = r + intv;
        this.bluemin = r - intv;
        this.bluemax = r + intv;
        this.alphamin = r - intv;
        this.alphamax = r + intv;
    }

    public boolean valueIsColor(int r, int g, int b, int alpha){
        if(checkValueInRange(r, redmin, redmax) && checkValueInRange(g, greenmin, greenmax) && checkValueInRange(b, bluemin, bluemax) && checkValueInRange(alpha, alphamin, alphamax)){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean checkValueInRange(int value, int rangemin, int rangemax){
        if(value >= rangemin && value <= rangemax){
            return true;
        }
        else{
            return false;
        }
    }



}
