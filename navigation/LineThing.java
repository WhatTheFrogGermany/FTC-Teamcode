package com.qualcomm.ftcrobotcontroller.opmodes.navigation;

/**
 * Created by FTCAdministrator on 09.03.2016.
 * Wuff
 */
public class LineThing extends Thing{
    Color color;
    Coordinate a;
    Coordinate b;

    public LineThing(int ax, int ay, int bx, int by, Color color){
        super(0, 0, 0, new Coordinate(0, 0), 0);
        a = new Coordinate(ax, ay);
        b = new Coordinate(bx, by);
        this.color = color;
    }

    @Override
    public void set(Map map){
        int i;
        for(i = 0; i < sizeOfList && ListOfPoints[i] != null; i++){
            map.coSystem[ListOfPoints[i].x][ListOfPoints[i].y].setToOccupied(this);
        }
    }
}
