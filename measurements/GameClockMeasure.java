package org.firstinspires.ftc.teamcode.measurements;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.what.frog.FrogOpMode;

/**
 * Created by FTC2 on 07.04.2017.
 */
@TeleOp(name="MeasureGameClock", group="Measure")
public class GameClockMeasure extends FrogOpMode {
    ElapsedTime measureTime;
    ElapsedTime buttonTime;
    double lastTime;

    @Override
    public void init() {
        super.init();
        measureTime = new ElapsedTime();
        buttonTime = new ElapsedTime();
    }

    @Override
    public void loop() {
        super.loop();
        gameClock.setPower(1);
        if(gamepad1.a && buttonTime.milliseconds() > 300){
            lastTime = measureTime.seconds();
            measureTime.reset();
        }

        telemetry.addData("time", measureTime.milliseconds());
        telemetry.addData("lastTime", lastTime);
    }
}
