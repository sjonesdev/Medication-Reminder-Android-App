package com.example.medication_reminder_android_app.NotificationRelay;

//This class handles the acknowledge/dismiss from the user

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;

 /*
    @author: Karley Waguespack
    Last Modified: 03/24/2021

    Description: Receiver class for the acknowledge intent in MainActivity; listens for acknowledge requests
    which occur when the user hits "Acknowledge" on a notification.

  */

public class AcknowledgeReceiver extends BroadcastReceiver{


    private Integer reminderID = new Integer(0); //holds reminder ID associated with this request


    /*
   @author: Karley Waguespack
   Last Modified: 03/24/2021

   Description: this method gets called when the class receives an acknowledge intent. In other words,
   this is the code that gets executed when the user presses "acknowledge." It sends the input handler
   some information about the request (including the associated reminder ID and a boolean indicating
   what action occurred).

 */
    @Override
    public void onReceive(Context context, Intent intent){
        //do stuff here; this is what happens when acknowledge is pressed
        reminderID = intent.getParcelableExtra("reminderID");

        //send sam the reminderID and an acknowledge boolean somehow



    }

}
