package com.example.beyond_b.diary.view.write;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.beyond_b.R;

public class secondWriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_write);

        Intent intent = getIntent();
        String emotion = intent.getStringExtra("emotion");

        ImageButton cancel = findViewById(R.id.second_write_cancel);
        Button preBtn = findViewById(R.id.diary_second_write_pre_btn);
        Button nextBtn = findViewById(R.id.diary_second_write_next_btn);
        EditText editText = findViewById(R.id.diary_second_write_editText);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(secondWriteActivity.this, firstWriteActivity.class);
                startActivity(intent);

                finish();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String event = editText.getText().toString();
                if(!event.equals("")) {
                    Intent intent = new Intent(secondWriteActivity.this, thirdWriteActivity.class);

                    intent.putExtra("emotion", emotion);
                    intent.putExtra("event", event);

                    startActivity(intent);

                    finish();
                }
            }
        });
    }
}