package com.example.finalproject.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.finalproject.R;
import com.example.finalproject.controller.SignUpRequest;
import com.example.finalproject.controller.ValidateRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {
    EditText et_nickname, et_name, et_pwd, et_pwd2, et_id;
    RadioButton rb_man, rb_woman;
    RadioGroup rg_gender;
    String userGender = ""; // notSelect
    Button bt_validate; // 중복검사
    private boolean validate = false;
    private AlertDialog dialog;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        rb_man = findViewById(R.id.manRadioButton);
        rb_woman = findViewById(R.id.womanRadioButton);
        rg_gender = findViewById(R.id.signUpGenderGroup);

        et_nickname = findViewById(R.id.nicknameEditText);
        et_name = findViewById(R.id.nameEditText);
        et_pwd = findViewById(R.id.pwEditText);
        et_pwd2 = findViewById(R.id.pwEditText2);
        et_id = findViewById(R.id.idEditText);

        bt_validate = findViewById(R.id.validateButton);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.validateButton:
                userID = et_id.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {//사용할 수 있는 아이디라면
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                dialog = builder.setMessage("사용 가능한 아이디입니다.")
                                        .setPositiveButton("OK", null)
                                        .create();
                                dialog.show();
                                et_id.setEnabled(false); //아이디값을 바꿀 수 없도록 함
                                bt_validate.setEnabled(false);
                                validate = true; //검증완료
                                et_id.setBackgroundColor(getResources().getColor(R.color.Gray));
                                bt_validate.setBackgroundColor(getResources().getColor(R.color.Gray));
                            } else {//사용할 수 없는 아이디라면
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                dialog = builder.setMessage("중복된 아이디입니다.")
                                        .setNegativeButton("OK", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };// end Response.Listener
                //Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                ValidateRequest validateRequest = new ValidateRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                queue.add(validateRequest);
                break;
            case R.id.signUpButton:
                // 정보값 입력
                String userNickname = et_nickname.getText().toString();
                userID = et_id.getText().toString();
                String userPassword = et_pwd.getText().toString();
                String userPassword2 = et_pwd2.getText().toString();
                String userName = et_name.getText().toString();

                int gender = rg_gender.getCheckedRadioButtonId(); // gender select
                if (gender == rb_man.getId()) {
                    userGender = "남자";
                } else if (gender == rb_woman.getId()) {
                    userGender = "여자";
                }

                if (userNickname.length() < 2) { // nickname 진위여부
                    Toast.makeText(this, "닉네임은 2글자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
                    et_nickname.setText("");
                } else if (userNickname.length() > 8) {
                    Toast.makeText(this, "닉네임는 8글자 이하이어야 합니다.", Toast.LENGTH_SHORT).show();
                    et_nickname.setText("");
                } else if (userID.length() < 4) { // id 진위여부
                    Toast.makeText(this, "아이디는 4글자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
                    et_id.setText("");
                } else if (userID.length() > 10) {
                    Toast.makeText(this, "아이디는 10글자 이하이어야 합니다.", Toast.LENGTH_SHORT).show();
                    et_id.setText("");
                } else if (!validate) { //id 중복 진위여부
                    Toast.makeText(this, "아이디 중복검사를 실시하세요.", Toast.LENGTH_SHORT).show();
                } else if (userPassword.length() < 6) { // pwd 진위여부
                    Toast.makeText(this, "비밀번호는 6글자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
                    et_pwd.setText("");
                } else if (userPassword.length() > 15) {
                    Toast.makeText(this, "비밀번호는 15글자 이하이어야 합니다.", Toast.LENGTH_SHORT).show();
                    et_pwd.setText("");
                } else if (!userPassword.equals(userPassword2)) { // pwd2 진위여부
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    et_pwd.setText("");
                    et_pwd2.setText("");
                } else if (userName.length() < 2) { // name 진위여부
                    Toast.makeText(this, "이름은 2글자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
                    et_name.setText("");
                } else if (userName.length() > 4) {
                    Toast.makeText(this, "이름은 4글자 이하이어야 합니다.", Toast.LENGTH_SHORT).show();
                    et_name.setText("");
                } else if (userGender.equals("")) {
                    Toast.makeText(this, "성별을 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else { // if 성공적이면
                    Response.Listener<String> responseListener2 = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success) {
                                    Toast.makeText(getApplicationContext(), "회원 등록에 성공하였습니다", Toast.LENGTH_SHORT).show();
                                } else { //회원 등록에 실패한 경우
                                    Toast.makeText(getApplicationContext(), "회원 등록에 실패하였습니다", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    /* 서버로 Volley를 이용해서 요청함 */
                    SignUpRequest signUpRequest = new SignUpRequest(userNickname, userID, userPassword, userName, userGender, responseListener2);
                    RequestQueue queue2 = Volley.newRequestQueue(SignUpActivity.this);
                    queue2.add(signUpRequest);
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

}
