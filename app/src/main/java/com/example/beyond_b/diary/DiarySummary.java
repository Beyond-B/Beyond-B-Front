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

    @SerializedName("result")
    private ArrayList<DiarySummaries> result;

    @SerializedName("date")
    private String date;

    @SerializedName("feeling")
    private String feeling;

    public ArrayList<DiarySummaries> getResult(){
        return result;
    }

    public String getDate(){
        return date;
    }

    public String getFeeling(){
        return feeling;
    }
}
