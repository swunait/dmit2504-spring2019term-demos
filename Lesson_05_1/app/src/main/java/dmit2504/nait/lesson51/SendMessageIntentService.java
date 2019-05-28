package dmit2504.nait.lesson51;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class SendMessageIntentService extends IntentService {

    private static final String TAG = "SendMessageIntentService";

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_FOO = "dmit2504.nait.lesson51.action.FOO";
    public static final String ACTION_BAZ = "dmit2504.nait.lesson51.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "dmit2504.nait.lesson51.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "dmit2504.nait.lesson51.extra.PARAM2";

    public SendMessageIntentService() {
        super("SendMessageIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String message = intent.getStringExtra(EXTRA_PARAM1);
            // copy and paste hold from sendMessage method to here
            Integer responseCode = 200;
            HttpURLConnection connection = null;
            try {
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("LOGIN_NAME","Lesson 5.1")
                        .appendQueryParameter("DATA",message);
                String query = builder.build().getEncodedQuery();
                URL url = new URL("http://www.youcode.ca/JitterServlet");
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(query);
                writer.flush();
                writer.close();
                responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                   Log.i(TAG,"Send message was successful");
                } else {
                   Log.i(TAG,"Send message was not successful");
                }
            } catch (Exception e) {
                Log.e(TAG,"Error sending message.");
            }

//            final String action = intent.getAction();
//            if (ACTION_FOO.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//                handleActionFoo(param1, param2);
//            } else if (ACTION_BAZ.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//                handleActionBaz(param1, param2);
//            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
