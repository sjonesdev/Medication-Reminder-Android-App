package com.example.medication_reminder_android_app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;
import com.example.medication_reminder_android_app.SQLiteDB.MedicationEntity;

public class MedViewActivity extends AppCompatActivity {

    private MainViewModel mavm;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_med_view);

        mavm = new ViewModelProvider(this).get(MainViewModel.class);

        findViewById(R.id.individual_med_back_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.deleteMedButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {


            }
        });
    }







    }
