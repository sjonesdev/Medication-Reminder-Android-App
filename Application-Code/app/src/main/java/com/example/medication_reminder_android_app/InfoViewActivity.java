package com.example.medication_reminder_android_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InfoViewActivity extends AppCompatActivity implements InfoRecyclerAdapter.OnItemListener{

    private String names[]; //array of medication names to be displayed
    private String dosages[]; //array of dosages to be displayed
    private RecyclerView infoRecycler; /*Recycler view object that with display a scrolling list
                                        of cards showing each medication*/

    /**
     @author Robert Fahey
     When the activity is created, set the current layout being displayed to
     "info_view", set OnClickListeners for the two buttons displayed on the screen, and setup the
     RecyclerView to display the list of medications
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //set the layout to "info_view" when creating the activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_view);

        //populate the medication and dosage arrays
        names = getResources().getStringArray(R.array.debug_one);
        dosages = getResources().getStringArray(R.array.debug_one);

        /*
        instantiate the RecyclerView and its adapter, attach the adapter to the recycler view,
        and assign a linear layout manager to the recycler
        */
        infoRecycler = findViewById(R.id.info_recycler);
        InfoRecyclerAdapter infoadapter = new InfoRecyclerAdapter(this, names, dosages, this);
        infoRecycler.setAdapter(infoadapter);
        infoRecycler.setLayoutManager(new LinearLayoutManager(this));

        /**
         @author Robert Fahey
         When the "Back" button is clicked, end the current activity and return to MainActivity
         */
        findViewById(R.id.info_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         @author Robert Fahey
         When the "Add Notification" button is clicked, change activities to InfoInputActivity
         */
        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoViewActivity.this, InfoInputActivity.class));
            }
        });
    }

    /**
     @author Robert Fahey
     When a medication card in the RecyclerView is clicked change activities to MedViewActivity
     to view the individual medication
     */
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, MedViewActivity.class);
        startActivity(intent);
    }
}
