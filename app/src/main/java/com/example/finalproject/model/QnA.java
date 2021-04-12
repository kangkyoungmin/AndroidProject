package com.example.finalproject.model;

public class QnA {
    private int QnANum;
    private String nickname; // FK
    private String QnATitle;
    private String QnAContent;

    public QnA(int QnANum, String nickname, String QnATitle, String QnAContent){
        this.QnANum = QnANum;
        this.nickname = nickname;
        this.QnATitle = QnATitle;
        this.QnAContent = QnAContent;
    }

    public int getQnANum(){
        return QnANum;
    }
    public String getNickname(){
        return nickname;
    }
    public String getQnATitle(){
        return QnATitle;
    }
    public String getQnAContent(){
        return QnAContent;
    }
}
