package org.firstinspires.ftc.teamcode.measurements;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.teleop.TeleOpOmni4;
import org.firstinspires.ftc.teamcode.what.frog.FrogOpMode;
import org.firstinspires.ftc.teamcode.what.frog.FrogTableTelemetry;

/**
 * Created by FTC2 on 23.03.2017.
 */
@TeleOp(name="Measure: OmniDrive", group="Measure")
public class MeasureOmniDrive extends FrogOpMode{
    FrogTableTelemetry tableWriter;

    ElapsedTime elapsedTime;
    ElapsedTime storeTime;

    double motorPowerAC = 1;
    double motorPowerBD = 1;
    double ratioSteps = 0.1;
    int xDistance = 0;
    int yDistance = 100;

    @Override
    public void init() {
        super.init();
        elapsedTime = new ElapsedTime();
        storeTime = new ElapsedTime();
        tableWriter = new FrogTableTelemetry("omni_drive.csv", 9, "omni",telemetry);
        changeDirection(GABI_FRONT);
    }

    @Override
    public void loop() {
        super.loop();

        //setting the x and y distances
        if(gamepad1.dpad_up && elapsedTime.milliseconds() > 150){
            yDistance++;
            elapsedTime.reset();
        }
        if(gamepad1.dpad_down && elapsedTime.milliseconds() > 150){
            yDistance--;
            elapsedTime.reset();
        }
        if(gamepad1.dpad_right && elapsedTime.milliseconds() > 150){
            xDistance++;
            elapsedTime.reset();
        }
        if(gamepad1.dpad_left && elapsedTime.milliseconds() > 150){
            xDistance--;
            elapsedTime.reset();
        }

        //outputting the x and y values in the telemetry
        telemetry.addData("X", xDistance);
        telemetry.addData("Y", yDistance);

        //driving the robot
        if(gamepad1.right_bumper && elapsedTime.milliseconds() > 500){
            elapsedTime.reset();
            frontLeftDrive.setPower(motorPowerAC);
            frontRightDrive.setPower(motorPowerBD);
            backRightDrive.setPower(motorPowerAC);
            backLeftDrive.setPower(motorPowerBD);
        }

        if(gamepad1.left_bumper && elapsedTime.milliseconds() > 500){
            elapsedTime.reset();
            frontLeftDrive.setPower(0);
            frontRightDrive.setPower(0);
            backRightDrive.setPower(0);
            backLeftDrive.setPower(0);
        }

        //saving the values into the table
        tableWriter.setNextValue((int)(motorPowerAC*100));
        tableWriter.setNextValue((int)(motorPowerBD*100));
        tableWriter.setNextValue(frontLeftDrive.getCurrentPosition());
        tableWriter.setNextValue(frontRightDrive.getCurrentPosition());
        tableWriter.setNextValue(backRightDrive.getCurrentPosition());
        tableWriter.setNextValue(backLeftDrive.getCurrentPosition());
        tableWriter.resetCurrentCell();
        tableWriter.addTelemetry();

        //resetting the encoders
        if(gamepad1.y && elapsedTime.milliseconds() > 500){
            aOmni.reset();
            bOmni.reset();
            cOmni.reset();
            dOmni.reset();
            elapsedTime.reset();
        }

        //adding a row
        if(gamepad1.a && elapsedTime.milliseconds() > 500){
            elapsedTime.reset();
            telemetry.addData("Status", "Added another line");
            tableWriter.addColumn();

            motorPowerBD-=ratioSteps;
            aOmni.reset();
            bOmni.reset();
            cOmni.reset();
            dOmni.reset();
        }

        //saving the file
        if(gamepad1.x && elapsedTime.milliseconds() > 500){
            elapsedTime.reset();
            telemetry.addData("Status", "Saved file");
            tableWriter.writeAll();
            requestOpModeStop();
        }
    }
}
