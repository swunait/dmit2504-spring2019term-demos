package dmit2504.nait.ca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private final String[] items = {"Item 1","Item 2","Item 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(
          this,
          R.layout.custom_list_item,
          R.id.item_text,
          items
        );
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(dataAdapter);

    }
}
