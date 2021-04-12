package com.example.finalproject.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.adapter.ChatListViewAdapter;
import com.example.finalproject.model.ChatRoom;

import java.util.ArrayList;

public class MapDetailActivity extends AppCompatActivity {
    ArrayList<ChatRoom> chatRoom;
    Context context;
    TextView tv_restName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapdetail);
        context = this;
        tv_restName = findViewById(R.id.mapDetailTextView);
        tv_restName.setText("고수찜닭"); // todo. 식당 이름 출력하도록 변경

        this.InitializeRoomList();    //현재 방의 정보를 받아오는 함수

        ListView listView = (ListView) findViewById(R.id.mapDetailListView);
        final ChatListViewAdapter chatListViewAdapter = new ChatListViewAdapter(this, chatRoom);
        listView.setAdapter(chatListViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // 해당 부분은 RoomListActivity와 동일
                Intent intentChat = new Intent(MapDetailActivity.this, RoomDetailActivity.class);
                intentChat.putExtra("chatTitle", chatRoom.get(position).getRoomTitle());
                intentChat.putExtra("chatRest", chatRoom.get(position).getRestName());
                intentChat.putExtra("chatTime", chatRoom.get(position).getRoomTime());
                intentChat.putExtra("chatPerson", chatRoom.get(position).getRoomPersonnel());
                intentChat.putExtra("chatGender", chatRoom.get(position).getRoomGender());
                intentChat.putExtra("chatEct", chatRoom.get(position).getEct());
                startActivity(intentChat);
            }
        });
    }

    public void InitializeRoomList() { // RoomListActivity와 연동하여 생각 + 해당 식당만 출력
        chatRoom = new ArrayList<ChatRoom>(); // 리스트 선언
        // todo. 서버와 연동
        // 리스트 추가
    }
}
