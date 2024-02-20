package com.example.beyond_b.book.model;

import com.example.beyond_b.R;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookDetailResult {
    @SerializedName("bookContent")
    private BookContent bookContent;
    @SerializedName("quiz1Date")
    private LocalDateTime quiz1Date;
    private String quiz1DateS;
    @SerializedName("quiz2Date")
    private LocalDateTime quiz2Date;
    private String quiz2DateS;
    @SerializedName("quiz3Date")
    private LocalDateTime quiz3Date;
    private String quiz3DateS;
    @SerializedName("recommendationDate")
    private LocalDateTime recommendationDate;
    @SerializedName("emotion")
    private String emotion;
    private int badgeResource;

    public BookDetailResult() {
    }

    public BookContent getBookContent() {
        return bookContent;
    }

    public void setBookContent(BookContent bookContent) {
        this.bookContent = bookContent;
    }

    public String setQuiz1Date() {
        if (quiz1Date != null) {
            DateTimeFormatter formatter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            }
            String formattedDateTime = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formattedDateTime = quiz1Date.format(formatter);
            }
            quiz1DateS = formattedDateTime;
        } else {
            quiz1DateS = "Not solved yet";
        }
        return quiz1DateS;
    }
    public String getQuiz1Date() {
        return quiz1DateS;
    }

    public String setQuiz2Date() {
        if (quiz2Date != null) {
            DateTimeFormatter formatter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            }
            String formattedDateTime = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formattedDateTime = quiz2Date.format(formatter);
            }
            quiz2DateS = formattedDateTime;
        } else {
            quiz2DateS = "Not solved yet";
        }
        return quiz2DateS;
    }
    public String getQuiz2Date() {
        return quiz2DateS;
    }

    public String setQuiz3Date() {
        if (quiz3Date != null) {
            DateTimeFormatter formatter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            }
            String formattedDateTime = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formattedDateTime = quiz3Date.format(formatter);
            }
            quiz3DateS = formattedDateTime;
        } else {
            quiz3DateS = "Not solved yet";
        }
        return quiz3DateS;
    }
    public String getQuiz3Date() {
        return quiz3DateS;
    }

    public String getRecommendationDate() {
        if (recommendationDate != null) {
            DateTimeFormatter formatter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            }
            String formattedDateTime = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formattedDateTime = recommendationDate.format(formatter);
            }
            return formattedDateTime;
        } else {
            return "Not solved yet";
        }
    }

    public int getEmotion() {
        if(this.emotion.equals("HAPPY"))
            return R.drawable.ic_happy;
        else if(this.emotion.equals("DEPRESSED"))
            return R.drawable.ic_depressed;
        else if(this.emotion.equals("ANGRY"))
            return R.drawable.ic_angry;
        else if(this.emotion.equals("SURPRISED"))
            return R.drawable.ic_surprised;
        else if(this.emotion.equals("SADNESS"))
            return R.drawable.ic_sadness;
        else if(this.emotion.equals("WORRIED"))
            return R.drawable.ic_worried;
        return 0;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }
    public int getBadgeResource() {
        setBadgeResource();
        return badgeResource;
    }

    public void setBadgeResource() {
        if (quiz3Date!=null)
            this.badgeResource = R.drawable.ic_badge3;
        else if (quiz2Date!=null)
            this.badgeResource = R.drawable.ic_badge2;
        else if (quiz1Date!=null)
            this.badgeResource = R.drawable.ic_badge1;
        else
            this.badgeResource = R.drawable.ic_badge0;
    }

    public int setBadge(String getQuizDate, int n){
        if(n==3&&!getQuizDate.equals("Not solved yet")){
            return R.drawable.ic_badge3;
        }else if(n==2&&!getQuizDate.equals("Not solved yet")) {
            return R.drawable.ic_badge2;
        }else if(n==1&&!getQuizDate.equals("Not solved yet")) {
            return R.drawable.ic_badge1;
        }else if(getQuizDate.equals("Not solved yet")) {
            return R.drawable.ic_badge0;
        }
        return R.drawable.ic_badge0;
    }
}
