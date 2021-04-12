package com.example.finalproject.model;

public class User {
    private int userNum; // PK
    private String userName;
    private String userNickname;
    private String userId;
    private String userPwd;
    private String userGender;

    public User(int userNum, String userName, String userNickname, String userId, String userPwd, String userGender){
        this.userNum = userNum;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userId = userId;
        this.userPwd = userPwd;
        this. userGender = userGender;
    }

    public int getUserNum(){
        return userNum;
    }
    public String getUserName(){
        return userName;
    }
    public String getUserNickname(){
        return userNickname;
    }
    public String getUserId(){
        return userId;
    }
    public String getUserPwd(){
        return userPwd;
    }
    public String getUserGender(){
        return userGender;
    }
}
