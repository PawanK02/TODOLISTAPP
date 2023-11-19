package com.example.todolistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ProgramAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> task = new ArrayList<>();
    ArrayList<String> dt = new ArrayList<>();
    ArrayList<String> tm = new ArrayList<>();
    ArrayList<String> stat = new ArrayList<>();
    public ProgramAdapter(Context context, ArrayList<String> task,ArrayList<String> dt,ArrayList<String>tm,ArrayList<String>stat) {
        super( context, R.layout.index,R.id.tasktitle,task);
        this.context = (Context) context;
        this.task = task;
        this.dt = dt;
        this.tm = tm;
        this.stat = stat;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View singleitem = convertView;
        Programview holder = null;
        if(singleitem==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleitem  = layoutInflater.inflate(R.layout.index,parent,false);
            holder = new Programview(singleitem);
            singleitem.setTag(holder);
        }
        else{
            holder = (Programview) singleitem.getTag();
        }
        holder.taske.setText(task.get(position));
        holder.dte.setText(dt.get(position));
        holder.tim.setText(tm.get(position));
        holder.status.setText(stat.get(position));
        return singleitem;
    }
}

