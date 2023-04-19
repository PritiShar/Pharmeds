package com.priti.Pharmeds;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class
NotificationService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

    }
    public void showNotification(String title,String message) {
//        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.splash_screen_logo, null);
//        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
//        Bitmap largeIcon = bitmapDrawable.getBitmap();

//
//        Intent iNotify = new Intent(getApplicationContext(), News_page.class);
//        iNotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pi = PendingIntent.getActivity(this, REQ_CODES, iNotify, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotifications")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);
//                .setContentIntent(pi)
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(999, builder.build());


    }
}
