package com.example.beyond_b.diary;

import com.google.gson.annotations.SerializedName;
public class Diary {

    public Diary(String date, String emotion, String event, String thought, String emotionSpecific, String behavior,String result){
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

    public String getDate(){
        return date;
    }

    public String getEmotion(){
        return emotion;
    }

    public String getEvent(){
        return event;
    }

    public String getThought(){
        return thought;
    }

    public String getEmotionSpecific(){
        return emotionSpecific;
    }

    public String getBehavior(){
        return behavior;
    }

    public String getResult(){
        return result;
    }


}
