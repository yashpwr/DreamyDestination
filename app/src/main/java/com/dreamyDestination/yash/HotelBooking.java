package com.dreamyDestination.yash;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class HotelBooking extends AppCompatActivity {

    private Context mContext;
    private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_booking);

        mContext = this;

        this.webView = (WebView) findViewById(R.id.activity_main_webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        WebViewClientImpl webViewClient = new WebViewClientImpl(HotelBooking.this);
        webView.setWebViewClient(webViewClient);

        webView.loadUrl("https://www.makemytrip.com");
    }
}
