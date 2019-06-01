package dmit2504.nait.sharedpreferencedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WebsiteEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website_entry);
    }

    public void saveWebsite(View view) {
        Website newWebsite = new Website();
        EditText nameEditText = findViewById(R.id.activity_website_entry_name);
        EditText urlEditText = findViewById(R.id.activity_website_entry_url);
        newWebsite.name = nameEditText.getText().toString();
        newWebsite.url = urlEditText.getText().toString();
        WebsiteDatabaseHelper dbHelper = new WebsiteDatabaseHelper(this);
        dbHelper.addWebsite(newWebsite);
        // Clear the name and url fields
        nameEditText.setText("");
        urlEditText.setText("");
        Toast.makeText(this,"Save was successful", Toast.LENGTH_SHORT).show();
    }

    public void cancel(View view) {
        finish();
    }
}
