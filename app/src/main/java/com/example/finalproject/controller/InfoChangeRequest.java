package com.example.finalproject.controller;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InfoChangeRequest extends StringRequest {
    // 서버 URL 설정
    final static private String URL = "http://kkahbhm.dothome.co.kr/InfoChange.php";
    private Map<String, String> map;

    public InfoChangeRequest(String userID, String userNickname, String userPwd,
                             Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userNickname", userNickname);
        map.put("userPwd", userPwd);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
