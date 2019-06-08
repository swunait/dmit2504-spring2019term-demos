package dmit2504.nait.simpleappwidgetdemo;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import java.util.Random;

public class RandomService extends Service {

    // Broadcast Action when update is complete
    public static final String ACTION_RANDOM_NUMBER
            = "dmit2504.nait.simpleappwidgetdemo.ACTION_RANDOM_NUMBER";

    // Current random number
    private static int sRandomNumber;

    public static int getRandomNumber() {
        return sRandomNumber;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Generate a random number between 1 and 50
        Random randomNumber = new Random();
        sRandomNumber = randomNumber.nextInt(50) + 1;

        // Create the AppWidget view
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.simple_app_widget);
        views.setTextViewText(R.id.simple_app_widget_number,"" + sRandomNumber);

        // Set an intent for the refresh button to start this service again
        PendingIntent refreshIntent = PendingIntent.getService(
                this,
                0,
                new Intent(this,RandomService.class),
                0);
        views.setOnClickPendingIntent(R.id.simple_app_widget_refreshButton, refreshIntent);

        // Set an intent so that tapping the widget text will open the activity
        PendingIntent appIntent = PendingIntent.getActivity(
                this,
                0,
                new Intent(this,MainActivity.class),
                0
        );
        views.setOnClickPendingIntent(R.id.simple_app_widget_title, appIntent);

        // Update the app widget
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        ComponentName widget = new ComponentName(this, SimpleAppWidget.class);
        manager.updateAppWidget(widget, views);

        // Send a broadcast to notify listeners
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_RANDOM_NUMBER);
        sendBroadcast(broadcastIntent);

        // This service should not continue to run
        stopSelf();
        return START_NOT_STICKY;
        //return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
