package com.example.medication_reminder_android_app.NotificationRelay;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.example.medication_reminder_android_app.UserInputHandler.InputWrapper.InputType;


 /**
    @author: Karley Waguespack
    Last Modified: 03/24/2021

    Description: Receiver class for the ingore intent in MainActivity; listens for ignore requests
    which occur when the user hits "Ignore" on a notification

  */

public class IgnoreReceiver extends BroadcastReceiver{

    private Integer reminderID = new Integer(0);
    private final boolean IGNORED = true; //ignore is equivalent to a true boolean value


    /**
   @author: Karley Waguespack
   Last Modified: 03/24/2021

   Description: this method gets called when the class receives an ignore intent; happens when the user
   presses "ignore" on the notif. Method sends the input handler the associated reminder ID and a boolean
   indicating the notification was ignored so that it may respond appropriately

 */
    @Override
    public void onReceive(Context context, Intent intent){
        //pass the
        reminderID = intent.getParcelableExtra("reminderID");
        OutOfAppNotifications.inputWrapper.processAcknowledgementRequest(InputType.Medication, reminderID, IGNORED);

    }
}
