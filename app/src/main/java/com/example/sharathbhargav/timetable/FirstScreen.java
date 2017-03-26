package com.example.sharathbhargav.timetable;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;



import java.util.ArrayList;

public class FirstScreen extends AppCompatActivity {
    LiquidButton splashToAboutCSE,splashToSyllabus;
    LiquidButton splashhToTT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);




        splashhToTT=(LiquidButton)findViewById(R.id.splashToTimeTable);
    splashhToTT.setButtonText("TimeTable");
        splashhToTT.setFillAfter(true);
        splashhToTT.setAutoPlay(true);
        splashhToTT.startPour();
        splashToSyllabus=(LiquidButton) findViewById(R.id.splashToSyllabus);

        splashToSyllabus.setButtonText("Syllabus");
        splashToSyllabus.setFillAfter(true);
        splashToSyllabus.setAutoPlay(true);
        splashToSyllabus.startPour();
        splashToAboutCSE=(LiquidButton)findViewById(R.id.splashToAboutCSE);
        splashToAboutCSE.setButtonText("About cse");
        splashToAboutCSE.setFillAfter(true);
        splashToAboutCSE.setAutoPlay(true);

        splashToAboutCSE.startPour();


        splashhToTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        splashToAboutCSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AboutCSE.class));
            }
        });

        splashToSyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),WebViewManual.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();



    }

    @Override
    protected void onPause() {
        super.onPause();


    }
}
