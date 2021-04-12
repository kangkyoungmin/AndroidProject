package com.example.finalproject.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

import java.text.DateFormat;
import java.util.Calendar;

public class CreateRoomActivity extends AppCompatActivity {
    Spinner persSpinner, restSpinner;
    String restName, cGender, pers, cRestName, hash = ""; // c : 생성 시 개입하는 변수 명
    int cPers;
    int cYear = 2000, cMonth = 1, cDay = 1; // ~Date
    int cHour = 0, cMinute = 0; // ~Time
    // todo. 변수 명, 종류 DB와 연관하여 정리할 것

    Button bt_date, bt_hour;
    CheckBox cb_man, cb_woman, cb_nope;
    EditText et_roomTitle, et_hash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createroom);

        bt_date = (Button) findViewById(R.id.dateSelectButton);
        bt_hour = (Button) findViewById(R.id.hourSelectButton);
        cb_man = (CheckBox) findViewById(R.id.manCheckBox);
        cb_woman = (CheckBox) findViewById(R.id.womanCheckBox);
        cb_nope = (CheckBox) findViewById(R.id.hopeRestaurantCheckBox);
        et_roomTitle = (EditText) findViewById(R.id.roomTitleEditText);
        et_hash = (EditText) findViewById(R.id.roomHashEditText);

        persSpinner = (Spinner) findViewById(R.id.spinner_personnel);
        restSpinner = (Spinner) findViewById(R.id.spinner_restName);

        persSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // Personnel Spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pers = (String) parent.getItemAtPosition(position);
                cPers = pers.charAt(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        restSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // Restaurant Spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                restName = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.createRoomButton:
                // gender set
                if (cb_man.isChecked() && cb_woman.isChecked()) {
                    cGender = "성별무관";
                } else if (cb_man.isChecked()) {
                    cGender = "남자만";
                } else if (cb_woman.isChecked()) {
                    cGender = "여자만";
                } else {
                    cGender = ""; // 0: 설정안함 ~ 예외처리
                }
                // restaurant checkBox set
                if (cb_nope.isChecked()) {
                    cRestName = "무관";
                } else {
                    cRestName = restName;
                }
                // hash set
                hash = et_hash.getText().toString();

                // 방 생성 진위여부
                String cRoomTitle = et_roomTitle.getText().toString();
                if (cRoomTitle.equals("")) { // 방 제목설정 안함
                    Toast.makeText(this, "방 제목을 입력하세요", Toast.LENGTH_SHORT).show();
                } else if (cRoomTitle.length() > 11) {
                    Toast.makeText(this, "방 제목은 11글자 이하입니다.", Toast.LENGTH_SHORT).show();
                    et_roomTitle.setText("");
                } else if (cYear == 2000 && cMonth == 1 && cDay == 1) { // 날짜 설정 안함
                    Toast.makeText(this, "날짜를 선택하세요", Toast.LENGTH_SHORT).show();
                } else if (cHour == 0 && cMinute == 0) { // 시간 설정 안함
                    Toast.makeText(this, "시간을 선택하세요", Toast.LENGTH_SHORT).show();
                } else if (cGender.equals("")) { // 성별 설정 안함
                    Toast.makeText(this, "성별을 선택해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    // todo. 방 목록에 추가
                    Toast.makeText(this, "채팅방을 생성하였습니다.", Toast.LENGTH_SHORT).show();
                    Intent intentChat = new Intent(this, ChatActivity.class);
                    // todo. putExtra("cYear", cYear); ... 채팅 방 정보 전달
                    startActivity(intentChat);
                    // todo. 방 화면으로 이동
                    finish();
                }
                break;
            case R.id.dateSelectButton:
                new DatePickerDialog(this, d, SelectDate.get(Calendar.YEAR),
                        SelectDate.get(Calendar.MONTH), SelectDate.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.hourSelectButton:
                new TimePickerDialog(this, t, SelectHour.get(Calendar.HOUR_OF_DAY),
                        SelectHour.get(Calendar.MINUTE), false).show();
                break;
        }
    }

    // 날짜, 시간 설정
    DateFormat fmDateAndTime = DateFormat.getDateTimeInstance();
    Calendar SelectDate = Calendar.getInstance();
    Calendar SelectHour = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            SelectDate.set(Calendar.YEAR, year);
            SelectDate.set(Calendar.MONTH, month);
            SelectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            bt_date.setText(year + "년 " + (month + 1) + "월 " + dayOfMonth + "일");
            cYear = year;
            cMonth = month + 1;
            cDay = dayOfMonth;
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            SelectHour.set(Calendar.HOUR_OF_DAY, hourOfDay);
            SelectHour.set(Calendar.MINUTE, minute);
            bt_hour.setText(hourOfDay + "시 " + minute + "분");
            cHour = hourOfDay;
            cMinute = minute;
        }
    };
}
