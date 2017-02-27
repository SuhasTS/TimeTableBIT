package com.example.sharathbhargav.timetable;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import layout.nameFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link changeDb.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link changeDb#newInstance} factory method to
 * create an instance of this fragment.
 */
public class changeDb extends Fragment implements nameFragment.OnFragmentInteractionListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView toolbarText;
    Button setDb,updateDb;
    ArrayList<String> link_list=new ArrayList<String>();
    ArrayList<String> fileDirExistingList=new ArrayList<String>();
    ArrayList<String> name_list=new ArrayList<String>();
    private OnFragmentInteractionListener mListener;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    FirebaseStorage storage;
    File mediaStorageDir;
    StorageReference temp;
    ArrayList<String> pathList=new ArrayList<String>();
    int count=0;
    File databasesDir;
    RecyclerView recyclerView;
    ProgressDialog progress;

    public changeDb() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment changeDb.
     */
    // TODO: Rename and change types and number of parameters
    public static changeDb newInstance(String param1, String param2) {
        changeDb fragment = new changeDb();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_change_db, container, false);



        databasesDir = new File("/data/data/" + getContext().getPackageName(),"DBFILES");
        updateDb=(Button)view.findViewById(R.id.update_button);
        progress=new ProgressDialog(getContext());
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view_radio);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(layoutManager);

        fileDirExistingList.clear();
        existingFilesList();
        SampleAdapter adapter=new SampleAdapter();
        recyclerView.setAdapter(adapter);
        final   MainActivity m=new MainActivity();
        final   DatabaseHelper d=new DatabaseHelper(getContext());


        setDb=(Button)view.findViewById(R.id.buttonSetTimeTable);
        setDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               nameFragment fragment = new nameFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                      .replace(R.id.container, fragment)
                      .commit();
            }
        });
        updateDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setTitle("Updating");
                progress.show();
                fileDirExistingList.clear();
                existingFilesList();
                link_list.clear();
                pathList.clear();
                name_list.clear();
                mFirebaseInstance = FirebaseDatabase.getInstance();
                mFirebaseDatabase = mFirebaseInstance.getReference();
                storage = FirebaseStorage.getInstance();
                 mediaStorageDir = new File(
                        Environment
                                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Databases");
                if (!mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()) {
                        Log.d("Tag1", "Oops! Failed create Testing  directory");
                    }
                }

                mFirebaseDatabase.child("DBFILES").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                            String name = noteDataSnapshot.getKey();
                            String link = noteDataSnapshot.getValue(String.class);
                            int i=0;
                            for(i=0;i<fileDirExistingList.size();i++) {
                                if (fileDirExistingList.get(i).equals(name)) {
                                    break;
                                }
                            }
                                if(i==fileDirExistingList.size()) {
                                    link_list.add(link);
                                    name_list.add(name);
                                }

                        }
                        count=0;
                        //name_list.removeAll(fileDirExistingList);
                        Log.v("Firebase"," size"+link_list.size());
                        for(int i=0;i<link_list.size();i++) {
                            temp = storage.getReferenceFromUrl(link_list.get(i));
                            File localFile=new File(mediaStorageDir.getPath() + File.separator+name_list.get(i));
                            temp.getFile(localFile).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    count++;
                                        if(count==link_list.size())
                                    {
                                        checkAndCopyDatabase();
                                        progress.dismiss();
                                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                                        ft.detach(changeDb.this).attach(changeDb.this).commit();
                                        
                                    }
                                }


                            });
                            pathList.add(localFile.getPath());
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                    //Log.v("Firebase","After downloading size= "+pathList.size());
                });

            }
        });

        return view;
    }

    private void checkAndCopyDatabase() {
        for(int i=0;i<pathList.size();i++)
        {
            String DB_NAME = name_list.get(i);

            String DB_PATH = databasesDir.getPath()+File.separator;
            String myPath = DB_PATH + DB_NAME;
            FileInputStream myInput = null;
                try {
                    myInput = new FileInputStream(pathList.get(i));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String outFileName = DB_PATH + DB_NAME;
                FileOutputStream myOutput = null;
                try {
                    myOutput = new FileOutputStream(outFileName);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                byte[] buffer = new byte[10];
                int length;
                try {
                    while ((length = myInput.read(buffer)) > 0) {
                        try {
                            myOutput.write(buffer, 0, length);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    myOutput.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    myOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    myInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        Log.v("Delete","Directory delete");
        File[] filesInDirDownloaded =mediaStorageDir.listFiles();
        for(int j=0;j<filesInDirDownloaded.length;j++)
            filesInDirDownloaded[j].delete();
        mediaStorageDir.delete();
        Log.v("Delete","After delete");

    }
    void existingFilesList()
    {
        File[] filesInDir =databasesDir.listFiles();
        for(int i=0;i<filesInDir.length;i++)
        {
            fileDirExistingList.add(filesInDir[i].getName());
        }
    }
    public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.ViewHolder> {


        private int lastCheckedPosition = 0;



        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.radio_button, null);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.changeDbText.setText(fileDirExistingList.get(position));
            Log.v("Change",holder.changeDbText.getText().toString());
            holder.radioButton.setChecked(position == lastCheckedPosition);
        }

        @Override
        public int getItemCount() {
            return fileDirExistingList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            RadioButton radioButton;
            TextView changeDbText;
            public ViewHolder(View itemView) {
                super(itemView);
                 radioButton=(RadioButton)itemView.findViewById(R.id.radioButton);
                changeDbText=(TextView)itemView.findViewById(R.id.changeDbText);
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lastCheckedPosition = getAdapterPosition();
                        notifyItemRangeChanged(0, fileDirExistingList.size());

                    }
                });
            }
        }
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
