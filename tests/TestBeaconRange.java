package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.TeleOpOmni4;
import org.firstinspires.ftc.teamcode.what.frog.FrogOpMode;

/**
 * Created by FTC2 on 03.04.2017.
 */
@TeleOp(name="Test: Beacon Range", group="Tests")
public class TestBeaconRange extends TeleOpOmni4 {
    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        super.loop();
        telemetry.addData("leftBeacon", leftBeaconRange.getUltrasonic());
        telemetry.addData("rightBeacon", rightBeaconRange.getUltrasonic());
    }
}
