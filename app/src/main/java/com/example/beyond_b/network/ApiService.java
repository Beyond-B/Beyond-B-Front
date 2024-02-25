package com.example.beyond_b.network;

import com.example.beyond_b.book.model.BookQuiz;
import com.example.beyond_b.book.model.BookQuizSubmit;
import com.example.beyond_b.diary.Diary;
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
    // Book API
    @GET("api/book")
    Call<ApiResponse.BookResponse> getBook(@Query("emotion") String emotion);
    @GET("api/book/{bookId}/detail")
    Call<ApiResponse.BookDetailResponse> getBookDetail(@Path("bookId") int bookId);

    // Quiz API
    @GET("api/quiz/{bookId}")
    Call<ApiResponse.QuizResponse> getQuiz(@Path("bookId") int bookId);
    @POST("api/quiz/submit")
    Call<ApiResponse.QuizSubmitResponse> submitQuiz(@Body BookQuizSubmit submit);

    // Diary API
    @GET("api/diary/{diaryId}/detail")
    Call<ApiResponse.DiaryDetailResponse> getDetailDiary(@Path("diaryId") int diaryId);
    @GET("api/diary/monthly")
    Call<ApiResponse.MonthlyDiaryResponse> monthlyDiary(@Query("year") String year, @Query("month") String month);
    //bookRecommend
    @GET("api/book/recommend")
    Call<ApiResponse.BookRecommend> recommendBook(@Query("emotion") String emotion);

    // Profile API
    @PATCH("age")
    Call<ApiResponse.MyPageResponse> updateAge(@Body MyPage ageRequest);
    @GET("mypage")
    Call<ApiResponse.MyPageResponse> getMyPage();
    @DELETE("user")
    Call<ApiResponse.DeleteAccountResponse> deleteAccount();
    @POST("/api/diary/create")
    Call<ApiResponse.DiaryCreateResponse> creatDiary(@Body Diary diary);
}