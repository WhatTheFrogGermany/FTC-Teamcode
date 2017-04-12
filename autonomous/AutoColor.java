package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by FTC on 21.12.2016.
 */
@Autonomous(name = "Test: Color", group = "Tests")
public class AutoColor extends OpMode {
    short RED = 1;
    short BLUE = 0;
    ColorSensor leftBColor;
    ColorSensor rightBColor;

    Servo leftBServo;
    Servo rightBServo;

    short color;
    boolean chosen = false;
    boolean done = false;
    @Override
    public void init() {
        leftBColor = hardwareMap.colorSensor.get("left_beacon_color");
        rightBColor = hardwareMap.colorSensor.get("right_beacon_color");

        leftBServo = hardwareMap.servo.get("left_beacon");
        rightBServo = hardwareMap.servo.get("right_beacon");
    }

    @Override
    public void loop() {
        if(!chosen){
            if(gamepad1.b){
                color = RED;
                chosen = true;
                if(leftBColor.red() >= 3 && leftBColor.blue() <= 2){
                    leftBServo.setPosition(1);
                }
            }
            if(gamepad1.x){
                color = BLUE;
                chosen = true;
            }
        } else if(!done){
            if(color == RED){
                if(leftBColor.red() >= 3 && leftBColor.blue() <= 2){
                    leftBServo.setPosition(1);
                    done = true;

                }
                if(rightBColor.red() >= 3 && rightBColor.blue() <= 2){
                    rightBServo.setPosition(0);
                    done = true;
                }
            }
            if(color == BLUE){
                if(leftBColor.blue() >= 3 && leftBColor.red() <= 2){
                    leftBServo.setPosition(1);
                    done = true;
                }
                if(rightBColor.blue() >= 3 && rightBColor.red() <= 2){
                    rightBServo.setPosition(0);
                    done = true;
                }
            }
        }
    }
}
