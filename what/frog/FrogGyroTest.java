package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by FTC2 on 24.01.2017.
 */
@TeleOp(name="Test: FrogGyro", group="Tests")
public class FrogGyroTest extends FrogOpMode {

    @Override
    public void init() {
        super.init();

        bottomGyro.reset();
        topGyro.reset();
        bottomGyro.reactivateRead();
        topGyro.reactivateRead();
    }

    @Override
    public void loop() {
        telemetry.addData("bottomGyro", bottomGyro.getHeading());
        telemetry.addData("topGyro", topGyro.getHeading());

        if(gamepad1.y){
            bottomGyro.reset();
        }
        bottomGyro.reactivateRead();
    }
}
