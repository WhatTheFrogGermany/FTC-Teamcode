package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by FTC on 21.12.2016.
 */
@TeleOp(name="Test: Color (Beacon)", group="Tests")
@Disabled
public class TestColorSensor extends OpMode {
    ColorSensor leftBeaconColor;
    ColorSensor rightBeaconColor;
    @Override
    public void init() {

        leftBeaconColor = hardwareMap.colorSensor.get("left_beacon_color");
        rightBeaconColor = hardwareMap.colorSensor.get("right_beacon_color");
    }

    @Override
    public void loop() {
        telemetry.addData("LeftAlpha", leftBeaconColor.alpha());
        telemetry.addData("LeftRed", leftBeaconColor.red());
        telemetry.addData("LeftGreen", leftBeaconColor.green());
        telemetry.addData("LeftBlue", leftBeaconColor.blue());

        if(gamepad2.a){
            leftBeaconColor.enableLed(true);
        }
        if(gamepad2.b){
            leftBeaconColor.enableLed(false);
        }

    }
}
