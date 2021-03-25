package com.example.medication_reminder_android_app.NotificationRelay;

//This class handles the acknowledge/dismiss from the user

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import com.example.medication_reminder_android_app.UserInputHandler.InputWrapper.InputType;

 /**
    @author: Karley Waguespack
    Last Modified: 03/24/2021

    Description: Receiver class for the acknowledge intent in MainActivity; listens for acknowledge requests
    which occur when the user hits "Acknowledge" on a notification.

  */

public class AcknowledgeReceiver extends BroadcastReceiver{


    private Integer reminderID = new Integer(0); //holds reminder ID associated with this request
    private final boolean ACKNOWLEDGED = false; //acknowledged is represented by a false boolean variable


    /**
   @author: Karley Waguespack
   Last Modified: 03/24/2021

   Description: this method gets called when the class receives an acknowledge intent; this code
   gets executed when the user presses "acknowledge." It sends the input handler
   the associated reminder ID and a boolean indicating what action occurred).

 */
    @Override
    public void onReceive(Context context, Intent intent){

        //do stuff here; this is what happens when acknowledge is pressed
        reminderID = intent.getParcelableExtra("reminderID");
        //use the input wrapper object inside of OutOfAppNotifications
        OutOfAppNotifications.inputWrapper.processAcknowledgementRequest(InputType.Medication, reminderID, ACKNOWLEDGED);



    }

}
