package com.qualcomm.ftcrobotcontroller.opmodes.navigation;

/**
 * Created by FTCAdministrator on 18.03.2016.
 */
public class SituationsRegistry {
    final static int MAX_SITUATIONS = 30;
    Situation situations[];

    public SituationsRegistry(){
        situations[0] = new Situation(new Coordinate(0,0),0,0,0,"nothing");
    }
}
