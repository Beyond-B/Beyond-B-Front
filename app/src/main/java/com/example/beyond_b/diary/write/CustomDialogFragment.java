package com.example.beyond_b.diary.write;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beyond_b.R;
import com.example.beyond_b.databinding.BookRecommendDialogBinding;
import com.example.beyond_b.databinding.FragmentBookBinding;
import com.example.beyond_b.membership.DatabaseHelper;
import com.example.beyond_b.network.ApiResponse;
import com.example.beyond_b.network.ApiService;
import com.example.beyond_b.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CustomDialogFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private BookRecommendDialogBinding binding;
    private String accessToken = "";

    public CustomDialogFragment() {
        // Required empty public constructor
    }

    public static CustomDialogFragment newInstance(String param1, String param2) {
        CustomDialogFragment fragment = new CustomDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = BookRecommendDialogBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        DatabaseHelper db = new DatabaseHelper(getContext());
        accessToken = db.getAccessToken();



        //binding.bookRecommendDialogMood.setText();
        //binding.bookRecommendDialogBookName.setText();
        //binding.bookRecommendDialogAuthor.setText();


        return view;
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
                }
            }

            @Override
            public void onFailure(Call<ApiResponse.BookRecommend> call, Throwable t) {
                Log.e("API Error", "Failed to fetch book details", t);
            }
        });
    }
}