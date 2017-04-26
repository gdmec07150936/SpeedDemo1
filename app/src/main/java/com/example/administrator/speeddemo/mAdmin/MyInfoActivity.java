package com.example.administrator.speeddemo.mAdmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.administrator.speeddemo.ClientApp;
import com.example.administrator.speeddemo.MainActivity;
import com.example.administrator.speeddemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */

public class MyInfoActivity extends Activity {
    private ClientApp clientApp = ClientApp.getClientApp();
    private ViewPager mViewPager;
    //导航页图片资源
    public int[] guides = new int[] { R.drawable.first,
            R.drawable.second, R.drawable.third,
            R.drawable.forth };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danghao);
        mViewPager = (ViewPager) findViewById(R.id.viewFlipper1);
        initWithPageGuideMode();
    }


    /**
     * 程序导航页效果
     */
    public void initWithPageGuideMode() {

        List<View> mList = new ArrayList<View>();
        LayoutInflater inflat = LayoutInflater.from(this);
        //先添加一个最左侧空的view
        View item = inflat.inflate(R.layout.pageguide, null);
        mList.add(item);
        for (int index : guides) {
            item = inflat.inflate(R.layout.pageguide, null);
            item.setBackgroundResource(index);
            mList.add(item);
        }
        //经过遍历，此时item是最后一个view，设置button
        Button btn = (Button) item.findViewById(R.id.button1);
        btn.setVisibility(View.VISIBLE);
        //设置最后一个页面上button的监听
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyInfoActivity.this,beginActivity.class);
                startActivity(intent);
            }
        });
        //再添加一个最右侧空的view
        item = inflat.inflate(R.layout.pageguide, null);
        mList.add(item);
        //ViewPager最重要的设置Adapter，这和ListView一样的原理
        MyInfoActivity.MViewPageAdapter adapter = new MyInfoActivity.MViewPageAdapter(mList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(adapter);
        mViewPager.setCurrentItem(1);

    }

    /**
     * 内部类，继承PagerAdapter，当然你也可以直接 new PageAdapter
     *
     * @author yangxiaolong
     */
    class MViewPageAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

        private List<View> mViewList;

        public MViewPageAdapter(List<View> views) {
            mViewList = views;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position), 0);
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {

            if (position == 0) {
                mViewPager.setCurrentItem(1);
            } else if (position == mViewList.size() - 1) {
                mViewPager.setCurrentItem(position - 1);
                Intent mIntent = new Intent(MyInfoActivity.this,MainActivity.class);
                startActivity(mIntent);

            }


        }

    }
}
