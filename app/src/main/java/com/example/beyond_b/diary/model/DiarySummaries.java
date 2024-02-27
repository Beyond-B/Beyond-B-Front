package com.example.beyond_b.diary.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.List;

public class DiarySummaries {
    @SerializedName("diarySummaries")
    private List<DiaryCalendar> diarySummaries;

    public List<DiaryCalendar> getDiarySummaries(){
        return diarySummaries;
    }

}
