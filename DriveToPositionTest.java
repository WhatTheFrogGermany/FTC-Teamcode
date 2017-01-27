package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FTC2 on 27.01.2017.
 */
@TeleOp(name="Test: DriveToPosition", group="Tests")
public class DriveToPositionTest extends OpMode {
    DcMotor testMotor;
    int targetPosition = 5000;
    int stoppingInterval = 3000;
    int stopstartPosition = targetPosition - stoppingInterval;
    double driveNormalPower = 1;
    double driveCurrentPower = driveNormalPower;

    @Override
    public void init() {
        testMotor = hardwareMap.dcMotor.get("test_motor");
        testMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        driveToPosition();
        telemetry.addData("currentPosition", testMotor.getCurrentPosition());
    }

    public void driveToPosition(){
        if(testMotor.getCurrentPosition() < stopstartPosition){
            testMotor.setPower(driveNormalPower);
        } else {
            int distance = targetPosition - testMotor.getCurrentPosition();
            telemetry.addData("distance", distance);
            double scale = (double)distance / stoppingInterval;
            telemetry.addData("scale", scale);
            driveCurrentPower = driveNormalPower * scale;
            testMotor.setPower(driveCurrentPower);
        }
    }

}
