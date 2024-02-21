package com.example.beyond_b.network;

import com.example.beyond_b.my_page.MyPage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    //book
    @GET("api/book")
    Call<ApiResponse.BookResponse> getBook(@Query("emotion") String emotion);
    @GET("api/book/{bookId}/detail")
    Call<ApiResponse.BookDetailResponse> getBookDetail(@Path("bookId") int bookId);
    @PATCH("age")
    Call<ApiResponse.MyPageResponse> updateAge(@Body MyPage ageRequest);
    @GET("mypage")
    Call<ApiResponse.MyPageResponse> getMyPage();
}