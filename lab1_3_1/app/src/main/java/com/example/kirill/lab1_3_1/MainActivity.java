package com.example.kirill.lab1_3_1;

import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    byte [] test;
    String flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.idEditText);
        textView = (TextView)findViewById(R.id.idTextView);
    }

    public void onWriteButtonClick(View view){
        File file  = new File(Environment.getExternalStorageDirectory(),"data.txt");
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fOut = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "data.txt"));
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            try {
                osw.write(encode(editText.getText().toString()));//TODO:encode
                osw.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }catch (IOException ex){

        }

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

        }

    }

    public String encode(String encode){
        SecretKeySpec sks = null;
        byte [] key;
        String str="";
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("some data".getBytes());
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128, sr);
            key = kg.generateKey().getEncoded();
            writeKeyToFile(Base64.encodeToString(key,Base64.DEFAULT));
            sks = new SecretKeySpec(key, "AES");
        } catch (Exception e) {
            Log.e("", "AES secret key spec error");
        }

        byte[] encodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, sks);
            encodedBytes = c.doFinal(encode.getBytes());
            str = Base64.encodeToString(encodedBytes,Base64.DEFAULT);
            test = Base64.decode(str,Base64.DEFAULT);
        } catch (Exception e) {
            Log.e("", "AES encryption error");
        }
        return str;

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
            Log.d("", "1:"+test+"//"+"2:"+avg);
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

    public void writeKeyToFile(String key){
        try {

            FileOutputStream fOut = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "key.txt"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fOut);
            try {
                outputStreamWriter.write(key);
                outputStreamWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }catch (Exception ex){

    }
    }

}
