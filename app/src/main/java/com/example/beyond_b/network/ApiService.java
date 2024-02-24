package com.example.beyond_b.network;

import com.example.beyond_b.book.model.BookQuiz;
import com.example.beyond_b.book.model.BookQuizSubmit;
import com.example.beyond_b.my_page.MyPage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/book")
    Call<ApiResponse.BookResponse> getBook(@Query("emotion") String emotion);
    @GET("api/book/{bookId}/detail")
    Call<ApiResponse.BookDetailResponse> getBookDetail(@Path("bookId") int bookId);
    @PATCH("age")
    Call<ApiResponse.MyPageResponse> updateAge(@Body MyPage ageRequest);
    @GET("mypage")
    Call<ApiResponse.MyPageResponse> getMyPage();
    @GET("api/quiz/{bookId}")
    Call<ApiResponse.QuizResponse> getQuiz(@Path("bookId") int bookId);
    @POST("api/quiz/submit")
    Call<ApiResponse.QuizSubmitResponse> submitQuiz(@Body BookQuizSubmit submit);
    @DELETE("user")
    Call<ApiResponse.DeleteAccountResponse> deleteAccount();
}