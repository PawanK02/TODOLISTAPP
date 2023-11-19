package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Overdue extends AppCompatActivity {
DBconnection db;
ListView odlist;
ProgramAdapter pg;
    @SuppressLint({"Range", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overdue);
        odlist = (ListView)findViewById(R.id.odlist);
        db= new DBconnection(this);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar ca = Calendar.getInstance();
        String sysdate = sdf.format(ca.getTime());

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
                val4 = (String.valueOf(c.getString(c.getColumnIndex("status"))));
                Date date1 = null;
                Date date2 = null;
                try {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                    date1 = sdf.parse(sysdate);
                    date2 = sdf.parse(val2);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (date1.after(date2) && !val4.equals("Completed:)")){
                arr2.add(val2);
                val2 = "";
                val1 = (String.valueOf(c.getString(c.getColumnIndex("Tasks"))));
                arr1.add(val1);
                val1 = "";
                val3 = (String.valueOf(c.getString(c.getColumnIndex("time"))));
                arr3.add(val3);
                val3 = "";
                arr4.add(val4);
                if(val4.equals("Completed:)") || val4.equals("Active:|")){

                }
                val4 = "";
            }
            } while (c.moveToNext());
        }

        pg = new ProgramAdapter(Overdue.this, arr1, arr2,arr3,arr4);
        odlist.setAdapter(pg);
        odlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onItemClick(AdapterView<?>parent, View view, int i, long l) {
                String str = (String)odlist.getItemAtPosition(i);
                Intent intent = new Intent(Overdue.this,TaskDetails.class);
                intent.putExtra("str",str);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(Overdue.this,home.class);
        startActivity(intent1);
    }
}