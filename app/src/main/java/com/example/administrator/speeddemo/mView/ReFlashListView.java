package com.example.administrator.speeddemo.mView;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.speeddemo.R;

/**
 * Created by Administrator on 2017/4/23.
 */

public class ReFlashListView extends ListView implements AbsListView.OnScrollListener{
    View header;//下拉View
    private int headerHeight;
    private int firstVisibleItem;
    //当前的滚动状态
    private int scrollState = 1;
    //当前状态
    int state;
    //是否当前是第一个Intent
    private boolean isRemark;
    //下拉时y的值
    int startY;
    //正常状态
    final int NONE = 0;
    //提示下拉状态;
    final int PULL = 1;
    //提示释放状态
    final int RELESE = 2;
    //刷新状态
    final int REFLASHING = 3;
    public ReFlashListView(Context context) {
        super(context);
        init(context);
    }

    public ReFlashListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ReFlashListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        LayoutInflater mLayoutInflater = ((Activity)context).getLayoutInflater();
        //获取下拉页面
        header = mLayoutInflater.inflate(R.layout.hert_layout,null);
        //通知父布局，占用的宽，高；
        measureView(header);
        //获取下拉显示的高度
        headerHeight = header.getMeasuredHeight();
        //隐藏下拉刷新显示的页面
        topPadding(-headerHeight);
        //添加下拉显示页面
        this.addHeaderView(header);
    }
    /**
     * 通知父布局，占用的宽，高；
     *
     * @param view
     */
    public void measureView(View view){
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if(p == null){
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int width = ViewGroup.getChildMeasureSpec(0,0,p.width);
        int height;
        int tempHeight = p.height;
        if(tempHeight > 0){
            height = MeasureSpec.makeMeasureSpec(tempHeight,MeasureSpec.EXACTLY);
        }else{
            height = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        }
        view.measure(width,height);

    }

    /**
     * 设置header 布局 上边距；
     * 用于隐藏下拉显示的页面
     * @param topPadding
     */

    private void topPadding(int topPadding){
        header.setPadding(header.getPaddingLeft(),topPadding,header.getPaddingRight(),header.getPaddingBottom());
        header.invalidate();
    }

    //监听ListView滚动状态
    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        //设置当前的滚动状态
        this.scrollState = i;
        Log.i("nnnnnnnn",scrollState+"");
    }
    //监听ListView  显示的第一个Intent
    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        //设置当前的滚动状态
        this.firstVisibleItem = i;
    }

    //监听下滑手势
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            //当手指按下屏幕时
            case MotionEvent.ACTION_DOWN:
                if(firstVisibleItem == 0){
                    isRemark = true;
                    startY = (int) ev.getY();
                }
                break;
            //当手指移动时
            case MotionEvent.ACTION_MOVE:
                onMove(ev);
                break;
            case MotionEvent.ACTION_UP:
                if(state == RELESE){
                    //刷新状态
                    state = REFLASHING;
                    reflashViewByState();
                    //加载最新数据

                }else{
                    state = NONE;
                    isRemark = false;
                    reflashViewByState();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 判断手指移动过程操作；
     *
     * @param ev
     */
    private void onMove(MotionEvent ev){
        //如果当前不是第一个Intent 就退出
        if(!isRemark){
            return;
        }
        //手指当前的Y值
        int tempY = (int) ev.getY();
        //手指下移的距离
        int space = tempY -startY;
        //下拉刷新页面的padding的距离
        int topPadding = space - headerHeight;
        switch (state){
            case NONE:
                if(space > 0){
                    state = PULL;
                    reflashViewByState();
                }
                break;
            case PULL:
                //改变为释放状态
                topPadding(topPadding);
                if(space > headerHeight+30 && scrollState == SCROLL_STATE_TOUCH_SCROLL){
                    state = RELESE;
                    reflashViewByState();
                }
                break;
            case RELESE:
                //显示下拉刷新页
                topPadding(topPadding);
                if(space < headerHeight + 30){
                    state = PULL;
                    reflashViewByState();
                }else if(space <= 0){
                    state = NONE;
                    isRemark = false;
                    reflashViewByState();
                }
                break;
        }

    }

    /**
     * 根据当前手指移动状态，改变界面显示；
     */
    private void reflashViewByState(){
        //提示
        TextView tip = (TextView) header.findViewById(R.id.tip);
        //下拉图片
        ImageView arrow = (ImageView) header.findViewById(R.id.arrow);
        //刷新动画 设置
        ProgressBar progressBar = (ProgressBar) header.findViewById(R.id.progress);
        RotateAnimation anim = new RotateAnimation(0,180,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        anim.setDuration(500);
        anim.setFillAfter(true);
        RotateAnimation anim1 = new RotateAnimation(0,180,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        anim1.setDuration(500);
        anim1.setFillAfter(true);

        switch (state){
            case NONE:
                //隐藏下拉页面
                arrow.clearAnimation();
                topPadding(-headerHeight);
                break;
            case PULL:
                //显示下拉
                arrow.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                tip.setText("下拉可以刷新");
                arrow.clearAnimation();
                arrow.setAnimation(anim1);
                break;
            case RELESE:
                //显示松手刷新
                arrow.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                tip.setText("松开可以刷新");
                arrow.clearAnimation();
                arrow.setAnimation(anim);
                break;
            case REFLASHING:
                //显示刷新
                topPadding(50);
                arrow.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                tip.setText("正在刷新");
                arrow.clearAnimation();
                break;
        }
    }


}
