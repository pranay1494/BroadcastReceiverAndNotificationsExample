package layout;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.provider.Contacts;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.root.recievebroadcast.MainActivity;
import com.example.root.recievebroadcast.R;

public class MyReceiver extends BroadcastReceiver {
    Notification.Builder notification ;
    private static final int UID = 4321;
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Broadcast Recieved",Toast.LENGTH_SHORT).show();
        notification = new Notification.Builder(context);
        notification.setAutoCancel(true);

        buildNotification(context);
    }

    private void buildNotification(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setSmallIcon(R.drawable.bat);
        notification.setTicker("This is Ticker");
        notification.setContentTitle("Title");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentText("this is body");
        notification.setSound(RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_NOTIFICATION));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notification.addAction(R.mipmap.ic_launcher,"Show Activity",pendingIntent);
        }

        notification.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification1 = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notification1 = new Notification.InboxStyle(notification)
                    .addLine("First message").addLine("Second message")
                    .addLine("Thrid message").addLine("Fourth Message")
                    .setSummaryText("+2 more").build();
        }
        notificationManager.notify(UID,notification1);

    }
}
