package com.example.administrator.speeddemo.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.speeddemo.Request.RequestBase;

import org.json.JSONObject;


/**
 * Created by Administrator on 2016/12/22.
 */

public class NetUtil {
    /**
     * 判断网络是否连接
     *
     * @param context
     *            上下文对象
     * @return 网络是否连接
     */
    public static boolean isNetworkAvailable(Context context) {
        boolean isNetworkOK = false;
        try {
            ConnectivityManager conn = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (null == conn || null == conn.getActiveNetworkInfo()) {
                isNetworkOK = false;
            } else {
                isNetworkOK = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isNetworkOK;
    }



    //发送数据获取数据
    public  void sendData(String url, JSONObject params, Context context, final RequestBase myRequestBase){

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url,params,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject jsonObject) {
                //回调请求类 的Json解析
                myRequestBase.setReasultData(jsonObject);
                Log.i("获取到的json",jsonObject.toString());
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("aaaa","ccc");
            }
        });
        //开启异步请求数据
        jsonObjectRequest.setTag("bcdPost");
        queue.add(jsonObjectRequest);
        queue.start();
    }


}
