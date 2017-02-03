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
public class MeasureDrive extends FrogOpMode {

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

        aOmni.setDirection(DcMotor.Direction.REVERSE);
        bOmni.setDirection(DcMotor.Direction.FORWARD);
        cOmni.setDirection(DcMotor.Direction.FORWARD);
        dOmni.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            aOmni.setPower(1);
            bOmni.setPower(1);
            cOmni.setPower(1);
            dOmni.setPower(1);

        }

        if(gamepad1.b){
            aOmni.setPower(0);
            bOmni.setPower(0);
            cOmni.setPower(0);
            dOmni.setPower(0);
        }

        if(gamepad1.dpad_up && elapsedTime.milliseconds() > 250){
            elapsedTime.reset();
            cm_travelled++;
        }

        if(gamepad1.dpad_down && elapsedTime.milliseconds() > 250){
            elapsedTime.reset();
            cm_travelled--;
        }

        if(gamepad1.x && storeTime.seconds() > 1){
            storeTime.reset();
            telemetry.addData("status", "added another line");

            aOmni.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            bOmni.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            cOmni.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            dOmni.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            aOmni.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bOmni.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            cOmni.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            dOmni.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        if(gamepad1.y &&storeTime.seconds() > 1){
            storeTime.reset();
            telemetry.addData("status", "saved file");

        }

        telemetry.addData("cm_travelled", cm_travelled);
        telemetry.addData("counter", 134124);
        telemetry.addData("a", aOmni.getCurrentPosition());
        telemetry.addData("b", bOmni.getCurrentPosition());
        telemetry.addData("c", cOmni.getCurrentPosition());
        telemetry.addData("d", dOmni.getCurrentPosition());
    }
}
