package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TaskDetails extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
Button b3,delete,done;
ImageButton back1;
    TextView txt1,txt4,txt2,txt3;
    CheckBox cb1,cb2;
    int day, month, year, hour, minute,myday, myMonth, myYear, myHour, myMinute;
    String status="";
    DBconnection db;
        @SuppressLint({"MissingInflatedId", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        txt1 = (EditText) findViewById(R.id.txt1);
        txt4 = (TextView) findViewById(R.id.txt4);
        txt2 = (TextView) findViewById(R.id.txt2);
        back1 = (ImageButton) findViewById(R.id.back1);
        b3 = (Button)findViewById(R.id.b3);
        cb1 = (CheckBox)findViewById(R.id.cb1);
        cb2 = (CheckBox)findViewById(R.id.cb2);
        delete = (Button)findViewById(R.id.delete);
        done = (Button)findViewById(R.id.done);
        db = new DBconnection(this);
        txt3 = (TextView)findViewById(R.id.txt3);
       String str1= getIntent().getStringExtra("str");
        txt1.setText(str1);
        String val1="";
        String val2="";
        String val3="";
        status = "Pending!! :(";
       Cursor c =  db.FetchData(str1);
        if(c.moveToFirst()){
            do{
                val1+=(String.valueOf(c.getString(c.getColumnIndex("description"))));
                txt3.setText(val1);
                val2+=(String.valueOf(c.getString(c.getColumnIndex("date"))));
                txt2.setText(val2);
                val3+=(String.valueOf(c.getString(c.getColumnIndex("time"))));
                txt4.setText(val3);
            }while(c.moveToNext());
        }
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskDetails.this,home.class);
                startActivity(intent);
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(TaskDetails.this, TaskDetails.this, year, month, day);
                datePickerDialog.show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.deleteRow(str1)==true){
                    Toast.makeText(TaskDetails.this,"Task Deleted Successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TaskDetails.this,home.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(TaskDetails.this,"Not Deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Calendar ca = Calendar.getInstance();
                String sysdate = sdf.format(ca.getTime());
                String inpdate = txt2.getText().toString();
                Date date1 = null;
                Date date2 = null;
                try {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                    date1 = sdf.parse(sysdate);
                    date2 = sdf.parse(inpdate);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(cb1.isChecked()){
                    status = "Completed:)";
                }
            if(cb2.isChecked()){
                    status = "Active:|";
                }
               if(date1.after(date2)){
                    status="OverDue:-";
                }
             if(date1.after(date2) && cb1.isChecked()){
                    status = "Completed:)";
                }
               if(txt1.getText().toString().equals("") || txt2.getText().toString().equals("") || txt4.getText().toString().equals("")) {
                        Toast.makeText(TaskDetails.this, "Failed Please set all fields", Toast.LENGTH_SHORT).show();
                    }
                else if(db.update(txt1.getText().toString(), str1, txt2.getText().toString(), txt4.getText().toString(),txt3.getText().toString(),status)) {
                        Toast.makeText(TaskDetails.this, "Task updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TaskDetails.this, home.class);
                        startActivity(intent);
                    }
                }
        });

    }
    @Override
    public void onTimeSet(TimePicker timePicker, int hr, int min) {
        myHour = hr;
        myMinute = min;
        if(myday <= 9 && myMonth+1 >9) {
            txt2.setText("0" + myday + "-" + (myMonth + 1) + "-" + myYear + "");
        } else if ((myMonth+1)<=9 && myday>9) {
            txt2.setText("" + myday + "-0" + (myMonth + 1) + "-" + myYear + "");
        }
        else if((myMonth+1)<=9 && myday<=9){
            txt2.setText("0" + myday + "-0" + (myMonth + 1) + "-" + myYear + "");
        } else{
            txt2.setText("" + myday + "-" + (myMonth + 1) + "-" + myYear + "");
        }
        if(myMinute<=9){
            txt4.setText("" + myHour + ": 0" +myMinute + "");}
        else {
            txt4.setText("" + myHour + ": " + myMinute + "");
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
        TimePickerDialog timePickerDialog = new TimePickerDialog((Context) TaskDetails.this, (TimePickerDialog.OnTimeSetListener) TaskDetails.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();}
    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(TaskDetails.this,home.class);
        startActivity(intent1);
    }
}