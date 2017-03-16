package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

import static com.qualcomm.robotcore.hardware.Servo.Direction.REVERSE;

/**
 * Created by FTC on 07.10.2016.
 */

public class TeleOpOmni extends OpMode {

    DcMotor aOmni;
    DcMotor bOmni;
    DcMotor cOmni;
    DcMotor dOmni;

    //enable to change the front (18.10.16)
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;
    DcMotor backLeftDrive;
    DcMotor backRightDrive;

    int lyMag;
    int lxMag;


    DcMotor steffiMotor;
    int steffiEncZero;
    boolean steffiUp;
    boolean steffiDown;

    DcMotor hildeMotor;

    Servo beaconSLeft;
    Servo beaconSRight;

    ColorSensor beaconColorLeft;
    ColorSensor beaconColorRight;

    @Override
    public void init (){
        aOmni = hardwareMap.dcMotor.get("a_omni");
        bOmni = hardwareMap.dcMotor.get("b_omni");
        cOmni = hardwareMap.dcMotor.get("c_omni");
        dOmni = hardwareMap.dcMotor.get("d_omni");

        beaconColorLeft = hardwareMap.colorSensor.get("cs_bea_left");
        beaconColorRight = hardwareMap.colorSensor.get("cs_bea_right");

        aOmni.setDirection(DcMotor.Direction.FORWARD);
        bOmni.setDirection(DcMotor.Direction.REVERSE);
        dOmni.setDirection(DcMotor.Direction.REVERSE);
        cOmni.setDirection(DcMotor.Direction.FORWARD);

        frontRightDrive = aOmni;
        frontLeftDrive = bOmni;
        backRightDrive = dOmni;
        backLeftDrive = cOmni;


        steffiMotor = hardwareMap.dcMotor.get("steffi_motor");
        //added Encoders to Steffi (18.10.16)
        //0 should be when touching ground.
        steffiEncZero = steffiMotor.getCurrentPosition();

        hildeMotor = hardwareMap.dcMotor.get("hilde_motor");

        beaconSLeft = hardwareMap.servo.get("beacon_left");
        beaconSRight = hardwareMap.servo.get("beacon_right");

        beaconSRight.setDirection(REVERSE);

        //change if needed
        beaconSLeft.setPosition(0);
        beaconSRight.setPosition(0);
    }

    public void loop() {

        drive();

        changeDirection();
        beacon();
        steffi2();
        hilde();
    }


    public void drive(){
        double y = -(gamepad1.left_stick_y);
        double x = gamepad1.left_stick_x;

        if (y < 0) {
            lyMag = -1;
        } else {
            lyMag = 1;
        }

        if (x < 0) {
            lxMag = -1;
        } else{
            lxMag = 1;
        }

        y = y * y * lyMag;
        x = x * x * lxMag;

        double a = -x + y;
        double b = x + y;
        double c = x - y;
        double d = -x - y;

        double[] scaled = {a,b,c,d};
        scaled = scaleDown(scaled);
        a = scaled[0];
        b = scaled[1];
        c = scaled[2];
        d = scaled[3];

        if(x != 0 || y != 0) {
            //changed it from cornerMotors to directionMotors for changing the directions
            frontRightDrive.setPower(a);
            frontLeftDrive.setPower(b);
            backLeftDrive.setPower(c);
            backRightDrive.setPower(d);
        }

        //nur Stick 2 (drehen)
        double r = gamepad1.right_stick_x;
        if(r != 0) {
            telemetry.addData("RightstickX", r);
            //rechtsdrehung oder von liane zu enrico
            aOmni.setPower(-r);
            bOmni.setPower(r);
            cOmni.setPower(-r);
            dOmni.setPower(r);
        }

        if(r == 0 && x == 0 && y == 0){
            aOmni.setPower(0);
            bOmni.setPower(0);
            cOmni.setPower(0);
            dOmni.setPower(0);
        }

    }

