package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.what.frog.FrogFileWriter;
import org.firstinspires.ftc.teamcode.what.frog.FrogOpMode;
import org.firstinspires.ftc.teamcode.what.frog.FrogTableTelemetry;

/**
 * Created by FTC2 on 27.01.2017.
 */

@TeleOp(name="Measure: Drive", group="Measure")
//This OpMode is for measuring the encoder values that correlate to the distance driven.
public class MeasureDrive extends TeleOpOmni4 {

    FrogTableTelemetry fileWriter;
    int cm_travelled = 100;

    ElapsedTime elapsedTime;
    ElapsedTime storeTime;
    //structure of the table:
    //number, milimetres, a, b, c, d
    @Override
    public void init() {
        super.init();
        elapsedTime = new ElapsedTime();
        storeTime = new ElapsedTime();

        fileWriter = new FrogTableTelemetry("measure_drive.csv", 6, "mes", telemetry);
    }

    @Override
    public void loop() {
        super.loop();
        if(gamepad1.dpad_right){
            extraDrive = true;
            frontLeftDrive.setPower(1);
            frontRightDrive.setPower(1);
            backLeftDrive.setPower(1);
            backRightDrive.setPower(1);

        }

        if(gamepad1.dpad_left){
            extraDrive = false;
            frontLeftDrive.setPower(0);
            frontRightDrive.setPower(0);
            backRightDrive.setPower(0);
            backLeftDrive.setPower(0);
        }

        if(gamepad1.dpad_up && elapsedTime.milliseconds() > 250){
            elapsedTime.reset();
            cm_travelled++;
        }

        if(gamepad1.dpad_down && elapsedTime.milliseconds() > 250){
            elapsedTime.reset();
            cm_travelled--;
        }

        fileWriter.setNextValue(cm_travelled);
        fileWriter.setNextValue(aOmni.getCurrentPosition());
        fileWriter.setNextValue(bOmni.getCurrentPosition());
        fileWriter.setNextValue(cOmni.getCurrentPosition());
        fileWriter.setNextValue(dOmni.getCurrentPosition());
        fileWriter.resetCurrentCell();
        fileWriter.addTelemetry();

        if(gamepad1.left_bumper && storeTime.seconds() > 1){
            storeTime.reset();
            telemetry.addData("status", "added another line");

            fileWriter.addColumn();

            aOmni.reset();
            bOmni.reset();
            cOmni.reset();
            dOmni.reset();
        }

        if(gamepad1.left_trigger > 0.5 && storeTime.seconds() > 1){
            storeTime.reset();
            telemetry.addData("status", "saved file");
            fileWriter.writeAll();
            requestOpModeStop();
        }

        if(gamepad1.right_bumper){
            aOmni.reset();
            bOmni.reset();
            cOmni.reset();
            dOmni.reset();
        }

        /*
        telemetry.addData("cm_travelled", cm_travelled);
        telemetry.addData("counter", 134124);
        telemetry.addData("a", aOmni.getCurrentPosition());
        telemetry.addData("b", bOmni.getCurrentPosition());
        telemetry.addData("c", cOmni.getCurrentPosition());
        telemetry.addData("d", dOmni.getCurrentPosition());
        */
    }
}
