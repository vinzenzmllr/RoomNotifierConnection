package com.example.vinze.roomnotifierconnection.NotificationManager;

import android.app.AlarmManager;
import android.app.AppComponentFactory;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.vinze.roomnotifierconnection.Activities.MainActivity;

import java.sql.Time;
import java.util.Calendar;

public class AlarmSetter extends AppCompatActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public void startAlarm(Calendar calendar) {
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;

            myIntent = new Intent(this,AlarmNotificationReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this,0,myIntent,0);
            manager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+calendar.getTimeInMillis(),pendingIntent);

    }
}
