package com.example.sharathbhargav.timetable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Suhas on 3/14/2017.
 */

public class FacultyCardGeneralInfo {
    String name,designation,tag,qualification,mail_id,fid,phonNo;
    DatabaseReference databaseReference;
    void FacultyCardGeneralInfo(){
        //databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://bitcse-90b07.firebaseio.com/");
    }

    //   void load()
    //   {

    //       databaseReference.child("facultyLinks").child(this.fid).addListenerForSingleValueEvent(new ValueEventListener() {
    //           @Override
    //           public void onDataChange(DataSnapshot dataSnapshot) {

    //               web_link = dataSnapshot.getValue(String.class);
    //               //Log.v("links",temp.web_link);


    //           }


    //           @Override
    //           public void onCancelled(DatabaseError databaseError) {

    //           }
    //       });

    //   }
}
