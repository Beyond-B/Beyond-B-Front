package com.example.beyond_b.diary.model;

import com.google.gson.annotations.SerializedName;
public class Diary {

    public Diary(String date, String emotion, String event, String thought, String emotionSpecific, String behavior, String result){
        this.date = date;
        this.emotion = emotion;
        this.thought = thought;
        this.emotionSpecific = emotionSpecific;
        this.behavior = behavior;
        this.event = event;
        this.result = result;
    }
    @SerializedName("date")
    private String date;
    @SerializedName("emotion")
    private String emotion;

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

    public String getResult(){
        return result;
    }


}
