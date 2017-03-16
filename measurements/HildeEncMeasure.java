package org.firstinspires.ftc.teamcode.measurements;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.what.frog.FrogOpMode;
import org.firstinspires.ftc.teamcode.what.frog.FrogTableTelemetry;

/**
 * Created by FTC2 on 15.03.2017.
 */
@TeleOp(name="Hilde Enc", group="Measurements")
public class HildeEncMeasure extends FrogOpMode {
    FrogTableTelemetry tableWriter;
    ElapsedTime elapsedTime;

    @Override
    public void init() {
        super.init();
        tableWriter = new FrogTableTelemetry("hilde_enc.csv", 3, "hilde", telemetry);
        elapsedTime = new ElapsedTime();
    }

    @Override
    public void loop() {
        super.loop();
        tableWriter.setNextValue(wildeHildeMotor.getCurrentPosition());
        tableWriter.setNextValue((int)elapsedTime.milliseconds());
        tableWriter.resetCurrentCell();
        tableWriter.addTelemetry();

        if(gamepad1.a){
            wildeHildeMotor.setPower(1);
            elapsedTime.reset();
        }
        if(gamepad1.b){
            wildeHildeMotor.setPower(0);
            wildeHildeMotor.reset();
            tableWriter.addColumn();
        }

        if(gamepad1.x){
            tableWriter.writeAll();
        }

    }
}
