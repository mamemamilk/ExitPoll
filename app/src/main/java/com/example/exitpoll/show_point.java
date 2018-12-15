package com.example.exitpoll;


import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.exitpoll.adapter.NameListAdapter;
import com.example.exitpoll.db.DB;
import com.example.exitpoll.model.Item;

import java.util.ArrayList;
import java.util.List;

import static com.example.exitpoll.db.DB.COL_ID;
import static com.example.exitpoll.db.DB.COL_IMAGE;
import static com.example.exitpoll.db.DB.COL_NUMBER;
import static com.example.exitpoll.db.DB.COL_TITLE;
import static com.example.exitpoll.db.DB.TABLE_NAME;

public class show_point extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private DB mHelper;
    private SQLiteDatabase mDb;
    private List<Item> mPhoneItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_point);

        mHelper = new DB(show_point.this);
        mDb = mHelper.getWritableDatabase();

        Button set = findViewById(R.id.set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData1();

            }
        });

    }
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPhoneData();
        setupListView();
    }
    private void loadPhoneData() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);

        mPhoneItemList = new ArrayList<>();
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(COL_ID));
            String title = c.getString(c.getColumnIndex(COL_TITLE));
            String number = c.getString(c.getColumnIndex(COL_NUMBER));
            String image = c.getString(c.getColumnIndex(COL_IMAGE));

            Item item = new Item(id, title, number,image);
            mPhoneItemList.add(item);

        }
        c.close();
    }
    private void setupListView() {
        NameListAdapter adapter = new NameListAdapter(
                show_point.this,
                R.layout.item,
                mPhoneItemList
        );
        ListView lv = findViewById(R.id.result_list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Item item = mPhoneItemList.get(position);
                Toast t = Toast.makeText(show_point.this,item.number, Toast.LENGTH_SHORT);
                t.show();

            }
        });

    }
    private void loadPhoneData1() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);
        while (c.moveToNext()) {

            long id = c.getLong(c.getColumnIndex(COL_ID));
            String title = c.getString(c.getColumnIndex(COL_TITLE));
            String number = c.getString(c.getColumnIndex(COL_NUMBER));
            String image = c.getString(c.getColumnIndex(COL_IMAGE));
                DB helper = new DB(show_point.this);
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(COL_NUMBER,"0");

                db.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(id)}
                );

        }
        c.close();
    }

}
