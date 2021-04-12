package com.example.finalproject.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.finalproject.R;
import com.example.finalproject.adapter.ChatListViewAdapter;
import com.example.finalproject.adapter.UserListViewAdapter;
import com.example.finalproject.controller.UserDeleteRequest;
import com.example.finalproject.controller.UserInfoRequest;
import com.example.finalproject.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManageUserActivity extends AppCompatActivity {
    ArrayList<User> user;
    int mInt = 0;
    String deleteUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageuser);

        this.InitializeUserList();    // todo. 유저들의 정보를 받아오는 함수

        ListView listView = findViewById(R.id.userListView);
        final UserListViewAdapter userListViewAdapter = new UserListViewAdapter(this, user);
        listView.setAdapter(userListViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
//                deleteUser = selectedUserId
                // todo. 선택된 회원에 대한 정보 받기
                // 선택 시 동작
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageUserActivity.this);

                builder.setTitle("회원 정보 삭제").setMessage("정말로 삭제할까요?");
                builder.setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 회원정보 삭제
                        Toast.makeText(getApplicationContext(), "삭제되었습니다", Toast.LENGTH_SHORT).show();
                        Response.Listener<String> responseListener2 = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    boolean success = jsonObject.getBoolean("success");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        /* 서버로 Volley를 이용해서 요청함 */
                        UserDeleteRequest userDeleteRequest = new UserDeleteRequest(deleteUser, responseListener2);
                        RequestQueue queue = Volley.newRequestQueue(ManageUserActivity.this);
                        queue.add(userDeleteRequest);
                    }
                });
                AlertDialog dialog1 = builder.create();
                dialog1.show();
            }
        });
    }

    public void InitializeUserList(){ // 모든 유저 정보를 받아오는 함수
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        /* 서버로 Volley를 이용해서 요청함 */
        UserInfoRequest userInfoRequest = new UserInfoRequest(responseListener); // todo. UserInfo.php파일 작성
        RequestQueue queue = Volley.newRequestQueue(ManageUserActivity.this);
        queue.add(userInfoRequest);
        // todo. 정보를 얻어온 뒤 User class에 값 대입
    }

    public boolean onCreateOptionsMenu(Menu menu) { // menu 생성
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_userdelete, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) { // menu 선택 시 동작
        switch (item.getItemId()) {
            case R.id.menu_deleteUser:
                mInt = 1;
                Toast.makeText(getApplicationContext(), "삭제할 회원정보를 선택하세요.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_notDeleteUser:
                mInt = 0;
                Toast.makeText(getApplicationContext(), "회원정보 삭제를 그만합니다.", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
