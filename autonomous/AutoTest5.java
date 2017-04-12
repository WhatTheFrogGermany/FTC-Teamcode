package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.what.frog.FrogAutonomousSimpler;

/**
 * Created by FTC2 on 03.03.2017.
 */
@Autonomous(name="Test 5", group="Tests")
public class AutoTest5 extends FrogAutonomousSimpler {
    @Override
    public void init() {
        super.init();
        addDriveToLine(GABI_FRONT);
    }
}
