package org.firstinspires.ftc.teamcode;

import android.os.Environment;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by FTC2 on 26.01.2017.
 */

@TeleOp(name="Test: writeFile", group="Tests")
public class WriteFileTest extends OpMode {
    File directory;
    String fileName = "blubb.txt";

    int[] array1 ={1, 12};
    int [] array2 = {23, 24};
    int[][] array3 = {array1, array2};

    @Override
    public void init() {
        if(isExternalStorageWritable()){
            directory = getFilesStorageDir("tests");
            int[] array = {0, 11, 2, 12, 13};
            telemetry.addData("directory", directory.getAbsolutePath());
            try {
                write(fileName, directory, array);
            } catch(IOException e){
                e.printStackTrace();
            }

            try{
                write2DArray("array.csv", directory, array3);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void loop() {

    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public File getFilesStorageDir(String dirName) {
        // Get the directory for the files
        File file = new File(Environment.getExternalStorageDirectory(), dirName);
        if (!file.mkdirs()) {
            Log.e("Error", "Directory not created");
        }
        return file;
    }

    public void write(String fileName, File directory,  int[] array) throws IOException{
        File writeFile = new File(directory.getPath(), fileName);
        writeFile.createNewFile();
        BufferedWriter outputWriter = new BufferedWriter(new FileWriter(writeFile));
        for(int i = 0; i < array.length; i++){
            outputWriter.write(Integer.toString(array[i])+"\n");
        }
        outputWriter.write("Blubb");
        outputWriter.flush();
        outputWriter.close();

    }

    public void write2DArray(String fileName, File directory, int[][] array) throws IOException{
        File writeFile = new File(directory.getPath(), fileName);
        writeFile.createNewFile();
        BufferedWriter outputWriter = new BufferedWriter(new FileWriter(writeFile));
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++ ) {
                outputWriter.write(Integer.toString(array[i][j]) + ",");
            } outputWriter.write("\n");
        }
        outputWriter.write("Blubb");
        outputWriter.flush();
        outputWriter.close();
    }

}
//hallo mein name ist Janine ich rieche nach head and shoulders