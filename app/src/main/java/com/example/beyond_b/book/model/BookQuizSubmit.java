package com.example.beyond_b.book.model;

import com.google.gson.annotations.SerializedName;

public class BookQuizSubmit {

    @SerializedName("bookId")
    private int bookId;
    @SerializedName("step")
    private int step;

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getBookId() {
        return bookId;
    }
    public int getStep(){
       return step;
    }
}
