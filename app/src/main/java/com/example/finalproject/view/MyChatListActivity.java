package com.example.finalproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.adapter.ChatListViewAdapter;
import com.example.finalproject.model.ChatRoom;

import java.util.ArrayList;

public class MyChatListActivity extends AppCompatActivity {
    ArrayList<ChatRoom> myChatRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mychatlist);

        this.InitializeRoomList();    //현재 방의 정보를 받아오는 함수

        ListView listView = (ListView) findViewById(R.id.myChatListView);
        final ChatListViewAdapter myChatListViewAdapter = new ChatListViewAdapter(this, myChatRoom);
        listView.setAdapter(myChatListViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // todo. 선택된 방 번호로 조작
                Intent intentChat = new Intent(MyChatListActivity.this, ChatActivity.class);
                startActivity(intentChat);// Intent 해당 채팅방으로 이동
            }
        });
    }

    public void InitializeRoomList() { // RoomListActivity와 연동하여 생각
//        myChatRoom = new ArrayList<ChatRoom>();

        // todo. RoomList(식당사진, 방이름, 방세부사항)를 DB에서 받아와서 표현하는 방법 구현
    }

}
