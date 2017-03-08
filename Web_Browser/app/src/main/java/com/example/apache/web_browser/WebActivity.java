package com.example.apache.web_browser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {


    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        web = (WebView)findViewById(R.id.web);
        Intent intent = getIntent();
        String addr = intent.getExtras().getString("addr");
        String address = intent.getExtras().getString("address");
        String url = intent.getExtras().getString("url");
        web.loadData(address, "text/html", "en_US");
        web.loadUrl(addr);
        web.loadUrl(url);
    }
}
