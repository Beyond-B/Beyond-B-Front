package com.example.beyond_b.book.model;

import com.google.gson.annotations.SerializedName;

public class BookContent {
    @SerializedName("bookId")
    private int bookId;
    @SerializedName("title")
    private String title;
    @SerializedName("bookSummary")
    private String bookSummary;
    @SerializedName("bookImage")
    private String bookImage;
    @SerializedName("author")
    private String author;

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

    public String getBookSummary() {
        return bookSummary;
    }

    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
