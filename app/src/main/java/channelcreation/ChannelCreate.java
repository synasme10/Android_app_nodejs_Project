package channelcreation;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class ChannelCreate {

    Context context;
    public final static String Channel_1="Channel1";
    public final static String Channel_2="Channel2";

    public ChannelCreate(Context context) {
        this.context = context;
    }

    public void createChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel Channel1=new NotificationChannel(
              Channel_1,
              "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            Channel1.setDescription("This is channel 1");

            NotificationChannel Channel2=new NotificationChannel(
                    Channel_2,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            Channel1.setDescription("This is channel 2");

            NotificationManager manager=context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(Channel1);
            manager.createNotificationChannel(Channel2);
        }
    }
}
