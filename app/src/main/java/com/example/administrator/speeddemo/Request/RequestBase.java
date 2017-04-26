package com.example.administrator.speeddemo.Request;

import android.util.Log;

import com.example.administrator.speeddemo.ClientApp;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Administrator on 2016/12/22.
 */

public abstract class RequestBase {
    private ClientApp mClientApp = ClientApp.getClientApp();
    //获取到数据时候的回调方法
    public abstract void setReasultData(JSONObject jsonObject);
    //获取请求的url名的方法
    public abstract String getReasultName();
    //获取要发送的json
    public abstract JSONObject getParams();

    public JSONObject sendData(){
        JSONObject mSendData = new JSONObject();
        JSONObject getJSON = getParams();
        try {
            mSendData.put("token",mClientApp.getToken());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("token",mSendData.toString());
        return mSendData;
    }


    public boolean isGetData(JSONObject myJson){
        try {
            if (myJson.getInt("code") == 200 || myJson.getInt("code")==403){
                return true;
            }else{
                //当获取不到数据时候调用
                notGetData();
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
    //获取不到数据调取的方法
    public void notGetData(){
    }

}
