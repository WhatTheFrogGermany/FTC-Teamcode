package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import static com.qualcomm.robotcore.hardware.Servo.Direction.REVERSE;

/**
 * Created by FTC on 07.10.2016.
 */

@TeleOp(name="TeleOp: Omni_alt 22122016", group="TeleOp")
@Disabled
public class TeleOpOmni2 extends OpMode {

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

    boolean slowModeToggle = false;

    //Gabi lift
    DcMotor gabiMotor;

    //the collecting mechanism
    DcMotor steffiMotor;    //the back
    DcMotor franzMotor;     //the front
    boolean collectingToggle = false;

    //the shooter
    DcMotor wildeHildeMotor;
    boolean shootingToggle = false;

    //Beacon
    Servo leftBeacon;
    Servo rightBeacon;

    @Override
    public void init (){
        aOmni = hardwareMap.dcMotor.get("a_omni");
        bOmni = hardwareMap.dcMotor.get("b_omni");
        cOmni = hardwareMap.dcMotor.get("c_omni");
        dOmni = hardwareMap.dcMotor.get("d_omni");

        aOmni.setDirection(DcMotor.Direction.FORWARD);
        bOmni.setDirection(DcMotor.Direction.REVERSE);
        dOmni.setDirection(DcMotor.Direction.REVERSE);
        cOmni.setDirection(DcMotor.Direction.FORWARD);

        frontRightDrive = aOmni;
        frontLeftDrive = bOmni;
        backRightDrive = dOmni;
        backLeftDrive = cOmni;

        gabiMotor = hardwareMap.dcMotor.get("gabi");

        steffiMotor = hardwareMap.dcMotor.get("steffi");
        franzMotor = hardwareMap.dcMotor.get("franz");
        franzMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        wildeHildeMotor = hardwareMap.dcMotor.get("hilde");

        leftBeacon = hardwareMap.servo.get("left_beacon");
        rightBeacon = hardwareMap.servo.get("right_beacon");
    }

    public void loop() {

        drive();
        changeDirection();

        slow();
        lift();

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

        //scale down to 10% if the slowModeToggle is on
        if(slowModeToggle){
            for(int i = 0; i < 4; i++){
                vals[i] = vals[i] * 0.1;
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
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    public void slow() {
        if(gamepad1.left_trigger > 0.5) {
            slowModeToggle = true;
            telemetry.addData("SlowMode", "On");
        }
        if(gamepad1.right_trigger > 0.5){
            slowModeToggle = false;
            telemetry.addData("SlowMode", "Off");
        }
    }
    public void lift(){
        gabiMotor.setPower(-gamepad2.right_stick_y);
    }

    public void collect(){
        if(collectingToggle){
            steffiMotor.setPower(1);
            franzMotor.setPower(1);
        } else {
            steffiMotor.setPower(0);
            franzMotor.setPower(0);
        }
        if(gamepad2.a){
            collectingToggle = true;
        }
        if(gamepad2.b){
            collectingToggle = false;
        }

    }

    public void shooter(){
        if(shootingToggle){
            wildeHildeMotor.setPower(1);
        } else {
            wildeHildeMotor.setPower(0);
        }
        if(gamepad2.x) {
            shootingToggle = true;
        }
        if(gamepad2.y){
            shootingToggle = false;
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
