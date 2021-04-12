package com.example.finalproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.finalproject.R;
import com.example.finalproject.controller.BackPressHandler;
import com.example.finalproject.controller.LoginRequest;
import com.example.finalproject.model.Restaurant;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static int boot = 0;

    RadioButton r_userBtn;
    RadioGroup radioGroup;
    EditText et_id, et_pwd;
    BackPressHandler backPressHandler = new BackPressHandler(this);

    // 어플을 켰을때  메인액티비티의 레이아웃이 사용자한테 보여지기 전에 호출
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // activity_main 레이아웃을 set

        if (boot == 0) {
            Intent intentLoading = new Intent(this, LoadingActivity.class);
            intentLoading.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY); // history에 남기지 않는다.
            startActivity(intentLoading);  // loadingActivity 구현
            boot++;
        }

        r_userBtn = findViewById(R.id.userRadioButton);
        radioGroup = findViewById(R.id.loginRadioGroup);   //radioButton
        et_id = findViewById(R.id.mIdEditText);
        et_pwd = findViewById(R.id.mPwdEditText);
    }


    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                int id = radioGroup.getCheckedRadioButtonId();
                String userID = et_id.getText().toString();
                String userPassword = et_pwd.getText().toString();

                if (userID.equals("")) {
                    Toast.makeText(this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
                } else if (userPassword.equals("")) {
                    Toast.makeText(this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    if (id == r_userBtn.getId()) {
                        // user id 진위여부
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject((response));
                                    boolean success = jsonObject.getBoolean("success");
                                    if (success) { //로그인에 성공한 경우
                                        String userNickname = jsonObject.getString("userNickname");
                                        String userID = jsonObject.getString("userID");
                                        String userPassword = jsonObject.getString("userPassword");
                                        String userName = jsonObject.getString("userName");
                                        String userGender = jsonObject.getString("userGender");

                                        Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다", Toast.LENGTH_SHORT).show();
                                        Intent userIntent = new Intent(MainActivity.this, UserActivity.class);
                                        userIntent.putExtra("userNickname", userNickname);
                                        userIntent.putExtra("userID", userID);
                                        userIntent.putExtra("userPassword", userPassword);
                                        userIntent.putExtra("userName", userName);
                                        userIntent.putExtra("userGender", userGender);
                                        startActivity(userIntent);
                                        finish();
                                    } else { //회원정보에 실패한 경우
                                        Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다", Toast.LENGTH_SHORT).show();
                                        et_pwd.setText("");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                        queue.add(loginRequest);
                    } else {
                        // manager id 진위여부 // 관리자1 id == onqijonqi, pwd == kkahbhm
                        if (userID.equals("onqijonqi") && userPassword.equals("kkahbhm")) {
                            Toast.makeText(this, "관리자모드로 실행합니다.", Toast.LENGTH_SHORT).show();
                            Intent intentManager = new Intent(this, ManagerActivity.class);
                            startActivity(intentManager);
                        } else {
                            Toast.makeText(getApplicationContext(), "관리자가 아닙니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case R.id.signUpButton:
                Intent intentSingUp = new Intent(this, SignUpActivity.class);
                startActivity(intentSingUp);
                break;
            case R.id.findPasswordButton:
                Intent intentFindPassword = new Intent(this, FindPasswordActivity.class);
                startActivity(intentFindPassword);
                break;
        }
    }

    @Override
    public void onBackPressed() {    //종료시 뒤로가기 두번 누르기
        backPressHandler.onBackPressed();
    }
}
