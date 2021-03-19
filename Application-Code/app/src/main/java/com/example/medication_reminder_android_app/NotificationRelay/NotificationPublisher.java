package com.example.medication_reminder_android_app.NotificationRelay;


import android.app.Notification ;
import android.app.NotificationChannel ;
import android.app.NotificationManager ;
import android.content.BroadcastReceiver ;
import android.content.Context ;
import android.content.Intent ;
import android.graphics.Color;
import static com.example.medication_reminder_android_app.MainActivity.ANDROID_CHANNEL_ID;
import static com.example.medication_reminder_android_app.MainActivity.ANDROID_CHANNEL_NAME;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificationPublisher extends BroadcastReceiver {

    private String NOTIFICATION_ID;
    private String NOTIFICATION = "notification" ;
    private NotificationChannel notificationChannel;
    public void onReceive (Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE ) ;
        Notification notification = intent.getParcelableExtra( NOTIFICATION ) ;
        createNotificationID(); //sets notification ID member variable

        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            notificationChannel = new NotificationChannel( ANDROID_CHANNEL_ID , ANDROID_CHANNEL_NAME, importance) ;
            // Sets whether notification posted to this channel should vibrate.
            //notificationChannel.enableVibration(true);
            // Sets the notification light color for notifications posted to this channel
            //notificationChannel.setLightColor(Color.GREEN);
            // Sets whether notifications posted to this channel appear on the lockscreen or not
            //notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }
        int id = intent.getIntExtra(NOTIFICATION_ID, 0 );
        assert notificationManager != null;
        notificationManager.notify(id, notification);
    }

    /*
    @author: Aliza Siddiqui
    Last Modified: 03/06/2021
     This method is for generating a unique ID for a specific notification based on the timestamp year/month/day
      hours:minutes: seconds */
    public String createNotificationID(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  //yyyy/MM/dd HH:mm:ss
        LocalDateTime now = LocalDateTime.now();
        this.NOTIFICATION_ID = dtf.format(now);
        return this.NOTIFICATION_ID;
    }

    public String getNotificationName(){
        return this.NOTIFICATION;
    }

    public NotificationChannel getNotificationChannel(){
        return this.notificationChannel;
    }




}