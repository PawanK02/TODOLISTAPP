package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Completed extends AppCompatActivity {
ProgramAdapter pg;
ListView list;
DBconnection db;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);
        db = new DBconnection(this);

        list = (ListView)findViewById(R.id.list);
        String val1="";
        String val2="";
        String val3="";
        String val4="";
        Cursor c = db.FetchAllData();
        ArrayList<String> arr1 = new ArrayList<>();
        ArrayList<String> arr2 = new ArrayList<>();
        ArrayList<String> arr3 = new ArrayList<>();
        ArrayList<String> arr4 = new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                val4 = (String.valueOf(c.getString(c.getColumnIndex("status"))));
                if(val4.equals("Completed:)")) {
                    val1 = (String.valueOf(c.getString(c.getColumnIndex("Tasks"))));
                    val2 = (String.valueOf(c.getString(c.getColumnIndex("date"))));
                    val3 = (String.valueOf(c.getString(c.getColumnIndex("time"))));
                    arr4.add(val4);
                    arr1.add(val1);
                    arr2.add(val2);
                    arr3.add(val3);
                    val4 = "";
                    val1 = "";
                    val2 = "";
                    val3="";


                }
            } while (c.moveToNext());
        }
        pg = new ProgramAdapter(Completed.this, arr1, arr2,arr3,arr4);
        list.setAdapter(pg);
    }
    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(Completed.this,home.class);
        startActivity(intent1);
    }
}