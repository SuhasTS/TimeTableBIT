package com.example.sharathbhargav.timetable;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mutualmobile.cardstack.CardStackAdapter;
import com.mutualmobile.cardstack.CardStackLayout;

import java.util.ArrayList;

/**
 * Created by SharathBhargav on 25-02-2017.
 */

public class EachDayDetailCardAdaptor extends CardStackAdapter {

    private final LayoutInflater mInflater;
    private final Context mContext;
    displayArraylist displayArrayList[];
    ArrayList<String> heads;
    int colors[];
    Context context;
RecyclerView recyclerView;
    TextView entireWeekDay;

    View view;
    String day[]={"MON","TUE","WED","THU","FRI","SAT"};

    public EachDayDetailCardAdaptor(DisplayEntireWeek activity,displayArraylist[] displayArrayList1,ArrayList<String> heads1){
        super(activity.getContext());
        mContext = activity.getContext();
        mInflater = LayoutInflater.from(activity.getContext());
        colors=new int[]{Color.GRAY,Color.BLUE,Color.CYAN,Color.RED,Color.MAGENTA};
        displayArrayList=displayArrayList1;
        Log.v("incoming","Lenght of incoming data="+displayArrayList1[0].displayArrayList.size());
        heads=heads1;
      //  displayArrayList.add(new display("asd","asf","asf","af"));
        context=activity.getContext();
        heads.add("fsdg");
        heads.add("fsdg");
        heads.add("fsdg");
        heads.add("fsdg");

         view=activity.getView();

    }
    @Override
    public View createView(int position, final ViewGroup container) {
        Log.v("incoming","on create called"+position);
        final CardView cardView=(CardView) mInflater.inflate(R.layout.entire_week_card_layout,container,false);
        cardView.setCardBackgroundColor(colors[position%5]);

        display_dialog_general.recycler_view_adapter recycler_view_adapter=new display_dialog_general().new recycler_view_adapter(displayArrayList[position].displayArrayList,heads,context);
        recyclerView=(RecyclerView)cardView.findViewById(R.id.displayEntireWeekRecycler);
entireWeekDay=(TextView)cardView.findViewById(R.id.entireWeekDay);
        entireWeekDay.setText(day[position]);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new EachDayDetailCardAdaptor.DividerItemDecoration());
        recyclerView.setAdapter(recycler_view_adapter);
recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        Log.v("recycle view","on intercept touch event");
        cardView.onInterceptTouchEvent(e);
        CardStackLayout cardStackLayout123=(CardStackLayout)cardView.getParent().getParent();

        cardStackLayout123.restoreCards();
        Log.v("recycle view",cardStackLayout123.toString());
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        Log.v("recycle view","ontouch event");
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
});

        Log.v("incoming","create view");
        return cardView;
    }

    @Override
    public int getCount() {
        return displayArrayList.length;
    }

    public class DividerItemDecoration extends RecyclerView.ItemDecoration {


        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            int position = parent.getChildAdapterPosition(view);
            view.setBackgroundResource(position%2==0 ? R.color.black : R.color.white);
        }
    }

}
