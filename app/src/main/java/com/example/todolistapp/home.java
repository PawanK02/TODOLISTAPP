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
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;

public class home extends AppCompatActivity  {
    ListView listview;
    TextView textView5;

    String txt;
    Button Addtask;
    ImageButton alt,img1,img2,img3;
    ProgramAdapter pg;
DBconnection db;
    @SuppressLint({"Range", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Addtask = (Button) findViewById(R.id.addtask);
        textView5 = (TextView) findViewById(R.id.textView5);
        listview = (ListView) findViewById(R.id.listview);
        alt = (ImageButton) findViewById(R.id.ib);
        img1 = (ImageButton) findViewById(R.id.img1);
        img2= (ImageButton) findViewById(R.id.img2);
        img3 = (ImageButton) findViewById(R.id.img3);
        TextView tv5 = (TextView)findViewById(R.id.textView5);
db = new DBconnection(this);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar ca = Calendar.getInstance();
        String date = sdf.format(ca.getTime());
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
                val2 = (String.valueOf(c.getString(c.getColumnIndex("date"))));
                if(val2.equals(date)) {
                    arr2.add(val2);
                    val2 = "";
                    val1 = (String.valueOf(c.getString(c.getColumnIndex("Tasks"))));
                    arr1.add(val1);
                    val1 = "";
                    val3 = (String.valueOf(c.getString(c.getColumnIndex("time"))));
                    arr3.add(val3);
                    val3 = "";
                    val4= (String.valueOf(c.getString(c.getColumnIndex("status"))));
                    arr4.add(val4);
                    val4="";
                }
            }while (c.moveToNext());
            pg = new ProgramAdapter(home.this, arr1, arr2,arr3,arr4);
            listview.setAdapter(pg);
        }


        alt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(home.this,Howtouse.class);
                startActivity(it);
            }
        });
        Addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this,AddTasks.class);
                startActivity(intent);
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onItemClick(AdapterView<?>parent, View view, int i, long l) {
                String str = (String)listview.getItemAtPosition(i);
               Intent intent = new Intent(home.this,TaskDetails.class);
                intent.putExtra("str",str);
                startActivity(intent);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this,Completed.class);
                startActivity(intent);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this,AlltaskList.class);

                startActivity(intent);
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this,Overdue.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(home.this,MainActivity.class);
        startActivity(intent1);
    }
}