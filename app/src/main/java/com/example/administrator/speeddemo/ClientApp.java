package com.example.administrator.speeddemo;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.administrator.speeddemo.net.PublicUtil;

/**
 * Created by Administrator on 2017/3/30.
 */

public class ClientApp extends Application{
    private static ClientApp myAppContext;
    //接口的token
    private  String token;
    //每隔多少秒从新生成token一次
    private static int TOKNTIME = 1000*60;
    public SharedPreferences mSharedPreferences;
    public SharedPreferences.Editor myEditor;

    @Override
    public void onCreate() {
        super.onCreate();
        this.myAppContext = this;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        /*setTken();*/
    }
    //判断是否是第一次打开
    public boolean openOne(){
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //从SharedPreferences中获取登录信息  如果没有该信息就返回false
        boolean info = mSharedPreferences.getBoolean("openInfo",false);
        return info;
    }
    //更改开启次数信息
    public boolean setOpenOne(){
            myEditor = mSharedPreferences.edit();
            myEditor.putBoolean("openInfo",true);
            myEditor.commit();
            boolean info = mSharedPreferences.getBoolean("openInfo",false);
            return info;
    }
    //单列模式
    public static ClientApp getClientApp(){
        return myAppContext;
    }

    //设置服务器Ip
    public void setIp(String ip){
        myEditor = mSharedPreferences.edit();
        myEditor.putString("ip",ip);
        myEditor.commit();
    }
    //获取服务器ip
    public String getIp(){
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String ip = mSharedPreferences.getString("ip","10.10.248.83");
        return ip;
    }

    //获取保存的数据
    public String getData(String tip){
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String data = mSharedPreferences.getString(tip,"null");
        return data;
    }

    //生成token
    private String initToken(){
        String myKet =  this.getString(R.string.token);
        String nowDate = PublicUtil.getDate("yyyy-MM-dd HH:mm");
        Log.i("time",nowDate);
        token = PublicUtil.md5(myKet+nowDate);
        return token;
    }

    //定时生成token
    private void setTken(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    token = initToken();
                    try {
                        Thread.sleep(TOKNTIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public String getToken() {
        return token;
    }

}
