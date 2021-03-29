package com.example.medication_reminder_android_app;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;
import com.example.medication_reminder_android_app.SQLiteDB.MedicationEntity;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MedViewActivity extends AppCompatActivity {

    private String currentMedString;
    private MainViewModel mavm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_med_view);

        findViewById(R.id.individual_med_back_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.deleteMedButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                mavm.deleteMedication(currentMedString);
                finish();
            }
        });

        currentMedString = getIntent().getStringExtra("current_medication");
        mavm = new ViewModelProvider(this).get(MainViewModel.class);
        mavm.getMedByName(currentMedString).subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<MedicationEntity>() {
            @Override
            public void onSuccess(@NonNull MedicationEntity medicationEntity) {
                onCreateHelper(medicationEntity);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                
            }
        });
    }

    private void onCreateHelper(MedicationEntity currentMedEntity) {
        TextView nameLabel = findViewById(R.id.med_name_view_label);
        TextView dosageLabel = findViewById(R.id.dosage_view_label);
        TextView startLabel = findViewById(R.id.start_date_view_label);
        TextView endLabel = findViewById(R.id.end_date_view_label);
        TextView recurringLabel = findViewById(R.id.recurring_view_label);

        String recur = currentMedEntity.getRecurring() == 1? "Recurring": "Not REcurring";

        nameLabel.setText(currentMedEntity.getMedName());
        dosageLabel.setText(currentMedEntity.getDosage());
        startLabel.setText(currentMedEntity.getFirstDate());
        endLabel.setText(currentMedEntity.getEndDate());
        recurringLabel.setText(recur);
    }
}
