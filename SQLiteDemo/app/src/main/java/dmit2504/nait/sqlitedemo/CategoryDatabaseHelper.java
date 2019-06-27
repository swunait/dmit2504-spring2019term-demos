package dmit2504.nait.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CategoryDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = CategoryDatabaseHelper.class.getSimpleName();
    private static final String CATEGORY_TABLE_NAME = "category_table";

    public CategoryDatabaseHelper(Context context) {
        super(context,"sqlitedemo.db",null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CATEGORY_TABLE_NAME
            + "(_id INTEGER PRIMARY KEY, "
            + " categoryName TEXT )";
        db.execSQL(createTableStatement);
        Log.d(TAG,"Created database table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableStatement = "DROP TABLE IF EXISTS " + CATEGORY_TABLE_NAME;
        db.execSQL(dropTableStatement);
        Log.d(TAG,"Dropped database table");
        onCreate(db);
    }

    public void insert(Category newCategory) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("categoryName",newCategory.getCategoryName());
        db.insert(CATEGORY_TABLE_NAME,null, values);
        Log.d(TAG,"Inserted data into database");
    }

    public Cursor findAll() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"_id","categoryName"};
        String sortOrder = "categoryName ASC";
        Cursor cursor = db.query(CATEGORY_TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);
        return cursor;
    }

    public List<Category> getCategoryList() {
        Cursor cursor = findAll();
        List<Category> categories = new ArrayList<>();
        while (cursor.moveToNext()) {
            Category newCategory = new Category();
            newCategory.setCategoryId(cursor.getInt(0));
            newCategory.setCategoryName(cursor.getString(1));
            categories.add(newCategory);
        }
        return categories;
    }
}
