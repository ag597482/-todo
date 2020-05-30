package com.example.android.test.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class TodoContract {

    private TodoContract() {}

    // making a uri;


    public static final String CONTENT_AUTHORITY = "com.example.android.test";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_TODO = "todo";

    public static final class ItemEntry implements BaseColumns {


        public final static String TABLE_NAME="todo";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TODO);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TODO;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TODO;



        public final static String _ID=BaseColumns._ID;

        public final static String COLUMN_TASK_NAME="task";
        public final static String COLUMN_TASK_DES="des";

    }
}
