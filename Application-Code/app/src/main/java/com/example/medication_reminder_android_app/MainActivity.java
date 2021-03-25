package com.example.medication_reminder_android_app;

/*
This class is the equivalent to the main class in Java.
This is the starting point of the code.

https://developer.android.com/guide/components/activities/activity-lifecycle#java
 */

import android.content.BroadcastReceiver;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.example.medication_reminder_android_app.NotificationRelay.AcknowledgeReceiver;
import com.example.medication_reminder_android_app.NotificationRelay.IgnoreReceiver;
import com.example.medication_reminder_android_app.NotificationRelay.NotificationPublisher;
import com.example.medication_reminder_android_app.NotificationRelay.OutOfAppNotifications;
import com.example.medication_reminder_android_app.SQLiteDB.DatabaseRepository;
import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;
import com.example.medication_reminder_android_app.SQLiteDB.MedicationEntity;
import com.example.medication_reminder_android_app.SQLiteDB.ReminderEntity;
import com.example.medication_reminder_android_app.UserInputHandler.InputWrapper;

public class MainActivity extends AppCompatActivity{


    //use mvm instead of database repository
    MainViewModel model;
    InputWrapper input;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_main);
        model = new ViewModelProvider(this).get(MainViewModel.class);
        input = new InputWrapper(model);
        //to call scheduleNotification: outOfAppNotifs.scheduleNotification(reminderID)
        OutOfAppNotifications outOfAppNotifs = new OutOfAppNotifications(model, this);

    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }


}
