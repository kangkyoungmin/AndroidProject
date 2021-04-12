package com.example.finalproject.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.finalproject.R;
import com.example.finalproject.controller.InfoChangeRequest;
import com.example.finalproject.controller.UserDeleteRequest;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.finalproject.view.UserActivity.userID;
import static com.example.finalproject.view.UserActivity.userName;
import static com.example.finalproject.view.UserActivity.userNickname;
import static com.example.finalproject.view.UserActivity.userPwd;

public class MyInfoChangeActivity extends AppCompatActivity {
    private String tempNickname = "", tempPwd = "";
    EditText et_nickname, et_pwd, et_pwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfochange);

        TextView tv_name = findViewById(R.id.infoChangeNameTextView2);
        TextView tv_id = findViewById(R.id.infoChangeIdTextView2);
        et_nickname = findViewById(R.id.infoChangeNicknameEditText);
        et_pwd = findViewById(R.id.infoChangePwdEditText);
        et_pwd2 = findViewById(R.id.infoChangePwdEditText2);

        tv_name.setText(userName);
        tv_id.setText(userID);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.nicknameChangeButton:
                String inputNickname = et_nickname.getText().toString();
                if (inputNickname.equals("")) {
                    Toast.makeText(this, "변경할 닉네임을 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    tempNickname = inputNickname;
                    Toast.makeText(this, "저장하기를 눌러주세요", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.pwdChangeButton:
                String inputPwd = et_pwd.getText().toString();
                String inputPwd2 = et_pwd2.getText().toString();
                if (inputPwd.equals("")) {
                    Toast.makeText(this, "변경할 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                } else if (inputPwd.equals(inputPwd2)) {
                    tempPwd = inputPwd;
                    Toast.makeText(this, "저장하기를 눌러주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.infoChangeButton:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("회원 정보 변경").setMessage("변경내용을 저장할까요?");
                builder.setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "변경되었습니다", Toast.LENGTH_SHORT).show();
                        if (!tempNickname.equals("")) {
                            userNickname = tempNickname;
                        }
                        if (!tempPwd.equals("")) {
                            userPwd = tempPwd;
                        }
                        Response.Listener<String> responseListener = new Response.Listener<String>() {  // todo. DB값 변경 (php파일 확인할 것)
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
                        /* 서버로 Volley를 이용해서 요청함 */ // todo. DB에서 값 변경이 되지 않음.
                        InfoChangeRequest infoChangeRequest = new InfoChangeRequest(userID, userNickname, userPwd, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(MyInfoChangeActivity.this);
                        queue.add(infoChangeRequest);

                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                AlertDialog dialog1 = builder.create();
                dialog1.show();
                break;
            case R.id.infoDeleteButton:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);

                builder2.setTitle("회원 정보 삭제").setMessage("회원정보를 삭제할까요?");
                builder2.setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder2.setNegativeButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
                        UserDeleteRequest userDeleteRequest = new UserDeleteRequest(userID, responseListener2);
                        RequestQueue queue = Volley.newRequestQueue(MyInfoChangeActivity.this);
                        queue.add(userDeleteRequest);
                        // 처음 화면으로 이동
                        Intent intentDelete = new Intent(MyInfoChangeActivity.this, MainActivity.class);
                        intentDelete.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intentDelete);
                    }
                });
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
                break;
        }

    }

}
