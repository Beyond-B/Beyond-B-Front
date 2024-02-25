package com.example.beyond_b.diary.write;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.beyond_b.R;

public class fifthWriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_write);

        Intent intent = getIntent();
        String emotion = intent.getStringExtra("emotion");
        String event = intent.getStringExtra("event");
        String thought = intent.getStringExtra("thought");
        String emotionSpecific = intent.getStringExtra("emotionSpecific");

        ImageButton cancel = findViewById(R.id.fifth_write_cancel);
        Button preBtn = findViewById(R.id.diary_fifth_write_pre_btn);
        Button nextBtn = findViewById(R.id.diary_fifth_write_next_btn);
        EditText editText = findViewById(R.id.diary_fifth_write_editText);

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
                String behavior = editText.getText().toString();
                if(!behavior.equals("")) {

                    Intent intent = new Intent(fifthWriteActivity.this, sixthWriteActivity.class);

                    intent.putExtra("emotion", emotion);
                    intent.putExtra("event", event);
                    intent.putExtra("thought", thought);
                    intent.putExtra("emotionSpecific", emotionSpecific);
                    intent.putExtra("behavior", behavior);

                    startActivity(intent);

                    finish();
                }
            }
        });
    }
}