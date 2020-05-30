package com.example.android.test;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.test.data.TodoContract;

public class ItemAdapter extends CursorAdapter {



    public ItemAdapter(Context context, Cursor c)
    {
        super(context, c, 0 /* flags */);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

    }

//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//
//        return super.getView(position, convertView, parent);
//    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        CheckBox taskTextView = (CheckBox) view.findViewById(R.id.name);
        TextView discTextView = (TextView) view.findViewById(R.id.summary);



        // Find the columns of pet attributes that we're interested in
        int taskColumnIndex = cursor.getColumnIndex(TodoContract.ItemEntry.COLUMN_TASK_NAME);
        int discColumnIndex = cursor.getColumnIndex(TodoContract.ItemEntry.COLUMN_TASK_DES);

        // Read the pet attributes from the Cursor for the current pet
        String petName = cursor.getString(taskColumnIndex);
        String petBreed = cursor.getString(discColumnIndex);

        //taskTextView.setChecked(true);
        // Update the TextViews with the attributes for the current pet


        taskTextView.setText(petName);
        discTextView.setText(petBreed);

    }
}