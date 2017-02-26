package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by FTC2 on 31.01.2017.
 */
public class FrogOpMode extends OpMode {
    //variables to save the front
    public final static short HILDE_FRONT = 0;
    public final static short FRANZ_FRONT = 1;
    public final static short BEACON_FRONT = 2;
    public final static short GABI_FRONT = 3;

    short front = GABI_FRONT;
    //drive
    protected FrogMotor aOmni;
    protected FrogMotor bOmni;
    protected FrogMotor cOmni;
    protected FrogMotor dOmni;

    //enable to change the front (18.10.16)
    //16.02 moved into the FrogOpMode
    protected FrogMotor frontRightDrive;
    protected FrogMotor frontLeftDrive;
    protected FrogMotor backLeftDrive;
    protected FrogMotor backRightDrive;

    protected FrogMotor gabiMotor;
    protected Servo gabiBlockServo;
    protected Servo gabiBlockServoRight;
    protected CRServo quewakServo;

    //the collecting mechanism
    protected FrogMotor steffiMotor;    //the back
    protected FrogMotor franzMotor;

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
        aOmni.reset();
        bOmni = new FrogMotor(hardwareMap.dcMotor.get("b_omni"));
        bOmni.reset();
        cOmni = new FrogMotor(hardwareMap.dcMotor.get("c_omni"));
        cOmni.reset();
        dOmni = new FrogMotor(hardwareMap.dcMotor.get("d_omni"));
        dOmni.reset();

        gabiMotor = new FrogMotor(hardwareMap.dcMotor.get("gabi"));
        gabiMotor.setDirection(DcMotor.Direction.REVERSE);
        gabiMotor.reset();

        gabiBlockServo = hardwareMap.servo.get("gabi_block_servo_left");
        gabiBlockServoRight = hardwareMap.servo.get("gabi_block_servo_right");
        quewakServo = hardwareMap.crservo.get("quewak_servo");

        steffiMotor = new FrogMotor(hardwareMap.dcMotor.get("steffi"));
        franzMotor = new FrogMotor(hardwareMap.dcMotor.get("franz"));
        franzMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        steffiMotor.reset();
        franzMotor.reset();

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
        bottomGyro.reset();
        topGyro.reset();
    }

    @Override
    public void start() {
        super.start();
        bottomGyro.reactivateRead();
        topGyro.reactivateRead();
        changeDirection(GABI_FRONT);
    }

    @Override
    public void loop() {

    }

    public void changeDirection(short direction){
        if(direction == GABI_FRONT){
            frontRightDrive = aOmni;
            frontLeftDrive = bOmni;
            backLeftDrive = cOmni;
            backRightDrive = dOmni;
            front = direction;
        }
        if(direction == HILDE_FRONT){
            frontRightDrive = bOmni;
            frontLeftDrive = cOmni;
            backLeftDrive = dOmni;
            backRightDrive = aOmni;
            front = direction;
        }
        if(direction == FRANZ_FRONT){
            frontRightDrive = cOmni;
            frontLeftDrive = dOmni;
            backLeftDrive = aOmni;
            backRightDrive = bOmni;
            front = direction;
        }
        if(direction == BEACON_FRONT){
            frontRightDrive = dOmni;
            frontLeftDrive = aOmni;
            backLeftDrive = bOmni;
            backRightDrive = cOmni;
            front = direction;
        }

        frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public int getHeading(){
        //int result = Math.round(((360-topGyro.getHeading())+bottomGyro.getHeading())/2);
        //telemetry.addData("result", result);
        return bottomGyro.getHeading();
    }
}
