package dmit2504.nait.sharedpreferencedemo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class WebsiteListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String EXTRA_URL = "ca.nait.dmit2504.WebsiteListActivity.URL";

    private ListView mWebsiteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website_list);

        mWebsiteListView = findViewById(R.id.activity_website_list_listview);
        mWebsiteListView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WebsiteDatabaseHelper dbHelper = new WebsiteDatabaseHelper(this);
        Cursor cursor = dbHelper.retreiveAll();
        // Define mappings from columns to the view
        String[] fromFields = {"name","url"};
        int[] toViews = {R.id.website_list_item_name, R.id.website_list_item_url};
//        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
//                R.layout.website_list_item,
//                cursor,
//                fromFields,
//                toViews);
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                R.layout.website_list_item,
                cursor,
                fromFields,
                toViews,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mWebsiteListView.setAdapter(cursorAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        WebsiteDatabaseHelper dbHelper = new WebsiteDatabaseHelper(this);
        Website currentWebsite = dbHelper.retrieveAllWebsites().get(position);
        Toast.makeText(this,currentWebsite.name, Toast.LENGTH_SHORT).show();
        // Create an intent for the WebsiteViewActivity and pass the url to the intent
        Intent intent = new Intent(this, WebsiteViewActivity.class);
        intent.putExtra(EXTRA_URL, currentWebsite.url);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_website_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_website_list_menu_new_website:
                // process menu selection
                Intent intent = new Intent(this, WebsiteEntryActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
