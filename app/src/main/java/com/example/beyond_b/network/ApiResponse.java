package com.example.beyond_b.network;

import com.example.beyond_b.book.model.Book;
import com.example.beyond_b.book.model.BookDetailResult;
import com.example.beyond_b.my_page.MyPage;

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

    public class MyPageResponse{
        private boolean isSuccess;
        private String code;
        private String message;

        private MyPage result;

        public MyPage getResult() {
            return this.result;
        }
    }
}
