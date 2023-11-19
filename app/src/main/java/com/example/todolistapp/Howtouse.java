package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Howtouse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howtouse);
    }
    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(Howtouse.this,home.class);
        startActivity(intent1);
    }
}