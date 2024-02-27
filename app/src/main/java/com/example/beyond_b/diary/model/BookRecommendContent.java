package com.example.beyond_b.diary.model;

import com.google.gson.annotations.SerializedName;

public class BookRecommendContent {
    @SerializedName("bookId")
    private int bookId;
    @SerializedName("title")
    private String title;
    @SerializedName("author")
    private String author;

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

}
