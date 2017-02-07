package org.firstinspires.ftc.teamcode.what.frog;

/**
 * Created by FTC2 on 07.02.2017.
 */
public class FrogAutonomousSimpler extends FrogAutonomous {
    public void driveToHeading(int heading){
        final int heading_final = heading;
        addAction(new FrogAction() {
            @Override
            public void action() {
                initDriveToHeading(heading_final);
                nextAction();
            }
        });
        addAction(new FrogAction() {
            @Override
            public void action() {
                driveToHeading();
                if(!drivingToHeading()){
                    stopDrive();
                    nextAction();
                }
            }
        });
    }

    public void driveDistance(int distance){
        final int distance_final = distance;
        addAction(new FrogAction() {
            @Override
            public void action() {
                resetDrive();
                initDriveDistance(distance_final);
                nextAction();
            }
        });

        addAction(new FrogAction() {
            @Override
            public void action() {
                driveDistance();
                if(!drivingToPosition()){
                    stopDrive();
                    nextAction();
                }
            }
        });
    }
}
