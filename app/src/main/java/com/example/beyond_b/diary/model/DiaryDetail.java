package com.example.beyond_b.diary.model;

import com.google.gson.annotations.SerializedName;

public class DiaryDetail {
    @SerializedName("isSuccess")
    private boolean isSuccess;
    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String massage;
    @SerializedName("result")
    private DiaryDetailContent result;

    public DiaryDetailContent getResult(){
        return result;
    }
}
