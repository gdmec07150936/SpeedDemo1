package com.example.administrator.speeddemo.mAdmin;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.speeddemo.R;

public class beginActivity extends AppCompatActivity {
    private AlertDialog dialog;
    private TextView allow;
    private TextView ban;
    private TimeCount time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagestart);
        time = new TimeCount(60000, 1000);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.pagedialog, null);
        allow = (TextView)view.findViewById(R.id.allow);
        ban = (TextView)view.findViewById(R.id.ban);
        allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(beginActivity.this,homeActivity.class);
                startActivity(intent);
            }
        });
        ban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.cancel();
                time.start();
            }
        });
        builder.setView(view);
        dialog  = builder.create();
        dialog.show();
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            Intent intent=new Intent(beginActivity.this,homeActivity.class);
            startActivity(intent);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            ban.setClickable(false);
            ban.setText("禁止"+millisUntilFinished / 1000 + "s");
        }
    }
}