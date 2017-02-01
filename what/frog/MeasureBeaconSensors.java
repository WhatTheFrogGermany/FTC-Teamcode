package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by FTC2 on 31.01.2017.
 */
@TeleOp(name="Measure: Beacon Sensors", group="Measure")

public class MeasureBeaconSensors extends FrogOpMode {
    FrogTableTelemetry fileWriter;
    int leftColor = 0;
    int rightColor = 0;
    boolean leftLED = false;
    boolean rightLED = false;

    FrogToggle LEDToggle;
    FrogToggle leftColorToggle;
    FrogToggle rightColorToggle;

    ElapsedTime buttonTime;
    @Override
    public void init() {
        super.init();
        fileWriter = new FrogTableTelemetry("mega_table.csv", 15, "mes", telemetry);
        buttonTime = new ElapsedTime();

        LEDToggle = new FrogToggle(500);
        leftColorToggle = new FrogToggle(500);
        rightColorToggle = new FrogToggle(500);
    }

    @Override
    public void loop() {
        super.loop();

        fileWriter.setNextValue(leftColor);
        fileWriter.setNextValue(rightColor);
        fileWriter.setNextValue(leftLED ? 1 : 0);
        fileWriter.setNextValue(rightLED ? 1 : 0);
        fileWriter.setNextValue(leftBeaconRange.getOptical());
        fileWriter.setNextValue(rightBeaconRange.getOptical());
        fileWriter.setNextValue(leftBeaconColor.alpha());
        fileWriter.setNextValue(leftBeaconColor.red());
        fileWriter.setNextValue(leftBeaconColor.green());
        fileWriter.setNextValue(leftBeaconColor.blue());
        fileWriter.setNextValue(rightBeaconColor.alpha());
        fileWriter.setNextValue(rightBeaconColor.red());
        fileWriter.setNextValue(rightBeaconColor.green());
        fileWriter.setNextValue(rightBeaconColor.blue());
        fileWriter.resetCurrentCell();

        if(gamepad1.a && buttonTime.milliseconds() > 500) {
            buttonTime.reset();
            fileWriter.addColumn();
        }

        if(gamepad1.b && buttonTime.milliseconds() > 500){
            buttonTime.reset();
            fileWriter.writeAll();
        }
        fileWriter.addTelemetry();

        LEDToggle.toggle(gamepad1.x);
        leftColorToggle.toggle(gamepad1.dpad_left);
        rightColorToggle.toggle(gamepad1.dpad_right);

        if(LEDToggle.getState()){
            leftBeaconColor.enableLed(true);
            rightBeaconColor.enableLed(true);
            leftLED = true;
            rightLED = true;
        } else{
            leftBeaconColor.enableLed(false);
            rightBeaconColor.enableLed(false);
            leftLED = false;
            rightLED = false;
        }

        if(leftColorToggle.getState()){
            leftColor = 1;
        } else{
            leftColor = 0;
        }

        if(rightColorToggle.getState()){
            rightColor = 1;
        } else {
            rightColor = 0;
        }

    }
}
