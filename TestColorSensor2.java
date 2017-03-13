package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.what.frog.FrogOpMode;

/**
 * Created by FTC2 on 02.03.2017.
 */
@TeleOp(name="Test: color", group="Tests")
public class TestColorSensor2 extends FrogOpMode {
    @Override
    public void loop() {
        super.loop();
        telemetry.addData("b", beaconColor.red());
        telemetry.addData("alpha", beaconColor.alpha());
        beaconColor.enableLed(true);
    }
}
