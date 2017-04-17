package com.example.sharathbhargav.timetable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import easyFonts.EasyFonts;


public class aboutDevelopers extends AppCompatActivity {


    TextView name1,name2,name3,name4,des1,des2,des3,des4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developers);

        name1=(TextView)findViewById(R.id.aboutDevName1);
        des1=(TextView)findViewById(R.id.aboutDevdes1);
        name2=(TextView)findViewById(R.id.aboutDevName2);
        des2=(TextView)findViewById(R.id.aboutDevdes2);
        name3=(TextView)findViewById(R.id.aboutDevName3);
        des3=(TextView)findViewById(R.id.aboutDevdes3);
        name4=(TextView)findViewById(R.id.aboutDevName4);
        des4=(TextView)findViewById(R.id.aboutDevdes4);


        name1.setTypeface(EasyFonts.captureIt(this));
        name2.setTypeface(EasyFonts.captureIt(this));
        name3.setTypeface(EasyFonts.captureIt(this));
        name4.setTypeface(EasyFonts.captureIt(this));
        des1.setTypeface(EasyFonts.tangerineBold(this));
        des2.setTypeface(EasyFonts.tangerineBold(this));
        des3.setTypeface(EasyFonts.tangerineBold(this));
        des4.setTypeface(EasyFonts.tangerineBold(this));

    }
}