    public double[] scaleDown(double[] vals){
        //instead of the check we will scale down proportionally (10.10.16)
        //first we check for the greatest of all four values
        double greatest = 0;
        for(int i = 0; i < 4; i++){
            if(vals[i] > greatest){
                greatest = vals[i];
            }
        }

        //then we scale the rest accordingly to make sure the greates value equals one.
        double scale = 1 / greatest;
        for(int i = 0; i < 4; i++){
            vals[i] = vals[i] * scale;
            vals[i] = check(vals[i]);
        }
        return vals;
    }

    public double check(double val){
        if (val > 1){
            val = 1;
        }
        if (val < -1){
            val = -1;
        }
        return val;
    }

    public void changeDirection(){
        //to change the front. (18.10.16)
        if(gamepad1.y){
            //Liane
            frontRightDrive = aOmni;
            frontLeftDrive = bOmni;
            backRightDrive = dOmni;
            backLeftDrive = cOmni;
            changeMotorDirections();
        }
        if(gamepad1.b){
            //Enrico
            frontRightDrive= dOmni;
            frontLeftDrive = aOmni;
            backRightDrive = cOmni;
            backLeftDrive = bOmni;
            changeMotorDirections();
        }
        if(gamepad1.a){
            //Jens
            frontRightDrive = cOmni;
            frontLeftDrive = dOmni;
            backRightDrive = bOmni;
            backLeftDrive = aOmni;
            changeMotorDirections();
        }
        if(gamepad1.x){
            //Raimar
            frontRightDrive = bOmni;
            frontLeftDrive = cOmni;
            backRightDrive = aOmni;
            backLeftDrive = dOmni;
            changeMotorDirections();
        }
    }

    public void changeMotorDirections(){
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    public void hilde(){
        hildeMotor.setPower(gamepad2.left_stick_y);
    }
    public void beacon(){
        if (gamepad2.dpad_left == true){
            beaconSLeft.setPosition(1);
            telemetry.addData("left_done", gamepad2.dpad_left);
        }

        if (gamepad2.dpad_right == true){

            beaconSRight.setPosition(1);
            telemetry.addData("right_done", gamepad2.dpad_right);
        }

        if (gamepad2.dpad_down == true){
            beaconSLeft.setPosition(0);
            beaconSRight.setPosition(0);
            telemetry.addData("reset_done", gamepad2.dpad_down);
        }

        // add color sensors, show values and whether they are blue/red or not
        // if blue() > red(), Farbe ist blau usw

    }


    public void steffi2(){
        if (gamepad2.y){
            steffiMotor.setPower(1);
            telemetry.addData("Oben", gamepad1.y);
        }

        if (gamepad2.a){
            steffiMotor.setPower(-1);
            telemetry.addData("Unten", gamepad1.a);
        }

        boolean drivingToPos = steffiDriveToPos();
        if (!gamepad2.y && !gamepad2.a && !drivingToPos) {
            steffiMotor.setPower(0);
        }

    }

    public boolean steffiDriveToPos(){
        if(gamepad2.b){
            steffiDown= false;
            steffiUp = true;
        } else if(gamepad2.x){
            steffiUp = false;
            steffiDown = true;
        }

        int currentPos = steffiMotor.getCurrentPosition();
        telemetry.addData("SteffiVal", currentPos);
        if(steffiUp){
            telemetry.addData("SteffiPos", "up");
            steffiMotor.setPower(1);
            if(steffiMotor.getCurrentPosition() >= 5000 - steffiEncZero){
                steffiUp = false;
            }
            return true;
        } else if(steffiDown){
            telemetry.addData("SteffiPos", "down");
            steffiMotor.setPower(-1);
            if(steffiMotor.getCurrentPosition() <= steffiEncZero + 50){
                steffiDown = false;
            }
            return true;
        } else {
            return false;
        }
    }
}