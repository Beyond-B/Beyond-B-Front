package com.example.beyond_b.diary;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beyond_b.R;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView dayofMonth;
    private final CalendarAdapter.onItemListener onItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.onItemListener onItemListener) {
        super(itemView);
        dayofMonth = itemView.findViewById(R.id.diary_cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), (String) dayofMonth.getText());
    }
}
