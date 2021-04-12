package com.example.finalproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.controller.BackPressHandler;
import com.example.finalproject.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {


    //public static int userNumber;
    public static String userNickname, userID, userName, userPwd, userGender;
    private BackPressHandler backPressHandler = new BackPressHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();
        //userNumber = intent.getIntExtra("userNumber",0); // todo. DB와 연동하여 회원번호 받기
        userNickname = intent.getStringExtra("userNickname");
        userID = intent.getStringExtra("userID");
        userPwd = intent.getStringExtra("userPassword");
        userName = intent.getStringExtra("userName");
        userGender = intent.getStringExtra("userGender"); // user정보 저장
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.listImageView:
                Intent intentList = new Intent(this, RoomListActivity.class);
                startActivity(intentList);
                break;
            case R.id.mapImageView:
                Intent intentMap = new Intent(this, MapActivity.class);
                startActivity(intentMap);
                break;
            case R.id.myPageImageView:
                Intent intentMyPage = new Intent(this, MyPageActivity.class);
                startActivity(intentMyPage);
                break;
        }
    }

    @Override
    public void onBackPressed() {    //종료시 뒤로가기 두번 누르기
        backPressHandler.onBackPressed();
    }
}
