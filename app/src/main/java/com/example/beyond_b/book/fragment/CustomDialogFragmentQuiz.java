package com.example.beyond_b.book.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.beyond_b.R;
import com.example.beyond_b.databinding.FragmentDialogCustomQuizBinding;

public class CustomDialogFragmentQuiz extends DialogFragment {
    private static String ARG_ISCORRECT = "";


    public static CustomDialogFragmentQuiz newInstance(String isCorrect) {
        CustomDialogFragmentQuiz fragment = new CustomDialogFragmentQuiz();
        Bundle args = new Bundle();
        args.putString(ARG_ISCORRECT, isCorrect);
        ARG_ISCORRECT=isCorrect;
        fragment.setArguments(args);
        return fragment;
    }
    private FragmentDialogCustomQuizBinding dialogCustomQuizBinding;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        dialogCustomQuizBinding = FragmentDialogCustomQuizBinding.inflate(inflater, null, false);
        View view = dialogCustomQuizBinding.getRoot();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        // 내용 설정
        if(ARG_ISCORRECT.equals("correct")){
            dialogCustomQuizBinding.dialogMessage.setText("Congratulations!");
            dialogCustomQuizBinding.tx1.setText("That's Correct Answer");
            dialogCustomQuizBinding.tx2.setText("Try it again tomorrow!");
            dialogCustomQuizBinding.imageView2.setImageResource(R.drawable.ic_correct);
        }else{
            dialogCustomQuizBinding.dialogMessage.setText("That's not a Correct Answer");
            dialogCustomQuizBinding.tx1.setText("Try it again!");
            dialogCustomQuizBinding.imageView2.setImageResource(R.drawable.ic_incorrect);
        }
        //2.5초후 사라짐
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (getDialog() != null && getDialog().isShowing()) {
                getDialog().dismiss(); // 다이얼로그 닫기
                // 이전 화면으로 돌아가기
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        }, 2500); // 1000ms = 1초


        AlertDialog dialog = builder.create();
        // 다이얼로그 모서리 둥글게 하기 위한 작업
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }

        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dialogCustomQuizBinding = null;
    }

}

