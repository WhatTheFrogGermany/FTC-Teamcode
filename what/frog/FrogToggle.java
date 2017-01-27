package org.firstinspires.ftc.teamcode.what.frog;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by FTC2 on 27.01.2017.
 */
//This is a time based toggle
public class FrogToggle {
    ElapsedTime time;
    int interval;
    boolean state;

    public FrogToggle(int interval){
        time = new ElapsedTime();
        this.interval = interval;
    }

    public boolean getState(){
        return state;
    }

    public void toggle(boolean toggleButton){
        if(toggleButton && time.milliseconds() > interval){
            state = !state;
            time.reset();
        }
    }
}
