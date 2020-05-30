package com.example.android.test.data;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.test.data.TodoContract.ItemEntry;

public class TodoHelper extends SQLiteOpenHelper {


    public static final String LOG_TAG = TodoHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 1;

    public TodoHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String SQL_CREATE_TABLE =
                "CREATE TABLE " + ItemEntry.TABLE_NAME + " ("
                        + ItemEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + ItemEntry.COLUMN_TASK_NAME + " TEXT, "
                        + ItemEntry.COLUMN_TASK_DES + " TEXT);";

         db.execSQL(SQL_CREATE_TABLE);

//         String SQL_DELETE_ENTRIES =
//                "DROP TABLE IF EXISTS " + ItemEntry.TABLE_NAME;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
