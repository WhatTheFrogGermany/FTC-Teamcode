package org.firstinspires.ftc.teamcode;

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

    //for driving with the Gyros
    int desiredHeading = 0;
    FrogToggle gyrosToggle;

    int lyMag;
    int lxMag;

    double[] scaled = new double[4];

    FrogToggle gabiBlockToggle;

    //the collecting mechanism
    ElapsedTime steffiTime; //Added TimeToggle on 14.01
    boolean steffiToggle = false;
    ElapsedTime franzTime;
    boolean franzToggle = false;

    int lastTargetPositionHilde = 0;
    boolean hildeRotatedFully = true;
    boolean customMoveHilde = false;

    @Override
    public void init (){
        super.init();

        gyrosToggle = new FrogToggle(500);
        gyrosToggle.toggle(true);

        slowModeToggle = new FrogToggle(500);
        gabiBlockToggle = new FrogToggle(500);

        steffiTime = new ElapsedTime();

        franzTime = new ElapsedTime();
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
        slowModeToggle.toggle(gamepad1.left_trigger > 0.5);
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

        double r = gamepad1.right_stick_x;

        if(r != 0){
            desiredHeading = getHeading();
        }

        double a = -x + y - r;
        double b = x + y + r;
        double c = - x + y + r;
        double d = x + y - r;

        double[] beforeScaled = {a,b,c,d};
        scaled = scaleDown(beforeScaled, x, y, r);

        gyrosToggle.toggle(gamepad1.right_trigger > 0.5);
        if(gyrosToggle.getState()) {
            double[] adjusted = adjustToHeading(beforeScaled);
            scaled = scaleDown(adjusted, x, y, r);
            telemetry.addData("GyrosDrive", true);
        } else {
            scaled = scaleDown(beforeScaled, x, y, r);
            telemetry.addData("GyrosDrive", false);
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

    public double[] adjustToHeading(double[] vals){
        int difference = desiredHeading - getHeading();
        if(difference > 180){
            difference = 360 - difference;
        }else if (difference < -180){
            difference = difference + 360;
        }
        double r_extra = ((difference) * 0.005556);
        telemetry.addData("power", r_extra);
        telemetry.addData("heading", desiredHeading);
        telemetry.addData("gyro val", getHeading());

        vals[0] += r_extra;
        vals[1] -= r_extra;
        vals[2] -= r_extra;
        vals[3] += r_extra;

        return vals;
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
        double scale = Math.abs(1 / greatest);
        for(int i = 0; i < 4; i++){
            vals[i] = vals[i] * scale;
            vals[i] = check(vals[i]);
        }

        //Afterwards we scale according to the absolute value of x and y (the distance the stick is from 0)
        double absolute = Math.sqrt(x*x + y*y);
        if(absolute > Math.abs(r)) {
            for (int i = 0; i < 4; i++) {
                vals[i] = vals[i] * absolute;
            }
        } else {
            for (int i = 0; i < 4; i++) {
                vals[i] = vals[i] * r;
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


        if(gamepad2.a && (steffiTime.milliseconds() > 500)){
            steffiToggle = !steffiToggle;
            steffiTime.reset();
        }
        if(gamepad2.b && (franzTime.milliseconds() > 500
        )){
            franzToggle = !franzToggle;
            franzTime.reset();
        }

    }

    //public void kerstin(){
       // if(gamepad2.dpad_left){
           // kerstinServo.setPosition(0);
        //}
        //if(gamepad2.dpad_right){
          //  kerstinServo.setPosition(1);
      //  }
   // }

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
