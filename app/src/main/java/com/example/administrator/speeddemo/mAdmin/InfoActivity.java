package com.example.administrator.speeddemo.mAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.speeddemo.ClientApp;
import com.example.administrator.speeddemo.MainActivity;

/**
 * Created by Administrator on 2017/3/30.
 */

public class InfoActivity  extends AppCompatActivity {
    private ClientApp clientApp = ClientApp.getClientApp();
    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是否第一次登录
        if(clientApp.openOne()){
            mIntent = new Intent(this,MainActivity.class);
        }else{
            mIntent = new Intent(this,MyInfoActivity.class);
            clientApp.setOpenOne();
        }
        this.startActivity(mIntent);
    }
}
