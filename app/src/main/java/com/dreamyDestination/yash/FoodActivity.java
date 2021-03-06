package com.dreamyDestination.yash;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class FoodActivity extends AppCompatActivity {

    private WebView webView = null;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        mContext = this;

        this.webView = (WebView) findViewById(R.id.activity_main_webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        WebViewClientImpl webViewClient = new WebViewClientImpl(FoodActivity.this);
        webView.setWebViewClient(webViewClient);

        webView.loadUrl("https://www.swiggy.com/");
    }
}
