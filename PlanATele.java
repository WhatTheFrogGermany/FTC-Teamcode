
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by FTC on 08.12.2016.
 */
//Time for Christmas!!!
//It's a pony kind of christmas in every color shape
//All throughout the world of Equestria christmas is here today
//It's a pony kind of christmas
//It's light never fades

@TeleOp(name="TeleOp: TeleA", group="TeleOp")
public class PlanATele extends OpMode {


    DcMotor AufzugMotor;

    DcMotor DriveA;
    DcMotor DriveB;
    DcMotor DriveC;
    DcMotor DriveD;

    Servo beaconSLeft;
    Servo beaconSRight;

    Servo aufzugLeft;
    Servo aufzugRight;

    ColorSensor beaconColorLeft;
    ColorSensor beaconColorRight;

    double ServoPosL;// Anfang 0
    double ServoPosR;// Anfang 1

    @Override
    public void init(){


        ServoPosL = 0.1;
        ServoPosR = 0.9;


        AufzugMotor = hardwareMap.dcMotor.get("aufzug_M");

        DriveA = hardwareMap.dcMotor.get("drive_a");
        DriveB = hardwareMap.dcMotor.get("drive_b");
        DriveC = hardwareMap.dcMotor.get("drive_c");
        DriveD = hardwareMap.dcMotor.get("drive_d");

        beaconSLeft = hardwareMap.servo.get("beacon_left");
        beaconSRight = hardwareMap.servo.get("beacon_right");

        aufzugLeft = hardwareMap.servo.get("aufzug_left");
        aufzugRight = hardwareMap.servo.get("aufzug_right");

        beaconColorLeft = hardwareMap.colorSensor.get("b_left");
        beaconColorRight = hardwareMap.colorSensor.get("b_right");
        DriveD.setDirection(DcMotorSimple.Direction.REVERSE);
        DriveC.setDirection(DcMotorSimple.Direction.REVERSE);


        aufzugLeft.setPosition(0);
        aufzugRight.setPosition(1);


    }

    public void loop(){

        drive();
        beacon();
        aufzug();
        notaus();

    }

    public void drive(){
        DriveB.setPower(gamepad1.left_stick_y);
        DriveD.setPower(gamepad1.left_stick_y);

        DriveC.setPower(gamepad1.left_stick_x);
        DriveA.setPower(gamepad1.left_stick_x);

        if (gamepad1.right_stick_x < 0){
            DriveB.setPower(gamepad1.right_stick_x);
            DriveD.setPower(gamepad1.right_stick_x);

            DriveA.setPower(gamepad1.right_stick_x);
            DriveC.setPower(-gamepad1.right_stick_x);
        }
        else if (gamepad1.right_stick_x > 0){
            DriveB.setPower(gamepad1.right_stick_x);
            DriveD.setPower(gamepad1.right_stick_x);

            DriveA.setPower(-gamepad1.right_stick_x);
            DriveC.setPower(gamepad1.right_stick_x);
        }
    }

    public void beacon(){

        if (gamepad2.dpad_left == true){
            beaconSLeft.setPosition(0);
            telemetry.addData("left_done", gamepad2.dpad_left);
        }

        if (gamepad2.dpad_right == true){

            beaconSRight.setPosition(1);
            telemetry.addData("right_done", gamepad2.dpad_right);
        }

        if (gamepad2.x == true){
            beaconSLeft.setPosition(1);
            beaconSRight.setPosition(0);
            telemetry.addData("reset_done", gamepad2.dpad_down);
        }
    }

    public void aufzug(){

        if (gamepad2.left_bumper == true){
            AufzugMotor.setPower(1);
        }
        else if (gamepad2.right_bumper == true){
            AufzugMotor.setPower(-1);
        }
        else {
            AufzugMotor.setPower(0);
        }

        if (gamepad2.right_stick_x > 0){
            ServoPosR = ServoPosR - 0.01;
        }
        else if (gamepad2.right_stick_x < 0){
            ServoPosR = ServoPosR + 0.01;
        }

        aufzugRight.setPosition(ServoPosR);

        if(gamepad2.left_stick_x > 0){
            ServoPosL = ServoPosL - 0.01;
        }

        if (gamepad2.left_stick_x < 0){
            ServoPosL = ServoPosL + 0.01;
        }

        aufzugLeft.setPosition(ServoPosL);

    }

    public void notaus() {

        if (gamepad2.x == true) {
            AufzugMotor.setPower(0);
            DriveC.setPower(0);
            DriveB.setPower(0);
            DriveA.setPower(0);
            DriveD.setPower(0);
        }
    }
}
