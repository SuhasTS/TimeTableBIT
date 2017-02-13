package com.example.sharathbhargav.timetable;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import layout.nameFragment;
import menu.room;

/*This activity is the activity that holds all fragments
* An app drawer is set up for navigation
* Each fragment is deployed according to the selection in nav drawer*/


public class MainActivity extends AppCompatActivity implements  nameFragment.OnFragmentInteractionListener,changeDb.OnFragmentInteractionListener ,dayRoom.OnFragmentInteractionListener, room.OnFragmentInteractionListener, dayFaculty.OnFragmentInteractionListener, daySem.OnFragmentInteractionListener {
    DatabaseHelper myDbHelper;
    DrawerLayout  drawerLayout;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    TextView toolbarText;
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
                // creates call to onPrepareOptionsMenu()
                InputMethodManager mgr = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(drawerView.getWindowToken(), 0);
            }
        };

//Initially name fragment is deployed
       drawerLayout.setDrawerListener(actionBarDrawerToggle);
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
        toolbarText.setText("Search by Room Number");
        drawerLayout.closeDrawers();

    }

    void nameFragment(MenuItem item)
    {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new nameFragment());
        fragmentTransaction.commit();
        toolbarText.setText("Search by Faculty name");
        item.setChecked(true);
        drawerLayout.closeDrawers();
    }


    void facultyFragment(MenuItem item)
    {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new dayFaculty());
        fragmentTransaction.commit();
        toolbarText.setText("Day schedule : Faculty");
      //  toolbarText.setTextColor(555555);
        item.setChecked(true);
        drawerLayout.closeDrawers();
    }
    void semFragment(MenuItem item)
    {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new daySem());
        fragmentTransaction.commit();
        toolbarText.setText("Day schedule : Sem");
        item.setChecked(true);
        drawerLayout.closeDrawers();
    }

    void dayRoomFragment(MenuItem item)
    {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new dayRoom());
        fragmentTransaction.commit();
        toolbarText.setText("Day schedule :Room");
        item.setChecked(true);
        drawerLayout.closeDrawers();
    }


    void changeDB(MenuItem item)
    {
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new changeDb());
        fragmentTransaction.commit();
        toolbarText.setText("Change Time Table");
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
    }
}
