package com.example.sharathbhargav.timetable;

import java.util.HashMap;

/**
 * Created by SharathBhargav on 31-10-2016.
 */
public class slotConvert {
    String slotConversion(String s)
    {
        HashMap<String,String> slotConversion=new HashMap<>();
        slotConversion.put("1","8:00 AM-8:50 AM");
        slotConversion.put("2","8:50 AM-9:40 AM");
        slotConversion.put("3","9:40 AM-10:30 AM");
        slotConversion.put("4","10:30 AM-11:00 AM");
        slotConversion.put("5","11:00 AM-11:50 AM");
        slotConversion.put("6","11:50 AM-12:40 PM");
        slotConversion.put("7","12:40 AM-1:30 PM");
        slotConversion.put("8","1:30 PM-2:15 PM");
        slotConversion.put("9","2:15 PM-3:05 PM");
        slotConversion.put("10","3:05 PM-3:55 PM");
        slotConversion.put("11","3:55 PM-4:45 PM");
        slotConversion.put("12","8:00 AM-10:30 AM");
        slotConversion.put("13","11:00 AM-1:30 PM");
        slotConversion.put("14","2:15 PM-4:45 PM");
        return slotConversion.get(s);


    }
}
