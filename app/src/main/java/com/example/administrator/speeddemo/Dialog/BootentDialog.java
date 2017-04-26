package com.example.administrator.speeddemo.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.administrator.speeddemo.R;
import com.example.administrator.speeddemo.mView.TextColorNumberPicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/18.
 */

public class BootentDialog extends Dialog implements View.OnClickListener{
    private TextView quxiao,quding;
    private DialogTime chooseTime;
    private String[] data;
    private String nowDate;
    private ArrayList<String> dialogList ;
    private SimpleDateFormat mDateFormat;
    private SimpleDateFormat mHourFormat;
    private SimpleDateFormat mPonitsFormat;
    private Calendar mCalendar;
    private Pick_in_Interface  mPick_in;
    private int addTime;
    String pointsData[];

    TextColorNumberPicker date,hour,points;
    //定义最低价格、最高价格的初始值
    //选择时间的弹出窗口
    /*
    * 选择时间的弹出窗口
    *  contentx 上下文对象
    *  mFragment 回调接口
    *  temrResId弹出的动画
    *  addTime 在现在时间的基础上添加时间
    * */
    public BootentDialog(Context context, Object mFragment, int themeResId,int addTime) {
        super(context,themeResId);
        this.addTime = addTime;
        init();
        if (addTime == 0){
            chooseTime = (DialogTime) mFragment;
        }else {
            mPick_in = (Pick_in_Interface) mFragment;
        }
        LinearLayout root = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.layout_camera_control, null);
        setNum(root);
        setContentView(root);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) context.getResources().getDisplayMetrics().widthPixels; // 宽度
//      lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//      lp.alpha = 9f; // 透明度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
    }

    public void init(){
        dialogList = new ArrayList<String>();
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mHourFormat = new SimpleDateFormat("HH");
        mPonitsFormat = new SimpleDateFormat("mm");
        mCalendar = Calendar.getInstance();
    }

    private void setNum(View view){
        //取消按钮
        quxiao = (TextView) view.findViewById(R.id.quxiao);
        quxiao.setOnClickListener(this);
        //确认按钮
        quding = (TextView) view.findViewById(R.id.quding);
        quding.setOnClickListener(this);
        //设置天的日期
        date= (TextColorNumberPicker) view.findViewById(R.id.date);
        date.setNumberPickerDividerColor(date);
        //设置不可滚动
        date.setWrapSelectorWheel(false);
        //设置日期的选项
        data = new String[]{"今天", "明天", "后天"};
        date.setDisplayedValues(data);
        //设置天的当前值
        date.setValue(0);
        date.setMaxValue(data.length-1);
        dialogList.add(0,mDateFormat.format(new Date()));
        date.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                String data = null;
                mCalendar = Calendar.getInstance();
                //当选择不同日期的时候的相应
                switch (i1){
                    case 0:
                        //今天
                        mCalendar.roll(mCalendar.DAY_OF_YEAR,0);
                        hour.setMinValue(Integer.parseInt(mHourFormat.format(new Date())));
                        break;
                    case 1:
                        //明天
                        mCalendar.roll(mCalendar.DAY_OF_YEAR,1);
                        hour.setMinValue(0);
                        break;
                    case 2:
                        //后天
                        mCalendar.roll(mCalendar.DAY_OF_YEAR,2);
                        hour.setMinValue(0);
                        break;
                    default:
                        mCalendar.roll(mCalendar.DAY_OF_YEAR,0);
                        break;
                }
                data = mDateFormat.format(mCalendar.getTime());
                dialogList.remove(0);
                dialogList.add(0,data);
            }
        });

        //设置时的
        hour= (TextColorNumberPicker) view.findViewById(R.id.hour);
        hour.setNumberPickerDividerColor(hour);
        //设置不可循环滚动
        hour.setWrapSelectorWheel(false);
        int mHour = Integer.parseInt(mHourFormat.format(new Date()));
        //设置np1的最小值和最大值
        hour.setMinValue(mHour+addTime);
        hour.setMaxValue(23);
        //设置时的当前值
        hour.setValue(mHour+addTime);
        dialogList.add(1,mHour+"");
        hour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                dialogList.remove(1);
                dialogList.add(1,i1+"");
            }
        });


        //设置分的参数
        points= (TextColorNumberPicker) view.findViewById(R.id.points);
        //设置颜色
        points.setNumberPickerDividerColor(points);
        //设置数据
        pointsData = new String[]{"10","20","30","40","50"};
        points.setDisplayedValues(pointsData);
        //设置最小值的选项
        points.setMinValue(0);
        points.setMaxValue(pointsData.length-1);
        //设置当前显示值
        points.setValue(0);
        dialogList.add(2,pointsData[0]);
        points.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                dialogList.remove(2);
                dialogList.add(2,pointsData[i1]);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.quxiao:
                this.dismiss();
                break;
            case R.id.quding:
                if(addTime == 0){
                    chooseTime.getTime(dialogList);
                }else if(addTime == 2){
                    mPick_in.pick_in_dialog(dialogList);
                }
                break;
        }
    }



    public interface DialogTime{
        public void getTime(ArrayList<String> dialogList);
    }

    public interface Pick_in_Interface{
        public void pick_in_dialog(ArrayList<String> dialogList);
    }




}
