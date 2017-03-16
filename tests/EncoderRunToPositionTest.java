package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FTC2 on 20.01.2017.
 */

@TeleOp(name="Test: EncRunToPosition", group="Tests")
public class EncoderRunToPositionTest extends OpMode {
    DcMotor testMotor;
    @Override
    public void init() {
        testMotor = hardwareMap.dcMotor.get("test_motor");
        testMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void init_loop() {
        super.init_loop();
        testMotor.setTargetPosition(1400);
        testMotor.setPower(1);
        testMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    @Override
    public void loop() {
        telemetry.addData("TargetPos", testMotor.getTargetPosition());
        telemetry.addData("CurrentPos", testMotor.getCurrentPosition());
        telemetry.addData("Mode", testMotor.getMode());
    }
}
