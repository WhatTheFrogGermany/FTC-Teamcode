package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by FTC2 on 24.01.2017.
 */
@TeleOp(name="Test: FrogGyro", group="Tests")
public class FrogGyroTest extends OpMode {
    FrogGyro bottomGyro = new FrogGyro(this.hardwareMap, "bottom_gyro", 0x10);
    FrogGyro topGyro = new FrogGyro(this.hardwareMap, "top_gyro", 0x20);
    @Override
    public void init() {
        bottomGyro.init();

    }

    @Override
    public void loop() {
        telemetry.addData("bottomGyro", bottomGyro.getHeading());
        telemetry.addData("topGyro", topGyro.getHeading());
    }
}
