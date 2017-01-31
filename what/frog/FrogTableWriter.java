package org.firstinspires.ftc.teamcode.what.frog;

import java.util.ArrayList;

/**
 * Created by FTC2 on 31.01.2017.
 */
public class FrogTableWriter extends FrogFileWriter {
    ArrayList<int[]> rows;
    int[] columns;
    int currentCell = 0;

    public FrogTableWriter(String name, int columnNumber){
        super(name);
        columns = new int[columnNumber];
    }

    public void setSpecificValue(int column, int value){
        columns[column] = value;
    }

    public void setNextValue(int value){
        columns[currentCell] = value;
        currentCell++;
    }

    public void addColumn(){
        rows.add(columns);
        currentCell = 0;
    }

    public int getRowNumber(){
        return rows.size();
    }

    public void writeAll(){
        int[][] array = new int[rows.size()][columns.length];
        array = rows.toArray(array);
        write2DArray(array);
    }
}
