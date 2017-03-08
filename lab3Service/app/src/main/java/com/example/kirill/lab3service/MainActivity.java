package com.example.kirill.lab3service;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonStart, buttonStop;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonStop);
        mTextView = (TextView) findViewById(R.id.textView);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);


    }

    public void onClick(View src) {
        switch (src.getId()) {
            case R.id.buttonStart:
                startService(new Intent(this, MyService.class));
                break;
            case R.id.buttonStop:{
                stopService(new Intent(this, MyService.class));
                readFromFile();
            }

                break;
        }
    }

    public void readFromFile(){
        try{
            FileInputStream inputStream = new FileInputStream (new File(Environment.getExternalStorageDirectory()+"/service.txt"));

            if(inputStream!=null){
                InputStreamReader tmp = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(tmp);
                String str="";
                StringBuilder buf = new StringBuilder();

                while((str = reader.readLine())!=null){
                    buf.append(str+"\n");
                }
                inputStream.close();
                mTextView.setText("Text from file: "+buf.toString());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

}
