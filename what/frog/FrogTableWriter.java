package org.firstinspires.ftc.teamcode.what.frog;

import java.util.ArrayList;

/**
 * Created by FTC2 on 31.01.2017.
 */
public class FrogTableWriter extends FrogFileWriter {
    ArrayList<int[]> rows;
    int[] columns;
    int currentCell = 1;
    int columnNumber;

    public FrogTableWriter(String name, int columnNumber){
        super(name);
        rows = new ArrayList<int[]>();
        columns = new int[columnNumber];
        this.columnNumber = columnNumber;
    }

    public void setSpecificValue(int column, int value){
        columns[column] = value;
    }

    public void setNextValue(int value){
        columns[currentCell] = value;
        currentCell++;
    }

    public void resetCurrentCell(){
        currentCell = 1;
    }
    public void addColumn(){
        columns[0] = rows.size();
        rows.add(columns);
        columns = new int[columnNumber];
        currentCell = 1;
    }

    public int getRowNumber(){
        return rows.size();
    }

    public void writeAll(){
        int[][] array = new int[rows.size()][columnNumber];
        array = rows.toArray(array);
        write2DArray(array);
    }
}
