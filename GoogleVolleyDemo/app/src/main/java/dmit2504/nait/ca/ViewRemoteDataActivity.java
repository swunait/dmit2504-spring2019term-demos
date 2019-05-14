package dmit2504.nait.ca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ViewRemoteDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_remote_data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Create an instance of the Menu inflater
        MenuInflater inflater = getMenuInflater();
        // Inflate the menu
        inflater.inflate(R.menu.options_menu, menu);
        // Return true if tghe menu inflated OK
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Get the id of the selected menu item
        switch (item.getItemId()) {
            case R.id.menu_send_data:
                // create an intent to start the MainActivity
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
