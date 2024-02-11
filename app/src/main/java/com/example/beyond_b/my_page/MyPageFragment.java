package com.example.beyond_b.my_page;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beyond_b.databinding.FragmentMyPageBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyPageFragment() {
        // Required empty public constructor
    }



    public static MyPageFragment newInstance(String param1, String param2) {
        MyPageFragment fragment = new MyPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentMyPageBinding binding;
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
        binding = FragmentMyPageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //연령수정 캘린더 호출
        binding.txCorrectAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        // 로그아웃 다이얼로그
        binding.txLogoutDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("로그아웃")
                        .setMessage("정말 로그아웃 하시겠습니까?")
                        .setPositiveButton("로그아웃하기", setLogout(v.getRootView()) )
                        .setNegativeButton("잘못 눌렀어요", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // 회원탈퇴 다이얼로그
        binding.txWithdrawalDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("회원탈퇴")
                        .setMessage("회원탈퇴 하시겠습니까?")
                        .setPositiveButton("회원탈퇴하기", onClickWithdrawal(v.getRootView()) )
                        .setNegativeButton("잘못 눌렀어요", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }

    private FragmentManager getSupportFragmentManager() {
        return null;
    }

    // 회원탈퇴 처리 코드
    private DialogInterface.OnClickListener onClickWithdrawal(View rootView) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(rootView.getContext());
//        builder.setTitle("회원탈퇴")
//                .setMessage("정말 회원탈퇴 하시겠습니까?")
//                .setPositiveButton("진짜 회원탈퇴하기", setWithdrawal(rootView.getRootView()) )
//                .setNegativeButton("안 할래요", null);
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
        return null;
    }


    // 회원탈퇴 시 메인화면
    private DialogInterface.OnClickListener setWithdrawal(View rootView) {

        return null;
    }

    // 로그아웃 시 메인화면
    public DialogInterface.OnClickListener setLogout(View view){
        return null;
    }

    public void viewDialogue(View view){

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}