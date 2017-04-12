package com.qualcomm.ftcrobotcontroller.opmodes.playground;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FTC on 18.10.2016.
 */
public class lisatest extends OpMode {
    DcMotor dc_drive_left ;
    DcMotor dc_drive_right;

    @Override
    public void init() {
        dc_drive_left = hardwareMap.dcMotor.get("a_omni");
        dc_drive_right = hardwareMap.dcMotor.get("b_omni");

    }

    @Override
    public void loop() {

        dc_drive_left.setPower(gamepad1.left_stick_y);
        dc_drive_right.setPower(gamepad1.right_stick_y);

    }
}