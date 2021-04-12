package com.example.finalproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.finalproject.view.UserActivity.userGender;
import static com.example.finalproject.view.UserActivity.userNickname;

public class MyPageActivity extends AppCompatActivity {
    final static int ACT_EDIT = 0;
    TextView tv_nickname;
    CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        tv_nickname = findViewById(R.id.myPageNicknameTextView);
        circleImageView = findViewById(R.id.myPageImageView);

        if (userGender.equals("여자")) { // image set
            circleImageView.setImageResource(R.drawable.image_woman);
        }
        tv_nickname.setText(userNickname);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.myChatListTextView:
                Intent intentMyChatList = new Intent(this, MyChatListActivity.class);
                startActivity(intentMyChatList);
                finish();
                break;
            case R.id.myInfoChangeTextView:
                Intent intentInfoChange = new Intent(this, MyInfoChangeActivity.class);
                startActivityForResult(intentInfoChange,ACT_EDIT);
                break;
            case R.id.serviceCenterTextView:
                Intent intentServiceCenter = new Intent(this, ServiceCenterActivity.class);
                startActivity(intentServiceCenter);
                break;
            case R.id.logoutTextView:
                Toast.makeText(this, "로그인 화면으로 돌아갑니다", Toast.LENGTH_SHORT).show();
                Intent intentLogout = new Intent(this, MainActivity.class);
                intentLogout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentLogout);
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case ACT_EDIT:
                if (resultCode == RESULT_OK) {
                   tv_nickname.setText(data.getStringExtra("TextOut"));
                }
                break;
        }
    }
}

