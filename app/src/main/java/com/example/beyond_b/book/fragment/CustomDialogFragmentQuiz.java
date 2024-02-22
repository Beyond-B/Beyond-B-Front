package com.example.beyond_b.book.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.beyond_b.R;
import com.example.beyond_b.databinding.FragmentDialogCustomQuizBinding;

public class CustomDialogFragmentQuiz extends DialogFragment {
    private static final String ARG_ISCORRECT = "";


    public static CustomDialogFragmentQuiz newInstance(String isCorrect) {
        CustomDialogFragmentQuiz fragment = new CustomDialogFragmentQuiz();
        Bundle args = new Bundle();
        args.putString(ARG_ISCORRECT, isCorrect);
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
            dialogCustomQuizBinding.dialogMessage.setText("축하합니다!");
            dialogCustomQuizBinding.tx1.setText("정답입니다");
            dialogCustomQuizBinding.tx2.setText("내일 다시 풀어보세요!");
            dialogCustomQuizBinding.imageView2.setImageResource(R.drawable.ic_correct);
        }else{
            dialogCustomQuizBinding.dialogMessage.setText("틀렸습니다");
            dialogCustomQuizBinding.tx1.setText("다시 한 번 풀어보세요!");
            dialogCustomQuizBinding.imageView2.setImageResource(R.drawable.ic_incorrect);
        }

        AlertDialog dialog = builder.create();

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

