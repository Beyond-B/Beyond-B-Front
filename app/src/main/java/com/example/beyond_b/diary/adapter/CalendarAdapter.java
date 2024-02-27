package com.example.beyond_b.diary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beyond_b.R;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final ArrayList<String> dayofMonth;
    private final ArrayList<Integer> diaryIds;
    private final onItemListener onItemListener;


    public CalendarAdapter(ArrayList<String> dayofMonth, ArrayList<Integer> diaryIds, CalendarAdapter.onItemListener onItemListener) {
        this.dayofMonth = dayofMonth;
        this.diaryIds = diaryIds;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.calendar_cell,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int)(parent.getHeight()*0.166666666);
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.dayofMonth.setText(dayofMonth.get(position));
        holder.setDiaryId(diaryIds.get(position));
    }

    @Override
    public int getItemCount() {
        return dayofMonth.size();
    }

    public interface onItemListener{
        void onItemClick(int position, int diaryId, String dayText, ImageView moodImg);
    }
}
