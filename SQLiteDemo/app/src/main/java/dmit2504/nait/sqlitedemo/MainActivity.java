package dmit2504.nait.sqlitedemo;

import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mCategoryEditText;
    private Button mSubmitButton;
    private Spinner mCategorySpinner;
    private ListView mCategoryListView;
    private RecyclerView mCategoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the views in the layout
        mCategoryEditText = findViewById(R.id.activity_main_categoryName_editText);
        mSubmitButton = findViewById(R.id.activity_main_submit_button);
        mCategorySpinner = findViewById(R.id.activity_main_spinner);
        mCategoryListView = findViewById(R.id.activity_main_listview);
        mCategoryRecyclerView = findViewById(R.id.activity_main_recyclerview);

        // add a click event listener to the button
        mSubmitButton.setOnClickListener(
                (View view) ->
                {
                    // get the text value from the edit text field
                    String categoryName = mCategoryEditText.getText().toString();
                    // create a new Category and add it to the database
                    Category newCategory = new Category();
                    newCategory.setCategoryName(categoryName);
                    CategoryDatabaseHelper dbHelper = new CategoryDatabaseHelper(this);
                    dbHelper.insert(newCategory);
                    Toast.makeText(this,"Category added!", Toast.LENGTH_SHORT).show();
                    // clear the edit text field
                    mCategoryEditText.setText("");
                    onResume();
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        String[] fromFields = {"_id","categoryName"};
        int[] toViews = {R.id.listitem_categoryId, R.id.listitem_categoryName};

        CategoryDatabaseHelper dbHelper = new CategoryDatabaseHelper(this);
        Cursor categoryCursor = dbHelper.findAll();
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                R.layout.listitem,
                categoryCursor,
                fromFields,
                toViews,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        mCategoryListView.setAdapter(cursorAdapter);

        String[] spinnerFromFields = {"categoryName"};
        int[] spinnerToViews = {R.id.spinneritem_categoryName};
        SimpleCursorAdapter spinnerCursorAdapter = new SimpleCursorAdapter(this,
                R.layout.spinneritem,
                categoryCursor,
                spinnerFromFields,
                spinnerToViews,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        mCategorySpinner.setAdapter(spinnerCursorAdapter);

        List<Category> categories = dbHelper.getCategoryList();
        CategoryRecyclerViewAdapter recyclerViewAdapter = new CategoryRecyclerViewAdapter(this,categories);
        mCategoryRecyclerView.setAdapter(recyclerViewAdapter);
//        mCategoryRecyclerView.setLayoutManager( new LinearLayoutManager(this));
        mCategoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
