package com.qualcomm.ftcrobotcontroller.opmodes.navigation;

/**
 * Created by FTCAdministrator on 04.03.2016.
 */
public class Thing {
    final int sizeOfList = 500;
    int width;
    int depth;
    int height;
    Coordinate position;
    Coordinate movementVector;
    int angle;
    Coordinate[] ListOfPoints = new Coordinate[sizeOfList]; //the variable will have to vary depending on the subclass
    Coordinate frontRight;
    Coordinate frontLeft;
    Coordinate backRight;
    Coordinate backLeft;
    Coordinate corners[]; //this is important for being used with the diagonals
    final int sizeOfDiagonal = 100;
    Coordinate[][] Diagonals = new Coordinate[4][sizeOfDiagonal];
    String status; //status that can be displayed by using telemetry.addData in the main OpMode

    //List of Functions
    //calcPositionAngle(Coordinate position, int angle)
    //calcOutline(Coordinate position, int angle)
    //calcBetweenPoints(Coordinate a, Coordinate b)
    //moveStraight(Coordinate vector);
    //set(Map map)

    public Thing(int width, int depth, int height, Coordinate position, int angle){
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.position = position;
        this.angle = angle;
        calcPositionAngle(this.position, this.angle);
        calculateDiagonals();
    }

    protected void calcPositionAngle(Coordinate position, int angle){
        //This function will set ListOfPoints by using a given position and an angle
        this.angle = angle;
        this.position = position;
        Coordinate[] outline = calcOutline(position, angle);
        corners = outline;
        this.backLeft = outline[0];
        this.backRight = outline[1];
        this.frontRight = outline[2];
        this.frontLeft = outline[3];

        //need to floor the coordinates that were calculated above
        //???Do I calculate two points? One floored and one + 1?
        //calcBetweenPoints() four times
    }

    protected Coordinate[] calcOutline(Coordinate position, int angle){
        Coordinate[] toReturn = new Coordinate[4];
        double x = Math.sqrt((double)depth)/(4 * (Math.tan(angle) + 4));  //calculate x to add to position.x
        //this formula is derived from pythagoras and tangens
        double y = Math.tan(angle) * x;                                    //calculate y to add to position.y
        //in principle it is a linear function

        double outlineFrontX = position.x + x;
        double outlineFrontY = position.y + y;

        double outlineBackX = position.x - x;
        double outlineBackY = position.y - y;

        x = Math.sqrt((double)width / (4 * (Math.tan(angle - 90) + 4)));   //basically the same formula as above just using a different angle
        y = Math.tan(angle - 90);                                          //the angle is the angle minus 90 degrees because it needs to be orthogonal

        //set the coordinates of the corners
        //still in double, needs to be floored to int later
        double outlineBackRightX = outlineBackX + x;
        double outlineBackRightY = outlineBackY + y;

        toReturn[1].setXY((int)Math.floor(outlineBackRightX), (int)Math.floor(outlineBackRightY));

        double outlineBackLeftX = outlineBackX - x;
        double outlineBackLeftY = outlineBackY - y;

        toReturn[0].setXY((int)Math.floor(outlineBackLeftX), (int)Math.floor(outlineBackLeftY));

        double outlineFrontRightX = outlineFrontX + x;
        double outlineFrontRightY = outlineFrontY + y;

        toReturn[2].setXY((int)Math.floor(outlineFrontRightX), (int)Math.floor(outlineFrontRightY));

        double outlineFrontLeftX = outlineFrontX - x;
        double outlineFrontLeftY = outlineFrontY - y;

        toReturn[3].setXY((int)Math.floor(outlineFrontLeftX), (int)Math.floor(outlineFrontLeftY));

        return toReturn;
    }

    protected Coordinate[] calcBetweenPoints(Coordinate a, Coordinate b){
        //I dislike this part of the code because it wastes memory by using an array with a set size
        //it could probably be changed by using lists that are then converted into array
        Coordinate[] line = new Coordinate[100];

        //calculate the points that belong to one x value
        double yToX = (b.y - a.y) / (b.x - a.x);

        int i;
        int j;
        int count = 0;
        for(i = 0; i < b.x - a.x; i ++){
            for(j = 0; j < yToX; j++){
                line[count].x = i + a.x;
                line[count].y = (int)Math.floor(yToX * i  + j + a.y) + 1;
                count++;
            }
        }

        //FINISHED!!!
        //starting at a calculate points using the vector until b is reached
        //I will finish this sometime later when I have given it some thought and already have the algorithm planned out
        //???How will I know how to calculate the y coordinates that belong to one x?
        //I will need to use calculations based on the proportion of y to x or something
        //the algorithm needs to be time efficient as well
        //maybe there is a simpler mathematical formula which would reduce the time needed
        return line;
    }

    protected void calculateDiagonals(){
        Diagonals[0] = calcBetweenPoints(backLeft, position);
        Diagonals[1] = calcBetweenPoints(backRight, position);
        Diagonals[2] = calcBetweenPoints(frontRight, position);
        Diagonals[3] = calcBetweenPoints(frontLeft, position);
    }
    public void moveStraight(Coordinate vector){
        int i;
        for(i = 0; i < sizeOfList && ListOfPoints[i] != null; i++){
            ListOfPoints[i].x += vector.x;
            ListOfPoints[i].y += vector.y;
        }
        //we have to consider the diagonals and the outline and the corner as well
    }

    //I´m still unsure whether I should put the set method in the Thing.class or in the Map.class
    //I am sure I could just copy it into other classes if I would like to
    public void set(Map map){
        int i;
        int j;
        for(i = 0; i < sizeOfList && ListOfPoints[i] != null; i++){
            map.coSystem[ListOfPoints[i].x][ListOfPoints[i].y].setToOccupied(this);
        }
        for(i = 0; i < 4; i++){
            for(j = 0; j < sizeOfDiagonal && Diagonals[i][j] != null; i++){
                map.coSystem[Diagonals[i][j].x][Diagonals[i][j].y].setDiagonal(this, (short)i);
            }
        }
    }

    public void free(Map map){
        int i;
        int j;
        for(i = 0; i < sizeOfList && ListOfPoints[i] != null; i++){
            map.coSystem[ListOfPoints[i].x][ListOfPoints[i].y].toDefault();
        }
        for(i = 0; i < 4; i++){
            for(j = 0; j < sizeOfDiagonal && Diagonals[i][j] != null; i++){
                map.coSystem[Diagonals[i][j].x][Diagonals[i][j].y].toDefault();
            }
        }
    }

}
