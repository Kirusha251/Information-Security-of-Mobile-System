package com.example.kirill.lab1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private final static String NOTES="notes.txt";
    private EditText editor;
    public Button saveButton;
    public Button readButton;
    public Button closeButton;
    public TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editor = (EditText)findViewById(R.id.idEditText);
        saveButton = (Button)findViewById(R.id.idSaveToFile);
        readButton = (Button)findViewById(R.id.idReadFromFile);
        closeButton = (Button)findViewById(R.id.idClose);
        textView = (TextView)findViewById(R.id.idTextView);
    }


    public void onSaveButtonClick(View view){
        try {
            FileOutputStream mOutput = openFileOutput(NOTES, Activity.MODE_APPEND);
            String data = editor.getText().toString()+"\n";
            mOutput.write(data.getBytes());
            mOutput.close();
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException ea) {

            ea.printStackTrace();
            Toast.makeText(this,"Error:"+ea.getMessage(),Toast.LENGTH_SHORT).show();
        } catch (IOException ee) {
            ee.printStackTrace();
            Toast.makeText(this,"Error:"+ ee.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    public void onReadButtonClick(View view){
        try{
            InputStream inputStream = openFileInput(NOTES);

            if(inputStream!=null){
                InputStreamReader tmp = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(tmp);
                String str="";
                StringBuilder buf = new StringBuilder();

                while((str = reader.readLine())!=null){
                    buf.append(str+"\n");
                }
                inputStream.close();
                textView.setText(buf.toString());
            }
        }
        catch (Exception e){

        }

    }
    public void onTranslateButtonClick(View view){
        try{
            InputStream inputStream = openFileInput(NOTES);

            if(inputStream!=null){
                InputStreamReader tmp = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(tmp);
                String str="";
                while((str = reader.readLine())!= null){
                    if(str.substring(0,str.indexOf(':')).equals(editor.getText().toString())) {
                        textView.setText(str);
                        break;
                    }else{
                        textView.setText("not found");
                    }
                }
                inputStream.close();
            }
        }
        catch (Exception e){

        }
    }
    public void onCloseButtonClick(View view){
        finish();
    }

}
