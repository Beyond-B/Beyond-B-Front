package com.example.beyond_b.diary;


import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beyond_b.R;
import com.example.beyond_b.databinding.CalendarCellBinding;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView dayofMonth;
    public final ImageView moodImg;
    private final CalendarAdapter.onItemListener onItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.onItemListener onItemListener) {
        super(itemView);
        dayofMonth = itemView.findViewById(R.id.diary_cellDayText);
        moodImg = itemView.findViewById(R.id.diary_moodImage);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), (String) dayofMonth.getText(), moodImg);
    }
}
