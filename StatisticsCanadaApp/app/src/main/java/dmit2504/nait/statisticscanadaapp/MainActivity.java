package dmit2504.nait.statisticscanadaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String[] sYEARS = {"2019"
            ,"2018","2017","2016","2015","2014","2013"};
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = findViewById(R.id.activity_main_spinner);
        ArrayAdapter<String> yearDataAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                sYEARS);
        mSpinner.setAdapter(yearDataAdapter);
    }

    public void changeYear(View view) {
        final String selectedYear = mSpinner.getSelectedItem().toString();
        //Toast.makeText(this,"Year " + selectedYear, Toast.LENGTH_SHORT).show();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www150.statcan.gc.ca/n1/dai-quo/ssi/homepage/schedule-key_indicators-eng.json";
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        RecyclerView keyIndicatorRecyclerView = findViewById(R.id.activity_main_recyclerview);
                        RecyclerViewAdapter dataAapter = new RecyclerViewAdapter(MainActivity.this);
                        keyIndicatorRecyclerView.setAdapter(dataAapter);
                        keyIndicatorRecyclerView.setLayoutManager(
                                new LinearLayoutManager(MainActivity.this));
                        keyIndicatorRecyclerView.addItemDecoration(
                                new DividerItemDecoration(MainActivity.this,
                                        DividerItemDecoration.VERTICAL));
                        for (int index = 0; index < response.length(); index++) {

                            try {
                                JSONObject currentJsonObject = response.getJSONObject(index);
                                KeyIndicator currentItem = new KeyIndicator();
                                currentItem.setDate(currentJsonObject.getString("date"));
                                currentItem.setDescription(currentJsonObject.getString("description"));
                                currentItem.setTitle(currentJsonObject.getString("title"));
                                currentItem.setType(currentJsonObject.getString("type"));
                                currentItem.setUrl(currentJsonObject.getString("url"));

                                String statYear = currentJsonObject.getString("date").substring(0,4);
                                if (selectedYear.equalsIgnoreCase(statYear)) {
                                    dataAapter.addKeyIndicator(currentItem);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(MainActivity.this,
                                        e.getMessage(),Toast.LENGTH_SHORT).show();
                                Log.e("MainActivity",e.getMessage());
                            }

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(request);
    }
}
