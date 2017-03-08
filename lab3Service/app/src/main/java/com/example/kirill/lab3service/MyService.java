package com.example.kirill.lab3service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyService extends Service {

    MediaPlayer myPlayer;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        writeToFile("Service Created");
        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();

        myPlayer = MediaPlayer.create(this,R.raw.music);

        myPlayer.setLooping(false); // Set looping
    }
    @Override
    public void onStart(Intent intent, int startid) {
        writeToFile("Service Started");
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        myPlayer.start();
    }
    @Override
    public void onDestroy() {
        writeToFile("Service Stopped");
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
        myPlayer.stop();
    }

    public void writeToFile(String message){
        String date = new SimpleDateFormat("dd-MM-yyyy-hh:mm:ss").format(new Date());
        File path = Environment.getExternalStorageDirectory();
        File file  = new File(path,"service.txt");
        try{
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            OutputStreamWriter outputStream = new OutputStreamWriter(fileOutputStream);
            outputStream.append("Time: "+date+", message: "+message);
            outputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();

        }catch (IOException e){

        }
    }

}
