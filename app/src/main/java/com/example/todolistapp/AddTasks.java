package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddTasks extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
Button date,add;
EditText edt,edt2;
TextView tv,tv1;
DBconnection db;
    String task;
    String dat;
    String tim;
    String txt2;
    ArrayList<String> tasklist = new ArrayList<>();
    ArrayList<String> dtlist = new ArrayList<>();
    ArrayList<String> tmlist = new ArrayList<>();
    ArrayList<String> desc = new ArrayList<>();
    int day, month, year, hour, minute, myday, myMonth, myYear, myHour, myMinute;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);
        date = (Button)findViewById(R.id.date);
        add = (Button)findViewById(R.id.add);
        edt = (EditText) findViewById(R.id.edt);
        edt2 = (EditText) findViewById(R.id.edt2);
        db = new DBconnection(this);
        tv = (TextView)findViewById(R.id.tv);
        tv1 = (TextView)findViewById(R.id.tv1);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTasks.this, AddTasks.this, year, month, day);
                datePickerDialog.show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    Intent intent = new Intent(AddTasks.this,home.class);
                task = edt.getText().toString();
                dat = tv.getText().toString();
                tim = tv1.getText().toString();
                txt2 = edt2.getText().toString();
                if (!task.isEmpty() && !dat.isEmpty() && !tim.isEmpty()) {
                    if(db.inserttask(task,dat,tim,txt2,"Pending!")) {
                        Toast.makeText(AddTasks.this, "Added", Toast.LENGTH_SHORT).show();
                        tv.setText(null);
                        tv1.setText(null);
                        edt2.setText(null);
                        edt.setText(null);
                    }
                    else{
                        Toast.makeText(AddTasks.this,"Failed",Toast.LENGTH_SHORT).show();
                    }
                }
else{
    Toast.makeText(AddTasks.this,"Enter all fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onTimeSet(TimePicker timePicker, int hr, int min) {
        myHour = hr;
        myMinute = min;
        if(myday <= 9 && myMonth+1 >9) {
            tv.setText("0" + myday + "-" + (myMonth + 1) + "-" + myYear + "");
        } else if ((myMonth+1)<=9 && myday>9) {
            tv.setText("" + myday + "-0" + (myMonth + 1) + "-" + myYear + "");
        }
        else if((myMonth+1)<=9 && myday<=9){
            tv.setText("0" + myday + "-0" + (myMonth + 1) + "-" + myYear + "");
        } else{
            tv.setText("" + myday + "-" + (myMonth + 1) + "-" + myYear + "");
        }
        if(myMinute<=9){
            tv1.setText("" + myHour + ": 0" +myMinute + "");}
        else {
            tv1.setText("" + myHour + ": " + myMinute + "");
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        myYear = year;
        myday = day;
        myMonth = month;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(AddTasks.this, AddTasks.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }
    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(AddTasks.this,home.class);
        startActivity(intent1);
    }
}