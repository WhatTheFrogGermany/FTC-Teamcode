package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.what.frog.FrogAutonomousSimpler;

/**
 * Created by FTC2 on 22.02.2017.
 */
@Autonomous(name="Default: Blue", group="Default")
public class AutoBlue extends FrogAutonomousSimpler {
    @Override
    public void init() {
        super.init();
        setInitialHeading(90);
        setRobotXY(208, 20);
        addChangeFront(GABI_FRONT);
        addDriveToPosition(209, 70);
        addShootTwice();
        addDriveToPosition(270, 190);
        addDriveToHeading(90);
        addChangeFront(BEACON_FRONT);
        addDriveToWall(9);
        addDriveToLine(FRANZ_FRONT);
        addBlueBeacon();
    }
}
