package com.example.beyond_b.diary.view;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beyond_b.databinding.FragmentCalendarCellClickBinding;
import com.example.beyond_b.diary.model.DiaryDetailContent;
import com.example.beyond_b.membership.DatabaseHelper;
import com.example.beyond_b.network.ApiResponse;
import com.example.beyond_b.network.ApiService;
import com.example.beyond_b.network.RetrofitClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CalendarCellClickFragment extends Fragment {

    private FragmentCalendarCellClickBinding cellClickBinding;
    private static String accessToken = "";
    private static int diaryId = 0;

    public CalendarCellClickFragment() {
        // Required empty public constructor
    }

    public static CalendarCellClickFragment newInstance(int diaryId) {
        CalendarCellClickFragment fragment = new CalendarCellClickFragment();
        Bundle args = new Bundle();
        args.putInt("diaryId", diaryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 저장된 액세스 토큰 검색
        DatabaseHelper db = new DatabaseHelper(getContext());
        accessToken = db.getAccessToken();

        cellClickBinding = FragmentCalendarCellClickBinding.inflate(inflater, container, false);
        View view = cellClickBinding.getRoot();

        if (getArguments() != null) {
            diaryId = getArguments().getInt("diaryId");
            fetchDiaryDetail(diaryId);
            Log.d("diaryId", String.valueOf(diaryId));
        }

        cellClickBinding.diaryCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        return view;
    }

    //일기 자세히 api
    private void fetchDiaryDetail(int diaryId) {
        Retrofit retrofit = RetrofitClient.getClient(accessToken);
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ApiResponse.DiaryDetailResponse> call = apiService.getDetailDiary(diaryId);
        call.enqueue(new Callback<ApiResponse.DiaryDetailResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<ApiResponse.DiaryDetailResponse> call, Response<ApiResponse.DiaryDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("CalenderCellClick", "성공");

                    ApiResponse.DiaryDetailResponse bookDetailResponse = response.body();
                    DiaryDetailContent item = bookDetailResponse.getResult();
                    bind(item);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse.DiaryDetailResponse> call, Throwable t) {
                Log.e("API Error", "Failed to fetch diary details", t);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void bind(DiaryDetailContent item) {
        //날짜 원하는 형식으로 변경
        LocalDate date = LocalDate.parse(item.getDate());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d", Locale.ENGLISH);
        String formattedDate = date.format(formatter);

        cellClickBinding.diaryToday.setText(formattedDate);
        cellClickBinding.diaryFirstAnswer.setText(item.getEvent());
        cellClickBinding.diarySecondAnswer.setText(item.getThought());
        cellClickBinding.diaryThirdAnswer.setText(item.getEmotionSpecific());
        cellClickBinding.diaryFourthAnswer.setText(item.getBehavior());
        cellClickBinding.diaryFifthAnswer.setText(item.getResult());
    }

    
}