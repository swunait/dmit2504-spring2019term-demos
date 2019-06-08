package dmit2504.nait.simplebroadcastreceiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mMessageTextView;
    private SimpleBroadcastReceiver mBroadcastReceiver = new SimpleBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageTextView = findViewById(R.id.activity_main_textview);

        IntentFilter filter = new IntentFilter();
        filter.addAction("ca.nait.dmit2504.SIMPLE_NOTIFICATION");
        registerReceiver(mBroadcastReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(mBroadcastReceiver);
    }

    private class SimpleBroadcastReceiver extends BroadcastReceiver {
        private final String TAG = SimpleBroadcastReceiver.class.getSimpleName();
        private static final String CHANNEL_ID = "dmit2504.nait.simplebroadcastreceiver.CHANNEL_ID";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG,"Simple Broadcast message received");
            // Retreive the broadcast message
            String message = intent.getStringExtra("simpleBroadcastData");
            mMessageTextView.setText(message);

            createNotificationChannel();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.stat_notify_chat)
                    .setContentText(message)
                    .setContentTitle("Broadcast Receiver")
                    .setColorized(true)
                    .setColor(Color.GREEN)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(R.id.activity_main_textview, builder.build());

        }

        private void createNotificationChannel() {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "Simple Channel Name";
                String description = "Simple Channel Description";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);
                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
