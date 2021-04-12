package com.example.finalproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.model.ChatRoom;

public class RoomDetailActivity extends AppCompatActivity {
    // todo. DB와 연동하여 값 변경

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomdetail);
        Intent intent = getIntent();

        TextView tv_title = findViewById(R.id.detailTitleTextView);
        TextView tv_restaurant = findViewById(R.id.detailRestaurantTextView2);
        TextView tv_time = findViewById(R.id.detailTimeTextView2);
        TextView tv_personnel = findViewById(R.id.detailPersonnelTextView2);
        TextView tv_gender = findViewById(R.id.detailGenderTextView2);
        TextView tv_other = findViewById(R.id.detailOtherTextView2);

        tv_title.setText(intent.getStringExtra("chatTitle"));
        tv_restaurant.setText(intent.getStringExtra("chatRest"));
        tv_time.setText(intent.getStringExtra("chatTime"));
        tv_personnel.setText(intent.getIntExtra("chatPerson",0)+" 명");
        tv_other.setText(intent.getStringExtra("chatEct"));
        tv_gender.setText(intent.getStringExtra("chatGender"));
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.detailButton:
                Toast.makeText(this, "임시 채팅방으로 이동", Toast.LENGTH_SHORT).show();
                // todo 1. 성별 진위여부 확인
                // todo 2. 방 번호
                Intent chatIntent = new Intent(this, ChatActivity.class);
                startActivity(chatIntent);
                finish();
                break;
        }
    }
}
