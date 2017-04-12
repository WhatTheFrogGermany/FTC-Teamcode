package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FTC on 18.11.2016.
 */
public class TeleOpOmniWolfgang extends OpMode {
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

        bOmni.setDirection(DcMotor.Direction.REVERSE);
        cOmni.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {
        drive();
    }

    private void drive(){
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;

        telemetry.addData("Y_Value: ", y);
        telemetry.addData("X_Value: ", x);

        aOmni.setPower(y);
        bOmni.setPower(x);
        cOmni.setPower(y);
        dOmni.setPower(x);

        if(x == 0 && y == 0){
            aOmni.setPower(0);
            bOmni.setPower(0);
            cOmni.setPower(0);
            dOmni.setPower(0);
        }

    }
}
