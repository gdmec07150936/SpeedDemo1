package com.example.administrator.speeddemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.speeddemo.Fragmen.DinDanFragmen;
import com.example.administrator.speeddemo.Fragmen.HomeFragmen;
import com.example.administrator.speeddemo.Fragmen.JiJian_Fragmen;
import com.example.administrator.speeddemo.Fragmen.MyFragmen;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements View.OnClickListener{
    private ViewPager mViewPager;
    private FragmentPagerAdapter mPagerAdapter;
    private List<Fragment> mFragmentList;

    private LinearLayout mTabHome;
    private LinearLayout mTabJiJian;
    private LinearLayout mTabDinDan;
    private LinearLayout mTabMy;

    private ImageButton mImageHome;
    private ImageButton mImageJiJian;
    private ImageButton mImageDinDan;
    private ImageButton mImageMy;

    private TextView mTextHome;
    private TextView mTextJiJian;
    private TextView mTextDinDan;
    private TextView mTextMy;

    private Fragment mHomeFragmen;
    private Fragment mJiJianFragmen;
    private Fragment mDinDanFragmenn;
    private Fragment mMyFragment;

    private ClientApp clientApp = ClientApp.getClientApp();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_main);
        init();
        setOnClick();
        setSelect(0);
    }

    private void init(){
        mViewPager = (ViewPager) this.findViewById(R.id.MyViewPage);

        mTabHome = (LinearLayout) this.findViewById(R.id.MyHomeLayout);
        mTabDinDan = (LinearLayout) this.findViewById(R.id.MyDindanLayout);
        mTabJiJian = (LinearLayout) this.findViewById(R.id.MyJiJianLayout);
        mTabMy = (LinearLayout) this.findViewById(R.id.MyLayout);

        mImageHome = (ImageButton) this.findViewById(R.id.MyHome);
        mImageJiJian = (ImageButton) this.findViewById(R.id.MyJiJian);
        mImageDinDan = (ImageButton) this.findViewById(R.id.MyDinDan);
        mImageMy = (ImageButton) this.findViewById(R.id.MyButton);

        mTextHome = (TextView) this.findViewById(R.id.MyHomeText);
        mTextDinDan = (TextView) this.findViewById(R.id.MyDinDanText);
        mTextJiJian = (TextView) this.findViewById(R.id.MyJiJianText);
        mTextMy = (TextView) this.findViewById(R.id.MyText);

        mFragmentList = new ArrayList<Fragment>();
        mHomeFragmen = new HomeFragmen();
        mJiJianFragmen = new JiJian_Fragmen();
        mMyFragment = new MyFragmen();
        mDinDanFragmenn = new DinDanFragmen();

        mFragmentList.add(mHomeFragmen);
        mFragmentList.add(mJiJianFragmen);
        mFragmentList.add(mDinDanFragmenn);
        mFragmentList.add(mMyFragment);
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        };
        mViewPager.setAdapter(mPagerAdapter);
    }
    private void setOnClick(){
        mTabDinDan.setOnClickListener(this);
        mTabHome.setOnClickListener(this);
        mTabJiJian.setOnClickListener(this);
        mTabMy.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentIten = mViewPager.getCurrentItem();
                setTab(currentIten);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onClick(View view)
    {
        switch (view.getId()){
            case R.id.MyHomeLayout:
                setSelect(0);
                break;
            case R.id.MyJiJianLayout:
                setSelect(1);
                break;
            case R.id.MyDindanLayout:
                setSelect(2);
                break;
            case R.id.MyLayout:
                setSelect(3);
                break;
            }
    }

    public void ClearStyle(){
        mImageHome.setImageResource(R.drawable.homepage_up);
        mImageJiJian.setImageResource(R.drawable.jijian_up);
        mImageDinDan.setImageResource(R.drawable.dindan_up);
        mImageMy.setImageResource(R.drawable.my_up);

        mTextHome.setTextColor(this.getResources().getColor(R.color.bottom));
        mTextJiJian.setTextColor(this.getResources().getColor(R.color.bottom));
        mTextDinDan.setTextColor(this.getResources().getColor(R.color.bottom));
        mTextMy.setTextColor(this.getResources().getColor(R.color.bottom));
    }

    private void setSelect(int i){
        //设置图片为高亮
        setTab(i);
        mViewPager.setCurrentItem(i);
    }
    private void setTab(int i){
        //设置图片为高亮
        ClearStyle();
        switch (i){
            case 0:
                mImageHome.setImageResource(R.drawable.homepage_in);
                mTextHome.setTextColor(this.getResources().getColor(R.color.light_red));
                break;
            case 1:
                mImageJiJian.setImageResource(R.drawable.jijian_in);
                mTextJiJian.setTextColor(this.getResources().getColor(R.color.light_red));
                break;
            case 2:
                mImageDinDan.setImageResource(R.drawable.dindan_in );
                mTextDinDan.setTextColor(this.getResources().getColor(R.color.light_red));
                break;
            case 3:
                mImageMy.setImageResource(R.drawable.my_in);
                mTextMy.setTextColor(this.getResources().getColor(R.color.light_red));
                break;
        }
    }
}

