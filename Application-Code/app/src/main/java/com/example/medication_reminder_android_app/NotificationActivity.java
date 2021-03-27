package com.example.medication_reminder_android_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationActivity extends AppCompatActivity {

    String[] notifTitles; //array of notification titles to be displayed
    String[] contents; //array of notification contents to be displayed
    RecyclerView notifRecycler; /*Recycler view object that with display a scrolling list
                                        of cards showing each notification*/

    /**
     * @author Robert Fahey
     * When the activity is created, set the layout being displayed to "notification_view",
     * setup the RecyclerView for displaying the notifications,
     * and set an onCLickListener for the back button
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_view);

        //populate the notification content arrays
        notifTitles = getResources().getStringArray(R.array.debug_one);
        contents = getResources().getStringArray(R.array.debug_one);
        notifRecycler = findViewById(R.id.notif_recycler);

        /*
        instantiate the RecyclerView and its adapter, attach the adapter to the recycler view,
        and assign a linear layout manager to the recycler
        */
        NotifRecyclerAdapter notifadapter = new NotifRecyclerAdapter(this, notifTitles, contents);
        notifRecycler.setAdapter(notifadapter);
        notifRecycler.setLayoutManager(new LinearLayoutManager(this));

        /**
        @author Robert Fahey
        When the "Back" button is clicked, end the current activity and return to MainActivity
         */
        findViewById(R.id.notifs_back_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}