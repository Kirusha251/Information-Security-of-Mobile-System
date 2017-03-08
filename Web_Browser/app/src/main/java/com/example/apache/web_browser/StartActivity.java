package com.example.apache.web_browser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StartActivity extends AppCompatActivity {

    ListView listOfSite;
    ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        listOfSite = (ListView)findViewById(R.id.ListOfSites);
        final String[] siteNames  = new String[]{
                "My Web-Site", "Another Web-Site", "WebViewClient"
        };

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, siteNames);
        listOfSite.setAdapter(listAdapter);

        listOfSite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    String address = "file:///android_asset/mypage.html";
                    Intent intent = new Intent(StartActivity.this, WebActivity.class);
                    intent.putExtra("addr", address);
                    startActivity(intent);
                }
                if (position == 1){
                    String address = "<body>\n" +
                            "<h1><center><br><br>\n" +
                            "Second way!!!!\n" +
                            "</br></br></center>\n" +
                            "</h1>\n" +
                            "</body>\n";
                    Intent intent = new Intent(StartActivity.this, WebActivity.class);
                    intent.putExtra("address", address);
                    startActivity(intent);
                }
                if (position == 2){
                    String url = "http://javadevblog.com";
                    Intent intent = new Intent(StartActivity.this, WebActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);                }
            }
        });

    }

}
