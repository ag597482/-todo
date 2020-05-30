package com.example.android.test;


import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.android.test.data.TodoContract.ItemEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int TASK_LOADER = 0;

    ItemAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, EnterItem.class);
                startActivity(intent);
            }
        });


        final ListView todolist = (ListView)findViewById(R.id.list);

        View emptyview = (View)findViewById(R.id.emptyView);
        todolist.setEmptyView(emptyview);


        mCursorAdapter=new ItemAdapter(this,null);
        todolist.setAdapter(mCursorAdapter);

        FloatingActionButton done = (FloatingActionButton) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int cou=0;
                for(int i=0;i<todolist.getChildCount();i++)
                {
                    Object o1=todolist.getChildAt(i);
                    if(o1 instanceof LinearLayout)
                    {
                        Object c1=((LinearLayout) o1).getChildAt(0);
                        if(((CheckBox) c1).isChecked())
                        {
                            cou++;
                            Uri currentUri = ContentUris.withAppendedId(ItemEntry.CONTENT_URI,todolist.getItemIdAtPosition(i));
                            int rowsDeleted = getContentResolver().delete(currentUri, null, null);
                        }
                    }

                }
                if(cou==0)
                {
                    Toast.makeText(MainActivity.this,"No Task Selected",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(MainActivity.this, cou+ " Tasks Completed", Toast.LENGTH_SHORT).show();
                }

                //  Toast.makeText(MainActivity.this,"No Tasks Selected"+todolist.getChildCount()+" "+cou,Toast.LENGTH_SHORT).show();
            }
        });
//        if(todolist.getChildCount()==0)
//        {
//            done.setVisibility(View.INVISIBLE);
//        }
//        else
//        {
//            done.setVisibility(View.VISIBLE);
//        }


//        mTodoHelper=new TodoHelper(this);
//        displayDatabaseInfo();


        todolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                CheckBox cb= (CheckBox)view.findViewById(R.id.name);

//                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    public void onCheckedChanged(CompoundButton cb, boolean isChecked) {
//                        if(cb.isChecked()) {
//                            Toast.makeText(MainActivity.this,"checked"+position,Toast.LENGTH_SHORT).show();
//                            // action
//                        }
//                        else if(isChecked==false) {
//
//                            Toast.makeText(MainActivity.this,"checked"+position,Toast.LENGTH_SHORT).show();
//                            // action
//                        }
//                    }
//                });

                Intent intent = new Intent(MainActivity.this,EnterItem.class);

                Uri currentUri = ContentUris.withAppendedId(ItemEntry.CONTENT_URI,id);

                intent.setData(currentUri);
                startActivity(intent);

            }
        });

       getLoaderManager().initLoader(TASK_LOADER,null,this);


    }

    private void insertPet() {

        ContentValues values = new ContentValues();
        values.put(ItemEntry.COLUMN_TASK_NAME, "Do Exersise");
        values.put(ItemEntry.COLUMN_TASK_DES, "Do a full body workout and do 50 situps per day for a good Abs");

        Uri newUri = getContentResolver().insert(ItemEntry.CONTENT_URI, values);
    }

    private void deleteAllPets() {
        int rowsDeleted = getContentResolver().delete(ItemEntry.CONTENT_URI, null, null);
        Log.v("MainActivity", rowsDeleted + " rows deleted from pet database");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_dummy:
                insertPet();

                return true;
            case R.id.action_delete:
                deleteAllPets();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                ItemEntry._ID,
                ItemEntry.COLUMN_TASK_NAME,
                ItemEntry.COLUMN_TASK_DES };

        return new CursorLoader(this,ItemEntry.CONTENT_URI,projection,null,null,null);

    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }



}
