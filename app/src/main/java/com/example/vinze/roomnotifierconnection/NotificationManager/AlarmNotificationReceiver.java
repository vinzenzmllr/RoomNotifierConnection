package com.example.vinze.roomnotifierconnection.NotificationManager;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.vinze.roomnotifierconnection.Activities.AddEditReminderActivity;
import com.example.vinze.roomnotifierconnection.R;

public class AlarmNotificationReceiver extends BroadcastReceiver {

    public String title;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.lucidus)
                .setContentTitle("Title")
                .setContentText("Bitte nehmen Sie Ihr Medikament ein!")
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());


    }


}
