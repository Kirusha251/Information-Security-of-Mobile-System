package com.example.apache.web_browser;

import android.app.Activity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebClientActivity extends WebViewClient {

    private Activity activity = null;

    public WebClientActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        return false;
    }
}
