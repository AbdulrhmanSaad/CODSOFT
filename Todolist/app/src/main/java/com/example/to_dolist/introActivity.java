package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class introActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Button start =findViewById(R.id.start);

        start.setOnClickListener(view -> {
            Intent intent=new Intent(introActivity.this,MainActivity.class);
            startActivity(intent);


        });
    }
}