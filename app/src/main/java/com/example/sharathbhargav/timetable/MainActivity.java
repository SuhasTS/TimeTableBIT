package com.example.sharathbhargav.timetable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.List;

import layout.nameFragment;
import menu.room;
import proguard.annotation.KeepClassMembers;


/*This activity is the activity that holds all fragments
* An app drawer is set up for navigation
* Each fragment is deployed according to the selection in nav drawer*/

@Keep
@proguard.annotation.Keep
@KeepClassMembers
public class MainActivity extends AppCompatActivity implements  nameFragment.OnFragmentInteractionListener,changeDb.OnFragmentInteractionListener ,dayRoom.OnFragmentInteractionListener, room.OnFragmentInteractionListener, dayFaculty.OnFragmentInteractionListener, daySem.OnFragmentInteractionListener,DisplayEntireWeek.OnFragmentInteractionListener {
    DatabaseHelper myDbHelper;
   public static boolean firstRun=true;
    public static boolean firstRun2=true;
    static String toolbarTitle="";
    DrawerLayout  drawerLayout;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    TextView toolbarText;


    ImageView locButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=(Toolbar)findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        navigationView=(NavigationView) findViewById(R.id.navigationView);


      toolbarText=(TextView)findViewById(R.id.toolbarText);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);




        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                InputMethodManager mgr = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(drawerView.getWindowToken(), 0);

                MenuItem menuItem= navigationView.getMenu().findItem(R.id.change);

                Log.v("hamburger","menum"+navigationView.getMenu());



            }
        };




//Initially name fragment is deployed
       drawerLayout.setDrawerListener(actionBarDrawerToggle);
        Drawable d = toolbar.getNavigationIcon();

        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container,new nameFragment());
        toolbarText.setText("Search by Faculty name");
        fragmentTransaction.commit();


//Seletion in nav drawer determines the switch and accordingly a function is called
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.name:
                       nameFragment(item);
                        break;
                    case R.id.room:
                        roomFragment(item);
                        break;
                    case R.id.sem:
                        semFragment(item);
                        break;
                    case R.id.faculty:

                        facultyFragment(item);
                        break;
                    case R.id.dayRoom:
                        dayRoomFragment(item);
                        break;

                    case R.id.change:
                        changeDB(item);
                        break;


                }




                return true;
            }
        });






    }
    public ImageButton getNavButtonView(Toolbar toolbar) {
        try {
            Class<?> toolbarClass = Toolbar.class;
            Field navButtonField = toolbarClass.getDeclaredField("mNavButtonView");
            navButtonField.setAccessible(true);
            ImageButton navButtonView = (ImageButton) navButtonField.get(toolbar);

            return navButtonView;
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return null;
    }

// Functions for deploying req fragment
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    void roomFragment(MenuItem item)
    {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new room());
        fragmentTransaction.commit();
        item.setChecked(true);

        drawerLayout.closeDrawers();

    }

    void nameFragment(MenuItem item)
    {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new nameFragment());
        fragmentTransaction.commit();

        item.setChecked(true);
        drawerLayout.closeDrawers();
    }

     void nameFragment()
    {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new nameFragment());
        fragmentTransaction.commit();

    }
    void facultyFragment(MenuItem item)
    {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new dayFaculty());
        fragmentTransaction.commit();

      //  toolbarText.setTextColor(555555);
        item.setChecked(true);
        drawerLayout.closeDrawers();
    }
    void semFragment(MenuItem item)
    {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new daySem());
        fragmentTransaction.commit();

        item.setChecked(true);
        drawerLayout.closeDrawers();
    }

    void dayRoomFragment(MenuItem item)
    {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new dayRoom());
        fragmentTransaction.commit();

        item.setChecked(true);
        drawerLayout.closeDrawers();
    }


    void changeDB(MenuItem item)
    {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new changeDb());
        fragmentTransaction.commit();

      //  Toast.makeText(getApplicationContext(),"This is dummy \nPlease do not judge",Toast.LENGTH_SHORT);
        item.setChecked(true);
        drawerLayout.closeDrawers();
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public void freeMemory()
    {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();


    }
    void setToolbarText(String s)
    {

        toolbarText.setText(s);
    }
    @Override
    protected void onPause() {
        super.onPause();
        freeMemory();
        SharedPreferences sp = getSharedPreferences("shared", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("firstRun", firstRun);
        editor.commit();


    }

    @Override
    protected void onStart() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("shared", Activity.MODE_PRIVATE);
        firstRun = sp.getBoolean("firstRun", true);
        Log.v("first","In main "+firstRun);
    }

    @Override
    public void onBackPressed() {
        Log.v("back pressed","back pressed");

        if (getFragmentManager().getBackStackEntryCount() > 0) {
           getFragmentManager().popBackStack();

            Log.v("back pressed",toolbarTitle);
        //    Toast.makeText(getApplicationContext(),""+tag,Toast.LENGTH_SHORT).show();
          //  new DisplayEntireWeek().changeFragment("asd");
        } else {
            super.onBackPressed();
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);


        getMenuInflater().inflate(R.menu.drawer_menu, menu);
      locButton = (ImageButton) menu.findItem(R.id.change).getActionView();
        Log.v("hamburger","entering options");

        return true;
    }
*/
}
