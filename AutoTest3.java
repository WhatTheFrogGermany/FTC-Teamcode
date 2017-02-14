package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.what.frog.FrogAutonomousSimpler;

/**
 * Created by FTC2 on 13.02.2017.
 */
@Autonomous(name="Test: 3", group="Tests")
public class AutoTest3 extends FrogAutonomousSimpler {
    @Override
    public void init() {
        super.init();
        changeDirection(GABI_FRONT);
        //addDriveToPosition(100, 100);
        addBlueBeacon();
    }
}
