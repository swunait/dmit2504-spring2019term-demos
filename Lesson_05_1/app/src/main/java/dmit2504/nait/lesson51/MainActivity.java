package dmit2504.nait.lesson51;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    private EditText mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMessage = findViewById(R.id.activity_main_message);
    }

    public void sendMessage(View view) {
        String message = mMessage.getText().toString();
        if (message == null || message.isEmpty()) {
            Toast.makeText(this,"Message value is required",Toast.LENGTH_SHORT).show();
            Log.e(TAG,"Message value is required");
        } else {
            new Thread(new Runnable() {

                final String message = mMessage.getText().toString();

                @Override
                public void run() {
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
                            mMessage.setText("");
                        } else {
                            Log.i(TAG,"Send message was not successful");
                        }
                    } catch (Exception e) {
                        Log.e(TAG,"Error sending message.");
                    }
                }
            }).start();

//            Integer responseCode = 200;
//            HttpURLConnection connection = null;
//            try {
//                Uri.Builder builder = new Uri.Builder()
//                        .appendQueryParameter("LOGIN_NAME","Lesson 5.1")
//                        .appendQueryParameter("DATA",message);
//                String query = builder.build().getEncodedQuery();
//                URL url = new URL("http://www.youcode.ca/JitterServlet");
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setDoOutput(true);
//                connection.setRequestMethod("POST");
//
//                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
//                writer.write(query);
//                writer.flush();
//                writer.close();
//                responseCode = connection.getResponseCode();
//                if (responseCode == 200) {
//                   Log.i(TAG,"Send message was successful");
//                } else {
//                   Log.i(TAG,"Send message was not successful");
//                }
//            } catch (Exception e) {
//                Log.e(TAG,"Error sending message.");
//            }
        }


    }

    public void sendMessageUsingAsyncTask(View view) {
        String message = mMessage.getText().toString();
        if (message == null || message.isEmpty()) {
            Toast.makeText(this,"Message value is required",Toast.LENGTH_SHORT).show();
            Log.e(TAG,"Message value is required");
        } else {
            new SendMessageTask(mMessage).execute(message);

        }
    }

    public void sendMessageUsingIntentService(View view) {
        String message = mMessage.getText().toString();
        if (message == null || message.isEmpty()) {
            Toast.makeText(this,"Message value is required",Toast.LENGTH_SHORT).show();
            Log.e(TAG,"Message value is required");
        } else {
            Intent intent = new Intent(this, SendMessageIntentService.class);
            intent.putExtra(SendMessageIntentService.EXTRA_PARAM1, message);
            startService(intent);
        }
    }

    private class SendMessageTask extends AsyncTask<String, Void, Void> {

        private EditText messageEditText;


        public SendMessageTask(EditText messageEditText) {
            this.messageEditText = messageEditText;
        }

        @Override
        protected Void doInBackground(String... strings) {
            String message = strings[0];
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
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //super.onPostExecute(aVoid);
            messageEditText.setText("");
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }
    }

}
