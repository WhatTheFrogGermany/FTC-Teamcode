package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.what.frog.FrogAutonomousSimpler;

/**
 * Created by FTC2 on 22.02.2017.
 */
@Autonomous(name="Test: 4 (Red)", group="Tests")
public class AutoTest4 extends FrogAutonomousSimpler {
    @Override
        public void init() {
            super.init();
        changeDirection(GABI_FRONT);
        setDriveTolerances(0.1);
        wildeHildeMotor.setTolerance(0.2);
        setRobotXY(20, 208);
        addChangeFront(GABI_FRONT);
        addDriveToPosition(70, 208);
        addShootTwice();
        //addNextBall();
        //addShoot();
        addDriveToPosition(175, 310);
        addDriveToHeading(180);
        addChangeFront(BEACON_FRONT);
        addDriveToWall(8);
        addDriveToLine(GABI_FRONT);
        addRedBeacon();
    }

}
