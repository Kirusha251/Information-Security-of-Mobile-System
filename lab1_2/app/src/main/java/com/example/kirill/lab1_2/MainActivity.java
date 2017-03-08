package com.example.kirill.lab1_2;

import android.app.AlertDialog;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    private List<String> item = null;
    private List<String> path = null;
    private String root;
    private TextView myPath;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list);
        FileOutputStream fOut = null;
        boolean test;
        //Since you are creating a subdirectory, you need to make sure it's there first
        File directory = new File(Environment.getExternalStorageDirectory(), "AutoWriter");
        if (!directory.exists()) {
            test = directory.mkdir();
            Log.d("q", "onCreate: " + test);
        }

        try {
            //Create the stream pointing at the file location
            fOut = new FileOutputStream(new File(directory, "samplefile.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String nochartOutput = "My sample file";

        OutputStreamWriter osw = new OutputStreamWriter(fOut);
        try {
            osw.write(encode(nochartOutput));

            osw.flush();
            osw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        ////
        myPath = (TextView)findViewById(R.id.path);

        root = Environment.getExternalStorageDirectory().getPath();

        getDir(root);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                File file = new File(path.get(i));
                String fileData="";
                if (file.isDirectory())
                {
                    if(file.canRead()){
                        getDir(path.get(i));
                    }else{
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("[" + file.getName() + "] folder can't be read!")
                                .setPositiveButton("OK", null).show();
                    }
                }else {
                    try{
                        String s  = file.getPath();
                       // InputStream inputStream = openFileInput(file.getPath());
                        Log.d("", "onItemClick: " + s );
                        //if(inputStream!=null){
                            //InputStreamReader tmp = new InputStreamReader(inputStream);
                            BufferedReader reader = new BufferedReader(new FileReader(file));
                            String str="";
                            while((str = reader.readLine())!= null){
                               fileData += str;
                            }
                            //inputStream.close();
                        //}
                    }
                    catch (Exception e){

                    }
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Content:"+fileData)
                            .setPositiveButton("OK", null).show();

                }

            }
        });
    }

    private void getDir(String dirPath)
    {
        myPath.setText("Location: " + dirPath);
        item = new ArrayList<String>();
        path = new ArrayList<String>();
        File f = new File(dirPath);
        File[] files = f.listFiles();

        if(!dirPath.equals(root))
        {
            item.add(root);
            path.add(root);
            item.add("../");
            path.add(f.getParent());
        }

        for(int i=0; i < files.length; i++)
        {
            File file = files[i];

            if(!file.isHidden() && file.canRead()){
                path.add(file.getPath());
                if(file.isDirectory()){
                    item.add(file.getName() + "/");
                }else{
                    item.add(file.getName());
                }
            }
        }

        ArrayAdapter<String> fileList =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, item);
        listView.setAdapter(fileList);
        listView.deferNotifyDataSetChanged();
    }

    public String encode(String encodeString){
        SecretKeySpec sks = null;
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("any data used as random seed".getBytes());
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128, sr);
            sks = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
        } catch (Exception e) {
            Log.e("", "AES secret key spec error");
        }

        byte[] encodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, sks);
            encodedBytes = c.doFinal(encodeString.getBytes());
        } catch (Exception e) {
            Log.e("", "AES encryption error");
        }
                return Base64.encodeToString(encodedBytes, Base64.DEFAULT);

    }

}
