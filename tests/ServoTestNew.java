package com.qualcomm.ftcrobotcontroller.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by FTC on 12.10.2016.
 */
public class ServoTestNew extends OpMode {

    Servo beaconSLeft;
    Servo beaconSRight;

    @Override
    public void init(){

        beaconSLeft = hardwareMap.servo.get("beacon_left");
        beaconSRight = hardwareMap.servo.get("beacon_right");
    }

    @Override
    public void loop(){
        if (gamepad2.dpad_left == true){

            //change position
            beaconSLeft.setPosition(0.8);
        }
        if (gamepad2.dpad_right == true){

            //change if needed
            beaconSRight.setPosition(0.2);
        }
        if (gamepad2.y == true){

            //reset servos to nullpos, change nullpos if needed
            beaconSLeft.setPosition(0);
            beaconSRight.setPosition(0);
        }
    }
}
