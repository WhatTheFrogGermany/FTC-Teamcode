package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.what.frog.FrogAutonomousSimpler;

/**
 * Created by FTC2 on 11.02.2017.
 */
@Autonomous(name="Scrimmage", group="Execute")
public class AutoScrimmage extends FrogAutonomousSimpler {
    @Override
    public void init() {
        super.init();
        changeDirection(GABI_FRONT);
        wildeHildeMotor.setTolerance(0.1);
        setDriveTolerances(0.1);
        addDriveDistance(100);
        addShoot();
        addNextBall();
        addShoot();
        //addDriveDistance(3000);
        //addBlueBeacon();
    }

    @Override
    public void loop() {
        super.loop();
    }
}

