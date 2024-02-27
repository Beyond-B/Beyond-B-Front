package com.example.beyond_b.diary.view.write;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.beyond_b.R;
import com.example.beyond_b.diary.model.BookRecommendContent;
import com.example.beyond_b.diary.model.Diary;
import com.example.beyond_b.membership.DatabaseHelper;
import com.example.beyond_b.network.ApiResponse;
import com.example.beyond_b.network.ApiService;
import com.example.beyond_b.network.RetrofitClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class sixthWriteActivity extends AppCompatActivity {
    private String accessToken = "";
    private String date;

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

        LocalDate now = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDate.now();
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = now.format(formatter);
        }

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
                if (!result.equals("")) {
                    fetchDiary(date, emotion, event, thought, emotionSpecific, behavior, result);
                    fetchRecommendBook(emotion);
                }

            }
        });
    }

    private void fetchDiary(String date, String emotion, String event, String thought, String emotionSpecific, String behavior, String result){
        Diary diary = new Diary(date, emotion, event, thought, emotionSpecific, behavior, result);
        Retrofit retrofit = RetrofitClient.getClient(accessToken);
        ApiService apiService = retrofit.create(ApiService.class);

        Call<ApiResponse.DiaryCreateResponse> call = apiService.createDiary(diary);
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

    //책 추천 api
    private void fetchRecommendBook(String emotion) {
        Retrofit retrofit = RetrofitClient.getClient(accessToken);
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ApiResponse.BookRecommend> call = apiService.recommendBook(emotion);
        call.enqueue(new Callback<ApiResponse.BookRecommend>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse.BookRecommend> call, Response<ApiResponse.BookRecommend> response) {
                if (response.isSuccessful() && response.body() != null) {
                    response.body().getResult();
                    Log.d("recommendBook","Success RecommendBook");

                    ApiResponse.BookRecommend bookRecommendResponse = response.body();
                    BookRecommendContent book = bookRecommendResponse.getResult();

                    customDialog(emotion, book);

                }
            }

            @Override
            public void onFailure(Call<ApiResponse.BookRecommend> call, Throwable t) {
                Log.e("API Error", "Failed to fetch book details", t);
            }
        });
    }

    public void customDialog(String emotion, BookRecommendContent book){
        Dialog dialog = new Dialog(sixthWriteActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.book_recommend_dialog);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }

        ConstraintLayout layout = findViewById(R.id.sixth_write_constrainlayout);
        layout.setBackgroundColor(999999);

        TextView textViewBookName = dialog.findViewById(R.id.book_recommend_dialog_book_name);
        TextView textViewAuthor = dialog.findViewById(R.id.book_recommend_dialog_author);
        TextView textViewEmotion = dialog.findViewById(R.id.book_recommend_dialog_mood);

        //호출한 책 정보 바인딩
        textViewBookName.setText(book.getTitle());
        textViewAuthor.setText(book.getAuthor());
        textViewEmotion.setText(emotion);

        //다이얼로그가 종료되었을 시
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        });
        dialog.show();

    }
}