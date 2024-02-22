package com.example.beyond_b.my_page;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.beyond_b.databinding.FragmentDialogCustomBinding;

public class CustomDialogFragment extends DialogFragment {

    private static final String ARG_MESSAGE = "message";
//    private static final String ARG_POSITIVE_BUTTON_TEXT = "positiveButtonText";

    public static CustomDialogFragment newInstance(String message, String positiveButtonText) {
        CustomDialogFragment fragment = new CustomDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
//        args.putString(ARG_POSITIVE_BUTTON_TEXT, positiveButtonText);
        fragment.setArguments(args);
        return fragment;
    }

    public interface DialogListener {
        boolean onPositiveButtonClick(DialogFragment dialog);
        void onNegativeButtonClick(DialogFragment dialog);
    }

    private DialogListener listener;

    public void setDialogListener(DialogListener listener){
        this.listener = listener;
    }

    private FragmentDialogCustomBinding binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        binding = FragmentDialogCustomBinding.inflate(inflater, null, false);
        View view = binding.getRoot();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        // 내용 설정
        TextView messageTextView = binding.dialogMessage;
        if (getArguments() != null) {
            String message = getArguments().getString(ARG_MESSAGE);
            messageTextView.setText(message);
        }

        // '네' 버튼 설정
        binding.btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onPositiveButtonClick(CustomDialogFragment.this);
                }
            }
        });

        // '아니오' 버튼 설정
        binding.btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onNegativeButtonClick(CustomDialogFragment.this);
                }
            }
        });

        AlertDialog dialog = builder.create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = (int) (displayMetrics.widthPixels * 0.7);
            int height = (int) (displayMetrics.heightPixels * 0.2);

            dialog.getWindow().setLayout(width, height);
        }

        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // 메모리 누수 방지
    }

}

