package com.example.beyond_b.diary.model;

import com.google.gson.annotations.SerializedName;

public class DiaryCalendar {
    @SerializedName("diaryId")
    private int diaryId;
    @SerializedName("date")
    private String date;
    @SerializedName("feeling")
    private String feeling;
    public int getDiaryId(){return diaryId;}
    public void setDiaryId(int diaryId){
        this.diaryId = diaryId;
    }

    public String getDate(){
        return date;
    }

    public String getFeeling(){
        return feeling;
    }
}
