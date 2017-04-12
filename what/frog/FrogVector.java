package org.firstinspires.ftc.teamcode.what.frog;

/**
 * Created by FTC2 on 31.03.2017.
 */
public class FrogVector {
    double x;
    double y;
    double angle;
    double length;

    public FrogVector(double x, double y){
        this.x = x;
        this.y = y;
        xyToPolar();
    }

    public double getX(){return x;}
    public double getY(){return y;}
    public double getAngle(){return angle;}
    public double getLength(){return length;}

    public void setXY(double x, double y){
        this.x = x;
        this.y = y;
        xyToPolar();
    }

    public void setPolar(double angle, double length){
        this.angle = angle;
        this.length = length;
        polarToXy();
    }

    public void setLength(double length){
        this.length = length;
        polarToXy();
    }

    private void xyToPolar(){
        double length;
        double degrees;
        length = Math.sqrt(x*x + y*y);
        degrees = Math.toDegrees(Math.asin(Math.abs(y)/length));
        if(y < 0) {
            degrees += 180;
            FrogMath.degreesInCircle((int)degrees);
        }
        this.angle = degrees;
        this.length = length;
    }

    private void polarToXy(){
        x = Math.cos(Math.toRadians(angle)) * length;
        y = Math.sin(Math.toRadians(angle)) * length;
    }

    public void addVector(FrogVector secondVector){
        this.x = this.x + secondVector.getX();
        this.y = this.y + secondVector.getY();
        xyToPolar();
    }

    public void subtractVector(FrogVector secondVector){
        this.x = this.x - secondVector.getX();
        this.y = this.y - secondVector.getY();
        xyToPolar();
    }
}
