package com.example.exitpoll;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.exitpoll.db.DB;
import com.example.exitpoll.model.Item;

import java.util.ArrayList;
import java.util.List;

import static com.example.exitpoll.db.DB.COL_ID;
import static com.example.exitpoll.db.DB.COL_IMAGE;
import static com.example.exitpoll.db.DB.COL_NUMBER;
import static com.example.exitpoll.db.DB.COL_TITLE;
import static com.example.exitpoll.db.DB.TABLE_NAME;

public class MainActivity extends AppCompatActivity {
    private DB mHelper;
    private SQLiteDatabase mDb;
    private String vote;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelper = new DB(MainActivity.this);
        mDb = mHelper.getWritableDatabase();
        DB helper = new DB(MainActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();


        Button one = findViewById(R.id.one_button);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote = "one";
                loadPhoneData();
                Toast t = Toast.makeText(MainActivity.this,item.number, Toast.LENGTH_SHORT);
                t.show();
            }
        });
        Button two = findViewById(R.id.two_button);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote = "two";
                loadPhoneData();
                Toast t = Toast.makeText(MainActivity.this,item.number, Toast.LENGTH_SHORT);
                t.show();
            }
        });
        Button three = findViewById(R.id.three_button);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote = "three";
                loadPhoneData();
                Toast t = Toast.makeText(MainActivity.this,item.number, Toast.LENGTH_SHORT);
                t.show();
            }
        });
        Button no = findViewById(R.id.no_vote_button);
       no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote = "no";
                loadPhoneData();
                Toast t = Toast.makeText(MainActivity.this,item.number, Toast.LENGTH_SHORT);
                t.show();
            }
        });

        Button result = findViewById(R.id.see_result_button);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,show_point.class);
                startActivity(intent);
            }
        });
    }
    private void loadPhoneData() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);
        while (c.moveToNext()) {

            long id = c.getLong(c.getColumnIndex(COL_ID));
            String title = c.getString(c.getColumnIndex(COL_TITLE));
            String number = c.getString(c.getColumnIndex(COL_NUMBER));
            String image = c.getString(c.getColumnIndex(COL_IMAGE));
            if(vote.indexOf(title)!= -1) {
                
                item = new Item(id, title, number, image);

            }
        }
        c.close();
    }
}
