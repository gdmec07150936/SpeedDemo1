package com.example.administrator.speeddemo.Fragmen;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.speeddemo.R;
import com.example.administrator.speeddemo.mView.SlideShowView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/30.
 */

public class HomeFragmen extends Fragment{
    private String[] imageUrls = new String[]{
            "http://120.25.249.220/MyThinkDemo/Public/appImage/one.gif",
            "http://120.25.249.220/MyThinkDemo/Public/appImage/tow.gif",
            "http://120.25.249.220/MyThinkDemo/Public/appImage/three.gif",
             } ;
    private String[] imageUris = new String[]{
            "http://www.baidu.com",
            "http://www.sina.com.cn",
            "http://www.taobao.com",
             };

    private List<Map<String,String>> imageList ;
    ImageView demo;
    HorizontalScrollView mScroll;
    Button mButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragmen,container,false);
        imageList = new ArrayList<Map<String,String>>();
        mScroll = (HorizontalScrollView) view.findViewById(R.id.scroll);
         demo = (ImageView) view.findViewById(R.id.demo);

        for (int i = 0; i < 3; i++) {
            Map<String,String> image_uri = new HashMap<String,String>();
            image_uri.put("imageUrls", imageUrls[i]);
            image_uri.put("imageUris", imageUris[i]);
            imageList.add(image_uri);
        }

        SlideShowView mView = (SlideShowView) view.findViewById(R.id.slideshowView);
        mView.setImageUrls(imageList);
        return view;
    }
    private int zhuanhuan(int x){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,x,getContext().getResources().getDisplayMetrics());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Handler mHandler = new Handler();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mScroll.scrollTo(zhuanhuan(371),0);
            }
        });
        /*mScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if(i+100 < demo.getWidth()/2){
                    mScroll.scrollTo(demo.getWidth()-100,0);
                }
            }
        });*/
        mScroll.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                /*if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(mScroll.getScaleX() == (demo.getWidth() - mScroll.getWidth())){

                    }else{

                    }
                }*/
                int action = motionEvent.getAction();
                switch (action)
                {

                    case MotionEvent.ACTION_UP:
                        int scrollX = mScroll.getScrollX();
                        if(scrollX >= zhuanhuan(100)/2){
                            mScroll.smoothScrollTo(zhuanhuan(180),0);
                            demo.setImageResource(R.drawable.demo3);
                        }else{
                            mScroll.smoothScrollTo(0,0);

                        }
                        return true;
                    case MotionEvent.ACTION_DOWN:
                        demo.setImageResource(R.drawable.demo2);
                        return true;
                }

                return false;
            }
        });
    }
}
