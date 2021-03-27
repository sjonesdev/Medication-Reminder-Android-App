package com.example.medication_reminder_android_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;

public class SettingsActivity extends AppCompatActivity {

    @Override
    /**
     * @author Lucas Colegrove
     * Set onClickListeners for the three buttons on the screen when SettingsActivity is created
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu);

        /**
         * @author Lucas Colegrove
         * End SettingsActivity and return to MainActivity when the "back" button is clicked
         */
        findViewById(R.id.settings_back_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * @author Lucas Colegrove
         * End the current activity and change to CreditsActivity when the "credits" button is clicked
         */
        findViewById(R.id.to_credits_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, CreditsActivity.class));
            }
        });

        /**
         * @author Lucas Colegrove
         * When the "delete all" button is clicked, the "are you sure" prompt is openened
         */
        findViewById(R.id.deleteAll).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                openDialog();
            }
        });
    }

    /**
    * @author Lucas Colegrove
     * Instantiate and show the "are you sure prompt"
     */
    private void openDialog() {
        DeletionDialog deleteDialog = new DeletionDialog();
        deleteDialog.show(getSupportFragmentManager(), "delete all");
    }
}
