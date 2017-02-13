package com.example.sharathbhargav.timetable;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SharathBhargav on 02-02-2017.
 */
public class display_dialog_general {
    RecyclerView recyclerView;
    ArrayList<display> displayArrayList=new ArrayList<display>();
    View view;
    ArrayList<String> head_parameters=new ArrayList<String>();
    Context context;
    String optionalTextFaculty="";
    DisplayMetrics metrics;
    public display_dialog_general(ArrayList<display> displayArrayList,View view,ArrayList<String> head_parameters,Context context,DisplayMetrics metrics)
    {
       // this.recyclerView=recyclerView;
        this.displayArrayList=displayArrayList;
        this.view=view;
        this.head_parameters=head_parameters;
        this.context=context;
        this.metrics=metrics;
        optionalTextFaculty="sd";

    }
    public display_dialog_general(ArrayList<display> displayArrayList,View view,ArrayList<String> head_parameters,Context context,String optional,DisplayMetrics metrics)
    {
        // this.recyclerView=recyclerView;
        this.displayArrayList=displayArrayList;
        this.view=view;
        this.head_parameters=head_parameters;
        this.context=context;
        this.optionalTextFaculty=optional;
        this.metrics=metrics;

    }
    public  class recycler_view_adapter extends RecyclerView.Adapter<recycler_view_adapter.myViewHolder> {
        public class myViewHolder extends RecyclerView.ViewHolder {
            public TextView head1, head2, head3, head4, disp1, disp2, disp3, disp4;
            public myViewHolder(View view) {
                super(view);
                head1 = (TextView) view.findViewById(R.id.customListHead1);
                head2 = (TextView) view.findViewById(R.id.customListHead2);
                head3 = (TextView) view.findViewById(R.id.customListHead3);
                head4 = (TextView) view.findViewById(R.id.customListHead4);
                disp1 = (TextView) view.findViewById(R.id.customListText1);
                disp2 = (TextView) view.findViewById(R.id.customListText2);
                disp3 = (TextView) view.findViewById(R.id.customListText3);
                disp4 = (TextView) view.findViewById(R.id.customListText4);
            }
        }



        @Override
        public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.customlist, parent, false);

            return new myViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(myViewHolder holder, int position) {
            holder.head1.setTextColor(position%2!=0 ? Color.BLACK:Color.WHITE);
            holder.head2.setTextColor(position%2!=0 ? Color.BLACK:Color.WHITE);
            holder.head3.setTextColor(position%2!=0 ? Color.BLACK:Color.WHITE);
            holder.head4.setTextColor(position%2!=0 ? Color.BLACK:Color.WHITE);
            holder.disp1.setTextColor(position%2!=0 ? Color.BLACK:Color.WHITE);
            holder.disp2.setTextColor(position%2!=0 ? Color.BLACK:Color.WHITE);
            holder.disp3.setTextColor(position%2!=0 ? Color.BLACK:Color.WHITE);
            holder.disp4.setTextColor(position%2!=0 ? Color.BLACK:Color.WHITE);
            holder.head1.setText(head_parameters.get(0));
            holder.head2.setText(head_parameters.get(1));
            holder.head3.setText(head_parameters.get(2));
            holder.head4.setText(head_parameters.get(3));
            holder.disp1.setText(new slotConvert().slotConversion(displayArrayList.get(position).string1) );
            if(displayArrayList.get(position).string2.length()<=3)
                holder.disp2.setText(displayArrayList.get(position).string2);
            else
                holder.disp2.setText(displayArrayList.get(position).string2.substring(0,displayArrayList.get(position).string2.length()-1));
            holder.disp3.setText(displayArrayList.get(position).string3);
            holder.disp4.setText(displayArrayList.get(position).string4);
        }


        @Override
        public int getItemCount() {
            return displayArrayList.size();
        }

    }
    public class DividerItemDecoration extends RecyclerView.ItemDecoration {


        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            int position = parent.getChildAdapterPosition(view);
            view.setBackgroundResource(position%2==0 ? R.color.black : R.color.white);
        }
    }


    public void showDialog() {

        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.alertdialog_theme);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration());
        recycler_view_adapter adapter=new recycler_view_adapter();
        recyclerView.setAdapter(adapter);
        builder.setTitle("Schedule");
        builder.setView(view);
        builder.setPositiveButton("OK", null);
        Log.v("Optional text",optionalTextFaculty+"sdbfkj");
        if(optionalTextFaculty.equals("")) {
            builder.setMessage("Please enter faculty name");
        Log.v("Optional text",optionalTextFaculty+"askcfjfh");
        }
        AlertDialog alert=builder.create();
        alert.show();
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        if(displayArrayList.size()>7)
            alert.getWindow().setLayout(width,height-50);

        Log.v("Testing Button","Inflate started");
    }

}
