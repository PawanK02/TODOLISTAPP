package com.example.todolistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBconnection extends SQLiteOpenHelper {
    private Context context;
    public DBconnection(@Nullable Context context) {
        super(context,"DBTasks", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table DBTables(Tasks TEXT,date TEXT,time TEXT,description TEXT,status TEXT)");
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
        long res=db.insert("DBTables",null,cv);
        if(res==-1) {
            return false;
        }
        return  true;
    }
    public Cursor FetchData(String str1)
    {
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor c=database.rawQuery("Select * from DBTables where Tasks = '"+str1+"'",null);
        return c;
    }
    public Cursor FetchAllData()
    {
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor c=database.rawQuery("Select * from DBTables ",null);
        return c;
    }
    public boolean update(String newtask,String oldtask,String dt,String tm,String desc,String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if(newtask.equals("") && dt.equals("") && tm.equals("")){
            return false;
        }
        else {
            cv.put("Tasks",newtask);
            cv.put("date", dt);
            cv.put("time", tm);
            cv.put("description",desc);
            cv.put("status",status);
            db.update("DBTables", cv, "Tasks='" + oldtask + "'", null);
            db.close();
            return true;
        }
    }
    public boolean deleteRow(String values)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("DBTables","Tasks='"+values+"'",null);
        db.close();
        return true;
    }

}
