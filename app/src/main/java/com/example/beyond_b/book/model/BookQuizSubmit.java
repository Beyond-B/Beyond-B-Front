package com.example.beyond_b.book.model;

import com.google.gson.annotations.SerializedName;

public class BookQuizSubmit {
    @SerializedName("bookId")
    private String bookId;

    @SerializedName("step")
    private String step;

    public String setBookId() {
        return bookId;
    }

}
