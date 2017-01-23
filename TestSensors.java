package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by FTC2 on 23.01.2017.
 */

@TeleOp(name="Test: Sensors", group="Tests")
public class TestSensors extends OpMode {
    int ULTRASONIC_REGISTER = 0x04;
    ColorSensor beaconLeftColor;
    ColorSensor beaconRightColor;

    byte[] beaconLeftRangeCache;
    byte[] beaconRightRangeCache;

    I2cDevice beaconLeftRange;
    I2cDevice beaconRightRange;

    I2cDeviceSynch beaconLeftRangeRead;
    I2cDeviceSynch beaconRightRangeRead;

    @Override
    public void init() {
        beaconLeftColor = hardwareMap.colorSensor.get("left_beacon_color");
        beaconRightColor = hardwareMap.colorSensor.get("right_beacon_color");

        beaconLeftRange = hardwareMap.i2cDevice.get("left_beacon_range");
        beaconRightRange = hardwareMap.i2cDevice.get("right_beacon_range");

        beaconLeftColor.setI2cAddress(I2cAddr.create8bit(0x1c));
        beaconRightColor.setI2cAddress(I2cAddr.create8bit(0x2c));

        beaconLeftRangeRead = new I2cDeviceSynchImpl(beaconLeftRange, I2cAddr.create8bit(0x18), false);
        beaconRightRangeRead = new I2cDeviceSynchImpl(beaconRightRange, I2cAddr.create8bit(0x28), false);

        beaconRightRangeRead.engage();
        beaconLeftRangeRead.engage();
    }

    @Override
    public void loop() {
        telemetry.addData("LeftColor", beaconLeftColor.red());
        telemetry.addData("RightColor", beaconRightColor.red());

        beaconLeftRangeCache = beaconLeftRangeRead.read(ULTRASONIC_REGISTER, 2);
        beaconRightRangeCache = beaconRightRangeRead.read(ULTRASONIC_REGISTER, 2);

        telemetry.addData("LeftRangeUS", beaconLeftRangeCache[0] & 0xFF);
        telemetry.addData("LeftRangeODS", beaconLeftRangeCache[1] & 0xFF);
        telemetry.addData("RightRangeUS", beaconRightRangeCache[0] & 0xFF);
        telemetry.addData("RightRangeODS", beaconRightRangeCache[1] & 0xFF);


    }
}
