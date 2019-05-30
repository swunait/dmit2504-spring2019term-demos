package dmit2504.nait.sharedpreferencedemo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class WebsiteListActivity extends AppCompatActivity {

    private ListView mWebsiteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website_list);

        mWebsiteListView = findViewById(R.id.activity_website_list_listview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WebsiteDatabaseHelper dbHelper = new WebsiteDatabaseHelper(this);
        Cursor cursor = dbHelper.retreiveAll();
        // Define mappings from columns to the view
        String[] fromFields = {"name","url"};
        int[] toViews = {R.id.website_list_item_name, R.id.website_list_item_url};
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                R.layout.website_list_item,
                cursor,
                fromFields,
                toViews);
        mWebsiteListView.setAdapter(cursorAdapter);
    }
}
