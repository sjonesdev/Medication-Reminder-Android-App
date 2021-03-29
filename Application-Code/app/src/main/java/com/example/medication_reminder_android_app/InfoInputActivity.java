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
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.medication_reminder_android_app.NotificationRelay.OutOfAppNotifications;
import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;
import com.example.medication_reminder_android_app.UserInputHandler.InputWrapper;
import com.example.medication_reminder_android_app.UserInputHandler.MedicationInputHandler;

public class InfoInputActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.roomwordssample.REPLY";
    private String startDateString; //state date for reminders, to be passed to the input handler
    private String endDateString; //end date for reminders, to be passed to the input handler
    private Boolean isStart = true;

    private MainViewModel inputMVM;
    private InputWrapper handler;
    private OutOfAppNotifications oan;

    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
    //format string for dates

    /**
     * @author Robert Fahey
     * When the activity is created, do the following:
     * - set the current layout being displayed to "info_input"
     * - create and setup date and time picker dialogs for date and time selection
     * - set onClickListeners for the 5 buttons displayed onscreen
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_input);

        inputMVM = new ViewModelProvider(this).get(MainViewModel.class);
        handler = new InputWrapper(inputMVM);
        oan = new OutOfAppNotifications(inputMVM, this, handler);
        handler.setOutOfAppNotifications(oan);

        //instantiate a java calendar object to generate and store time/date data
        final Calendar cal = Calendar.getInstance();

        //instantiate EditText objects for the text input fields on the screen
        EditText name = (EditText) findViewById(R.id.name_editText);
        EditText dosage = (EditText) findViewById(R.id.dosage_editText);
        EditText interval = (EditText)  findViewById(R.id.interval_editText);


        /**
         * @author Robert & Karley
         * Set the OnTimeSetListener to store the selected time when the "ok" button
         * on the dialog is clicked
         */
        TimePickerDialog.OnTimeSetListener setTimeVariables = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                cal.set(Calendar.HOUR, hour);
                cal.set(Calendar.MINUTE, minute);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
            }
        };

        /**
         * @author Robert & Karley
         * Set the OnDateSetListener to store the selected date when the "ok" button
         * on the dialog is clicked
         */
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

        /*Instantiate a date picker dialog box. Pass in the context,
        the previously set On___SetListener, and the current year, month, and day */
        DatePickerDialog dpg = new DatePickerDialog(InfoInputActivity.this, setDateVariables,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
        );

        /*Instantiate a time picker dialog box. Pass in the context,
        the previously set On___SetListener, the current hour and minute, and set the dialog to
        display in the 24-hour time format */
        TimePickerDialog tpg = new TimePickerDialog( InfoInputActivity.this , setTimeVariables,
                cal.get(Calendar.HOUR),
                cal.get(Calendar.MINUTE),
                true
        );

        /**
         * @author Robert Fahey
         * When the "pick a time" button is clicked, display the time picker prompt
         */
        findViewById(R.id.pick_time_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tpg.show();
            }
        });

        /**
         * @author Robert Fahey
         * When the "start date" button is clicked, set isStart to true and
         * display the date picker prompt
         */
        findViewById(R.id.pick_day_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = true;
                dpg.show();
            }
        });

        /**
         * @author Robert Fahey
         * When the "end date" button is clicked, set isTrue to false and
         * display the date picker prompt
         */
        findViewById(R.id.pick_end_day_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = false;
                dpg.show();
            }
        });

        /**
         * @author Robert Fahey
         * When the "discard" button is clicked, empty the three text input fields,
         * end the current activity, and change the current activity to InfoViewActivity
         */
        findViewById(R.id.discard_med_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name.getText().clear();
                dosage.getText().clear();
                interval.getText().clear();

                finish();
            }
        });

        /**
         * @author Robert Fahey
         * When the "save" button is clicked, compile the information in the text boxes and
         * date strings int a map. Pass the map to the input handler using the inputRequest method
         */
        findViewById(R.id.save_med_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> map = new HashMap<>();
                map.put("name", name.getText().toString());
                map.put("dosage", dosage.getText().toString());
                map.put("startDate", startDateString);
                map.put("endDate", endDateString);
                map.put("interval", interval.getText().toString());

                map.put("warnings", "");
                map.put("activeIngredient", "");
                map.put("purpose", "");
                map.put("userPurpose", "");
                map.put("tags", "");
                map.put("recurring", "true");

                //call Sam's input method
                handler.processInput(InputWrapper.InputType.Medication, map);

                name.getText().clear();
                dosage.getText().clear();
                interval.getText().clear();
                finish();
            }
        });
    }
}
