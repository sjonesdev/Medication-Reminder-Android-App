package com.example.medication_reminder_android_app.NotificationRelay;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.example.medication_reminder_android_app.SQLiteDB.DatabaseRepository;


 /*
    @author: Karley Waguespack
    Last Modified: 03/24/2021

    Description: Receiver class for the ingore intent in MainActivity; listens for ignore requests
    which occur when the user hits "Ignore" on a notification

  */

public class IgnoreReceiver extends BroadcastReceiver{

    private Integer reminderID = new Integer(0);


    /*
   @author: Karley Waguespack
   Last Modified: 03/24/2021

   Description: this method gets called when the class receives an ignore intent. In other words,
   this is the code that gets executed when the user presses "ignore." It sends the input handler
   some information about the request (including the associated reminder ID and a boolean indicating
   what action occurred)

 */
    @Override
    public void onReceive(Context context, Intent intent){
       //do stuff here; this is what happens when ignore is pressed
        reminderID = intent.getParcelableExtra("reminderID");

    }
}
