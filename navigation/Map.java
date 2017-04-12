package org.firstinspires.ftc.teamcode.navigation;

/**
 * Created by FTCAdministrator on 09.03.2016.
 */
public class Map {
    Point[][] coSystem = new Point[100][100];

    public Point pointForCoordinate(Coordinate coord){
        return coSystem[coord.x][coord.y];
    }
}
