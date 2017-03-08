package com.example.kirill.lab1_3_2;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.idTextView);
    }
    public void onReadButtonClick(View view){
        String data = "";
        String str = "";
        File file = new File(Environment.getExternalStorageDirectory(),"data.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((str = reader.readLine()) != null) {
                data += str;
            }
            textView.setText(decode(data));
        }catch (Exception e){
            Log.d("", "onReadButtonClick: " + e.getMessage());
        }

    }

    public String decode(String decode){
        SecretKeySpec sks = null;
        byte [] avg = null;
        try {
            sks = new SecretKeySpec(Base64.decode(readKeyFromFile(),Base64.DEFAULT), "AES");
            avg = Base64.decode(readKeyFromFile(),Base64.DEFAULT);
        } catch (Exception e) {
            Log.e("", "AES secret key spec error");
        }
        Log.d("", "decode: "+avg.toString());
        byte[] decodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, sks);
            avg = decode.getBytes();
            decodedBytes = c.doFinal(Base64.decode(decode,Base64.DEFAULT));
        } catch (Exception e) {
            Log.e("", "AES decryption error");
        }
        return new String(decodedBytes);
    }

    public String readKeyFromFile(){
        String str = "";
        String data = "";
        File file = new File(Environment.getExternalStorageDirectory(),"key.txt");

        try {
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while((str = reader.readLine())!= null){
                data += str;
            }
        }catch (Exception e){

        }
        return data;

    }
}
