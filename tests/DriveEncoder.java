package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FTC2 on 25.01.2017.
 */
@TeleOp(name="Test: DriveEncoder", group="Measure")
@Disabled
public class DriveEncoder extends OpMode {
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

        aOmni.setDirection(DcMotor.Direction.REVERSE);
        bOmni.setDirection(DcMotor.Direction.FORWARD);
        dOmni.setDirection(DcMotor.Direction.REVERSE);
        cOmni.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            aOmni.setPower(1);
            bOmni.setPower(1);
            cOmni.setPower(1);
            dOmni.setPower(1);

        }

        if(gamepad1.b){
            aOmni.setPower(0);
            bOmni.setPower(0);
            cOmni.setPower(0);
            dOmni.setPower(0);
        }

        telemetry.addData("a", aOmni.getCurrentPosition());
        telemetry.addData("b", bOmni.getCurrentPosition());
        telemetry.addData("c", cOmni.getCurrentPosition());
        telemetry.addData("d", dOmni.getCurrentPosition());
    }
}
