package broadcaste;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;


import com.e.contracterandworkerfinder.R;

import channelcreation.ChannelCreate;

public class BroadcastReceiverDayjob extends BroadcastReceiver {

    private NotificationManagerCompat notificationManagerCompat;
    Context context;

    public BroadcastReceiverDayjob(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean noConnectivity;
        notificationManagerCompat=NotificationManagerCompat.from(context);

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            noConnectivity=intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);

            if (noConnectivity){
                Toast.makeText(context, "Wi-Fi Disconnected", Toast.LENGTH_SHORT).show();
                nowifi();
            }
            else
            {
                Toast.makeText(context, "Wi-Fi Connected", Toast.LENGTH_SHORT).show();
                wifi();
            }

        }
    }


    private void nowifi() {
        Notification notification =new NotificationCompat.Builder(context, ChannelCreate.Channel_1)
                .setSmallIcon(R.drawable.ic_signal_wifi_off_black_24dp)
                .setContentTitle("No Connection")
                .setContentTitle("Please Check your Network Connection..")
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .build();
        notificationManagerCompat.notify(1,notification);
    }


    private void wifi() {
        Notification notification = new NotificationCompat.Builder(context, ChannelCreate.Channel_2)
//        Notification notification = new NotificationCompat.Builder(context, ChannelCreate.Channel_1)
                .setSmallIcon(R.drawable.ic_wifi_black_24dp)
                .setContentTitle("Connected")
                .setContentTitle("Wi-Fi Connected ")
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .build();
        notificationManagerCompat.notify(2, notification);
    }

}
