package com.example.medication_reminder_android_app;

import android.app.Activity;
import android.app.DatePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class InfoInputActivity extends AppCompatActivity {

    private String startDateString;
    private String endDateString;
    private Boolean isStart = true;

    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_input);

        final Calendar cal = Calendar.getInstance();
        EditText name = (EditText) findViewById(R.id.name_editText);
        EditText dosage = (EditText) findViewById(R.id.dosage_editText);
        EditText interval = (EditText)  findViewById(R.id.interval_editText);

        TimePickerDialog.OnTimeSetListener setTimeVariables = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                cal.set(Calendar.HOUR, hour);
                cal.set(Calendar.MINUTE, minute);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
            }
        };

        DatePickerDialog.OnDateSetListener setDateVariables = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                if(isStart){
                    startDateString = dateFormat.format(cal.getTime());
                }else{
                    endDateString = dateFormat.format(cal.getTime());
                }
            }
        };

        DatePickerDialog dpg = new DatePickerDialog(InfoInputActivity.this, setDateVariables,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
        );

        TimePickerDialog tpg = new TimePickerDialog( InfoInputActivity.this , setTimeVariables,
                cal.get(Calendar.HOUR),
                cal.get(Calendar.MINUTE),
                true
        );

        //Pick time button listener
        findViewById(R.id.pick_time_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tpg.show();
            }
        });

        //Pick start day button listener
        findViewById(R.id.pick_day_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = true;
                dpg.show();
            }
        });

        //Pick end day button listener
        findViewById(R.id.pick_end_day_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = false;
                dpg.show();
            }
        });

        //Discard button listener
        findViewById(R.id.discard_med_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name.getText().clear();
                dosage.getText().clear();
                interval.getText().clear();

                finish();
            }
        });

        //Save button listener
        findViewById(R.id.save_med_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call Sam's input method

                Map<String, String> map = new HashMap<>();
                map.put("name", name.getText().toString());
                map.put("dosage", dosage.getText().toString());
                map.put("startDate", startDateString);
                map.put("endDate", endDateString);
                map.put("interval", interval.getText().toString());

                //recurring?


                //call Sam's input method
                //MedicationInputHandler.inputRequest(map)
                //call Karley's input method


                name.getText().clear();
                dosage.getText().clear();
                interval.getText().clear();
                finish();
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
