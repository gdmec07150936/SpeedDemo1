package com.example.administrator.speeddemo.Request;

import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/3/27.
 */

public class DemoRequest extends RequestBase {
    TextView mTextView ;
    public DemoRequest(TextView mTextView){
        this.mTextView = mTextView;
    }
    //接口的尾缀
    private static String REQUESTNAME = "index.php";
    //获取到数据时候处理的方法
    @Override
    public void setReasultData(JSONObject jsonObject) {
        mTextView.setText(jsonObject.toString());
    }

    //获取请求的url名的方法
    @Override
    public String getReasultName() {
        return REQUESTNAME;
    }
    //要传过去的json数据
    @Override
    public JSONObject getParams() {
        JSONObject myJSon = new JSONObject();
        try {
            myJSon.put("","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return myJSon;
    }
}
