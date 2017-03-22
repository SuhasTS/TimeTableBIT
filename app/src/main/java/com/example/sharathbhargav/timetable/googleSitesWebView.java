package com.example.sharathbhargav.timetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class googleSitesWebView extends AppCompatActivity implements View.OnClickListener {
    WebView webView;
    Button webviewBackButton,webviewForwardButton,webviewReloadButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sites_web_view);
        webView=(WebView) findViewById(R.id.googleSitesWebView);

        Intent i=getIntent();
        String site=i.getStringExtra("site");
     /*   webviewBackButton=(Button)findViewById(R.id.webviewBackButton);
        webviewForwardButton=(Button)findViewById(R.id.webviewForwardButton);
        webviewReloadButton=(Button)findViewById(R.id.webviewReloadButton);
     */

        webView.loadUrl("https://"+site);

        webView.setWebViewClient(new CustomWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

   /*     webviewReloadButton.setOnClickListener(this);
        webviewForwardButton.setOnClickListener(this);
        webviewBackButton.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View view) {
      /*  switch (view.getId())
        {
            case R.id.webviewBackButton:
                if(webView.canGoBack())
                    webView.goBack();
                break;
            case R.id.webviewForwardButton:
                if(webView.canGoForward())
                    webView.goForward();
                break;
            case R.id.webviewReloadButton:
                webView.reload();
        }*/
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
        else
            finish();
    }
}
