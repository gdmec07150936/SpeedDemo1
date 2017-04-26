package com.example.administrator.speeddemo.mAdmin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.speeddemo.R;

/**
 * Created by Administrator on 2017/4/23.
 */

public class Dindan_Activity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dindan_activity);
    }

    public void OnBack(View view){
        finish();
    }
}
