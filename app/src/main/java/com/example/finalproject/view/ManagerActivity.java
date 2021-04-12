package com.example.finalproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

public class ManagerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.manageUserTextView:
                Intent userIntent = new Intent(this, ManageUserActivity.class);
                startActivity(userIntent);
                break;
            case R.id.manageQnATextView:
                Intent qnAIntent = new Intent(this, QnAActivity.class);
                startActivity(qnAIntent);
                break;
            case R.id.manageEventTextView:
                Intent eventIntent = new Intent(this, EventActivity.class);
                startActivity(eventIntent);
                break;
        }
    }
}
