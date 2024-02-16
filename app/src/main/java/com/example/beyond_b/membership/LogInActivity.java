package com.example.beyond_b.membership;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beyond_b.MainActivity;
import com.example.beyond_b.databinding.ActivityLoginBinding;

public class LogInActivity extends AppCompatActivity implements LoginRequest.LoginCallback {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText emailEditText = binding.loginEmail;
        EditText passwordEditText = binding.loginPassword;

        //sign up 버튼 클릭(로그인)
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    new Thread() {
                        public void run() {
                            LoginRequest loginRequest = new LoginRequest(LogInActivity.this, LogInActivity.this);
                            loginRequest.Login(email, password);
                        }
                    }.start();


                } else {
                    Toast.makeText(getApplicationContext(), "이메일과 비밀번호를 모두 입력해주세요",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //creat new account 버튼 클릭(회원가입)
        binding.loginNewAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onLoginResponse(boolean isSuccess, String message) {
        if (isSuccess) {
            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            // 로그인 실패 처리
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
}