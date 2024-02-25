package com.example.beyond_b.diary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.beyond_b.R;
import com.example.beyond_b.book.model.BookDetailResult;
import com.example.beyond_b.databinding.FragmentCalendarCellClickBinding;
import com.example.beyond_b.membership.DatabaseHelper;
import com.example.beyond_b.network.ApiResponse;
import com.example.beyond_b.network.ApiService;
import com.example.beyond_b.network.RetrofitClient;

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
        args.putString("accessToken", accessToken);
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
        DatabaseHelper db = new DatabaseHelper(getContext());

        // 저장된 액세스 토큰 검색
        accessToken = db.getAccessToken();
        View view = cellClickBinding.getRoot();
        if (getArguments() != null) {
            diaryId = getArguments().getInt("diaryId");
            accessToken = getArguments().getString("accessToken");
            fetchDiaryDetail(diaryId);
        }


        return view;
    }


    private void fetchDiaryDetail(int diaryId) {
        Retrofit retrofit = RetrofitClient.getClient(accessToken);
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ApiResponse.DiaryDetailResponse> call = apiService.getDetailDiary(diaryId);
        call.enqueue(new Callback<ApiResponse.DiaryDetailResponse>() {
            @Override
            public void onResponse(Call<ApiResponse.DiaryDetailResponse> call, Response<ApiResponse.DiaryDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse.DiaryDetailResponse data = response.body();
                    DiaryDetail item = response.body().getResult();
                    bind(item);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse.DiaryDetailResponse> call, Throwable t) {
                Log.e("API Error", "Failed to fetch diary details", t);
            }
        });
    }

    private void bind(DiaryDetail item) {
        //제대로 되는지 확인 필요
        cellClickBinding.diaryToday.setText(item.getDate());
        cellClickBinding.diaryFirstAnswer.setText(item.getEvent());
        cellClickBinding.diarySecondAnswer.setText(item.getThought());
        cellClickBinding.diaryThirdAnswer.setText(item.getEmotionSpecific());
        cellClickBinding.diaryFourthAnswer.setText(item.getBehavior());
        cellClickBinding.diaryFifthAnswer.setText(item.getResult());
    }

    
}