package dmit2504.nait.sharedpreferencedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WebsiteDatabaseHelper extends SQLiteOpenHelper {

    public WebsiteDatabaseHelper(Context context) {
        super(context,"websites.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create the database tables
        db.execSQL("CREATE TABLE website_table ("
            + "_id INTEGER PRIMARY KEY, "
            + "name TEXT, "
            + "url TEXT )"
        );
        db.execSQL("INSERT INTO website_table(name,url) values('Home Depot','https://www.homedepot.ca')");
        db.execSQL("INSERT INTO website_table(name,url) values('Android Developer','https://developer.android.com')");
        //db.execSQL("INSERT INTO website_table(name,url) values('NAIT Moodle','https://moodle.nait.ca')");
        ContentValues values = new ContentValues();
        values.put("name","NAIT Moodle Site");
        values.put("url","https://moodle.nait.ca");
        long newRowId = db.insert("website_table",null,values);
        Log.i("WebsiteDatabaseHelper","newRowId = " + newRowId);
    }

    public Cursor retreiveAll() {
        String[] columns = {"_id","name","url"};
        String sortOrder = "name ASC";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("website_table",
                columns,
                null,
                null,
                null,
                null,
                sortOrder);
        return cursor;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
