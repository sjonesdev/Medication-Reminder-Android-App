package com.example.medication_reminder_android_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CreditsActivity extends AppCompatActivity {
    /**
     * @author Robert Fahey
     * When the activity is created, set the current layout being displayed to "credits_menu",
     * and set the OnClickListener for the back button
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits_menu);

        /**
         * @author Robert Fahey
         * When the "Back" button is clicked, end the current activity and return to SettingsActivity
         */
        findViewById(R.id.credits_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
