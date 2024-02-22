package com.example.beyond_b.book.fragment;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.beyond_b.MainActivity;
import com.example.beyond_b.R;
import com.example.beyond_b.book.model.BookQuiz;
import com.example.beyond_b.book.model.BookQuizSubmit;
import com.example.beyond_b.databinding.FragmentBookQuizBinding;
import com.example.beyond_b.my_page.CustomDialogFragment;
import com.example.beyond_b.network.ApiResponse;
import com.example.beyond_b.network.ApiService;
import com.example.beyond_b.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BookQuizFragment extends Fragment {

    private int bookId;
    private static String accessToken;
    private FragmentBookQuizBinding quizBinding;

    public BookQuizFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        quizBinding = FragmentBookQuizBinding.inflate(inflater, container, false);
        View view = quizBinding.getRoot();
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            bookId = getArguments().getInt("bookId");
            accessToken = getArguments().getString("accessToken");
            fetchGetQuiz(bookId);
        }

        return view;

    }
    @Override
    public void onResume() {
        super.onResume();
        // Activity를 MainActivity로 캐스팅
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showBottomNavigation(false); // Bottom Navigation 숨기기
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // Fragment가 pause 상태가 될 때 다시 Bottom Navigation을 보이게 함
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showBottomNavigation(true);
        }
    }

    private BookQuiz item = new BookQuiz();
    //quiz 데이터 가져오기 api
    private void fetchGetQuiz(int bookId) {
        Retrofit retrofit = RetrofitClient.getClient(accessToken);
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ApiResponse.QuizResponse> call = apiService.getQuiz(bookId);
        call.enqueue(new Callback<ApiResponse.QuizResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse.QuizResponse> call, Response<ApiResponse.QuizResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse.QuizResponse ageResponse = response.body();
                    item = response.body().getResult();
                    bind(item);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse.QuizResponse> call, Throwable t) {
                Log.e("API Error", "Failed to fetch age", t);
            }
        });
    }
    private int selectNum = 0;
    private void bind(BookQuiz item) {
        quizBinding.txQuizTitle.setText(item.getQuestion());
        quizBinding.txNo1.setText(item.getOption1());
        quizBinding.txNo2.setText(item.getOption2());
        quizBinding.txNo3.setText(item.getOption3());
        quizBinding.txNo4.setText(item.getOption4());

        quizBinding.btnNo1.setOnClickListener(v -> {setButtonSelected(quizBinding.btnNo1, quizBinding.btnNo1Circle, quizBinding.txNo1); selectNum=1;});
        quizBinding.btnNo2.setOnClickListener(v -> {setButtonSelected(quizBinding.btnNo2, quizBinding.btnNo2Circle, quizBinding.txNo2); selectNum=2;});
        quizBinding.btnNo3.setOnClickListener(v -> {setButtonSelected(quizBinding.btnNo3, quizBinding.btnNo3Circle, quizBinding.txNo3); selectNum=3;});
        quizBinding.btnNo4.setOnClickListener(v -> {setButtonSelected(quizBinding.btnNo4, quizBinding.btnNo4Circle, quizBinding.txNo4); selectNum=4;});

        StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.drawable.btn_aniamte);
        quizBinding.btnNo1.setStateListAnimator(stateListAnimator);
        quizBinding.btnNo2.setStateListAnimator(stateListAnimator);
        quizBinding.btnNo3.setStateListAnimator(stateListAnimator);
        quizBinding.btnNo4.setStateListAnimator(stateListAnimator);


        quizBinding.backstack.setOnClickListener(v -> {
            checkToQuit();
        });

        quizBinding.btnSubmitQuiz.setOnClickListener(v -> {
            checkToSubmit(item);
        });
    }

    private void resetButtons() {
        quizBinding.btnNo1.setBackgroundResource(R.drawable.btn_customquiz); // btn_default는 초기 상태의 배경 리소스
        quizBinding.btnNo2.setBackgroundResource(R.drawable.btn_customquiz);
        quizBinding.btnNo3.setBackgroundResource(R.drawable.btn_customquiz);
        quizBinding.btnNo4.setBackgroundResource(R.drawable.btn_customquiz);
        quizBinding.btnNo1Circle.setBackgroundResource(R.drawable.btn_customquizcircle);
        quizBinding.btnNo2Circle.setBackgroundResource(R.drawable.btn_customquizcircle);
        quizBinding.btnNo3Circle.setBackgroundResource(R.drawable.btn_customquizcircle);
        quizBinding.btnNo4Circle.setBackgroundResource(R.drawable.btn_customquizcircle);
        quizBinding.txNo1.setTextColor(Color.parseColor("#919191"));
        quizBinding.txNo1.setTypeface(ResourcesCompat.getFont(getContext(), R.font.pretendard_light));
        quizBinding.txNo2.setTextColor(Color.parseColor("#919191"));
        quizBinding.txNo2.setTypeface(ResourcesCompat.getFont(getContext(), R.font.pretendard_light));
        quizBinding.txNo3.setTextColor(Color.parseColor("#919191"));
        quizBinding.txNo3.setTypeface(ResourcesCompat.getFont(getContext(), R.font.pretendard_light));
        quizBinding.txNo4.setTextColor(Color.parseColor("#919191"));
        quizBinding.txNo4.setTypeface(ResourcesCompat.getFont(getContext(), R.font.pretendard_light));
    }

    private void setButtonSelected(Button selectedButton, Button selectedCircleButton, TextView selectedText) {
        resetButtons(); // 먼저 모든 버튼을 초기 상태로 설정
        selectedButton.setBackgroundResource(R.drawable.btn_customquizs);
        selectedCircleButton.setBackgroundResource(R.drawable.btn_customquizcircles);
        quizBinding.btnSubmitQuiz.setBackgroundResource(R.drawable.btn_custom);
        selectedText.setTextColor(Color.parseColor("#86D780"));
        selectedText.setTypeface(ResourcesCompat.getFont(getContext(), R.font.pretendard_semibold));

    }

    private void checkToQuit(){
        CustomDialogFragment dialog = CustomDialogFragment.newInstance("Would you like to stop taking the quiz and return to the list?", "out");
        dialog.setDialogListener(new CustomDialogFragment.DialogListener() {
            @Override
            public boolean onPositiveButtonClick(DialogFragment dialog) {
                requireActivity().getSupportFragmentManager().popBackStack();
                dialog.dismiss();
                return true;
            }

            @Override
            public void onNegativeButtonClick(DialogFragment dialog) {
                dialog.dismiss();
            }
        });
        dialog.show(getChildFragmentManager(), "backstackDialog");
    }

    //퀴즈 정답인지 확인
    private void checkToSubmit(BookQuiz item) {
        if(selectNum==0){
            Toast.makeText(getContext(),"You have to choose one option.", Toast.LENGTH_LONG).show();
        }
        else if(selectNum == item.getAnswerOption()) {
            fetchSubmitQuiz();
            createDialog("correct");
        }
        else createDialog("incorrect");

    }

    private void createDialog(String str){
        CustomDialogFragmentQuiz dialog = CustomDialogFragmentQuiz.newInstance(str);
        dialog.show(getChildFragmentManager(), "quizSubmitDialog");
    }

    private final BookQuizSubmit submit = new BookQuizSubmit();

    //submit api
    private void fetchSubmitQuiz() {
        Retrofit retrofit = RetrofitClient.getClient(accessToken);
        ApiService apiService = retrofit.create(ApiService.class);
        submit.setBookId(bookId);
        submit.setStep(item.getStep());

        Call<ApiResponse.QuizSubmitResponse> call = apiService.submitQuiz(submit);
        call.enqueue(new Callback<ApiResponse.QuizSubmitResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse.QuizSubmitResponse> call, Response<ApiResponse.QuizSubmitResponse> response) {

            }

            @Override
            public void onFailure(Call<ApiResponse.QuizSubmitResponse> call, Throwable t) {
                Log.e("API Error", "Failed to fetch quiz submit", t);
            }
        });

    }
}