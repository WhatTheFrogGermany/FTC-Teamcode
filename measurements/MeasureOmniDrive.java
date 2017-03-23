package org.firstinspires.ftc.teamcode.measurements;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.what.frog.FrogOpMode;
import org.firstinspires.ftc.teamcode.what.frog.FrogTableTelemetry;

/**
 * Created by FTC2 on 23.03.2017.
 */
@TeleOp(name="Measure: OmniDrive", group="Measure")
public class MeasureOmniDrive extends FrogOpMode {
    FrogTableTelemetry tableWriter;
    ElapsedTime elapsedTime;
    double ratio = 1;
    double ratioSteps;

    @Override
    public void init() {
        super.init();
        elapsedTime = new ElapsedTime();
        tableWriter = new FrogTableTelemetry("omni_drive.csv", 9, "omni",telemetry);
    }
}
