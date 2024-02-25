package com.example.beyond_b.diary.write;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.beyond_b.R;
import com.example.beyond_b.diary.Diary;
import com.example.beyond_b.diary.DiaryFragment;
import com.example.beyond_b.membership.DatabaseHelper;
import com.example.beyond_b.network.ApiResponse;
import com.example.beyond_b.network.ApiService;
import com.example.beyond_b.network.RetrofitClient;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class sixthWriteActivity extends AppCompatActivity {
    private String accessToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth_write);

        DatabaseHelper db = new DatabaseHelper(this);
        accessToken = db.getAccessToken();

        Intent intent = getIntent();
        String emotion = intent.getStringExtra("emotion");
        String event = intent.getStringExtra("event");
        String thought = intent.getStringExtra("thought");
        String emotionSpecific = intent.getStringExtra("emotionSpecific");
        String behavior = intent.getStringExtra("behavior");

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String date = year + "-" + month + "-" + dayOfMonth;

        ImageButton cancel = findViewById(R.id.sixth_write_cancel);
        Button preBtn = findViewById(R.id.diary_sixth_write_pre_btn);
        Button doneBtn = findViewById(R.id.diary_sixth_write_next_btn);
        EditText editText = findViewById(R.id.diary_sixth_write_editText);

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
                String result = editText.getText().toString();
                if(!result.equals("")){
                    fetchDiary(date, emotion, event, thought, emotionSpecific, behavior, result);

                    CustomDialogFragment fragment = new CustomDialogFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                    finish();
                }
            }
        });
    }

    private void fetchDiary(String date, String emotion, String event, String thought, String emotionSpecific, String behavior, String result){
        Diary diary = new Diary(date, emotion, event, thought, emotionSpecific, behavior, result);
        Retrofit retrofit = RetrofitClient.getClient(accessToken);
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ApiResponse.DiaryCreateResponse> call = apiService.creatDiary(diary);
        call.enqueue(new Callback<ApiResponse.DiaryCreateResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse.DiaryCreateResponse> call, Response<ApiResponse.DiaryCreateResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("diary", "성공");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse.DiaryCreateResponse> call, Throwable t) {
                Log.e("API Error", "Failed to fetch book details", t);
            }
        });
    }
}