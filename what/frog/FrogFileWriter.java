package org.firstinspires.ftc.teamcode.what.frog;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by FTC2 on 26.01.2017.
 */
public class FrogFileWriter {
    File directory;
    File file;
    String fileName;

    public FrogFileWriter(String name){
        if(isExternalStorageWritable()) {
            directory = getFilesStorageDir("FrogTests");
            file = new File(directory.getPath(), name);
            fileName = name;
        }
    }
    /* Checks if external storage is available for read and write */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private File getFilesStorageDir(String dirName) {
        // Get the directory for the files
        File file = new File(Environment.getExternalStorageDirectory(), dirName);
        if (!file.mkdirs()) {
            Log.e("Error", "Directory not created");
        }
        return file;
    }

    public void writeArray(int[] array) throws IOException{
        file.createNewFile();
        BufferedWriter outputWriter = new BufferedWriter(new FileWriter(file));
        for(int i = 0; i < array.length; i++){
            outputWriter.write(Integer.toString(array[i])+"\n");
        }
        outputWriter.flush();
        outputWriter.close();

    }
    public void write2DArray( int[][] array) throws IOException {
        file.createNewFile();
        BufferedWriter outputWriter = new BufferedWriter(new FileWriter(file));
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++ ) {
                outputWriter.write(Integer.toString(array[i][j]) + ",");
            } outputWriter.write("\n");
        }
        outputWriter.flush();
        outputWriter.close();
    }

}
