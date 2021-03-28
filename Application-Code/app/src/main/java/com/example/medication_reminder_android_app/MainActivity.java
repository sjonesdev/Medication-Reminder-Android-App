package com.example.medication_reminder_android_app;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.medication_reminder_android_app.NotificationRelay.OutOfAppNotifications;
import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;
import com.example.medication_reminder_android_app.UserInputHandler.InputWrapper;

public class MainActivity extends AppCompatActivity {

    MainViewModel model;
    InputWrapper inputWrapper;

    /**
    @author Robert
    When the activity is created on app startup, set the current layout being displayed to
    "activity_main" (which includes content_main, the layout file that contains the actual UI
    elements) and set event listeners for the three buttons displayed onscreen
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MainViewModel.class);
        inputWrapper = new InputWrapper(model);
        OutOfAppNotifications outOfAppNotifs = new OutOfAppNotifications(model, this, inputWrapper);
        inputWrapper.setOutOfAppNotifications(outOfAppNotifs);

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
}