package com.example.sharathbhargav.timetable;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by SharathBhargav on 26-02-2017.
 */

public class displayArraylist {
    public ArrayList<display> displayArrayList;
  public displayArraylist()
    {
        this.displayArrayList=new ArrayList<display>();
        Log.v("incoming","display called");
    }
}
