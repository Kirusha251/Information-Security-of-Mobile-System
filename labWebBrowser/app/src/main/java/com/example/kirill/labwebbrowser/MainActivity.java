package com.example.kirill.labwebbrowser;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.modtext);
        Button chf=(Button) findViewById(R.id.change);
        chf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                // tv.setText("Hie, You, old Nail");
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://google.ru")));

            }
        });



    }
}
