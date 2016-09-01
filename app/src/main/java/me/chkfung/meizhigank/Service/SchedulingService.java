package me.chkfung.meizhigank.Service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import me.chkfung.meizhigank.R;
import me.chkfung.meizhigank.ui.MainActivity;

/**
 * Created by Fung on 31/08/2016.
 */

public class SchedulingService extends IntentService {
    public static final String TAG = "Scheduling";
    // An ID used to post the notification.
    private static final int NOTIFICATION_ID = 1;

    public SchedulingService() {
        super("SchedulingService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        sendNotification();
        AlarmReceiver.completeWakefulIntent(intent);
    }

    private void sendNotification() {
        NotificationManager mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(getResources().getString(R.string.app_name))
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(getString(R.string.Notification_bigText)))
                        .setContentText(getString(R.string.Notification_content))
                        .setAutoCancel(true);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}