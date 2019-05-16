package dmit2504.nait.ca;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    SharedPreferences settings;

    public void sendData(View view) {
        // Find the views in the layout
        final EditText messageEditText = findViewById(R.id.activity_main_message_edittext);
        EditText senderEditText = findViewById(R.id.activity_main_sender_edittext);

        // Retrieve text value from the EditText fields
        final String message = messageEditText.getText().toString();
        final String sender = senderEditText.getText().toString();

        // Validate that the message and sender value are not empty
        if (message.isEmpty() || sender.isEmpty() ) {
            Toast.makeText(this,"Message and Sender values are required",
                    Toast.LENGTH_SHORT).show();
        } else {
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://www.youcode.ca/JitterServlet";

// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            //textView.setText("Response is: "+ response.substring(0,500));
                            Toast.makeText(MainActivity.this,"Send data is successful",
                                    Toast.LENGTH_SHORT).show();
                            // clear the text value from the message EditText field
                            messageEditText.setText("");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //textView.setText("That didn't work!");
                    Log.e("MainActivity", error.getMessage());
                    Toast.makeText(MainActivity.this, "That didn't work!",
                            Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    //return super.getParams();
                    Map<String, String> params = new HashMap<>();
                    params.put("DATA", message);
                    params.put("LOGIN_NAME", sender);
                    return params;
                }
            };

// Add the request to the RequestQueue.
            queue.add(stringRequest);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = PreferenceManager.getDefaultSharedPreferences(this);
        settings.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Create an instance of the Menu inflater
        MenuInflater inflater = getMenuInflater();
        // Inflate the menu
        inflater.inflate(R.menu.options_menu, menu);
        // Return true if the menu inflated OK
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Get the id of the selected menu item
        switch (item.getItemId()) {
            case R.id.menu_view_data: {
                // create an intent to start the ViewRemoteDataActivity
                Intent intent = new Intent(this, ViewRemoteDataActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.menu_settings: {
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            }
            default:
               return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        View layout = findViewById(R.id.activity_main_layout);
        String bgColor = settings.getString("main_bg_color_list","#660000");
        layout.setBackgroundColor(Color.parseColor(bgColor));

    }
}
