package com.example.beyond_b.book.model;

import com.example.beyond_b.R;
import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("bookId")
    private int bookId;
    @SerializedName("title")
    private String title;
    @SerializedName("author")
    private String author;
    @SerializedName("quiz1")
    private boolean quiz1;
    @SerializedName("quiz2")
    private boolean quiz2;
    @SerializedName("quiz3")
    private boolean quiz3;

    private int badgeResource;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isQuiz1() {
        return quiz1;
    }

    public void setQuiz1(boolean quiz1) {
        this.quiz1 = quiz1;
    }

    public boolean isQuiz2() {
        return quiz2;
    }

    public void setQuiz2(boolean quiz2) {
        this.quiz2 = quiz2;
    }

    public boolean isQuiz3() {
        return quiz3;
    }

    public void setQuiz3(boolean quiz3) {
        this.quiz3 = quiz3;
    }

    public int getBadgeResource() {
        setBadgeResource();
        return badgeResource;
    }

    public void setBadgeResource() {
        if (quiz3)
            this.badgeResource = R.drawable.ic_badge3;
        else if (quiz2)
            this.badgeResource = R.drawable.ic_badge2;
        else if (quiz1)
            this.badgeResource = R.drawable.ic_badge1;
        else
            this.badgeResource = R.drawable.ic_badge0;
    }
}
