package com.example.medication_reminder_android_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;
import com.example.medication_reminder_android_app.SQLiteDB.MedicationEntity;

import java.util.List;
import java.util.ArrayList;

public class InfoViewActivity extends AppCompatActivity implements InfoRecyclerAdapter.OnItemListener{

    private String names[]; //array of medication names to be displayed
    private String dosages[]; //array of dosages to be displayed
    private RecyclerView infoRecycler; /*Recycler view object that with display a scrolling list
                                        of cards showing each medication*/

    private InfoRecyclerAdapter infoadapter;
    private MainViewModel infoMVM;
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

        infoMVM = new ViewModelProvider(this).get(MainViewModel.class);

        //populate the medication and dosage arrays
        names = getResources().getStringArray(R.array.debug_one);
        dosages = getResources().getStringArray(R.array.debug_one);

        /*
        instantiate the RecyclerView and its adapter, attach the adapter to the recycler view,
        and assign a linear layout manager to the recycler
        */
        infoRecycler = findViewById(R.id.info_recycler);
        infoadapter = new InfoRecyclerAdapter(this, names, dosages, this);
        infoRecycler.setAdapter(infoadapter);
        infoRecycler.setLayoutManager(new LinearLayoutManager(this));

        infoMVM.getMeds().observe(this, new Observer<MedicationEntity[]>() {

            @Override
            public void onChanged(MedicationEntity[] medicationEntity) {
                ArrayList<String> medsFromEntities = new ArrayList<>();
                for(MedicationEntity me : medicationEntity){
                    medsFromEntities.add(me.getMedName());
                }
                infoadapter.setWords(medsFromEntities);
            }
        });

        //instantiate a SearchView object
        SearchView medSearch = findViewById(R.id.med_search);
        /* If the contents of the search bar are changed, filter the contents of the RecyclerView by the query*/
        medSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                infoadapter.getFilter().filter(newText);
                return false;
            }
        });

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
