package com.example.beyond_b.book.model;

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
        private List<BookDetailResult> result;
    }

}
