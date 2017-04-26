package com.example.administrator.speeddemo.Request;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.speeddemo.ClientApp;
import com.example.administrator.speeddemo.net.NetUtil;


/**
 * Created by Administrator on 2016/12/22.
 */

public class RequestThread extends Thread {
    private RequestBase myRequestBase;
    private ClientApp myClientApp;
    private Context myContext;

    public RequestThread(RequestBase myRequestBase, Context context){
        myClientApp = ClientApp.getClientApp();
        this.myContext = context;
        this.myRequestBase = myRequestBase;
    }
    @Override
    public void run() {
        super.run();
        if(NetUtil.isNetworkAvailable(myContext)){

            String url = "http://";
            /*url += "192.168.56.1";*/
            url += myClientApp.getIp();
            url += "/formas/";
            url += myRequestBase.getReasultName();
            Log.i("url",url);
            NetUtil mynetUtil = new NetUtil();
            mynetUtil.sendData(url,myRequestBase.sendData(),myContext,myRequestBase);

        }else{
            Toast.makeText(myContext,"无法连接网络", Toast.LENGTH_SHORT).show();
        }
    }
}
