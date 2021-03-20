package com.example.medication_reminder_android_app;

/*
This class is the equivalent to the main class in Java.
This is the starting point of the code.

https://developer.android.com/guide/components/activities/activity-lifecycle#java
 */

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.widget.*;

import com.example.medication_reminder_android_app.NotificationRelay.NotificationPublisher;

public class MainActivity extends AppCompatActivity{

    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    final Calendar myCalendar = Calendar.getInstance () ;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_main) ;
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



    private void scheduleNotification (Notification notification, long delay) {
        //create a new intent to start the notification publisher
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        //put some extra data in it: the notification's ID and the notification itself (built with notificationCompat)
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID , 1 ) ;
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION , notification) ;
        //create a new pendingIntent to pass onto alarm manager; alarm manager will be able to use the data to send the notif
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT );
        //create a new alarm manager object
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        //send the notification using the specified delay; delay must be ms from now
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP , delay , pendingIntent) ;
    }

    //here we are returning the notification object to be sent to the user
    private Notification getNotification (String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, default_notification_channel_id ) ;
        builder.setContentTitle( "Scheduled Notification" ) ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable.ic_launcher_foreground ) ;
        builder.setAutoCancel( true ) ;
        builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
        return builder.build() ;
    }


    //when the user has finished selecting a date, set the the calendar variables to that date
    DatePickerDialog.OnDateSetListener setDateVariables = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet (DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year) ;
            myCalendar.set(Calendar.MONTH, monthOfYear) ;
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth) ;
        }
    };

    TimePickerDialog.OnTimeSetListener setTimeVariables = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            myCalendar.set(Calendar.HOUR, hour) ;
            myCalendar.set(Calendar.MINUTE, minute);
            myCalendar.set(Calendar.SECOND, 0);
            myCalendar.set(Calendar.MILLISECOND, 0);
            updateLabel();
        }
    };

    //create a datepicker dialog; use the setCalendarVariables listener; set the current date
    //to the current year, month, and day.
    public void setDate (View view) {
        new DatePickerDialog(MainActivity.this, setDateVariables,
                myCalendar.get(Calendar.YEAR ),
                myCalendar.get(Calendar.MONTH ),
                myCalendar.get(Calendar.DAY_OF_MONTH )
        ).show();
    }

    //do the same for the timepicker
    public void setTime(View view){

        new TimePickerDialog(MainActivity.this, setTimeVariables,
                myCalendar.get(Calendar.HOUR),
                myCalendar.get(Calendar.MINUTE),
                true

        ).show();

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy" ; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat , Locale. getDefault ());

        //this is the chosenTime from the user (TODO: need to get specified time from user)
        long chosenTime = myCalendar.getTimeInMillis();
        long currentTime = System.currentTimeMillis();
        long delay = chosenTime - currentTime;
        System.out.println(delay);
        String date = sdf.format(chosenTime);
        scheduleNotification(getNotification(date), delay);
    }

}
