package com.example.medication_reminder_android_app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class InfoInputActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_input);

        findViewById(R.id.discard_med_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //empty text boxes before finishing
                finish();
            }
        });

        findViewById(R.id.save_med_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call Sam's input method
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
