package com.example.todolistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOver extends SQLiteOpenHelper {
    private Context context;
    public DBOver(@Nullable Context context) {
        super(context,"DBOver", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table DBDue(Tasks TEXT,date TEXT,time TEXT,description TEXT,status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean inserttask(String task,String date,String time,String desc,String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Tasks",task);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("description",desc);
        cv.put("status",status);
        long res=db.insert("DBDue",null,cv);
        if(res==-1) {
            return false;
        }
        return  true;
    }


}
