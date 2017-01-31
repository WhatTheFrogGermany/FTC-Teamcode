package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by FTC2 on 31.01.2017.
 */
public class FrogOpMode extends OpMode {
    //drive
    DcMotor aOmni;
    DcMotor bOmni;
    DcMotor cOmni;
    DcMotor dOmni;

    DcMotor gabiMotor;
    Servo gabiBlockServo;

    //the collecting mechanism
    DcMotor steffiMotor;    //the back
    DcMotor franzMotor;

    Servo kerstinServo; //the blockade after steffi
    FrogMotor wildeHildeMotor;

    //Beacon
    Servo leftBeaconServo;
    Servo rightBeaconServo;

    @Override
    public void init() {
        aOmni = hardwareMap.dcMotor.get("a_omni");
        bOmni = hardwareMap.dcMotor.get("b_omni");
        cOmni = hardwareMap.dcMotor.get("c_omni");
        dOmni = hardwareMap.dcMotor.get("d_omni");

        gabiMotor = hardwareMap.dcMotor.get("gabi");
        gabiMotor.setDirection(DcMotor.Direction.REVERSE);

        gabiBlockServo = hardwareMap.servo.get("gabi_block_servo");

        steffiMotor = hardwareMap.dcMotor.get("steffi");
        franzMotor = hardwareMap.dcMotor.get("franz");
        franzMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        kerstinServo = hardwareMap.servo.get("kerstin_servo");

        wildeHildeMotor = new FrogMotor(hardwareMap.dcMotor.get("hilde"));
        wildeHildeMotor.setGearRatio(720);

        leftBeaconServo = hardwareMap.servo.get("left_beacon");
        rightBeaconServo = hardwareMap.servo.get("right_beacon");
    }

    @Override
    public void loop() {

    }
}
