package com.example.vinze.roomnotifierconnection.NotificationManager;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.vinze.roomnotifierconnection.R;
import com.example.vinze.roomnotifierconnection.R;

import java.util.Locale;

public class NotificationHelper extends ContextWrapper{
    public static final String channel1ID = "CHANNEL1_ID";
    public static final String channel1NAME = "CHANNEL1_NAME";
    private NotificationManager mManager;


    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createChannels();
    }


    @TargetApi(Build.VERSION_CODES.O)
    public void createChannels(){
        System.out.println("Im create Channels");
        NotificationChannel channel1 = new NotificationChannel(channel1ID, channel1NAME, NotificationManager.IMPORTANCE_HIGH);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.colorPrimary);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        getManager().createNotificationChannel(channel1);

    }

    public NotificationManager getManager() {
        if(mManager == null){
            mManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getChannel1Notification(String Title, String subheading)
    {
        System.out.println("Im gC1N");
        return new NotificationCompat.Builder(getApplicationContext(), channel1ID)

                .setSmallIcon(R.drawable.lucidus)
                .setContentTitle(Title)
                .setContentText(subheading)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setCategory(NotificationCompat.CATEGORY_ALARM);



    }


}
