package com.example.beyond_b.diary.write;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.beyond_b.R;

public class firstWriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_write);

        ImageButton cancel = findViewById(R.id.first_write_cancel);

        ImageView happy = findViewById(R.id.diary_happy);
        ImageView depressed = findViewById(R.id.diary_depressed);
        ImageView angry = findViewById(R.id.diary_angry);
        ImageView surprized = findViewById(R.id.diary_surprised);
        ImageView sadness = findViewById(R.id.diary_sadness);
        ImageView worried = findViewById(R.id.diary_worried);


        //취소 버튼 클릭시
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //각 감정 이모지 클릭시
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(firstWriteActivity.this, secondWriteActivity.class);
                startActivity(intent);

                finish();
            }
        });

        depressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(firstWriteActivity.this, secondWriteActivity.class);
                startActivity(intent);

                finish();
            }
        });

        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(firstWriteActivity.this, secondWriteActivity.class);
                startActivity(intent);

                finish();
            }
        });

        surprized.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(firstWriteActivity.this, secondWriteActivity.class);
                startActivity(intent);

                finish();
            }
        });

        sadness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(firstWriteActivity.this, secondWriteActivity.class);
                startActivity(intent);

                finish();
            }
        });

        worried.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(firstWriteActivity.this, secondWriteActivity.class);
                startActivity(intent);

                finish();
            }
        });
    }
}