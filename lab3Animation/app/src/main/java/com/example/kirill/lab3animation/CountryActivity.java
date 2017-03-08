package com.example.kirill.lab3animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountryActivity extends AppCompatActivity {
    Map<String,String[]> list;
    String currentCountry;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contry);
        list = new HashMap<>();
        list.put("Belgium",new String[]{"Bruges","Brussels","Gent","Antwerp"});
        list.put("Canada",new String[]{"Toronto","Montreal","Vancouver","Quebec"});
        list.put("Denmark",new String[]{"Calling","Copenhagen","Odense","Aarhus"});
        list.put("England",new String[]{"London","Manchester","Liverpool","Nottingham"});
        currentCountry = getIntent().getExtras().getString("country");
        listView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, list.get(currentCountry));
        listView.setAdapter(adapter);
    }
}
