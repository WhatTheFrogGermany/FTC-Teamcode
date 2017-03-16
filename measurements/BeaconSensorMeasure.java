package org.firstinspires.ftc.teamcode.measurements;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.what.frog.FrogFileWriter;
import org.firstinspires.ftc.teamcode.what.frog.FrogToggle;

/**
 * Created by FTC2 on 30.01.2017.
 */
@TeleOp(name="Measure: BeaconSensor", group="Measure")
@Disabled
public class BeaconSensorMeasure extends OpMode {
    ColorSensor beaconLeftColor;
    ColorSensor beaconRightColor;

    ElapsedTime buttonTime;

    int array[][] = new int[50][6];
    int counter = 0;

    int color = 0;
    FrogFileWriter fileWriter;

    FrogToggle colorToggle;
    FrogToggle ledToggle;

    @Override
    public void init() {
        beaconLeftColor = hardwareMap.colorSensor.get("left_beacon_color");
        beaconRightColor = hardwareMap.colorSensor.get("right_beacon_color");

        beaconLeftColor.setI2cAddress(I2cAddr.create8bit(0x1c));
        beaconRightColor.setI2cAddress(I2cAddr.create8bit(0x2c));

        buttonTime = new ElapsedTime();
        fileWriter = new FrogFileWriter("colorBeacon.csv");

        colorToggle = new FrogToggle(500);
        ledToggle = new FrogToggle(500);
    }

    @Override
    public void loop() {
        if(gamepad1.a && buttonTime.milliseconds() > 500){
            buttonTime.reset();
            array[counter][0] = counter;
            array[counter][1] = beaconLeftColor.alpha();
            array[counter][2] = beaconLeftColor.red();
            array[counter][3] = beaconLeftColor.green();
            array[counter][4] = beaconLeftColor.blue();
            array[counter][5] = color;
            counter++;
        }

        telemetry.addData("color", color);
        telemetry.addData("counter", counter);

        colorToggle.toggle(gamepad1.x);

        ledToggle.toggle(gamepad1.y);

        if(ledToggle.getState()){
            beaconLeftColor.enableLed(true);
        } else{
            beaconLeftColor.enableLed(false);
        }
        if(gamepad1.b && buttonTime.milliseconds() > 500){
            buttonTime.reset();
            fileWriter.write2DArray(array);
            requestOpModeStop();
        }

        if(colorToggle.getState()){
            color = 1;
        } else {
            color = 0;
        }

    }
}

