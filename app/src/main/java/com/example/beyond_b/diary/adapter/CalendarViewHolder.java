package com.example.beyond_b.diary.adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beyond_b.R;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView dayofMonth;
    public final ImageView moodImg;
    private int diaryId;
    private final CalendarAdapter.onItemListener onItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.onItemListener onItemListener) {
        super(itemView);
        dayofMonth = itemView.findViewById(R.id.diary_cellDayText);
        moodImg = itemView.findViewById(R.id.diary_moodImage);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), diaryId, (String) dayofMonth.getText(), moodImg);
    }
}
