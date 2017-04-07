package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.what.frog.FrogAutonomousSimpler;

/**
 * Created by FTC2 on 07.04.2017.
 */
@Autonomous(name="Ball: Blue", group="Default")
public class AutoBallBlue extends FrogAutonomousSimpler {
    @Override
    public void init() {
        super.init();
        setInitialHeading(90);
        setRobotXY(208, 20);
        addChangeFront(GABI_FRONT);
        addDriveToPosition(209, 70);
        addShootTwice();
        addDriveToPosition(208, 180);
    }
}
