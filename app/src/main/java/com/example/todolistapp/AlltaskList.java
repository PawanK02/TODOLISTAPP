package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class AlltaskList extends AppCompatActivity {
    ListView tasks;
    DBconnection db;
    Button back;
    ProgramAdapter pg;
    ImageButton last;

    @SuppressLint({"MissingInflatedId", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alltask_list);
        tasks = (ListView)findViewById(R.id.listview);
        back = (Button)findViewById(R.id.back);
        db = new DBconnection(this);
        last = (ImageButton)findViewById(R.id.last);
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
                val1 = (String.valueOf(c.getString(c.getColumnIndex("Tasks"))));
                arr1.add(val1);
                val1 = "";
                val2 = (String.valueOf(c.getString(c.getColumnIndex("date"))));
                arr2.add(val2);
                val2 = "";
                val3 = (String.valueOf(c.getString(c.getColumnIndex("time"))));
                arr3.add(val3);
                val3 = "";
                val4 = (String.valueOf(c.getString(c.getColumnIndex("status"))));
                arr4.add(val4);
                val4 = "";

            } while (c.moveToNext());
        }
        pg = new ProgramAdapter(AlltaskList.this, arr1, arr2,arr3,arr4);
        tasks.setAdapter(pg);
        tasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onItemClick(AdapterView<?>parent, View view, int i, long l) {
                String str = (String)tasks.getItemAtPosition(i);
                Intent intent = new Intent(AlltaskList.this,TaskDetails.class);
                intent.putExtra("str",str);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlltaskList.this,home.class);
                startActivity(intent);
            }
        });
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlltaskList.this,Completed.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(AlltaskList.this,home.class);
        startActivity(intent1);
    }
}