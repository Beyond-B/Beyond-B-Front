package com.example.beyond_b.membership;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beyond_b.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity implements SignUpRequest.SignupCallback {
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText usernameEditText = binding.signUpUsername;
        EditText ageEditText = binding.signUpAge;
        EditText emailEditText = binding.signUpEmail;
        EditText passwordEditText = binding.signUpPassword;

        //sign up 버튼 클릭(회원가입)
        binding.signUpSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String age = ageEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                new Thread() {
                    public void run() {
                        SignUpRequest signUpRequest = new SignUpRequest(SignUpActivity.this);
                        signUpRequest.signUp(username, age, email, password);
                    }
                }.start();
            }
        });

    }

    @Override
    public void onSignupResponse(boolean isSuccess, String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isSuccess) {
                    finish();
                    Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}