package com.example.beyond_b.diary.model;

import com.google.gson.annotations.SerializedName;

public class DiaryDetailContent {
    @SerializedName("diaryId")
    private int diaryId;
    @SerializedName("date")
    private String date;
    @SerializedName("event")
    private String event;
    @SerializedName("thought")
    private String thought;
    @SerializedName("emotionSpecific")
    private String emotionSpecific;
    @SerializedName("behavior")
    private String behavior;
    @SerializedName("result")
    private String result;
    @SerializedName("feeling")
    private String feeling;


    public int getDiaryId() {
        return diaryId;
    }

    public String getDate() {
        return date;
    }

    public String getEvent() {
        return event;
    }

    public String getThought() {
        return thought;
    }

    public String getEmotionSpecific() {
        return emotionSpecific;
    }

    public String getBehavior() {
        return behavior;
    }

    public String getResult() {
        return result;
    }
}
