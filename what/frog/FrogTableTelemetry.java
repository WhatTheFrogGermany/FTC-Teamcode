package org.firstinspires.ftc.teamcode.what.frog;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by FTC2 on 31.01.2017.
 */
public class FrogTableTelemetry extends FrogTableWriter {
    String prefix;
    Telemetry telemetry;

    public FrogTableTelemetry(String name, int columnNumber, String telemetryPrefix, Telemetry telemetry){
        super(name, columnNumber);
        prefix = telemetryPrefix;
        this.telemetry = telemetry;
    }

    public void addTelemetry(){
        telemetry.addData(prefix + "row", getRowNumber());
        for(int i = 0; i < columnNumber; i++){
            telemetry.addData(prefix + Integer.toString(i), columns[i]);
        }
    }
}
