package com.example.administrator.speeddemo.mAdmin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.speeddemo.R;

/**
 * Created by Administrator on 2017/4/19.
 */

public class Price_Inquiry_Activity extends Activity{
    private WebView mVebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.price_inquiry);
        mVebView = (WebView) findViewById(R.id.mVebView);
        mVebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mVebView.loadUrl("http://baidu.com");
    }

    public void OnBack(View view){
        this.finish();
    }
}
