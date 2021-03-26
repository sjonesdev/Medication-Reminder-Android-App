package com.example.medication_reminder_android_app;

/*
This class is the equivalent to the main class in Java.
This is the starting point of the code.

https://developer.android.com/guide/components/activities/activity-lifecycle#java
 */

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import com.example.medication_reminder_android_app.NotificationRelay.OutOfAppNotifications;
import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;
import com.example.medication_reminder_android_app.SQLiteDB.MedicationEntity;
import com.example.medication_reminder_android_app.UserInputHandler.InputWrapper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{


    //private Application app = this.getApplication();
    private MainViewModel model;
    private InputWrapper input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = new InputWrapper(model);
        OutOfAppNotifications outOfAppNotifications = new OutOfAppNotifications(model, this, input);
        input.provideOutOfAppNotificationsObject(outOfAppNotifications);
        model = new ViewModelProvider(this).get(MainViewModel.class);
        Log.d("app-debug", "MVM & IW initialized");
        //Log.d("app-debug", String.format("%d",runTests()));

        findViewById(R.id.viewInfoButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("app-debug", String.format("%d",runTests()));
                //startActivity(new Intent(MainActivity.this, InfoViewActivity.class));
            }
        });

        findViewById(R.id.viewNotifsButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            }
        });

        findViewById(R.id.viewSettingsButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }


    private int runTests() {
        Log.d("app-debug", "Running Tests");
        HashMap<String,String> med = new HashMap<>();

        Log.d("app-debug", "makeMed");
        makeMed(med, "med", "100 mg", "2021-03-21", "2021-03-30",
                "1", "don't take too much", "crack",
                "pain relief", "I'm hurtin'", "gamer,fook", true);
        Log.d("app-debug", "makeMed successful");

        HashMap<String,String> temp = new HashMap<>();
        Log.d("app-debug", "temp made");

        temp.putAll(med);
        Log.d("app-debug", "med copied");

        long id = 0;
        //inputWrapper.processInput(InputType.Medication, temp);
        try {
            id = model.insertMedication("med", "100 mg", true, "2021-03-21 08:00", "2021-03-30 08:00",
                    "1", "don't take too much", "crack",
                    "pain relief");
        } catch(Exception e) {
            Log.d("app-debug", e.toString());
        }
        try { Thread.sleep(5000); } catch(Exception e) {};
        Log.d("app-debug", "input inputted");


        //MedicationEntity[] m = mainViewModel.getMeds().getValue();
        MedicationEntity med2 = model.repository.getMedByName("med");
        MedicationEntity med3 = model.repository.getMedById(id);
        Log.d("app-debug", "med gotted");


        /*try {
            MedicationEntity medE = m[0];
            Log.d("app-debug", "med isolated");
            String str = getMedArr(m[0]).toString();
            Log.d("app-debug", "str gotted");
            Log.d("app-debug", str);
        } catch(Exception e) { Log.d("app-debug", e.toString()); }*/


        String str = getMedArr(med2).toString();
        Log.d("app-debug", "str gotted");
        Log.d("app-debug", str);

        return 0;
    }

    private void makeMed(Map<String,String> map, String name, String dosage, String startDate, String endDate, String interval,
                         String warnings, String activeIngredient, String purpose, String userPurpose,
                         String tags, boolean recurring) {
        map.put("name", name);
        map.put("dosage", dosage);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("interval", interval);
        map.put("warnings", warnings);
        map.put("activeIngredient", activeIngredient);
        map.put("purpose", purpose);
        map.put("userPurpose", userPurpose);
        map.put("tags", tags);
        map.put("recurring", String.format("%s", recurring));
    }

    private String[] getMedArr(MedicationEntity m) {
        String[] arr = new String[9];
        for(int i = 0; i < 9; i++) arr[i] = "";
        try {
            arr[0] = m.getMedName();
            arr[1] = m.getDosage();
            arr[2] = m.getFirstDate();
            arr[3] = m.getEndDate();
            arr[4] = m.getTimeRule();
            arr[5] = m.getWarnings();
            arr[6] = m.getIngredients();
            arr[7] = m.getTags();
            arr[8] = String.format("%s", m.getRecurring());
            return arr;
        } catch(Exception e) {
            Log.d("app-debug", e.toString());
        }
        return arr;
    }

}
