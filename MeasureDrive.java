package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.what.frog.FrogFileWriter;

/**
 * Created by FTC2 on 27.01.2017.
 */

//This OpMode is for measuring the encoder values that correlate to the distance driven.
public class MeasureDrive extends OpMode {
    DcMotor aOmni;
    DcMotor bOmni;
    DcMotor cOmni;
    DcMotor dOmni;

    FrogFileWriter fileWriter;
    int[][] array = new int[50][6];
    int counter = 0;
    int mm_travelled;

    ElapsedTime elapsedTime;
    ElapsedTime storeTime;
    //structure of the table:
    //number, milimetres, a, b, c, d
    @Override
    public void init() {
        elapsedTime = new ElapsedTime();
        storeTime = new ElapsedTime();

        fileWriter = new FrogFileWriter("measure_drive.csv");
        aOmni = hardwareMap.dcMotor.get("a_omni");
        bOmni = hardwareMap.dcMotor.get("b_omni");
        cOmni = hardwareMap.dcMotor.get("c_omni");
        dOmni = hardwareMap.dcMotor.get("d_omni");

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
            mm_travelled++;
        }

        if(gamepad1.dpad_down && elapsedTime.milliseconds() > 250){
            elapsedTime.reset();
            mm_travelled--;
        }

        if(gamepad1.x && storeTime.seconds() > 1){
            storeTime.reset();
            array[counter][0] = counter;
            array[counter][1] = mm_travelled;
            array[counter][2] = aOmni.getCurrentPosition();
            array[counter][3] = bOmni.getCurrentPosition();
            array[counter][4] = cOmni.getCurrentPosition();
            array[counter][5] = dOmni.getCurrentPosition();
            counter++;
            telemetry.addData("status", "added another line");
        }

        if(gamepad1.y &&storeTime.seconds() > 1){
            storeTime.reset();
            fileWriter.write2DArray(array);

        }

        telemetry.addData("a", aOmni.getCurrentPosition());
        telemetry.addData("b", bOmni.getCurrentPosition());
        telemetry.addData("c", cOmni.getCurrentPosition());
        telemetry.addData("d", dOmni.getCurrentPosition());
    }
}
