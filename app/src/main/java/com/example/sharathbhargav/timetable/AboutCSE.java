package com.example.sharathbhargav.timetable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class AboutCSE extends AppCompatActivity {

    WebView aboutCSE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_cse);
        aboutCSE=(WebView) findViewById(R.id.webViewAboutCSE);
        aboutCSE.loadUrl("file:///android_asset/test.html");
        aboutCSE.setWebViewClient(new CustomWebViewClient());
        aboutCSE.getSettings().setJavaScriptEnabled(true);
        aboutCSE.getSettings().setLoadWithOverviewMode(true);
        aboutCSE.getSettings().setUseWideViewPort(false);

    }
    @Override
    public void onBackPressed() {
        if(aboutCSE.canGoBack())
            aboutCSE.goBack();
        else
            finish();
    }
}
