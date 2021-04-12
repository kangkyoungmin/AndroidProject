package com.example.finalproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.adapter.ChatListViewAdapter;
import com.example.finalproject.model.ChatRoom;

import java.util.ArrayList;

public class RoomListActivity extends AppCompatActivity {
    ArrayList<ChatRoom> chatRoom;
    private ArrayList<ChatRoom> data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomlist);

        this.InitializeRoomList();    // todo 1. 아이템 정의 2. 리스트에 추가

        ListView listView = findViewById(R.id.roomListView);

        // 리스트 속의 아이템 연결결
        final ChatListViewAdapter chatListViewAdapter = new ChatListViewAdapter(this, chatRoom);
        listView.setAdapter(chatListViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // todo. id로 채팅방 선별
                Intent intentChat = new Intent(RoomListActivity.this, RoomDetailActivity.class);
                intentChat.putExtra("chatTitle", chatRoom.get(position).getRoomTitle());
                intentChat.putExtra("chatRest", chatRoom.get(position).getRestName());
                intentChat.putExtra("chatTime", chatRoom.get(position).getRoomTime());
                intentChat.putExtra("chatPerson", chatRoom.get(position).getRoomPersonnel());
                intentChat.putExtra("chatGender", chatRoom.get(position).getRoomGender());
                intentChat.putExtra("chatEct", chatRoom.get(position).getEct());
                startActivity(intentChat);// Intent 해당 채팅방으로 이동
            }
        });
    }

    public void InitializeRoomList() { // 아이템 정의, 리스트 추가
        chatRoom = new ArrayList<ChatRoom>(); // 리스트 선언
        // todo. 서버와 연동
        // 리스트 추가
        chatRoom.add(new ChatRoom(1, R.drawable.image_rest1, 5,20, 13,
                0, "나혼자 산다", "남자만", 2, "", "후문에서"));
        chatRoom.add(new ChatRoom(2, R.drawable.image_rest2, 5,21, 11,
                50, "후식은 밥먹고", "성별무관", 4, "기타제약사항적어봤다", ""));
    }

    public boolean onCreateOptionsMenu(Menu menu) { // menu 생성
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_roomcreate, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) { // menu 선택 시 동작
        switch (item.getItemId()) {
            case R.id.menu_createRoom:
                Intent createIntent = new Intent(this, CreateRoomActivity.class);
                startActivity(createIntent);
                break;
        }
        return true;
    }
}
