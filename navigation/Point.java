package com.qualcomm.ftcrobotcontroller.opmodes.navigation;

/**
 * Created by FTCAdministrator on 04.03.2016.
 */
public class Point {
    int tilt;
    Color color;
    boolean driveOn; //can be driven on if true
    int height;
    Thing occupiedBy; // thing that is on this point
    short diagCross; //this short is needed to check when something crosses a diagonal
    LineThing lineOnPoint; //if there's another thing on same point
    int certain; //how certain is point overlap
    int routeCalc; //which

    //constructor for points, tilt=0, color=grey, driveOn=true, height=zero, occupiedby not given, lineonpoint not given, checkpoint=0
    public Point(){
        this.tilt = 0;
        color = Color.GREY;
        driveOn = true;
        this.height = 0;
        this.diagCross = 5;
    }

    //should I set these to private and require for a public function a ListOfPoints from which these are calculated
    //I actually need these to be public anyway because I'd put the other functions in the Map class
    //I think I have decided to put the set and delete classes into the Thing.class because that is how I planned it on the wall

    public void setToOccupied(Thing thing){
        this.height = thing.height;
        this.driveOn = false;
        this.occupiedBy = thing;
    }

    public void setColor(LineThing thing){
        this.color = thing.color;
    }

    public void setDiagonal(Thing thing, short diag){
        this.occupiedBy = thing;
        this.diagCross = diag;
    }

    public void toDefault(){
        this.tilt = 0;
        color = Color.GREY;
        driveOn = true;
        this.height = 0;
        this.occupiedBy = null;
        this.diagCross = 5;
    }

}
