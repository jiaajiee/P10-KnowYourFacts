package com.example.a15017274.p10_knowyourfacts;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScheduledNotificationReceiver extends BroadcastReceiver {

    int reqCode = 12345;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is
        //receiving an Intent broadcast.
        int current = intent.getIntExtra("current", 0);

        Intent i = new Intent(context, MainActivity.class);
        i.putExtra("currents", current);
        PendingIntent pIntent = PendingIntent.getActivity(context, reqCode,
                i, PendingIntent.FLAG_CANCEL_CURRENT);

        // build notification
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Read later!");
        builder.setContentText("Click to read");
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        builder.setContentIntent(pIntent);
        builder.setAutoCancel(true);
        Notification n = builder.build();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(123, n);
    }
}
