package com.example.beyond_b.network;

import com.example.beyond_b.book.model.Book;
import com.example.beyond_b.book.model.BookContent;
import com.example.beyond_b.book.model.BookDetailResult;
import com.example.beyond_b.book.model.BookQuiz;
import com.example.beyond_b.book.model.DiarySummaries;
import com.example.beyond_b.my_page.MyPage;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {

    public class BookResponse{
        private boolean isSuccess;
        private String code;
        private String message;
        private List<Book> result;

        public List<Book> getResult() {
            return this.result;
        }
    }

    public class BookDetailResponse{
        private boolean isSuccess;
        private String code;
        private String message;
        private BookDetailResult result;

        public BookDetailResult getResult() {
            return this.result;
        }
    }

    public class BookRecommend{
        private boolean isSuccess;
        private String code;
        private String message;
        private BookContent result;

        public BookContent getResult() {
            return this.result;
        }
    }

    public class MyPageResponse{
        private boolean isSuccess;
        private String code;
        private String message;
        private MyPage result;

        public MyPage getResult() {
            return this.result;
        }
    }

    public class QuizResponse{
        private boolean isSuccess;
        private String code;
        private String message;
        private BookQuiz result;

        public BookQuiz getResult() {
            return this.result;
        }
    }

    public class QuizSubmitResponse {
        private boolean isSuccess;
        private String code;
        private String message;

        public String getMessage() {
            return this.message;
        }
    }

    public class DeleteAccountResponse {
        private boolean isSuccess;
        private String code;
        private String message;
        private String result;
    }

    public  class DiaryCreateResponse{
        private String date;
        private String emotion;
        private String event;
        private String thought;
        private String emotionSpecific;
        private String behavior;
        private String result;

    public class MonthlyDairy {
        private boolean isSuccess;
        private String code;
        private String message;
        private List<DiarySummaries> result;

        public List<DiarySummaries> getResult() {
            return this.result;
        }
    }
}
