package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.what.frog.FrogAutonomous;
import org.firstinspires.ftc.teamcode.what.frog.FrogAutonomousSimpler;

/**
 * Created by FTC2 on 07.04.2017.
 */
@Autonomous(name="Ball: Red", group="Default")
public class AutoBallRed extends FrogAutonomousSimpler {
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
        addDriveToPosition(180, 209);
    }
}
