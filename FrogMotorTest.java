package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.what.frog.FrogMotor;

/**
 * Created by FTC2 on 27.01.2017.
 */
@TeleOp(name="Test: FrogMotor", group="Tests")
public class FrogMotorTest extends OpMode {
    FrogMotor testMotor;

    @Override
    public void init() {
        testMotor = new FrogMotor(hardwareMap.dcMotor.get("test_motor"));
        testMotor.initDriveToPosition(-5000, 3000, 1);
        testMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        testMotor.driveToPosition();
        telemetry.addData("Position", testMotor.getCurrentPosition());
        telemetry.addData("NormalMotor", testMotor.normalMotorPower);
        telemetry.addData("currentMotor", testMotor.motorCurrentPower);

    }
}
