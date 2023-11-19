package com.example.todolistapp;

import android.view.View;
import android.widget.TextView;

public class Programview
{
        TextView taske;
        TextView dte;
        TextView tim;
        TextView status;
        Programview(View v){
            taske=v.findViewById(R.id.tasktitle);
            dte = v.findViewById(R.id.timedate);
            tim = v.findViewById(R.id.time);
            status = v.findViewById(R.id.status);
        }
    }

