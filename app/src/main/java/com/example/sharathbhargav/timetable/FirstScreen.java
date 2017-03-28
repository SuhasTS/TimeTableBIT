package com.example.sharathbhargav.timetable;

import android.content.Intent;
import android.support.annotation.Keep;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.vstechlab.easyfonts.EasyFonts;

import liquidButton.LiquidButton;

@Keep
public class FirstScreen extends AppCompatActivity {
    LiquidButton splashToAboutCSE,splashToSyllabus,splashToFaculty,splashToAboutDev;
    LiquidButton splashhToTT;
    TextView textAboutCse,textAboutDev,textSyllabus,textFacultyData,textTimeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        textAboutCse=(TextView)findViewById(R.id.textAboutCse);
        textAboutDev=(TextView)findViewById(R.id.textAboutDev);
        textFacultyData=(TextView)findViewById(R.id.textFacultyData);
        textSyllabus=(TextView)findViewById(R.id.textSyllabus);
        textTimeTable=(TextView)findViewById(R.id.textTimeTable);


        textAboutCse.setTypeface(EasyFonts.androidNationBold(this));
        textAboutDev.setTypeface(EasyFonts.androidNationBold(this));
        textFacultyData.setTypeface(EasyFonts.androidNationBold(this));
        textSyllabus.setTypeface(EasyFonts.androidNationBold(this));
        textTimeTable.setTypeface(EasyFonts.androidNationBold(this));



        splashhToTT=(LiquidButton) findViewById(R.id.splashToTimeTable);
        splashhToTT.setButtonText("");
        splashhToTT.setFillAfter(true);
        splashhToTT.setAutoPlay(true);
        splashhToTT.startPour();
        splashhToTT.setPourFinishListener(new LiquidButton.PourFinishListener() {
            @Override
            public void onPourFinish() {
                textTimeTable.setVisibility(View.VISIBLE);
                Log.v("Text", (String) textTimeTable.getText());
            }

            @Override
            public void onProgressUpdate(float progress) {

            }
        });

        splashToSyllabus=(LiquidButton) findViewById(R.id.splashToSyllabus);
        splashToSyllabus.setButtonText("");
        splashToSyllabus.setFillAfter(true);
        splashToSyllabus.setAutoPlay(true);
        splashToSyllabus.startPour();
        splashToSyllabus.setPourFinishListener(new LiquidButton.PourFinishListener() {
            @Override
            public void onPourFinish() {
                textSyllabus.setVisibility(View.VISIBLE);
            }

            @Override
            public void onProgressUpdate(float progress) {

            }
        });

        splashToAboutCSE=(LiquidButton)findViewById(R.id.splashToAboutCSE);
        splashToAboutCSE.setButtonText("");
        splashToAboutCSE.setFillAfter(true);
        splashToAboutCSE.setAutoPlay(true);
        splashToAboutCSE.startPour();
        splashToAboutCSE.setPourFinishListener(new LiquidButton.PourFinishListener() {
            @Override
            public void onPourFinish() {
                textAboutCse.setVisibility(View.VISIBLE);
            }

            @Override
            public void onProgressUpdate(float progress) {

            }
        });


        splashToFaculty=(LiquidButton)findViewById(R.id.splashToFacultyData);
        splashToFaculty.setButtonText("");
        splashToFaculty.setFillAfter(true);
        splashToFaculty.setAutoPlay(true);
        splashToFaculty.startPour();
        splashToFaculty.setPourFinishListener(new LiquidButton.PourFinishListener() {
            @Override
            public void onPourFinish() {
                textFacultyData.setVisibility(View.VISIBLE);
            }

            @Override
            public void onProgressUpdate(float progress) {

            }
        });


        splashToAboutDev=(LiquidButton)findViewById(R.id.splashToAboutDev);
        splashToAboutDev.setButtonText("");
        splashToAboutDev.setFillAfter(true);
        splashToAboutDev.setAutoPlay(true);
        splashToAboutDev.startPour();
        splashToAboutDev.setPourFinishListener(new LiquidButton.PourFinishListener() {
            @Override
            public void onPourFinish() {
                textAboutDev.setVisibility(View.VISIBLE);
            }

            @Override
            public void onProgressUpdate(float progress) {

            }
        });
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

        splashToAboutDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),aboutDevelopers.class));
            }
        });
        splashToFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FacultyData.class));
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
