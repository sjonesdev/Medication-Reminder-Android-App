package com.example.medication_reminder_android_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InfoViewActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_view);
    }

    @Override
    public void onStart() {
        super.onStart();
        findViewById(R.id.info_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoViewActivity.this, InfoInputActivity.class));
            }
        });
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
