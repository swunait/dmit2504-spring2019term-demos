package dmit2504.nait.simplenewsreader;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

public class MainActivity extends AppCompatActivity  {

    private static final String API_KEY = "YOUR_API_KEY";
    private TextView mTextView;
    private RecyclerView mRecyclerView;
    private NewsArticleAdapter mRecylerViewAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.activity_main_recyclerView);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decoration);

        String newsUrl = "https://newsapi.org/v2/top-headlines?country=ca&apiKey=" + API_KEY;
        new FetchNewsTask().execute(newsUrl);
    }



    private class FetchNewsTask extends AsyncTask<String, Double, ArticleResponse> {

        @Override
        protected ArticleResponse doInBackground(String... urls) {
            try {
                String responseString = new NetworkAPI().getUrlResponseString(urls[0]);
                Gson gson = new Gson();
                ArticleResponse responseData = gson.fromJson(responseString, ArticleResponse.class);
                return responseData;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArticleResponse result) {
//            super.onPostExecute(result);
            mRecylerViewAdapter = new NewsArticleAdapter(MainActivity.this,result.getArticles());
            mRecyclerView.setAdapter(mRecylerViewAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }

        @Override
        protected void onProgressUpdate(Double... values) {
            super.onProgressUpdate(values);
        }
    }
}
