package com.example.beyond_b.membership;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginRequest {
    OkHttpClient client = new OkHttpClient();

    private LoginCallback callback;
    private DatabaseHelper databaseHelper;

    public LoginRequest(LoginCallback callback, Context context) {
        this.callback = callback;
        databaseHelper = new DatabaseHelper(context);
    }
    private String message, accessToken, refreshToken;
    private int userId;
    private boolean isSuccess, isLogin;


    public void Login(String email, String password){

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Login", e.getMessage());
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), JSON);
        Request request = new Request.Builder()
                .url("http://beyondb-dev-env.eba-uje38whv.ap-northeast-2.elasticbeanstalk.com/api/auth/login")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String myResponse = response.body().string();
                Log.d("tag", myResponse);
                try {
                    // JSON 파싱
                    JSONObject jsonResponse = new JSONObject(myResponse);
                    isSuccess = jsonResponse.getBoolean("isSuccess");
                    message = jsonResponse.getString("message");

                    // 성공 시 accessToken 저장
                    if (isSuccess) {
                        JSONObject resultObject = jsonResponse.getJSONObject("result");
                        //userId = resultObject.getInt("userId");
                        accessToken = resultObject.getString("accessToken");
                        refreshToken = resultObject.getString("refreshToken");
                        //isLogin = resultObject.getBoolean("isLogin");

                        // SQLite에 accessToken 저장
                        databaseHelper.addAccessToken(accessToken);

                        callback.onLoginResponse(isSuccess, message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Login", "JSONException: " + e.getMessage(), e);
                }
            }
        });
    }
    public interface LoginCallback {
        void onLoginResponse(boolean isSuccess, String message);
    }
}
