package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.what.frog.FrogAutonomousSimpler;

/**
 * Created by FTC2 on 03.03.2017.
 */
@Autonomous(name="Test: 6", group="Tests")
public class AutoTest6 extends FrogAutonomousSimpler{
    @Override
    public void init() {
        super.init();
        addChangeFront(GABI_FRONT);
        addDriveDiagonalRed();
        addDriveToWall(8);
        addBlueBeacon();
        addDriveToLine(GABI_FRONT);
        addRedBeacon();
    }
}
