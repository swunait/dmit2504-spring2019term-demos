package dmit2504.nait.simplenewsreader;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "yourAPIKEY";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.activity_main_textview);

        String newsUrl = "https://newsapi.org/v2/top-headlines?country=ca&apiKey=" + API_KEY;
        // Make a request using HttpURLConnection to the newsUrl and display the response in the textview

//        try {
//            String responseString = new NetworkAPI().getUrlResponseString(newsUrl);
//            TextView textView = findViewById(R.id.activity_main_textview);
//            textView.setText(responseString);
//        } catch (IOException e) {
//            Log.e("MainActivity", e.getMessage());
//            Toast.makeText(this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//        new FetchNewsTask().execute(newsUrl);
        TextView textView = findViewById(R.id.activity_main_textview);
        new FetchNewsTask().execute(newsUrl);
    }

    private class FetchNewsTask extends AsyncTask<String, Double, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                String responseString = new NetworkAPI().getUrlResponseString(urls[0]);
                JSONObject json = new JSONObject(responseString);
                int totalResults = json.getInt("totalResults");
                JSONArray articles = json.getJSONArray("articles");
                JSONObject article1 = articles.getJSONObject(0);
                return article1.getString("title");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
            mTextView.setText(result);
        }

        @Override
        protected void onProgressUpdate(Double... values) {
            super.onProgressUpdate(values);
        }
    }
}
