package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.ams.AMSColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.what.frog.FrogMotor;
import org.firstinspires.ftc.teamcode.what.frog.FrogOpMode;
import org.firstinspires.ftc.teamcode.what.frog.FrogToggle;

/**
 * Created by FTC on 07.10.2016.
 */
@TeleOp(name="TeleOp: Omni", group="TeleOp")

public class TeleOpOmni4 extends FrogOpMode {

    FrogToggle slowModeToggle;

    public boolean extraDrive = false; // work around to make sure MeasureDrive is working properly


    int lyMag;
    int lxMag;
    double r_extra = 0;

    double[] scaled = new double[4];

    FrogToggle gabiBlockToggle;

    //the collecting mechanism
    ElapsedTime steffiTime; //Added TimeToggle on 14.01
    FrogToggle steffiToggle;
    ElapsedTime franzTime;
    FrogToggle franzToggle;
    ElapsedTime steffiStop;
    ElapsedTime franzStop;

    int lastTargetPositionHilde = 0;
    boolean hildeRotatedFully = true;
    boolean customMoveHilde = false;
    boolean slowModeOn = false;

    @Override
    public void init (){
        super.init();

        wildeHildeMotor.reset();
        wildeHildeMotor.setTolerance(0.2);

        slowModeToggle = new FrogToggle(500);
        gabiBlockToggle = new FrogToggle(500);

        steffiToggle = new FrogToggle(500);
        steffiTime = new ElapsedTime();
        steffiStop = new ElapsedTime();
        franzToggle = new FrogToggle(500);
        franzTime = new ElapsedTime();
        franzStop = new ElapsedTime();

        gabiBlockServo.setPosition(0);
        gabiBlockServoRight.setPosition(1);
    }

    public void loop() {
        super.loop();
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
        slowModeToggle.toggle(gamepad1.left_trigger > 0.5);
        double y = -(gamepad1.left_stick_y);
        double x = gamepad1.left_stick_x;

        quewakServo.setPower(1);
        //vonJanine keine ahnung wie das tut und welcherichtung und so

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

        double r = gamepad1.right_stick_x;
        if(!slowModeToggle.getState()) {
            r *= 0.5;
        }

        double a = -x + y - r;
        double b = x + y + r;
        double c = - x + y + r;
        double d = x + y - r;

        double[] beforeScaled = {a,b,c,d};
        beforeScaled = scaleDown(beforeScaled, x, y, r);

        r_extra = 0;
        scaled = scaleDown(beforeScaled, x, y, r);
        telemetry.addData("GyrosDrive", false);

        for(int i = 0; i < 4; i++){
            telemetry.addData(Integer.toString(i), scaled[i]);
        }
        a = scaled[0];
        b = scaled[1];
        c = scaled[2];
        d = scaled[3];

        if(!isAllNull(scaled)) {
            //changed it from cornerMotors to directionMotors for changing the directions
            frontRightDrive.setPower(a);
            frontLeftDrive.setPower(b);
            backLeftDrive.setPower(c);
            backRightDrive.setPower(d);
        } else if(!extraDrive){
            aOmni.setPower(0);
            bOmni.setPower(0);
            cOmni.setPower(0);
            dOmni.setPower(0);
        }

    }

    public boolean isAllNull(double[] vals){
        for(int i = 0; i < vals.length; i++){
            if(vals[i] != 0){
                return false;
            }
        }
        return true;
    }
    public double[] scaleDown(double[] vals, double x ,double y, double r){
        //instead of the check we will scale down proportionally (10.10.16)
        //first we check for the greatest of all four values
        double greatest = 0;
        for(int i = 0; i < 4; i++){
            if(Math.abs(vals[i]) > Math.abs(greatest)){
                greatest = vals[i];
            }
        }

        //then we scale the rest accordingly to make sure the greatest value equals one.
        double scale;
        if(greatest != 0) {
            scale = Math.abs(1 / greatest);
        } else {
            scale = 0;
        }

        for(int i = 0; i < 4; i++){
            vals[i] = vals[i] * scale;
            vals[i] = check(vals[i]);
        }

        //Afterwards we scale according to the absolute value of x and y (the distance the stick is from 0)
        double absolute = Math.abs(Math.sqrt(x*x + y*y));
        if(absolute > Math.abs(r)) {
            for (int i = 0; i < 4; i++) {
                vals[i] = vals[i] * absolute;
            }
        } else {
            for (int i = 0; i < 4; i++) {
                vals[i] = vals[i] * Math.abs(r);
            }
        }

        //scale down to 20% if the slowModeToggle is on ; up to 30% on 10.2 bc robot was too heavy
        if(slowModeToggle.getState()){
            for(int i = 0; i < 4; i++){
                vals[i] = vals[i] * 0.3;
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
            super.changeDirection(FRANZ_FRONT);
        }
        if(gamepad1.b){
            //Enrico
            super.changeDirection(HILDE_FRONT);
        }
        if(gamepad1.a){
            //Jens
            super.changeDirection(GABI_FRONT);
        }
        if(gamepad1.x){
            //Raimar
            super.changeDirection(BEACON_FRONT);
        }
    }

    public void lift(){
        gabiMotor.setPower(-gamepad2.right_stick_y);
    }

    public void gabiBlock(){
        gabiBlockToggle.toggle(gamepad2.left_trigger > 0.5 && gamepad2.right_trigger > 0.5);
        if(gabiBlockToggle.getState()){
            gabiBlockServo.setPosition(1); //before this the values were 0.8 and 0.15
            gabiBlockServoRight.setPosition(0);
        } else {
            gabiBlockServo.setPosition(0);
            gabiBlockServoRight.setPosition(1);
        }
    }

    public void collect(){
        steffiToggle.toggle(gamepad2.a);
        franzToggle.toggle(gamepad2.b);
        if(steffiToggle.getState()){
            steffiMotor.setPower(1);
        } else {
            steffiMotor.setPower(0);
            steffiTime.reset();
        }

        if(franzToggle.getState()) {
            franzMotor.setPower(1);
        } else {
            franzMotor.setPower(0);
            franzTime.reset();
        }

        if(franzTime.seconds() > 40){
            franzMotor.setPower(0);
            franzTime.reset();
        }

        if(steffiTime.seconds() > 40){
            steffiMotor.setPower(0);
            steffiTime.reset();
        }
    }

    public void shooter(){
        if(gamepad2.x && !wildeHildeMotor.drivingToPosition && !gamepad2.y){
            wildeHildeMotor.initRotateRounds(1,1);
        }

        if(!gamepad1.y){
            wildeHildeMotor.driveToPosition();
        }

        if(gamepad2.y){
            wildeHildeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            if(gamepad2.x){
                wildeHildeMotor.setPower(-1);
            } else {
                wildeHildeMotor.setPower(1);
            }
            customMoveHilde = true;
        } else if(customMoveHilde){
            wildeHildeMotor.setPower(0);
            wildeHildeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            wildeHildeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lastTargetPositionHilde = 0;
            customMoveHilde = false;
            hildeRotatedFully= true;
            wildeHildeMotor.drivingToPosition = false;
            wildeHildeMotor.lastTargetPosition = 0;
        }

    }

    public void beacon(){
        if(gamepad2.left_bumper){
            leftBeaconServo.setPosition(1);
        }
        if(gamepad2.right_bumper){
            rightBeaconServo.setPosition(0);
        }
        if(gamepad2.back){
            leftBeaconServo.setPosition(0);
            rightBeaconServo.setPosition(1);
        }
    }

}
