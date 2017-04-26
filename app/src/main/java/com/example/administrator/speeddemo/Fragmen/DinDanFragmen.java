package com.example.administrator.speeddemo.Fragmen;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.administrator.speeddemo.Adapter.Dindan_list_Adapter;
import com.example.administrator.speeddemo.Model.Dindan_Model;
import com.example.administrator.speeddemo.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/30.
 */

public class DinDanFragmen extends Fragment implements View.OnClickListener{
    private ListView mListView;
    private View view;
    private ArrayList<Dindan_Model> mArrayList;
    private Dindan_list_Adapter mListAdapter;
    private LinearLayout all_layout;
    private LinearLayout state_layout;
    private LinearLayout finish_layout;
    private LinearLayout cancel_layout;
    private View all_view;
    private View state_view;
    private View finish_view;
    private View cancle_view;
    private ArrayList<Dindan_Model> mAdapterList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dindan_fragment,null);
        init();
        setOncli();
        return view;
    }

    private void init(){
        mListView = (ListView) view.findViewById(R.id.dindan_ListView);
        mArrayList = new ArrayList<Dindan_Model>();
        all_view = view.findViewById(R.id.dindan_hert_all_view);
        state_view = view.findViewById(R.id.dindan_hert_state_view);
        finish_view = view.findViewById(R.id.dindan_hert_finish_view);
        all_layout = (LinearLayout) view.findViewById(R.id.dindan_hert_all);
        state_layout = (LinearLayout) view.findViewById(R.id.dindan_hert_state);
        finish_layout = (LinearLayout) view.findViewById(R.id.dindan_hert_finish);
        cancel_layout = (LinearLayout) view.findViewById(R.id.dindan_hert_cancel);
        cancle_view = view.findViewById(R.id.dindan_hert_cancel_view);
            Dindan_Model mDindanModel = new Dindan_Model();
            mDindanModel.setDindan_price(5000);
            mDindanModel.setDindan_state(1);
            mArrayList.add(mDindanModel);
            mDindanModel = new Dindan_Model();
            mDindanModel.setDindan_price(6000);
            mDindanModel.setDindan_state(0);
            mArrayList.add(mDindanModel);
        mDindanModel = new Dindan_Model();
        mDindanModel.setDindan_price(7000);
        mDindanModel.setDindan_state(2);
        mDindanModel.setPrick_in_address("北京天安门");
        mArrayList.add(mDindanModel);
        mAdapterList = new ArrayList<Dindan_Model>();
        for(int i = 0; i < mArrayList.size();i++){
                mAdapterList.add(mArrayList.get(i));
        }
        mListAdapter = new Dindan_list_Adapter(this.getActivity(),mAdapterList);
        mListView.setAdapter(mListAdapter);
    }

    private void setOncli(){
        all_layout.setOnClickListener(this);
        state_layout.setOnClickListener(this);
        finish_layout.setOnClickListener(this);
        cancel_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        clearView();
        switch (view.getId()){
            case R.id.dindan_hert_all:
                all_view.setBackgroundColor(this.getResources().getColor(R.color.login_in));
                for(int i = 0; i < mArrayList.size();i++){
                    mAdapterList.add(mArrayList.get(i));
                }
                mListAdapter.notifyDataSetChanged();
                break;
            case R.id.dindan_hert_state:
                state_view.setBackgroundColor(this.getResources().getColor(R.color.login_in));
                for(int i = 0; i < mArrayList.size();i++){
                    if(mArrayList.get(i).getDindan_state() == 0){
                        mAdapterList.add(mArrayList.get(i));
                    }
                }
                mListAdapter.notifyDataSetChanged();
                break;
            case R.id.dindan_hert_finish:
                finish_view.setBackgroundColor(this.getResources().getColor(R.color.login_in));
                for(int i = 0; i < mArrayList.size();i++){
                    if(mArrayList.get(i).getDindan_state() == 1){
                        mAdapterList.add(mArrayList.get(i));
                    }
                }
                mListAdapter.notifyDataSetChanged();
                break;
            case R.id.dindan_hert_cancel:
                cancle_view.setBackgroundColor(this.getResources().getColor(R.color.login_in));
                for(int i = 0; i < mArrayList.size();i++){
                    if(mArrayList.get(i).getDindan_state() == 2){
                        mAdapterList.add(mArrayList.get(i));
                    }
                }
                mListAdapter.notifyDataSetChanged();
                break;
        }
    }

    public void clearView(){
        all_view.setBackgroundColor(this.getResources().getColor(R.color.transparent));
        state_view.setBackgroundColor(this.getResources().getColor(R.color.transparent));
        finish_view.setBackgroundColor(this.getResources().getColor(R.color.transparent));
        cancle_view.setBackgroundColor(this.getResources().getColor(R.color.transparent));
        mAdapterList.clear();
    }


}
