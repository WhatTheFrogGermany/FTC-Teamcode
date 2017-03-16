package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FTC2 on 20.01.2017.
 */
@TeleOp(name="Test: Encoder TestMotor", group="Tests")
public class EncoderTest extends OpMode {
    DcMotor testMotor;

    @Override
    public void init() {
        testMotor = hardwareMap.dcMotor.get("test_motor");
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            testMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            testMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        telemetry.addData("Encoder", testMotor.getCurrentPosition());

        testMotor.setPower(gamepad1.left_stick_y);

    }
}
