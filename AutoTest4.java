package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.what.frog.FrogAutonomousSimpler;

/**
 * Created by FTC2 on 22.02.2017.
 */
@Autonomous(name="Test: 4", group="Tests")
public class AutoTest4 extends FrogAutonomousSimpler {
    @Override
    public void init() {
        super.init();
        changeDirection(GABI_FRONT);
        setDriveTolerances(0.1);
        wildeHildeMotor.setTolerance(0.1);
        setRobotXY(28, 208);
        addChangeFront(GABI_FRONT);
        addDriveToPosition(82, 208);
        addShoot();
        addNextBall();
        addShoot();
        addDriveToPosition(154, 300);
        addDriveToHeading(180);
        addChangeFront(BEACON_FRONT);
        addDriveToWall();
        addBlueBeacon();
    }

}
