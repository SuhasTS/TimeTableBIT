package com.example.sharathbhargav.timetable;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FacultyData extends AppCompatActivity {
    //ArrayList<String> facultyNameList = new ArrayList<>();
    ArrayList<FacultyCardGeneralInfo> facultyNameListFiltered = new ArrayList<>();
    ArrayList<FacultyCardGeneralInfo> facultyGeneralInfoList=new ArrayList<>();
    DatabaseHelper myDbHelper;
    Context context;
    FloatingSearchView searchbox;
    RecyclerView recyclerFacultyInfo;
    facultyInfoAdapter adapter;
    StorageReference imagereference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_data);
        searchbox=(FloatingSearchView) findViewById(R.id.searchBox);
        recyclerFacultyInfo=(RecyclerView)findViewById(R.id.recyclerFacultyInfo);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerFacultyInfo.setLayoutManager(layoutManager);
        adapter=new facultyInfoAdapter();
        imagereference= FirebaseStorage.getInstance().getReferenceFromUrl("gs://timetable-dbdd5.appspot.com/nanda.jpg");
        myDbHelper  = new DatabaseHelper(getApplicationContext());
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
        Cursor fname=myDbHelper.getnames();
        while(fname.moveToNext())
        {
            FacultyCardGeneralInfo temp=new FacultyCardGeneralInfo();
           temp.name=fname.getString(0);
            temp.tag=fname.getString(1);
            facultyGeneralInfoList.add(temp);
        }
        facultyNameListFiltered.addAll(facultyGeneralInfoList);
        recyclerFacultyInfo.setAdapter(adapter);

      // searchbox.addTextChangedListener(new TextWatcher() {
      //     @Override
      //     public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      //     }

      //     @Override
      //     public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      //         AlterAdapter();
      //     }

      //     @Override
      //     public void afterTextChanged(Editable editable) {
      //         facultyNameListFiltered.clear();
      //         adapter.notifyDataSetChanged();
      //         AlterAdapter();

      //     }
      // });
        searchbox.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                Log.v("querylistner",searchbox.getQuery()+" "+newQuery+"\n");
                facultyNameListFiltered.clear();
                adapter.notifyDataSetChanged();
                AlterAdapter(newQuery);


            }
        });





        


    }

    public class facultyInfoAdapter extends RecyclerView.Adapter<facultyInfoAdapter.myViewHolder>
    {
        @Override
        public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.faculty_card, parent, false);

            return new facultyInfoAdapter.myViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final myViewHolder holder, int position) {
            holder.cardLecturerName.setText("Name: "+facultyNameListFiltered.get(position).name);
            holder.cardLecturerDesignation.setText("Designation: Professor & Head of Department");
            holder.cardLecturerQualification.setText("Qualification: M.Sc., M.Tech., M.S., Ph.D");
            holder.cardLecturerMailId.setText("Mail-Id: snandagopalan@gmail.com");
            Glide.with(getApplicationContext())
                    .using(new FirebaseImageLoader())
                    .load(imagereference)
                    .centerCrop()
                    .error(R.drawable.bitlogo)
                    .into(holder.profileImage);

            holder.googleSite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getApplicationContext(),googleSitesWebView.class);
                    i.putExtra("site","www.google.com");
                    startActivity(i);
                }
            });

        }

        @Override
        public int getItemCount() {
            return facultyNameListFiltered.size();
        }

        public class myViewHolder extends RecyclerView.ViewHolder{
            TextView cardLecturerName,cardLecturerDesignation,cardLecturerQualification,cardLecturerMailId;
            CardView cardLecturerInfo;
            CircleImageView profileImage;
            ImageView googleSite;
            public myViewHolder(View itemView) {
                super(itemView);
                cardLecturerName=(TextView)itemView.findViewById(R.id.cardLecturerName);
                cardLecturerDesignation=(TextView)itemView.findViewById(R.id.cardLecturerDesignation);
                cardLecturerQualification=(TextView)itemView.findViewById(R.id.cardLecturerQualification);
                cardLecturerMailId=(TextView)itemView.findViewById(R.id.cardLecturerMailId);
                cardLecturerInfo=(CardView)itemView.findViewById(R.id.cardLecturer);
                profileImage=(CircleImageView)itemView.findViewById(R.id.profileImage);
                googleSite=(ImageView)itemView.findViewById(R.id.websiteLink);

            }
        }
    }

    private void AlterAdapter(String newQuery) {
        facultyNameListFiltered.clear();
        if (newQuery.isEmpty()) {
            facultyNameListFiltered.addAll(facultyGeneralInfoList);
            Log.v("recycler123",facultyNameListFiltered.size()+"\n");
        }
        else {
            //Log.v("recycler","insideTextChanged"+facultyNameList.size());

            for (int k = 0; k < facultyGeneralInfoList.size(); k++) {
                //Log.v("recycler","inside for loop"+facultyNameList.get(k).toString().toUpperCase().contains(searchbox.getQuery().toString().toUpperCase())+"\n");
                if ((facultyGeneralInfoList.get(k).name.toString().toUpperCase().contains(newQuery.toUpperCase()))||facultyGeneralInfoList.get(k).tag.toString().toUpperCase().contains(newQuery.toUpperCase()))
                {
                   // Log.v("recycler",facultyNameList.get(k).toString()+"\n");
                    facultyNameListFiltered.add(facultyGeneralInfoList.get(k));
                }
                Log.v("recycler123",facultyNameListFiltered.size()+"\n");

            }

        }
        adapter.notifyDataSetChanged();
    }
    }




