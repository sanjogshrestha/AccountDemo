package np.cnblabs.accountdemo.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

import np.cnblabs.accountdemo.NavigationActivity;
import np.cnblabs.accountdemo.R;

/**
 * Created by sanjogstha on 12/17/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{
    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage != null && remoteMessage.getNotification()!= null ) {
            createNotification(remoteMessage.getNotification().getBody());
        }
    }

    private void createNotification(String body) {
        Intent intent = new Intent(this, NavigationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        String CHANNEL_ID = "lfa_channel";
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,
                CHANNEL_ID);

        notificationBuilder
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("LFA Notification Demo")
                .setContentText(body)
                .setContentIntent(pendingIntent)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(notificationManager != null)
            notificationManager.notify((int) System.currentTimeMillis(), notificationBuilder.build());
    }
}
