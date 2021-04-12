package com.example.finalproject.controller;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignUpRequest extends StringRequest {
    // 서버 URL 설정
    final static private String URL="http://kkahbhm.dothome.co.kr/Register.php";
    private Map<String, String> map;

    public SignUpRequest(String userNickname, String userID, String userPassword,
                         String userName, String userGender,
                         Response.Listener<String> listener) {
        super(Method.POST,URL,listener,null);

        map= new HashMap<>();
        map.put("userNickname",userNickname);
        map.put("userID",userID);
        map.put("userPassword",userPassword);
        map.put("userName",userName);
        map.put("userGender",userGender);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}