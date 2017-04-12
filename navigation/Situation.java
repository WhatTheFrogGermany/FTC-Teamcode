package org.firstinspires.ftc.teamcode.navigation;

/**
 * Created by FTCAdministrator on 18.03.2016.
 */
public class Situation {
    Coordinate toBe;
    int angle;
    int points;
    int estTime; //this is in seconds and supposed to be helpful in planning
    String toExecute;

    //I probably need a second layer in order to be able to describe the actions
    public Situation(Coordinate toBe, int angle, int points, int estTime, String toExecute){
        this.toBe = toBe;
        this.angle = angle;
        this.points = points;
        this.estTime = estTime;
        this.toExecute = toExecute;
    }


    //public abstract void action();
}
