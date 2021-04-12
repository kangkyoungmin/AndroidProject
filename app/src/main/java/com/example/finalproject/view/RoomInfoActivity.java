package com.example.finalproject.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

public class RoomInfoActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roominfo);

        // todo. DB와 연동하여 textView의 값 설정
    }

    public void mOnClick(View v){
        switch (v.getId()){
            case R.id.infoButton:
                finish();
                break;
        }
    }
}
