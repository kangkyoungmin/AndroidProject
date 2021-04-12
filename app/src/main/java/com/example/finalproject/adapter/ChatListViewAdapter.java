package com.example.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.model.ChatRoom;

import java.util.ArrayList;

public class ChatListViewAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<ChatRoom> sample;

    public ChatListViewAdapter(Context context, ArrayList<ChatRoom> data) {
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
    public ChatRoom getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.listview_roomlist, null); // todo. recycle구현

        ImageView imageView = view.findViewById(R.id.listViewImage);
        TextView roomTitle = view.findViewById(R.id.listViewTitleTextView);
        TextView roomSub = view.findViewById(R.id.listViewSubTextView);
        TextView roomInfo = view.findViewById(R.id.listViewInfoTextView);
        TextView roomPersonnel = view.findViewById(R.id.listViewPersonnelTextView);

        imageView.setImageResource(sample.get(position).getImage());
        roomTitle.setText(sample.get(position).getRoomTitle());
        roomInfo.setText(sample.get(position).getRestName() + " / " + sample.get(position).getRoomMonth() + "월 " +
                sample.get(position).getRoomDay() + "일 / " + sample.get(position).getRoomTime());
        roomPersonnel.setText(sample.get(position).getRoomPersonnel() + "명");
        if (sample.get(position).getHash().equals("")) { // set hashTag
            roomSub.setText("#" + sample.get(position).getRoomGender());
        } else {
            roomSub.setText("#" + sample.get(position).getRoomGender() + " #" + sample.get(position).getHash());
        }

        return view;
    }
}
