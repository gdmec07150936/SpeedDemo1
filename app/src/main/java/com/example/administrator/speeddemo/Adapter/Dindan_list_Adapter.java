package com.example.administrator.speeddemo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.speeddemo.Model.Dindan_Model;
import com.example.administrator.speeddemo.R;
import com.example.administrator.speeddemo.mAdmin.Dindan_Activity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */

public class Dindan_list_Adapter extends BaseAdapter{
    private Context mContext;
    private List<Dindan_Model> mList;
    private LayoutInflater mLayoutInflater;
    private Dindan_Model mModel;

    public Dindan_list_Adapter(Context mContext,List<Dindan_Model> mList){
        this.mContext = mContext;
        this.mList = mList;
        this.mLayoutInflater = ((Activity)mContext).getLayoutInflater();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyDindan_List_XML mXMlL;
        if(view == null){
            mXMlL = new MyDindan_List_XML();
            view = mLayoutInflater.inflate(R.layout.list_dindan,null);
            mXMlL.dindan_date = (TextView) view.findViewById(R.id.dindan_list_date);
            mXMlL.dindan_image = (ImageView) view.findViewById(R.id.dindan_list_state_image);
            mXMlL.prick_up_EditText = (EditText) view.findViewById(R.id.dindan_list_prickup_text);
            mXMlL.prick_in_EditText = (EditText) view.findViewById(R.id.dindan_list_prick_in_text);
            mXMlL.price_text = (TextView) view.findViewById(R.id.dindan_list_price);
            mXMlL.dindan_state = (TextView) view.findViewById(R.id.dindan_list_state);
            mXMlL.mLinearLayout = (RelativeLayout) view.findViewById(R.id.list_dindan);
            view.setTag(mXMlL);
        }else{
            mXMlL = (MyDindan_List_XML) view.getTag();
        }
        mModel = mList.get(i);
        mXMlL.dindan_date.setText(mModel.getDindan_date());
        mXMlL.prick_up_EditText.setText(mModel.getPrick_up_address());
        mXMlL.prick_in_EditText.setText(mModel.getPrick_in_address());
        mXMlL.price_text.setText("价格 ："+mModel.getDindan_price());
        switch (mModel.getDindan_state()){
            case 0:
                mXMlL.dindan_image.setImageResource(R.drawable.dindan_title);
                mXMlL.dindan_state.setText("等待接单中");
                break;
            case 1:
                mXMlL.dindan_image.setImageResource(R.drawable.dindan_titl_finish);
                break;
            case 2:
                mXMlL.dindan_image.setImageResource(R.drawable.dindan_titl_cancel);
                mXMlL.prick_up_EditText.setTextColor(mContext.getResources().getColor(R.color.cancle));
                mXMlL.prick_up_EditText.setHighlightColor(mContext.getResources().getColor(R.color.cancle));
                mXMlL.prick_in_EditText.setTextColor(mContext.getResources().getColor(R.color.cancle));
                mXMlL.prick_in_EditText.setHighlightColor(mContext.getResources().getColor(R.color.cancle));
                break;
        }
        mXMlL.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, Dindan_Activity.class);
                mContext.startActivity(mIntent);
            }
        });

        return view;
    }

    class MyDindan_List_XML{
        public TextView dindan_date;
        public ImageView dindan_image;
        public EditText prick_up_EditText;
        public EditText prick_in_EditText;
        public TextView price_text;
        public TextView dindan_state;
        public RelativeLayout mLinearLayout;
    }
}
