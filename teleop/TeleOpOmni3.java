package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.what.frog.FrogMotor;
import org.firstinspires.ftc.teamcode.what.frog.FrogToggle;

/**
 * Created by FTC on 07.10.2016.
 */


public class TeleOpOmni3 extends OpMode {

    //drive
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

    double[] scaled = new double[4];

    //Gabi lift
    DcMotor gabiMotor;
    FrogToggle gabiBlockToggle;
    Servo gabiBlockServo;

    //the collecting mechanism
    DcMotor steffiMotor;    //the back
    ElapsedTime steffiTime; //Added TimeToggle on 14.01
    boolean steffiToggle = false;
    DcMotor franzMotor;     //the front
    ElapsedTime franzTime;
    boolean franzToggle = false;

    //Servo kerstinServo; //the blockade after steffi
    //the shooter
    FrogMotor wildeHildeMotor;
    int lastTargetPositionHilde = 0;
    boolean hildeRotatedFully = true;
    boolean customMoveHilde = false;


    //Beacon
    Servo leftBeacon;
    Servo rightBeacon;

    @Override
    public void init (){
        aOmni = hardwareMap.dcMotor.get("a_omni");
        bOmni = hardwareMap.dcMotor.get("b_omni");
        cOmni = hardwareMap.dcMotor.get("c_omni");
        dOmni = hardwareMap.dcMotor.get("d_omni");

        aOmni.setDirection(DcMotor.Direction.REVERSE);
        bOmni.setDirection(DcMotor.Direction.FORWARD);
        dOmni.setDirection(DcMotor.Direction.FORWARD);
        cOmni.setDirection(DcMotor.Direction.REVERSE);

        frontRightDrive = aOmni;
        frontLeftDrive = bOmni;
        backRightDrive = dOmni;
        backLeftDrive = cOmni;



        gabiMotor = hardwareMap.dcMotor.get("gabi");
        gabiMotor.setDirection(DcMotor.Direction.REVERSE);
        gabiBlockToggle = new FrogToggle(500);
        gabiBlockServo = hardwareMap.servo.get("gabi_block_servo");

        steffiMotor = hardwareMap.dcMotor.get("steffi");
        steffiTime = new ElapsedTime();

        franzMotor = hardwareMap.dcMotor.get("franz");
        franzTime = new ElapsedTime();
        franzMotor.setDirection(DcMotorSimple.Direction.REVERSE);

       // kerstinServo = hardwareMap.servo.get("kerstin_servo");

        wildeHildeMotor = new FrogMotor(hardwareMap.dcMotor.get("hilde"));
        wildeHildeMotor.setGearRatio(720);

        leftBeacon = hardwareMap.servo.get("left_beacon");
        rightBeacon = hardwareMap.servo.get("right_beacon");
    }

    public void loop() {

        drive();
        changeDirection();

        lift();
        gabiBlock();

        //kerstin();

        collect();
        shooter();
        beacon();
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

        double[] beforeScaled = {a,b,c,d};
        scaled = scaleDown(beforeScaled, x, y);
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

        r*=0.5;
        if(r != 0) {
            if(gamepad1.right_stick_button){
                r = r * 0.1;
            }
            telemetry.addData("RightstickX", r);
            //rechtsdrehung oder von liane zu enrico
            frontLeftDrive.setPower(-r);
            frontRightDrive.setPower(r);
            backRightDrive.setPower(-r);
            backLeftDrive.setPower(r);
        }

        if(r == 0 && x == 0 && y == 0){
            aOmni.setPower(0);
            bOmni.setPower(0);
            cOmni.setPower(0);
            dOmni.setPower(0);
        }

    }

    public double[] scaleDown(double[] vals, double x ,double y){
        //instead of the check we will scale down proportionally (10.10.16)
        //first we check for the greatest of all four values
        double greatest = 0;
        for(int i = 0; i < 4; i++){
            if(vals[i] > greatest){
                greatest = vals[i];
            }
        }

        //then we scale the rest accordingly to make sure the greatest value equals one.
        double scale = 1 / greatest;
        for(int i = 0; i < 4; i++){
            vals[i] = vals[i] * scale;
            vals[i] = check(vals[i]);
        }

        //Afterwards we scale according to the absolute value of x and y (the distance the stick is from 0)
        double absolute = Math.sqrt(x*x + y*y);
        for(int i = 0; i < 4; i++){
            vals[i] = vals[i] * absolute;
        }
        //scale down to 20% if the slowModeToggle is on
        if(gamepad1.left_stick_button){
            for(int i = 0; i < 4; i++){
                vals[i] = vals[i] * 0.2;
            }
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
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
    }

    public void lift(){
        gabiMotor.setPower(-gamepad2.right_stick_y);
    }

    public void gabiBlock(){
        gabiBlockToggle.toggle(gamepad2.left_trigger > 0.5 && gamepad2.right_trigger > 0.5);
        if(gabiBlockToggle.getState()){
            gabiBlockServo.setPosition(0.8);
        } else {
            gabiBlockServo.setPosition(0.15);
        }
    }

    public void collect(){
        if(steffiToggle){
            steffiMotor.setPower(1);
        } else {
            steffiMotor.setPower(0);
        }

        if(franzToggle) {
            franzMotor.setPower(1);
        } else {
            franzMotor.setPower(0);
        }


        if(gamepad2.a && (steffiTime.time() > 1)){
            steffiToggle = !steffiToggle;
            steffiTime.reset();
        }
        if(gamepad2.b && (franzTime.time() > 1)){
            franzToggle = !franzToggle;
            franzTime.reset();
        }

    }

    //public void kerstin(){
       // if(gamepad2.dpad_left){
            //kerstinServo.setPosition(0);
        //}
       // if(gamepad2.dpad_right){
            //kerstinServo.setPosition(1);
        //}
    //}

    public void shooter(){
        if(gamepad2.x && !wildeHildeMotor.drivingToPosition){
            wildeHildeMotor.initRotateRounds(1,1);
        }

        wildeHildeMotor.driveToPosition();

        if(gamepad2.y){
            wildeHildeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            wildeHildeMotor.setPower(1);
            customMoveHilde = true;
        } else if(customMoveHilde){
            wildeHildeMotor.setPower(0);
            wildeHildeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            wildeHildeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lastTargetPositionHilde = 0;
            customMoveHilde = false;
            hildeRotatedFully= true;
        }

    }

    public void beacon(){
        if(gamepad2.left_bumper){
            leftBeacon.setPosition(1);
        }
        if(gamepad2.right_bumper){
            rightBeacon.setPosition(0);
        }
        if(gamepad2.back){
            leftBeacon.setPosition(0);
            rightBeacon.setPosition(1);
        }
    }

}
