package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.what.frog.FrogOpMode;

/**
 * Created by FTC2 on 13.02.2017.
 */
@TeleOp(name="Test: Motors", group="Tests")
public class TestMotors extends FrogOpMode {
    @Override
    public void loop() {
        super.loop();
        if(gamepad1.a){
            aOmni.setPower(1);
        } else {
            aOmni.setPower(0);
        }
        if(gamepad1.b){
            bOmni.setPower(1);
        } else {
            bOmni.setPower(0);
        }
        if(gamepad1.x){
            cOmni.setPower(1);
        } else {
            cOmni.setPower(0);
        }
        if(gamepad1.y){
            dOmni.setPower(1);
        } else {
            dOmni.setPower(0);
        }
        if(gamepad1.dpad_up){
            gabiMotor.setPower(1);
        } else {
            gabiMotor.setPower(0);
        }
        if(gamepad1.dpad_down){
            wildeHildeMotor.setPower(1);
        } else {
            wildeHildeMotor.setPower(0);
        }
        if(gamepad1.dpad_right){
            steffiMotor.setPower(1);
        } else {
            steffiMotor.setPower(0);
        }
        if(gamepad1.dpad_left){
            franzMotor.setPower(1);
        } else {
            franzMotor.setPower(0);
        }
    }
}
