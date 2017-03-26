package com.example.sharathbhargav.timetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import liquidButton.LiquidButton;

public class FirstScreen extends AppCompatActivity {
    LiquidButton splashToAboutCSE,splashToSyllabus,splashToFaculty,splashToAboutDev;
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


        splashToFaculty=(LiquidButton)findViewById(R.id.splashToFacultyData);
        splashToFaculty.setButtonText("Faculty data");
        splashToFaculty.setFillAfter(true);
        splashToFaculty.setAutoPlay(true);
        splashToFaculty.startPour();


        splashToAboutDev=(LiquidButton)findViewById(R.id.splashToAboutDev);
        splashToAboutDev.setButtonText("About Developers");
        splashToAboutDev.setFillAfter(true);
        splashToAboutDev.setAutoPlay(true);
        splashToAboutDev.startPour();

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
