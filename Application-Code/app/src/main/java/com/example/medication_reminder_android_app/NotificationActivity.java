package com.example.medication_reminder_android_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationActivity extends Activity {

    String[] notifTitles;
    String[] contents;
    RecyclerView notifRecycler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_view);

        notifTitles = getResources().getStringArray(R.array.debug_one);
        contents = getResources().getStringArray(R.array.debug_one);
        notifRecycler = findViewById(R.id.notif_recycler);

        NotifRecyclerAdapter notifadapter = new NotifRecyclerAdapter(this, notifTitles, contents);
        notifRecycler.setAdapter(notifadapter);
        notifRecycler.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.notifs_back_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        //TODO
        super.onResume();
    }

    @Override
    public void onPause() {
        //TODO
        super.onPause();
    }

    @Override
    public void onStop() {
        //TODO
        super.onStop();
    }

    @Override
    public void onDestroy() {
        //TODO
        super.onDestroy();
    }
}