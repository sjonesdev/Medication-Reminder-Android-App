package com.example.medication_reminder_android_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InfoViewActivity extends AppCompatActivity implements InfoRecyclerAdapter.OnItemListener{

    String s1[];
    RecyclerView infoRecycler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_view);

        s1 = getResources().getStringArray(R.array.debug_one); //This will likely be replaced with getting the info using Haley's methods
        infoRecycler = findViewById(R.id.info_recycler);

        InfoRecyclerAdapter infoadapter = new InfoRecyclerAdapter(this, s1, this);
        infoRecycler.setAdapter(infoadapter);
        infoRecycler.setLayoutManager(new LinearLayoutManager(this));
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

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, MedViewActivity.class);
        startActivity(intent);
    }
}
