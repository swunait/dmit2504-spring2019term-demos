package dmit2504.nait.spinnerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner mCategorySpinner;
    private Spinner mCategorySpinner2;

    public void changedCategory(View view) {
        String selectedCategory = (String) mCategorySpinner2.getSelectedItem();
        Toast.makeText(this,selectedCategory,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCategorySpinner = findViewById(R.id.category1Spinner);
        mCategorySpinner.setOnItemSelectedListener(this);

        mCategorySpinner2 = findViewById(R.id.category2Spinner);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] categories = getResources().getStringArray(R.array.category_entries);
        String selectedCategory = categories[position];
        Toast.makeText(this,selectedCategory, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
