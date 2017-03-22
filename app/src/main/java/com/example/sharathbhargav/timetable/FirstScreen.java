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

import com.igalata.bubblepicker.BubblePickerListener;
import com.igalata.bubblepicker.model.BubbleGradient;
import com.igalata.bubblepicker.model.Color;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FirstScreen extends AppCompatActivity {
    Button facultyData,splashhToTT,splashToAboutCSE,splashToSyllabus;

    BubblePicker picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);


        picker=(BubblePicker)findViewById(R.id.bubblePicker);
        ArrayList<PickerItem> list=new ArrayList<>();

        PickerItem p=new PickerItem("Time table",android.graphics.Color.RED,android.R.color.white,
                ContextCompat.getDrawable(this, R.drawable.download));
        PickerItem p1=new PickerItem("Time table",android.graphics.Color.RED,
                new BubbleGradient(android.graphics.Color.RED, android.graphics.Color.WHITE),android.R.color.white, Typeface.DEFAULT ,android.graphics.Color.BLUE,
                ContextCompat.getDrawable(this, R.drawable.download),true);
        PickerItem p2=new PickerItem("Time table",android.graphics.Color.RED,
                new BubbleGradient(android.graphics.Color.RED, android.graphics.Color.BLACK),android.R.color.white, Typeface.DEFAULT ,android.graphics.Color.BLUE,
                ContextCompat.getDrawable(this, R.drawable.download),true);
        for(int i=0;i<3;i++) {
            list.add(p);
            list.add(p1);
            list.add(p2);
        }
        picker.setItems(list);
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

    @Override
    protected void onResume() {
        super.onResume();
        picker.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        picker.onPause();
    }
}
