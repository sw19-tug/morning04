package com.group04.dictionary04;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.app.IntentService;



public class NotificationActivity extends Service {



    private static  Long MILLISECS_DAY = 86400000L;


    //used to specify the reminder for the Notification
    //private static long delay = MILLISECS_DAY * 3; //used for the final release
    private static long delay = 60000L; // 1 Min to receive notification after the app got closed(testing)



    @Override
    public void onCreate() {
        super.onCreate();


        SharedPreferences settings = getSharedPreferences(MainActivity.PREFS, MODE_PRIVATE);

        // checking if the NotificationService == enabled
        if (settings.getBoolean("enabled", true)) {
            //checks if the the service has reached the time limit
            if (settings.getLong("checkLast", Long.MAX_VALUE) < System.currentTimeMillis() - delay)
                sendNotification();

        }

        // sets a new alarm for the next notification
        setNotificationAlarm();

        //stops the service
        stopSelf();
    }

    //start when the app gets closed
    public void setNotificationAlarm() {

        Intent serviceIntent = new Intent(this, NotificationActivity.class);
        PendingIntent piIntent = PendingIntent.getService(this, 1234, serviceIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager timeManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        timeManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + delay, piIntent);
    }

    public void sendNotification() {

        Intent calledIntent = new Intent(this, MainActivity.class);

        //@SuppressWarnings("deprecation")
        Notification notification = new Notification.Builder(this)
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(this, 12345, calledIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT))
                .setContentTitle("Reminder")
                .setContentText("Don't forget to learn for your exam!")
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_android)
                .setWhen(System.currentTimeMillis())
                .getNotification();

        NotificationManager notificationManager
                = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(123456, notification);


    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }








}

