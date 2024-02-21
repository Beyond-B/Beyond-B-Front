package com.example.beyond_b.my_page;


import android.app.AlertDialog;
import android.app.Dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import com.example.beyond_b.databinding.FragmentNumberPickerDialogBinding;


public class NumberPickerFragment extends DialogFragment {

    private static final String ARG_CURRENT_VALUE = "currentValue";

    public interface NumberPickerDialogListener {
        void onNumberPicked(int value);
    }

    private NumberPickerDialogListener listener;

    public static NumberPickerFragment newInstance(int currentValue, NumberPickerDialogListener listener) {
        NumberPickerFragment fragment = new NumberPickerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CURRENT_VALUE, currentValue);
        fragment.setArguments(args);
        fragment.setNumberPickerListener(listener);
        return fragment;
    }

    public void setNumberPickerListener(NumberPickerDialogListener listener) {
        this.listener = listener;
    }

    private FragmentNumberPickerDialogBinding binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        binding = FragmentNumberPickerDialogBinding.inflate(inflater, null, false);
        View view = binding.getRoot();
        builder.setView(view);

        NumberPicker numberPicker = binding.numberPicker;
        numberPicker.setMinValue(6);
        numberPicker.setMaxValue(19);
        int currentValue = getArguments().getInt(ARG_CURRENT_VALUE, 0);
        numberPicker.setValue(currentValue);


        binding.btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onNumberPicked(binding.numberPicker.getValue());
                    dismiss();
                }
            }
        });

        binding.btnNo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dismiss();
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
