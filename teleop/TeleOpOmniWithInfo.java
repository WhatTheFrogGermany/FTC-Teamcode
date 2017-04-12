package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FTC2 on 20.01.2017.
 */
@TeleOp(name="Info: TeleOmni Encoder", group="info")
public class TeleOpOmniWithInfo extends TeleOpOmni4
{
    @Override
    public void init() {
        super.init();
        super.gabiMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        super.steffiMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        super.franzMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        super.wildeHildeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        super.aOmni.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        super.bOmni.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        super.cOmni.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        super.dOmni.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        super.loop();
        telemetry.addData("Gabi", super.gabiMotor.getCurrentPosition());
        telemetry.addData("Steffi", super.steffiMotor.getCurrentPosition());
        telemetry.addData("Franz", super.franzMotor.getCurrentPosition());
        telemetry.addData("Hilde", super.wildeHildeMotor.getCurrentPosition());
        telemetry.addData("aOmni", super.aOmni.getCurrentPosition());
        telemetry.addData("bOmni", super.bOmni.getCurrentPosition());
        telemetry.addData("cOmni", super.cOmni.getCurrentPosition());
        telemetry.addData("dOmni", super.dOmni.getCurrentPosition());

    }
}
