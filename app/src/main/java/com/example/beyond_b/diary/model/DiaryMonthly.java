package com.example.beyond_b.diary.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DiaryMonthly {
    @SerializedName("isSuccess")
    private boolean isSuccess;
    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private DiarySummaries result;
    public DiarySummaries getResult(){
        return result;
    }

}
