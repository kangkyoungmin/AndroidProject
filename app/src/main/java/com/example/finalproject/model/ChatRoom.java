package com.example.finalproject.model;

import com.example.finalproject.model.ChatRoom;

public class ChatRoom {
    private int image; // ~restNumber // FK
    private int roomMonth;
    private int roomDay;
    private int roomHour, roomMinute; // roomTime
    private String roomTitle;
    private String roomGender;
    private int roomPersonnel;
    private int roomNum; // PK
    private String ect = ""; // can NULL
    private String hash = ""; // can NULL
    // int userNum // FK

    private String roomTime;
    private String restName;

    // todo. DB와 연동하여 정보 저장
    public ChatRoom(int roomNum, int image,int roomMonth, int roomDay, int roomHour, int roomMinute, String roomTitle,
                    String roomGender, int roomPersonnel, String ect, String hash) {
        this.roomNum = roomNum;
        this.image = image;
        this.roomMonth = roomMonth;
        this.roomDay = roomDay;
        this.roomHour = roomHour;
        this.roomMinute = roomMinute;
        this.roomTitle = roomTitle;
        this.roomGender = roomGender;
        this.roomPersonnel = roomPersonnel;
        this.ect = ect;
        this.hash = hash;
        this.setRoomTime(this);
        this.setRestName(this);
    }

    private void setRoomTime(ChatRoom chatRoom) {
        String temp;
        if (chatRoom.getRoomMinute() < 10) {
            temp = chatRoom.getRoomHour() + ":0" + chatRoom.getRoomMinute();
        } else {
            temp = chatRoom.getRoomHour() + ":" + chatRoom.getRoomMinute();
        }
        chatRoom.roomTime = temp;
    }

    private void setRestName(ChatRoom chatRoom) {
//        for(restList room : ) resList for loop 돌아
        String temp = "식당이름"; // todo. 값 받아오기 구현
        chatRoom.restName = temp;
    }

    public int getRoomMonth() {
        return roomMonth;
    }

    public int getRoomDay() {
        return roomDay;
    }

    public String getRestName() {
        return restName;
    }

    private int getRoomHour() {
        return roomHour;
    }

    private int getRoomMinute() {
        return roomMinute;
    }

    public String getRoomTime() {
        return roomTime;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public int getImage() {
        return image;
    }

    public int getRoomPersonnel() {
        return roomPersonnel;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public String getRoomGender() {
        return roomGender;
    }

    public String getEct() {
        return ect;
    }

    public String getHash() {
        return hash;
    }
}
