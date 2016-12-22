package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import static com.qualcomm.robotcore.hardware.Servo.Direction.REVERSE;

/**
 * Created by FTC on 06.12.2016.
 */
public class ServoTest extends OpMode{

    Servo beaconSLeft;
    Servo beaconSRight;

    ColorSensor beaconColorLeft;
    ColorSensor beaconColorRight;


    @Override
    public void init (){
        beaconSLeft = hardwareMap.servo.get("beacon_left");
        beaconSRight = hardwareMap.servo.get("beacon_right");

        beaconSRight.setDirection(REVERSE);
    }

    @Override
    public void loop (){
        if (gamepad2.dpad_left == true){

            beaconSRight.setPosition(0);
            beaconSLeft.setPosition(0);

            telemetry.addData("left_done", gamepad2.dpad_left);
        }

        if (gamepad2.dpad_right == true){

            beaconSRight.setPosition(1);
            beaconSLeft.setPosition(1);



            telemetry.addData("right_done", gamepad2.dpad_right);
        }

        //COLOR SENSORS
        beaconColorLeft = hardwareMap.colorSensor.get("cs_bea_left");
        beaconColorRight = hardwareMap.colorSensor.get("cs_bea_right");

        telemetry.addData("Alpha_rechts", beaconColorRight.alpha());
        telemetry.addData("Alpha_links", beaconColorLeft.alpha());

        telemetry.addData("Blau?_links", beaconColorLeft.blue());
        telemetry.addData("Rot_links", beaconColorLeft.red());

        telemetry.addData("Blau_rechts", beaconColorRight.blue());
        telemetry.addData("Rot_rechts", beaconColorRight.red());
    }


}
