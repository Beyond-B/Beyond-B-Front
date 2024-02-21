package com.example.beyond_b.my_page;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.beyond_b.databinding.FragmentMyPageBinding;
import com.example.beyond_b.membership.DatabaseHelper;
import com.example.beyond_b.membership.LogInActivity;
import com.example.beyond_b.network.ApiResponse;
import com.example.beyond_b.network.ApiService;
import com.example.beyond_b.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyPageFragment extends Fragment {

    private String accessToken = "";

    private FragmentMyPageBinding myPageBinding;

    public MyPageFragment() {
        // Required empty public constructor
    }

    private MyPage myPage = new MyPage();



    public static MyPageFragment newInstance(String param1, String param2) {
        MyPageFragment fragment = new MyPageFragment();
        Bundle args = new Bundle();
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
        //토큰 가져오기
        DatabaseHelper db = new DatabaseHelper(getContext());
        accessToken = db.getAccessToken();
        myPageBinding = FragmentMyPageBinding.inflate(inflater, container, false);
        View view = myPageBinding.getRoot();

        fetchMyPageData();


        //연령수정 스피너 호출
        myPageBinding.txCorrectAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ageDialog();
            }
        });

        // 로그아웃 다이얼로그
        myPageBinding.txLogoutDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutDialog();
            }
        });

        // 회원탈퇴 다이얼로그
        myPageBinding.txWithdrawalDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                withdrawal();
//                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                builder.setTitle("회원탈퇴")
//                        .setMessage("회원탈퇴 하시겠습니까?")
//                        .setPositiveButton("회원탈퇴하기", onClickWithdrawal(v.getRootView()) )
//                        .setNegativeButton("잘못 눌렀어요", null);
//
//                AlertDialog dialog = builder.create();
//                dialog.show();
            }
        });

        return view;
    }

    private void bind(MyPage item) {
        myPageBinding.txAge.setText(item.getAge() + "세");
        myPageBinding.txName.setText(item.getUsername());
    }
    //마이페이지 정보 가져오기 api 연결
    private void fetchMyPageData() {
        Retrofit retrofit = RetrofitClient.getClient(accessToken);
        System.out.println(accessToken);
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ApiResponse.MyPageResponse> call = apiService.getMyPage();
        call.enqueue(new Callback<ApiResponse.MyPageResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse.MyPageResponse> call, Response<ApiResponse.MyPageResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse.MyPageResponse ageResponse = response.body();
                    MyPage item = response.body().getResult();
                    bind(item);
                    System.out.println("fetchMyPageData");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse.MyPageResponse> call, Throwable t) {
                Log.e("API Error", "Failed to fetch age", t);
            }
        });
    }

    //연령수정 처리 코드, 나중에 selectedNumber는 api로 넘겨야 함
    private int selectedNumber = 0;
    private void ageDialog() {
        NumberPickerFragment numberPickerDialog = NumberPickerFragment.newInstance(selectedNumber, new NumberPickerFragment.NumberPickerDialogListener() {
            @Override
            public void onNumberPicked(int value) {
                selectedNumber = value;
                String age = Integer.toString(selectedNumber);
                fetchAge(age);
                System.out.println(age);
                if(!age.equals("0")) {
                    myPageBinding.txAge.setText(selectedNumber + "세");
                }
            }
        });

        numberPickerDialog.show(getChildFragmentManager(), "numberPicker");

    }
    // 연령수정 api 연결
    private void fetchAge(String age) {
        Retrofit retrofit = RetrofitClient.getClient(accessToken);
        ApiService apiService = retrofit.create(ApiService.class);
        myPage.setAge(age); // 'age' 필드만 설정
        Call<ApiResponse.MyPageResponse> call = apiService.updateAge(myPage);
        call.enqueue(new Callback<ApiResponse.MyPageResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse.MyPageResponse> call, @NonNull Response<ApiResponse.MyPageResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse.MyPageResponse ageResponse = response.body();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse.MyPageResponse> call, Throwable t) {
                Log.e("API Error", "Failed to fetch age", t);
            }
        });
    }

    //로그아웃 처리 코드
    private void logoutDialog() {
        CustomDialogFragment dialog = CustomDialogFragment.newInstance("정말 로그아웃 하시겠습니까?", "로그아웃");
        dialog.setDialogListener(new CustomDialogFragment.DialogListener() {
            @Override
            public void onPositiveButtonClick(DialogFragment dialog) {
                Intent intent = new Intent(getActivity(), LogInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                getActivity().finish();
            }

            @Override
            public void onNegativeButtonClick(DialogFragment dialog) {
                // '아니오' 버튼 클릭 시 수행할 작업
                dialog.dismiss();
            }
        });
        dialog.show(getChildFragmentManager(), "logoutDialog");
        /*
        Dialog dialog = CustomDialogFragment.newInstance()
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("로그아웃")
                .setMessage("정말 로그아웃 하시겠습니까?")
                .setPositiveButton("로그아웃하기", setLogout(v.getRootView()) )
                .setNegativeButton("잘못 눌렀어요", null);

        AlertDialog dialog = builder.create();
        dialog.show();
        */
    }


    // 회원탈퇴 처리 코드
    private void withdrawal() {

        CustomDialogFragment dialog = CustomDialogFragment.newInstance("정말 회원탈퇴 하시겠습니까?", "회원탈퇴");
        dialog.setDialogListener(new CustomDialogFragment.DialogListener() {
            @Override
            public void onPositiveButtonClick(DialogFragment dialog) {
                // '네' 버튼 클릭 시 수행할 작업
            }

            @Override
            public void onNegativeButtonClick(DialogFragment dialog) {
                // '아니오' 버튼 클릭 시 수행할 작업
                dialog.dismiss();
            }
        });
        dialog.show(getChildFragmentManager(), "withdrawalDialog");

//        AlertDialog.Builder builder = new AlertDialog.Builder(rootView.getContext());
//        builder.setTitle("회원탈퇴")
//                .setMessage("정말 회원탈퇴 하시겠습니까?")
//                .setPositiveButton("진짜 회원탈퇴하기", setWithdrawal(rootView.getRootView()) )
//                .setNegativeButton("안 할래요", null);
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
    }


    // 회원탈퇴 시 메인화면
    private DialogInterface.OnClickListener setWithdrawal(View rootView) {

        return null;
    }

    // 로그아웃 시 메인화면
    public DialogInterface.OnClickListener setLogout(View view){
        return null;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        myPageBinding = null;
    }


}