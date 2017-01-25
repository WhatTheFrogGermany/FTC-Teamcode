package org.firstinspires.ftc.teamcode.navigation;

/**
 * Created by FTCAdministrator on 11.03.2016.
 */
public class Route {
    final static int maxTurnPoints = 100;
    Coordinate[] turningPoints = new Coordinate[maxTurnPoints];
    Thing forWhat;
    Map onWhat;
    int bufferLength;
    int count; //needed for calculating the turning points
    //Needs to calculate how to drive as well
    //how do I store this information?

    Coordinate startPoint;
    int startAngle;
    Coordinate endPoint;
    int endAngle;
    int drivingDistance[]; //an array of the distance that has to be driven in cm
    double turningAngle[]; //an array of the degrees one must turn in order drive the next line

    public Route(Thing forWhat, Map onWhat, Coordinate startPoint, Coordinate endPoint) {
        this.forWhat = forWhat;
        this.onWhat = onWhat;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }


    private void calcStartEnd(Coordinate start, Coordinate end) {
        //I seriously need to look over it and find a more efficient algorithm
        //I bet there is a much simpler way of doing this
        //Well the idea is, that if we find a obstacle in our way, we will calculate a new point
        //to which we will calculate a route using the same function
        //So this is basically recursive, but I have not figured out how to do it yet
        //I could also use a stack or something of the like, but I need it to be efficient as well
        //On the other hand it should not really matter, as the calculations do no

        //IMPORTANT!!!
        //NEEDS FIXING
        //Another problem is, that right now I am only calculating a thin line and not the full length of the robot
        //This I should be able to fix after some thinking
        //But still I need to make sure that I do not forget this

        final int maxCount = 100;
        boolean finished = false;
        if (maxCount == count) {
            count = 0;
            return;

        }
        int yToX = Math.round(end.y - start.y) / (end.x - start.x);

        int i;
        int j;
        int k;
        int l;

        Coordinate nearestCorner = new Coordinate(0, 0);

        turningPoints[count] = start;
        for (i = 0; i < end.x - start.x; i++) {
            for (j = 0; j < yToX; j++) {
                if (onWhat.coSystem[i + start.x][yToX * i + j + start.y].driveOn != true) {
                    for(k = i; k < end.x - start.x; k++){
                        for(l = j; l < yToX; l++){
                            if(onWhat.coSystem[k][yToX * k + l].diagCross != 5){
                                nearestCorner = forWhat.corners[onWhat.coSystem[k][yToX * k + l].diagCross];
                                break;
                            }
                        }
                        if(k == end.x - start.x - 1){
                            //here the function needs to return somehow, but I have not figured out how yet
                        }
                    }
                    Coordinate point = new Coordinate(i, yToX * i + j);
                    //this is to calculate a new point that is far enough from the nearest corner
                    //for this we still need to set the diagonals in Thing.class to calculate which one we will cross
                    //these diagonals should be a bit thicker than the ordinary LineThing, in order to make sure we will cross it.
                    point = calcNewPoint(start, nearestCorner, end);
                    calcStartEnd(start, point);
                    count++;
                    calcStartEnd(point, end);
                    finished = true;
                    break;
                }
                if (i == end.x && yToX * i + j == end.y) {
                    return;
                }
            }
        }
    }

    private Coordinate calcNewPoint(Coordinate start, Coordinate middle, Coordinate end) {
        //calculate vectors, the one between start and middle and the one between end and middle
        Coordinate vector = new Coordinate(0, 0);
        vector.x = middle.x - start.x;
        vector.y = middle.y - start.y;

        Coordinate vector2 = new Coordinate(0, 0);
        vector2.x = end.x - middle.x;
        vector2.y = end.y - middle.y;

        //calc angle between vectors, calc scalar product, calc length of vectors, use formula
        //this formula needs to be altered a bit, to make sure that we move the point to the right direction;
        int scalar = vector.x * vector2.x + vector.y * vector2.y;

        double lengthV1 = Math.abs(Math.sqrt(Math.pow(vector.x, 2) + Math.pow(vector.y, 2)));
        double lengthV2 = Math.abs(Math.sqrt(Math.pow(vector2.x, 2) + Math.pow(vector2.y, 2)));

        double angle = Math.acos(scalar / (lengthV1 * lengthV2));

        double angleStartMiddle = Math.atan(vector.x / vector.y);

        //still need to make sure, that this works for every combination of vectors
        double angleNewVector = -1 * Math.abs(180 - angleStartMiddle - (360 - Math.toDegrees(angle)));

        Coordinate returnVector = new Coordinate(0, 0);

        returnVector.x = (int) Math.round(Math.cos(angleNewVector) * bufferLength);
        returnVector.y = (int) Math.round(Math.sin(angleNewVector) * bufferLength);

        returnVector.x += vector.x;
        returnVector.y += vector.y;

        return returnVector;
    }

    public void calculateActualRoute() {
        //this calculates the driving distance and the angles corresponding to the calculated turningPoints
        int i = 0;
        Coordinate vector = new Coordinate(turningPoints[i + 1].x - turningPoints[i].x, turningPoints[i + 1].y - turningPoints[i].y);
        drivingDistance[i] = (int) Math.round(Math.sqrt((vector.x * 5) ^ 2 + (vector.y * 5) ^ 2));
        turningAngle[i] = (int) Math.round(Math.atan(vector.y / vector.x) - startAngle);

        for (i = 1; i < maxTurnPoints && turningPoints[i] != null; i++) {
            vector = new Coordinate(turningPoints[i + 1].x - turningPoints[i].x, turningPoints[i + 1].y - turningPoints[i].y);
            drivingDistance[i] = (int) Math.round(Math.sqrt((vector.x * 5) ^ 2 + (vector.y * 5) ^ 2));
            turningAngle[i] = (int) Math.round(Math.atan(vector.y / vector.x) - turningAngle[i - 1]);
        }

        turningAngle[i] = endAngle - turningAngle[i - 1];
    }
}
//Im just typing for the sake of typing. Because I am supposed to for the pictures and that's what I am doing, after all.
// Iambored to hell, bored to hell bored to heel, this will later have to be deleted. 