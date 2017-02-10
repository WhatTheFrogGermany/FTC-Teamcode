package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by FTC2 on 31.01.2017.
 */
public class FrogOpMode extends OpMode {
    //drive
    protected FrogMotor aOmni;
    protected FrogMotor bOmni;
    protected FrogMotor cOmni;
    protected FrogMotor dOmni;

    protected DcMotor gabiMotor;
    protected Servo gabiBlockServo;

    //the collecting mechanism
    protected DcMotor steffiMotor;    //the back
    protected DcMotor franzMotor;

//    protected Servo kerstinServo; //the blockade after steffi
    protected FrogMotor wildeHildeMotor;

    //Beacon
    protected Servo leftBeaconServo;
    protected Servo rightBeaconServo;

    //Sensors
    protected ColorSensor leftBeaconColor;
    protected ColorSensor rightBeaconColor;

    protected FrogRange leftBeaconRange;
    protected FrogRange rightBeaconRange;

    protected FrogGyro topGyro;
    protected FrogGyro bottomGyro;

    @Override
    public void init() {
        aOmni = new FrogMotor(hardwareMap.dcMotor.get("a_omni"));
        bOmni = new FrogMotor(hardwareMap.dcMotor.get("b_omni"));
        cOmni = new FrogMotor(hardwareMap.dcMotor.get("c_omni"));
        dOmni = new FrogMotor(hardwareMap.dcMotor.get("d_omni"));

        gabiMotor = hardwareMap.dcMotor.get("gabi");
        gabiMotor.setDirection(DcMotor.Direction.REVERSE);

        gabiBlockServo = hardwareMap.servo.get("gabi_block_servo");

        steffiMotor = hardwareMap.dcMotor.get("steffi");
        franzMotor = hardwareMap.dcMotor.get("franz");
        franzMotor.setDirection(DcMotorSimple.Direction.REVERSE);

  //      kerstinServo = hardwareMap.servo.get("kerstin_servo");

        wildeHildeMotor = new FrogMotor(hardwareMap.dcMotor.get("hilde"));
        wildeHildeMotor.setGearRatio(720);

        leftBeaconServo = hardwareMap.servo.get("left_beacon");
        rightBeaconServo = hardwareMap.servo.get("right_beacon");

        //Sensors
        leftBeaconColor = hardwareMap.colorSensor.get("left_beacon_color");
        rightBeaconColor = hardwareMap.colorSensor.get("right_beacon_color");
        leftBeaconColor.setI2cAddress(I2cAddr.create8bit(0x1c));
        rightBeaconColor.setI2cAddress(I2cAddr.create8bit(0x2c));

        leftBeaconRange = new FrogRange(hardwareMap, "left_beacon_range", 0x18);
        rightBeaconRange = new FrogRange(hardwareMap, "right_beacon_range", 0x28);

        bottomGyro = new FrogGyro(hardwareMap, "bottom_gyro", 0x10);
        topGyro = new FrogGyro(hardwareMap, "top_gyro", 0x20);

    }

    @Override
    public void loop() {

    }
}
