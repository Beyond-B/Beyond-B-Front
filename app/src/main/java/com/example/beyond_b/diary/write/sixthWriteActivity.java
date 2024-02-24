package com.example.beyond_b.diary.write;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.beyond_b.R;

public class sixthWriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth_write);

        ImageButton cancel = findViewById(R.id.sixth_write_cancel);
        Button preBtn = findViewById(R.id.diary_sixth_write_pre_btn);
        Button doneBtn = findViewById(R.id.diary_sixth_write_next_btn);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sixthWriteActivity.this, firstWriteActivity.class);
                startActivity(intent);

                finish();
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}