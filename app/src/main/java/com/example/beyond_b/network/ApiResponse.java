package com.example.beyond_b.network;

import com.example.beyond_b.book.model.Book;
import com.example.beyond_b.book.model.BookContent;
import com.example.beyond_b.book.model.BookDetailResult;
import com.example.beyond_b.book.model.BookQuiz;
import com.example.beyond_b.diary.DiarySummaries;
import com.example.beyond_b.diary.DiaryDetail;
import com.example.beyond_b.my_page.MyPage;

import java.util.ArrayList;
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

    public  class DiaryCreateResponse {
        private String date;
        private String emotion;
        private String event;
        private String thought;
        private String emotionSpecific;
        private String behavior;
        private String result;
    }

    public class DiaryDetailResponse {
        private boolean isSuccess;
        private String code;
        private String message;
        private DiaryDetail result;

        public DiaryDetail getResult(){
            return this.result;
        }
    }

    public class MonthlyDiaryResponse {
        private boolean isSuccess;
        private String code;
        private String message;
        private ArrayList<DiarySummaries> diarySummaries;

        public ArrayList<DiarySummaries> getDiarySummaries() {
            return this.diarySummaries;
        }
    }
}
