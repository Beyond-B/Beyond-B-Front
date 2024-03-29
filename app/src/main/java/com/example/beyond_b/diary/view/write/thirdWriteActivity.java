package com.example.beyond_b.diary.view.write;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.beyond_b.R;

public class thirdWriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_write);

        Intent intent = getIntent();
        String emotion = intent.getStringExtra("emotion");
        String event = intent.getStringExtra("event");

        ImageButton cancel = findViewById(R.id.third_write_cancel);
        Button preBtn = findViewById(R.id.diary_third_write_pre_btn);
        Button nextBtn = findViewById(R.id.diary_third_write_next_btn);
        EditText editText = findViewById(R.id.diary_third_write_editText);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thirdWriteActivity.this, secondWriteActivity.class);
                startActivity(intent);

                finish();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thought = editText.getText().toString();
                if(!thought.equals("")) {

                    Intent intent = new Intent(thirdWriteActivity.this, fourthWriteActivity.class);

                    intent.putExtra("emotion", emotion);
                    intent.putExtra("event", event);
                    intent.putExtra("thought", thought);

                    startActivity(intent);

                    finish();
                }
            }
        });
    }
}