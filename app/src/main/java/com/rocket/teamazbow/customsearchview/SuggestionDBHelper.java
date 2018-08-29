package com.rocket.teamazbow.customsearchview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SuggestionDBHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SearchSuggestion.db";

    private static String TABLE_NAME = "Suggestions";
    private static String _ID = "id";
    private static String SEARCH_QUERY_TITLE = "title";
    private static String SEARCH_QUERY_DESCRIPTION = "title_DESC";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    SEARCH_QUERY_TITLE + " TEXT," +
                    SEARCH_QUERY_DESCRIPTION + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public SuggestionDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static SuggestionDBHelper instance;

    /**
     * Retrieves the singleton instance of this class.
     *
     * @return singleton instance
     */
    public static SuggestionDBHelper getInstance(Context context) {
        if(null == instance) {
            instance = new SuggestionDBHelper(context);
        }
        return instance;
    }

    public static void removeInstance(){
        instance = null;
    }

    public void saveSearchQuery(String title){
        SQLiteDatabase db = instance.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(SEARCH_QUERY_TITLE,  title);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME, null, values);
    }

    public void saveSearchQuery(String saerchQuery , String query_description){
        SQLiteDatabase db = instance.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(SEARCH_QUERY_TITLE,  saerchQuery);
        values.put(SEARCH_QUERY_DESCRIPTION,  query_description);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME, null, values);
    }




    public List<String> getRecentSearchQuerys() throws IllegalArgumentException{
        SQLiteDatabase db = instance.getReadableDatabase();


        String[] projection = {
                _ID,
                SEARCH_QUERY_TITLE,
                SEARCH_QUERY_DESCRIPTION
        };

        String selection = SEARCH_QUERY_TITLE + " LIKE ?";
        String[] selectionArgs = {  };

        String sortOrder =
                SEARCH_QUERY_TITLE+ " DESC";

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List<String> querys = new ArrayList<>();
        while(cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(SEARCH_QUERY_TITLE));
            querys.add(itemId);
        }
        cursor.close();


        return querys;


    }

    public void clearData(){
        SQLiteDatabase db = instance.getWritableDatabase();

        db.delete(TABLE_NAME, null, null);
    }


}
