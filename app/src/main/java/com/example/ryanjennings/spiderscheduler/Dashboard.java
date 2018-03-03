package com.example.ryanjennings.spiderscheduler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import android.util.Log;

import java.util.*;
import java.io.*;

public class Dashboard extends AppCompatActivity {

    private TextView mTextMessage;
    private ListView mListView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mListView = (ListView) findViewById(R.id.event_list_view);
// 1
        ArrayList<Event> eventList = new ArrayList<Event>();
        Log.d("warning","before");
        try {
            eventList = Event.readCSV("events.csv", this);
            Log.d("warning","in this block");
        }
        catch(Exception E) {
            Log.d("warning","error");
        }
// 2
        String[] listItems = new String[eventList.size()];

// 3
        Log.d("warning",(" " + eventList.size()));
        for(int i = 0; i < eventList.size(); i++){
            Event event = eventList.get(i);
            listItems[i] = event.event_name;
            Log.d("warning",event.event_name);
        }
// 4
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);

    }

}
