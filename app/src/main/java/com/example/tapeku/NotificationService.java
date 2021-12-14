package com.example.tapeku;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class  NotificationService extends FirebaseMessagingService {

    public static final String TAG = "MyTag";

    public void showNotif(Context context, String judul, String isi, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent resultIntent = new Intent(this, HomeActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        int idNotification = 0;

        // Untuk vesi Oreo
        String channelID = "TapeKu";
        String channelName = "TapeKu Reminder";

        int importance = NotificationManager.IMPORTANCE_HIGH;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(channelID, channelName,importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelID)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.icontapeku)
                .setContentTitle(judul)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(isi))
                .setPriority(Notification.PRIORITY_MAX)
                .setContentIntent(pi)
                .setContentText(isi);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        notificationManager.notify(idNotification++, mBuilder.build());
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String judul = remoteMessage.getNotification().getTitle();
        String isi = remoteMessage.getNotification().getBody();

        showNotif(getApplicationContext(), judul, isi, new Intent());
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        Log.d(TAG, "onDeletedMessage: Called");
    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d(TAG, "NewToken: " +token);
    }
}
