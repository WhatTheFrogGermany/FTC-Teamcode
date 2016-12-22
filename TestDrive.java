package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FTC on 21.12.2016.
 */
@TeleOp(name="Test: Drive", group="Tests")
public class TestDrive extends OpMode {
    DcMotor aOmni;
    DcMotor bOmni;
    DcMotor cOmni;
    DcMotor dOmni;

    @Override
    public void init() {
        aOmni = hardwareMap.dcMotor.get("a_omni");
        bOmni = hardwareMap.dcMotor.get("b_omni");
        cOmni = hardwareMap.dcMotor.get("c_omni");
        dOmni = hardwareMap.dcMotor.get("d_omni");
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            aOmni.setPower(1);
        }else {
            aOmni.setPower(0);
        }
        if(gamepad1.b){
            bOmni.setPower(1);
        }else {
            bOmni.setPower(0);
        }
        if(gamepad1.y){
            cOmni.setPower(1);
        }else {
            cOmni.setPower(0);
        }
        if(gamepad1.x){
            dOmni.setPower(1);
        }else {
            dOmni.setPower(0);
        }

    }
}
