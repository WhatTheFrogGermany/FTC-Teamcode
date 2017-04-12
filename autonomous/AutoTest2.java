package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.what.frog.FrogAutonomousSimpler;

/**
 * Created by FTC2 on 07.02.2017.
 */
@Autonomous(name="Test: Test2", group="Tests")
public class AutoTest2 extends FrogAutonomousSimpler {
    @Override
    public void init() {
        super.init();
        changeDirection(GABI_FRONT);
        setDriveTolerances(0.1);
        setRobotXY(0,0);
        //setInitialHeading(90);
        addDriveToPosition(100, 20);
        addShootTwice();
        //addDriveToPosition(300, 156);
        //addDriveToPosition(100, 200);
        //addDriveToHeading(180);
        /*
        resetGyros();
        //addDriveDistance(3000);
        addDriveToHeading(150);
        robotWait(5000);
        addShoot();
        //addBlueBeacon();
        */
    }

    @Override
    public void loop() {
        super.loop();
    }
}
