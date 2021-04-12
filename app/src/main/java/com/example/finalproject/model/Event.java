package com.example.finalproject.model;

public class Event {
    private int eventNum;
    private String eventTitle;
    private String eventContent;

    public Event(int eventNum, String eventTitle, String eventContent){
        this.eventNum = eventNum;
        this.eventTitle = eventTitle;
        this.eventContent = eventContent;
    }

    public int getEventNum(){
        return eventNum;
    }
    public String getEventTitle(){
        return eventTitle;
    }
    public String getEventContent(){
        return eventContent;
    }
}
