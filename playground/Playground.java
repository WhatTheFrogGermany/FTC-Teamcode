package com.qualcomm.ftcrobotcontroller.opmodes.playground;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FTC on 18.10.2016.
 */
public class Playground extends OpMode {

    DcMotor testMotor;

    @Override
    public void init() {
        testMotor = hardwareMap.dcMotor.get("steffi_motor");
    }

    @Override
    public void loop(){
        if(gamepad1.y){
            testMotor.setPower(0.5);
        }
    }
}
