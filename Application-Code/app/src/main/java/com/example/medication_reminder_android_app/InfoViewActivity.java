package com.example.medication_reminder_android_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class InfoViewActivity extends AppCompatActivity implements InfoRecyclerAdapter.OnItemListener{

    private String names[];
    private String dosages[];
    private RecyclerView infoRecycler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //set the layout to "info_view" when creating the activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_view);

        //get the lists of all medication names and medication dosages
        names = getResources().getStringArray(R.array.debug_one);
        dosages = getResources().getStringArray(R.array.debug_one);

        /*
        instantiate the recycler that will display the medications, instantiate its adapter,
        and set the recycler's adapter and layout manager
        */
        infoRecycler = findViewById(R.id.info_recycler);
        InfoRecyclerAdapter infoadapter = new InfoRecyclerAdapter(this, names, dosages, this);
        infoRecycler.setAdapter(infoadapter);
        infoRecycler.setLayoutManager(new LinearLayoutManager(this));

        /*
        When the "Back" button is clicked, end the InfoViewActivity and return to the MainActivity
         */
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

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, MedViewActivity.class);
        startActivity(intent);
    }
}
