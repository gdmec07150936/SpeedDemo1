package com.example.administrator.speeddemo.mAdmin;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.speeddemo.Fragmen.HomeFragmen;
import com.example.administrator.speeddemo.Fragmen.JiJian_Fragmen;
import com.example.administrator.speeddemo.Fragmen.Login_Fragment;
import com.example.administrator.speeddemo.Fragmen.SignUp_Fragment;
import com.example.administrator.speeddemo.R;
import com.example.administrator.speeddemo.util.DensityUtil;
import com.example.administrator.speeddemo.util.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */

public class LoginActivity extends FragmentActivity implements View.OnClickListener{
    private TextView mLogin_text;
    private TextView mSingUp_text;
    private View mLogin_view;
    private View mSingUp_view;
    private LinearLayout Login_Layout;
    private LinearLayout SingUp_Layout;
    private Login_Fragment mLogin_Fragment;
    private SignUp_Fragment mSignUp_Fragment;
    private FrameLayout mFragmentLayout;
    private LinearLayout mLayout;
    private LinearLayout.LayoutParams mLayoutParams;
    private LinearLayout mOther_Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.login_up);//通知栏所需颜色
        }
        setContentView(R.layout.login);
        //初始化
        init();
        //为控件添加监听
        setOnclick();
        switchConent(new Login_Fragment(),0);

    }
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void init(){
        mLogin_text = (TextView) this.findViewById(R.id.login_text);
        mLogin_view = this.findViewById(R.id.login_view);
        mSingUp_text = (TextView) this.findViewById(R.id.singUp_text);
        mSingUp_view = this.findViewById(R.id.singUp_view);

        Login_Layout = (LinearLayout) this.findViewById(R.id.loginLayout);
        SingUp_Layout = (LinearLayout) this.findViewById(R.id.singUp_Layout);

        mFragmentLayout = (FrameLayout) this.findViewById(R.id.mLogin_Fragment);
        mOther_Login = (LinearLayout) this.findViewById(R.id.other_login);
        mLayout = (LinearLayout) this.findViewById(R.id.demoLayout);
        mLayoutParams = (LinearLayout.LayoutParams) mLayout.getLayoutParams();
    }
    //添加监听
    private void setOnclick(){
        Login_Layout.setOnClickListener(this);
        SingUp_Layout.setOnClickListener(this);
    }
    /**
     * 切换Fragment
     *
     * @param fragment  切换到的Fragment
     * @param select 切换时要做的相应处理 0:登录  1：注册
     */
    public void switchConent(Fragment fragment,int select) {
        clearStyle();
        switch (select){
            case 0:
                mLogin_text.setTextColor(getResources().getColor(R.color.login_in));
                mLogin_view.setBackgroundColor(getResources().getColor(R.color.login_in));
                break;
            case 1:
                mSingUp_text.setTextColor(getResources().getColor(R.color.login_in));
                mSingUp_view.setBackgroundColor(getResources().getColor(R.color.login_in));

                break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mLogin_Fragment, fragment).commit();
    }
    /*
    *清理登录 注册的样式
    * */
    public void clearStyle(){
        mLogin_text.setTextColor(getResources().getColor(R.color.login_up));
        mLogin_view.setBackgroundColor(getResources().getColor(R.color.mtransparent));
        mSingUp_text.setTextColor(getResources().getColor(R.color.login_up));
        mSingUp_view.setBackgroundColor(getResources().getColor(R.color.mtransparent));
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.loginLayout:
                if(mLogin_Fragment == null){
                    mLogin_Fragment = new Login_Fragment();
                }
                mLayoutParams.height = DensityUtil.dip2px(this,200);
                mOther_Login.setVisibility(View.VISIBLE);
                switchConent(mLogin_Fragment,0);
                break;
            case R.id.singUp_Layout:
                if(mSignUp_Fragment == null){
                    mSignUp_Fragment = new SignUp_Fragment();
                }
                mLayoutParams.height = DensityUtil.dip2px(this,315);
                mOther_Login.setVisibility(View.GONE);
                switchConent(mSignUp_Fragment,1);

                break;
        }
    }

}
