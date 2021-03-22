package com.example.medication_reminder_android_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    /*
    @author Robert
    When the activity is created on app startup, set the current layout being displayed to
    "activity_main" and set event listeners for the three buttons displayed onscreen
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
        @author Robert
        When the "View My Info button is clicked", swap activities from MainActivity to InfoViewActivity
         */
        findViewById(R.id.viewInfoButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoViewActivity.class));
            }
        });

        /**
        @author Robert
         When the "Notifications" button is clicked, swap activities from MainActivity to NotificationActivity
         */
        findViewById(R.id.viewNotifsButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            }
        });

        /**
        @author Robert
        When the "Settings" button is clicked, swap activities from MainActivity to SettingsActivity
         */
        findViewById(R.id.viewSettingsButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
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