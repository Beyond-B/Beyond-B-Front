package com.example.beyond_b.diary;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DiarySummary {
    @SerializedName("isSuccess")
    private boolean isSuccess;
    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String message;

    @SerializedName("diarySummaries")
    private ArrayList<String> diarySummaries;

    @SerializedName("date")
    private String date;

    @SerializedName("feeling")
    private String feeling;

    public boolean getisSuccess(){
        return isSuccess;
    }

    public ArrayList<String> getdiarySummaries(){
        return diarySummaries;
    }

    public String getDate(){
        return date;
    }

    public String getFeeling(){
        return feeling;
    }
}
