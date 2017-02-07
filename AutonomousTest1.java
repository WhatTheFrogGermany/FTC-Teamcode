package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.what.frog.FrogAutonomous;

/**
 * Created by FTC2 on 07.02.2017.
 */
@Autonomous(name="Test1", group="Tests")
public class AutonomousTest1 extends FrogAutonomous {
    @Override
    public void init() {
        super.init();
        changeDirection(GABI_FRONT);
    }

    @Override
    public void init_loop() {
        super.init_loop();
        resetDrive();
        driveDistance(3000);
    }

    @Override
    public void loop() {
        super.loop();
    }
}
