package com.example.finalproject.controller;

import android.app.Activity;
import android.widget.Toast;

public class BackPressHandler {
    private long backKeyPressedTime = 0;    // 뒤로가기 버튼 누른 시간 저장
    private Activity activity;  // 종료시킬 Activity

    public BackPressHandler(Activity activity){
        this.activity = activity;
    }

    public void onBackPressed(){
        if(System.currentTimeMillis() > backKeyPressedTime + 2000){
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if(System.currentTimeMillis() <= backKeyPressedTime + 2000){
            activity.finish();
        }
    }

    public void showGuide(){
        Toast.makeText(activity, "\'뒤로가기\' 버튼을 한번 더 누르면 종료", Toast.LENGTH_SHORT).show();
    }
}
