package com.dreamyDestination.yash;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class TravelTipWebViewUrl extends AppCompatActivity {
    private WebView webView = null;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_tip_web_view_url);
        mContext = this;
        Bundle extras = getIntent().getExtras();
        final String url= extras.getString("URL");

        this.webView = (WebView) findViewById(R.id.activity_main_webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        WebViewClientImpl webViewClient = new WebViewClientImpl(TravelTipWebViewUrl.this);
        webView.setWebViewClient(webViewClient);
        webView.loadUrl(url);
    }
}
