package com.example.kirill.lab3animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView ;
    private ImageView wv1;
    Animation anim;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv1=(ImageView)findViewById(R.id.imageView1);

        listView = (ListView) findViewById(R.id.list);
        anim = AnimationUtils.loadAnimation(this, R.anim.rotate);


        final String[] valuesq = new String[] {"Belgium",
                "Canada",
                "Denmark",
                "England"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, valuesq);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                int itemPosition     = arg2;

                String  itemValue    = (String) listView.getItemAtPosition(arg2);
                switch(itemPosition)
                {
                    case 0:
                        itemValue="image1";
                        wv1.setImageResource(R.drawable.image1);
                        wv1.startAnimation(anim);
                        intent = new Intent(getApplicationContext(),CountryActivity.class);
                        intent.putExtra("country",valuesq[itemPosition]);
                        startActivity(intent);
                        break;

                    case 1:
                        itemValue="image2";
                        wv1.setImageResource(R.drawable.image2);
                        wv1.startAnimation(anim);
                        intent = new Intent(getApplicationContext(),CountryActivity.class);
                        intent.putExtra("country",valuesq[itemPosition]);
                        startActivity(intent);
                        break;

                    case 2:
                        itemValue="image3";
                        wv1.setImageResource(R.drawable.image3);
                        wv1.startAnimation(anim);
                        intent = new Intent(getApplicationContext(),CountryActivity.class);
                        intent.putExtra("country",valuesq[itemPosition]);
                        startActivity(intent);
                        break;
                    case 3:
                        itemValue="image4";
                        wv1.setImageResource(R.drawable.image4);
                        wv1.startAnimation(anim);
                        intent = new Intent(getApplicationContext(),CountryActivity.class);
                        intent.putExtra("country",valuesq[itemPosition]);
                        startActivity(intent);
                        break;

                }
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();
            }

        });

    }
}
