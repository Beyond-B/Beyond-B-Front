package com.example.beyond_b.membership;

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

public class SignUpRequest {
    OkHttpClient client = new OkHttpClient();

    public void signUp(String username, String age, String email, String password) {
        // 이메일과 비밀번호를 JSON 형태로 변환
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("username", username);
            jsonObject.put("age", age);
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("SignUp", e.getMessage());
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

        // POST 요청 생성
        Request request = new Request.Builder()
                .url("http://beyondb-dev-env.eba-uje38whv.ap-northeast-2.elasticbeanstalk.com/sign-up")
                .post(body)
                .build();

        // 요청을 비동기적으로 실행
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                Log.d("SignUp", e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String myResponse = response.body().string();

            }
        });
    }
}
