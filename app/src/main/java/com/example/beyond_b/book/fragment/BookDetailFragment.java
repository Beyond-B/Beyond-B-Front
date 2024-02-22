package com.example.beyond_b.book.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.beyond_b.R;
import com.example.beyond_b.book.model.BookDetailResult;
import com.example.beyond_b.databinding.FragmentBookDetailBinding;
import com.example.beyond_b.network.ApiResponse;
import com.example.beyond_b.network.ApiService;
import com.example.beyond_b.network.RetrofitClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BookDetailFragment extends Fragment {

    private FragmentBookDetailBinding bookDetailBinding;
    private int bookId;
    private static String accessToken;

    public BookDetailFragment() {
        // Required empty public constructor
    }

    public static BookDetailFragment newInstance(int bookId) {
        BookDetailFragment fragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putInt("bookId", bookId);
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
        bookDetailBinding = FragmentBookDetailBinding.inflate(inflater, container, false);
        View view = bookDetailBinding.getRoot();
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            bookId = getArguments().getInt("bookId");
            accessToken = getArguments().getString("accessToken");
            fetchBookDetail(bookId);
        }

        return view;

    }

    private void fetchBookDetail(int bookId) {
        Retrofit retrofit = RetrofitClient.getClient(accessToken);
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ApiResponse.BookDetailResponse> call = apiService.getBookDetail(bookId);
        call.enqueue(new Callback<ApiResponse.BookDetailResponse>() {
            @Override
            public void onResponse(Call<ApiResponse.BookDetailResponse> call, Response<ApiResponse.BookDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse.BookDetailResponse data = response.body();
                    BookDetailResult item = response.body().getResult();
                    bind(item);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse.BookDetailResponse> call, Throwable t) {
                Log.e("API Error", "Failed to fetch book details", t);
            }
        });
    }

    public void bind(BookDetailResult item){
        ImageView backButton = bookDetailBinding.imageView6;
        backButton.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
            onDestroyView();
        });
        bookDetailBinding.txDetailTitle.setText(item.getBookContent().getTitle());
        bookDetailBinding.txDetailAuthor.setText(item.getBookContent().getAuthor());
        bookDetailBinding.imBadge.setImageResource(item.getBadgeResource());
        Glide.with(this).load(item.getBookContent().getBookImage()).into(bookDetailBinding.imBook);
        bookDetailBinding.txAbout.setText(item.getBookContent().getBookSummary());
        bookDetailBinding.txDate.setText(item.getRecommendationDate());
        bookDetailBinding.imMood.setImageResource(item.getEmotion());

        bookDetailBinding.txQuiz1Date.setText(item.setQuiz1Date());
        bookDetailBinding.imBadge1.setImageResource(item.setBadge(item.getQuiz1Date(), 1));
        bookDetailBinding.txQuiz2Date.setText(item.setQuiz2Date());
        bookDetailBinding.imBadge2.setImageResource(item.setBadge(item.getQuiz2Date(), 2));
        bookDetailBinding.txQuiz3Date.setText(item.setQuiz3Date());
        bookDetailBinding.imBadge3.setImageResource(item.setBadge(item.getQuiz3Date(), 3));


        String dateString= "";
        System.out.println(bookDetailBinding.txQuiz1Date.getText().toString());
        System.out.println(bookDetailBinding.txQuiz1Date.getText().toString().equals("Not solved yet"));
        if(!bookDetailBinding.txQuiz3Date.getText().toString().equals("Not solved yet")){
            dateString = bookDetailBinding.txQuiz3Date.getText().toString();
            getDate(dateString);
        }else if(!bookDetailBinding.txQuiz2Date.getText().toString().equals("Not solved yet")){
            dateString = bookDetailBinding.txQuiz2Date.getText().toString();
            getDate(dateString);
        }else if(!bookDetailBinding.txQuiz1Date.getText().toString().equals("Not solved yet")) {
            dateString = bookDetailBinding.txQuiz1Date.getText().toString();
            getDate(dateString);
        }else{
            goToQuizPage();
        }
    }

    private void getDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date quizDate = null;
        try {
            quizDate = sdf.parse(dateString); // 문자열을 Date 객체로 파싱
            System.out.println("파싱햇옹");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("quizDate"+quizDate);
        if (quizDate != null) {
            // 현재 날짜 가져오기
            Date currentDate = new Date();

            // 두 날짜의 차이 계산
            long diff = currentDate.getTime() - quizDate.getTime(); // 밀리초 단위 차이
            long days = TimeUnit.MILLISECONDS.toDays(diff); // 밀리초를 일 단위로 변환
            System.out.println("diff="+diff);
            // 하루 이상 차이나는지 확인
            if (days < 1) {
                bookDetailBinding.btnGotoQuiz.setText("See you 1 day later");
                bookDetailBinding.btnGotoQuiz.setBackgroundResource(R.drawable.btn_custom_gray);
                bookDetailBinding.btnGotoQuiz.setClickable(false);
            }else goToQuizPage();
        }
    }

    private void goToQuizPage() {
        bookDetailBinding.btnGotoQuiz.setOnClickListener(v -> {
            BookQuizFragment bookQuizFragment = new BookQuizFragment();

            Bundle args = new Bundle();
            args.putInt("bookId", bookId);
            args.putString("accessToken", accessToken);
            bookQuizFragment.setArguments(args);

            //백스택 추가
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, bookQuizFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bookDetailBinding = null;
    }
}