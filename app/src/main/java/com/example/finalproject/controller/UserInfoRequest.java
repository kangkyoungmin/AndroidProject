package com.example.finalproject.controller;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UserInfoRequest extends StringRequest {

    final static private String URL = "http://kkahbhm.dothome.co.kr/UserInfo.php";
    private Map<String, String> parameters;

    public UserInfoRequest(Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);//해당 URL에 POST방식으로 파마미터들을 전송함
        parameters = new HashMap<>();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}