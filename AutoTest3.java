package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.what.frog.FrogAutonomousSimpler;

/**
 * Created by FTC2 on 13.02.2017.
 */
public class AutoTest3 extends FrogAutonomousSimpler {
    @Override
    public void init() {
        super.init();
        changeDirection(GABI_FRONT);
        addDriveToPosition(100, 100);
    }
}
