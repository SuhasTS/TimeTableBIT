package com.example.sharathbhargav.timetable;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewManual extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_manual);

        webView=(WebView) findViewById(R.id.webViewManual);


      // webView.loadUrl("https://drive.google.com/open?id=0B1mpkvwZCxwNdVdTUVo5OThoc0E");
        String pdf = "http://www.pdf995.com/samples/pdf.pdf";
        webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);
        webView.setWebViewClient(new CustomWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
        else
            finish();
    }
}
