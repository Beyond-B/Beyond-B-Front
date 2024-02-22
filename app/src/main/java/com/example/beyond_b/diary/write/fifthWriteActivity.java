package com.example.beyond_b.diary.write;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.beyond_b.R;

public class fifthWriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_write);

        ImageButton cancel = findViewById(R.id.fifth_write_cancel);
        Button preBtn = findViewById(R.id.diary_fifth_write_pre_btn);
        Button nextBtn = findViewById(R.id.diary_fifth_write_next_btn);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fifthWriteActivity.this, fourthWriteActivity.class);
                startActivity(intent);

                finish();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fifthWriteActivity.this, sixthWriteActivity.class);
                startActivity(intent);

                finish();
            }
        });
    }
}