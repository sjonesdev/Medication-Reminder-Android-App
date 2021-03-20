package com.example.medication_reminder_android_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;

public class SettingsActivity extends AppCompatActivity {

    private MainViewModel mvm;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu);
        mvm = new ViewModelProvider(this).get(MainViewModel.class);

        findViewById(R.id.settings_back_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.deleteAll).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                mvm.deleteAllMedications();
                mvm.deleteAllReminders();

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }






}
