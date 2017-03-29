package com.example.sharathbhargav.timetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class googleSitesWebView extends AppCompatActivity implements View.OnClickListener {
    WebView webView;
    Button webviewBackButton,webviewForwardButton,webviewReloadButton;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sites_web_view);
        webView=(WebView) findViewById(R.id.googleSitesWebView);

        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://bitcse-90b07.firebaseio.com/");
        Intent i=getIntent();
        //String site=i.getStringExtra("site");
     /*   webviewBackButton=(Button)findViewById(R.id.webviewBackButton);
        webviewForwardButton=(Button)findViewById(R.id.webviewForwardButton);
        webviewReloadButton=(Button)findViewById(R.id.webviewReloadButton);
     */
        String fid=i.getStringExtra("fid");
        databaseReference.child("facultyLinks").child(fid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String site=dataSnapshot.getValue(String.class);
                webView.loadUrl(site);

                webView.setWebViewClient(new CustomWebViewClient());
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setLoadWithOverviewMode(true);
                webView.getSettings().setUseWideViewPort(true);
                webView.getSettings().setSupportZoom(true);

                webView.getSettings().setBuiltInZoomControls(true);
                webView.getSettings().setDisplayZoomControls(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
