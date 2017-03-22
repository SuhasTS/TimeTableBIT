package com.example.sharathbhargav.timetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstScreen extends AppCompatActivity {
    Button facultyData,splashhToTT,splashToAboutCSE,splashToSyllabus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        facultyData=(Button)findViewById(R.id.splashToFacultyData);
        splashhToTT=(Button)findViewById(R.id.splashToTimeTable);
        splashToSyllabus=(Button)findViewById(R.id.splashToSyllabus);
        splashToAboutCSE=(Button)findViewById(R.id.splashToAboutCSE);
        facultyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FacultyData.class));
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
    }
}
