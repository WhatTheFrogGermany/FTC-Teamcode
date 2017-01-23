package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by FTC2 on 23.01.2017.
 */
@TeleOp(name="Test: Sensors", group="Tests")
public class TestSensors extends OpMode {
    ColorSensor beaconLeftColor;
    ColorSensor beaconRightColor;
    ModernRoboticsI2cRangeSensor beaconLeftRange;
    ModernRoboticsI2cRangeSensor beaconRightRange;

    @Override
    public void init() {
        beaconLeftColor = hardwareMap.colorSensor.get("left_beacon_color");
        beaconRightColor = hardwareMap.colorSensor.get("right_beacon_color");

        beaconLeftRange = (ModernRoboticsI2cRangeSensor)hardwareMap.get("left_beacon_range");
        beaconRightRange = (ModernRoboticsI2cRangeSensor)hardwareMap.get("right_beacon_range");
    }

    @Override
    public void loop() {
        telemetry.addData("LeftColor", beaconLeftColor.red());
        telemetry.addData("RightColor", beaconRightColor.red());

        telemetry.addData("LeftUltrasonic", beaconLeftRange.read8(ModernRoboticsI2cRangeSensor.Register.ULTRASONIC));
        telemetry.addData("RightUltrasonic",beaconRightRange.read8(ModernRoboticsI2cRangeSensor.Register.ULTRASONIC));

    }
}
