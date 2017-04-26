package com.example.administrator.speeddemo.Fragmen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.speeddemo.Dialog.BootentDialog;
import com.example.administrator.speeddemo.R;
import com.example.administrator.speeddemo.mAdmin.Price_Inquiry_Activity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/30.
 */

public class JiJian_Fragmen extends Fragment implements View.OnClickListener,BootentDialog.DialogTime,BootentDialog.Pick_in_Interface{
    private View view;
    private ImageButton putongButton;
    private ImageButton xianshiButton;
    private ImageButton yuyueButton;
    private LinearLayout mYuyueLinear;
    private ImageButtonOnClick mImageButtonOnClick;
    private RelativeLayout mPick_up_time;
    private RelativeLayout mPick_in_time;
    private BootentDialog mDialog;
    private BootentDialog mPick_in_Dialog;
    private TextView mPick_up_text;
    private String mPick_up_str;
    private String mPick_in_str;
    private TextView mPick_in_text;
    private TextView mPrice_Button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.jijian_fragment,container,false);
        init();
        setOnClick();
        return view;
    }
    //初始话变量
    public void init(){
        putongButton = (ImageButton) view.findViewById(R.id.putongButton);
        xianshiButton = (ImageButton) view.findViewById(R.id.xianshiButton);
        yuyueButton = (ImageButton) view.findViewById(R.id.yuyueButton);
        mYuyueLinear = (LinearLayout) view.findViewById(R.id.yuyueLinear);
        mPick_up_time = (RelativeLayout) view.findViewById(R.id.pick_up_time);
        mPick_in_time = (RelativeLayout) view.findViewById(R.id.pick_in_time);
        mPick_up_text = (TextView) view.findViewById(R.id.pick_up_text);
        mPick_in_time = (RelativeLayout) view.findViewById(R.id.pick_in_time);
        mPick_in_text  = (TextView) view.findViewById(R.id.pick_in_text);
        mPrice_Button = (TextView) view.findViewById(R.id.price_Button);
        mImageButtonOnClick = new ImageButtonOnClick();

    }
    //设置监听
    public void setOnClick(){
        putongButton.setOnClickListener(mImageButtonOnClick);
        xianshiButton.setOnClickListener(mImageButtonOnClick);
        yuyueButton.setOnClickListener(mImageButtonOnClick);
        mPrice_Button.setOnClickListener(this);
        mPick_in_text.setOnClickListener(this);
        mPick_in_time.setOnClickListener(this);
        mPick_up_time.setOnClickListener(this);
        mPick_in_time.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pick_up_time:
                //调用取件时间的选择窗口
                mDialog = new BootentDialog(this.getActivity(),this,R.style.my_dialog,0);
                mDialog.show();
                break;
            case R.id.pick_in_time:
                //调用收件时间的选择窗口
                mPick_in_Dialog = new BootentDialog(this.getActivity(),this,R.style.my_dialog,2);
                mPick_in_Dialog.show();
                break;
            case R.id.price_Button:
                Intent mIntent = new Intent(this.getActivity(), Price_Inquiry_Activity.class);
                startActivity(mIntent);
                break;
        }
    }




    //取件时间 窗口确定后的处理
    @Override
    public void getTime(ArrayList<String> dialogList) {
        mPick_up_text.setPadding(0,50,30,0);
        mPick_up_text.setTextSize(14);
        mPick_up_str = dialogList.get(0) + " " +dialogList.get(1) +" : "+dialogList.get(2);
        mPick_up_text.setText(mPick_up_str);
        mDialog.dismiss();
    }
    //收件时间  确定后调用的处理
    @Override
    public void pick_in_dialog(ArrayList<String> dialogList) {
        mPick_in_text.setPadding(0,50,30,0);
        mPick_in_text.setTextSize(14);
        mPick_in_str = dialogList.get(0) + " " +dialogList.get(1) +" : "+dialogList.get(2);
        mPick_in_text.setText(mPick_in_str);
        mPick_in_Dialog.dismiss();
    }

    //对用户选择的不同的寄件方式进行处理
    class ImageButtonOnClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            cleanStyle();
            switch (view.getId()){
                case R.id.putongButton:
                    putongButton.setImageResource(R.drawable.puton_baoguo_in);
                    break;
                case R.id.xianshiButton:
                    xianshiButton.setImageResource(R.drawable.xianshi_baoguo_in);
                    break;
                case R.id.yuyueButton:
                    yuyueButton.setImageResource(R.drawable.yuyue_baoguo_in);
                    mYuyueLinear.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
    //清理寄件方式的样式
    public void cleanStyle(){
        putongButton.setImageResource(R.drawable.puton_baoguo);
        xianshiButton.setImageResource(R.drawable.xianshi_baoguo);
        yuyueButton.setImageResource(R.drawable.yuyue_baoguo);
        mYuyueLinear.setVisibility(View.GONE);
    }

}
