package com.example.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.model.User;

import java.util.ArrayList;

public class UserListViewAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<User> sample;

    public UserListViewAdapter(Context context, ArrayList<User> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public User getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.listview_userlist, null);

        TextView userName = view.findViewById(R.id.userListName);
        TextView userNickname = view.findViewById(R.id.userListNickname);
        TextView userID = view.findViewById(R.id.userListID);
        TextView userPwd = view.findViewById(R.id.userListPwd);

        userName.setText(sample.get(position).getUserName());
        userNickname.setText(sample.get(position).getUserNickname());
        userID.setText(sample.get(position).getUserId());
        userPwd.setText(sample.get(position).getUserPwd());
        return view;
    }
}
