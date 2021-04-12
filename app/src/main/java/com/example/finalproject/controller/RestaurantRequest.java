//package com.example.finalproject.controller;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Response;
//import com.android.volley.toolbox.StringRequest;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class RestaurantRequest extends StringRequest {
//    // 서버 URL 설정
//    final static private String URL = "http://kkahbhm.dothome.co.kr/Restaurant.php"; // todo. Restaurant.php 작성
//    private Map<String, Integer> map;
//
//    public RestaurantRequest(int restNum, Response.Listener<String> listener) {
//        super(Method.POST, URL, listener, null);
//
//        map = new HashMap<>();
//        map.put("restNum", restNum);
//    }
//
////    @Override
////    protected Map<String, String> getParams() throws AuthFailureError {
////        return map;
////    }
//}