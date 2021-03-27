package com.example.medication_reminder_android_app;

/*
This class is the equivalent to the main class in Java.
This is the starting point of the code.

https://developer.android.com/guide/components/activities/activity-lifecycle#java
 */

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;

public class SQliteDBMainActivity extends Fragment {

    private MainViewModel mainViewModel;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

}
