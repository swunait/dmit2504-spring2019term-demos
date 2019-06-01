package dmit2504.nait.sharedpreferencedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

    public long addWebsite(Website newWebsite) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", newWebsite.name);
        values.put("url", newWebsite.url);
        long newRowId = db.insert("website_table",null,values);
        Log.i("WebsiteDatabaseHelper","newRowId = " + newRowId);
        newWebsite.id = (int) newRowId;
        return newRowId;
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

    public List<Website> retrieveAllWebsites() {
        List<Website> websites = new ArrayList<>();
        Cursor websitesCursor = retreiveAll();
        while (websitesCursor.moveToNext()) {
            Website currentWebsite = new Website();
            currentWebsite.id = websitesCursor.getInt(0);
            currentWebsite.name = websitesCursor.getString(1);
            currentWebsite.url = websitesCursor.getString(2);
            websites.add(currentWebsite);
        }

        return websites;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
