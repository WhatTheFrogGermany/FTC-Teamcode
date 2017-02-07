package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.what.frog.FrogAction;
import org.firstinspires.ftc.teamcode.what.frog.FrogAutonomous;

/**
 * Created by FTC2 on 07.02.2017.
 */
@Autonomous(name="Test1", group="Tests")
public class AutonomousTest1 extends FrogAutonomous {
    @Override
    public void init() {
        super.init();
        changeDirection(GABI_FRONT);
        addAction(new FrogAction() {
            @Override
            public void action() {
                resetDrive();
                initDriveDistance(3000);
                nextAction();
            }
        });

        addAction(new FrogAction() {
            @Override
            public void action() {
                driveDistance();
                if(!drivingToPosition()){
                    nextAction();
                }
            }
        });
    }

    @Override
    public void loop() {
        super.loop();
    }
}
