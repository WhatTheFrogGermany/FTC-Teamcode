package org.firstinspires.ftc.teamcode.pushbot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FTC on 08.12.2016.
 */

@TeleOp(name="TeleOp: Pushbot", group="TeleOp")
public class PushBotTele extends OpMode {


    // Liste mit allen verbauten Motoren, Servos, Sensoren
    DcMotor leftDrive;
    DcMotor rightDrive;

    @Override
    public void init(){ // Einführungsteil

        //Teile werden gemappt, muss später am Handy eingeben, wo sie angeschlossen sind
        leftDrive = hardwareMap.dcMotor.get("left_drive");
        rightDrive = hardwareMap.dcMotor.get("right_drive");
    }

    //Teil, der immer wiederholt wird, eigentliches Programm
    public void loop() {
        drive();
    }

    public void drive(){
        //Strom, der Motoren zugeführt wird entspricht Werten der Knubbel am Gamepad
        leftDrive.setPower(gamepad1.left_stick_y);
        rightDrive.setPower(gamepad1.right_stick_y);

        //Notaus-Knopf auf dem zweiten Gamepad, der den zugeführten Strom auf null setzt
        if (gamepad2.x == true){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }
    }
}
