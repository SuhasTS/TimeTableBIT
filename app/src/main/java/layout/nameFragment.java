package layout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sharathbhargav.timetable.DatabaseHelper;
import com.example.sharathbhargav.timetable.R;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import tourguide.tourguide.Overlay;
import tourguide.tourguide.Pointer;
import tourguide.tourguide.ToolTip;
import tourguide.tourguide.TourGuide;


public class nameFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    AutoCompleteTextView nameInput;
    String lName;
    private OnFragmentInteractionListener mListener;
    Button testButton;
    Cursor res;
    DatabaseHelper myDbHelper;
    int timeArray[],slotTime[];
    TextView nameHead,nameRoomDisplay,nameSemDisplay,nameSubDisplay;
    Calendar calendar;
    SimpleDateFormat Hour;
    Cursor fname1;
    String[] fnames;
    ArrayAdapter<String> faculty_name;
    RadioGroup nameRadioGroup;
    RadioButton radioCurrent,radioCustom;
    TimePicker timePicker;
    TextClock textClock;

    TourGuide mTourGuideHandler;

    public nameFragment() {
        // Required empty public constructor
    }


    public static nameFragment newInstance(String param1, String param2) {
        nameFragment fragment = new nameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        myDbHelper  = new DatabaseHelper(getContext());




        timeArray= new int[]{800, 850, 940, 1030, 1100, 1150, 1240, 1330, 1415, 1505, 1555};
        slotTime=new int[]{1,2,3,4,5,6,7,8,9,10,11};

        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        int i=0;
        fname1=myDbHelper.getnames();
        fnames=new String[fname1.getCount()*2];
        while(fname1.moveToNext())
        {
            fnames[i]=fname1.getString(0);
            i++;
            fnames[i]=fname1.getString(1);
            i++;
        }
        faculty_name=new ArrayAdapter<String>(getContext(),android.R.layout.simple_expandable_list_item_1,fnames);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_name,container,false);

        testButton=(Button)view.findViewById(R.id.buttonNameSearch);
     //   nameHead=(TextView)view.findViewById(R.id.nameText);
        nameInput=(AutoCompleteTextView)view.findViewById(R.id.nameSearch);
        nameInput.setAdapter(faculty_name);
        //nameRoomDisplay=(TextView)view.findViewById(R.id.nameRoomDisplay);
        //nameSemDisplay=(TextView)view.findViewById(R.id.nameSemDisplay);
        //nameSubDisplay=(TextView)view.findViewById(R.id.nameSubDisplay);
        nameRadioGroup=(RadioGroup)view.findViewById(R.id.nameRadioGroup);
        radioCurrent=(RadioButton)view.findViewById(R.id.nameRadioCurrent);
        radioCustom=(RadioButton)view.findViewById(R.id.nameRadioCustom);
        timePicker=(TimePicker)view.findViewById(R.id.timePicker3);
        textClock=(TextClock)view.findViewById(R.id.textClockName);
        // Spinner element
        final Spinner spinner = (Spinner) view.findViewById(R.id.nameDaySpinner);

        // Spinner click listener

        mTourGuideHandler = TourGuide.init(getActivity()).with(TourGuide.Technique.Click)
                .setPointer(new Pointer())
                .setToolTip(new ToolTip().setTitle("Welcome!").setDescription("Click on Get Started to begin..."))
                .setOverlay(new Overlay())
                .playOn(nameInput);



        nameInput.setDropDownBackgroundDrawable(new ColorDrawable(getContext().getResources().getColor(R.color.autoComplete)));
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Monday");
        categories.add("Tuesday");
        categories.add("Wednesday");
        categories.add("Thursday");
        categories.add("Friday");
        categories.add("Saturday");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                ((TextView) v).setTextSize(14);
                // custom method to get a font from "assets" folder


                ((TextView) v) .setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);

                return v;
            }
        };;

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);



        calendar =Calendar.getInstance();
        Hour  = new SimpleDateFormat("EEEHmm");
        radioCurrent.setChecked(true);
        timePicker.setVisibility(View.INVISIBLE);
        nameRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.nameRadioCurrent)
                {
                    timePicker.setVisibility(View.INVISIBLE);
                    textClock.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.INVISIBLE);
                    //spinner.setEnabled(false);
                }
                else if(checkedId==R.id.nameRadioCustom)
                {
                    textClock.setVisibility(View.INVISIBLE);
                    timePicker.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.VISIBLE);
                  //  spinner.setEnabled(true);
                }

            }
        });
        final String[] customDay = new String[1];
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                customDay[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        nameInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTourGuideHandler.cleanUp();
            }
        });
        nameInput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                mTourGuideHandler.cleanUp();




                // nameRoomDisplay.setText("");
                //nameSemDisplay.setText("");
                //nameSubDisplay.setText("");
                int ind=nameRadioGroup.getCheckedRadioButtonId();
                String total=Hour.format(calendar.getTime());
                String day=total.substring(0,3);
                //  total="";
                if(ind==R.id.nameRadioCurrent)
                {
                    total=Hour.format(calendar.getTime());
                    Log.v("Time new name","current");
                }
                else if(ind==R.id.nameRadioCustom)
                {
                    day=customDay[0].substring(0,3);
                    Log.v("Time","custom");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if(timePicker.getMinute()<10)
                            total=day+timePicker.getHour()+"0"+timePicker.getMinute();
                        else
                            total=day+timePicker.getHour()+timePicker.getMinute();
                        Log.v("Time in picke 21 api",total);

                    }
                    else {
                        if(timePicker.getCurrentMinute()<10) {
                            total = day + timePicker.getCurrentHour() + "0" + timePicker.getCurrentMinute();
                            Log.v("Time in custom else if",total);
                        }
                        else {
                            total = day + timePicker.getCurrentHour() + timePicker.getCurrentMinute();
                            Log.v("Time  custom else else",total);
                        }
                        //    Log.v("Time in picke 21 api","else"+timePicker.getHour()+""+timePicker.getMinute());
                        //  Log.v("Time in picke 17 api","else"+total);
                    }
                }
                int time=Integer.parseInt(total.substring(3));
                int i=0;
                Log.v("Time new current",""+time);
                while(!(time<timeArray[i]) && i<10 )
                {
                    i++;
                    Log.v("Name while",""+i);
                }


                if(i!=0)
                    i--;
                Log.v("time outside whileafter",""+i);
                day=day.toUpperCase();
                lName=nameInput.getText().toString();
                nameInput.setText("");
                if(lName.length()>4)
                res = myDbHelper.getdataName(lName,day,slotTime[i]);
                else
                res=myDbHelper.getDataNameInitials(lName,day,slotTime[i]);
                if(res.getCount()==0)
                {   Log.v("Name slot lab",""+slotTime[i]);
                    if(slotTime[i]<4) {
                        res = myDbHelper.getdataName(lName, day, 12);
                    Log.v("In lab 1",""+res.getCount());
                    }
                        else if(slotTime[i]<8) {
                        res = myDbHelper.getdataName(lName, day, 13);
                        Log.v("In lab 2",""+res.getCount());
                    }
                    else
                        res=myDbHelper.getdataName(lName,day,14);

                }

                String result = null;
                //while(res.moveToNext()) {
                //    nameRoomDisplay.setText("Room No: "+res.getString(0));
                //    nameSemDisplay.setText("Class: "+res.getString(1));
                //    nameSubDisplay.setText("Subject: "+res.getString(2));
                //}
                //if(slotTime[i]==4 || slotTime[i]==8)
                //{
                //    nameSemDisplay.setText("Break");
                //}
                android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(getContext());
                alert.setTitle("Search Result");
                alert.setPositiveButton("OK", null);
                while(res.moveToNext()) {
                    if(res.getString(0).length()<=3)
                    result= "Room No: "+res.getString(0)+"\n"+"Subject: "+res.getString(1)+"\n"+"Sem: "+res.getString(2);
                    else
                        result= "Room No: "+res.getString(0).substring(0,res.getString(0).length()-1)+"\n"+"Class: "+res.getString(1)+"\n"+"Subject: "+res.getString(2);
                }
                if(slotTime[i]==4 || slotTime[i]==8)
                {
                    result="Break";
                }
                Log.v("Create","hello");
                if(result==null  || time>1645 || time<800)
                    result="No classes";
                if(lName.length()==0)
                    result="Please enter the Faculty name";
                alert.setMessage(result);
                Log.v("Create","message set");
                alert.show();
            }
        });

        return view;
    }








    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
