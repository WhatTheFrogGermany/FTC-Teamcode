package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.what.frog.FrogAutonomousSimpler;

/**
 * Created by FTC2 on 07.02.2017.
 */
public class AutoTest2 extends FrogAutonomousSimpler {
    @Override
    public void init() {
        super.init();
        addDriveDistance(3000);
        addDriveToHeading(150);
        addBlueBeacon();
    }

    @Override
    public void loop() {
        super.loop();
    }
}
