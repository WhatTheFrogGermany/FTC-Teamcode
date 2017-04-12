package com.qualcomm.ftcrobotcontroller.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FTC on 07.10.2016.
 */
public class omnitest extends OpMode {
    DcMotor aOmni;
    DcMotor bOmni;
    DcMotor cOmni;
    DcMotor dOmni;
    @Override
    public void init(){
        aOmni = hardwareMap.dcMotor.get("a_omni");
        bOmni = hardwareMap.dcMotor.get("b_omni");
        cOmni = hardwareMap.dcMotor.get("c_omni");
        dOmni = hardwareMap.dcMotor.get("d_omni");

        aOmni.setDirection(DcMotor.Direction.REVERSE);
        bOmni.setDirection(DcMotor.Direction.REVERSE);

    }

    public void loop(){
        if(gamepad1.a == true){
            aOmni.setPower(1);
        }
        else {
            aOmni.setPower(0);
        }

        if(gamepad1.b == true){
            bOmni.setPower(1);
        }
        else {
            bOmni.setPower(0);
        }

        if(gamepad1.x == true){
            cOmni.setPower(1);
        }
        else{
            cOmni.setPower(0);
        }

        if(gamepad1.y == true){
            dOmni.setPower(1);
        }
        else{
            dOmni.setPower(0);
        }

//a war c, wird umgesteckt

    }
}
