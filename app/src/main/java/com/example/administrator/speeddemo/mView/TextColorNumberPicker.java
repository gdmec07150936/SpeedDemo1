package com.example.administrator.speeddemo.mView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;


import com.example.administrator.speeddemo.R;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/4/16.
 */

public class TextColorNumberPicker extends NumberPicker {
    public TextColorNumberPicker(Context context) {
        super(context);
    }

    public TextColorNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextColorNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(View child, int index,
                        android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    @Override
    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }

    public void updateView(View view) {
        if (view instanceof EditText) {
            //这里修改显示字体的属性，主要修改颜色
            ((EditText) view).setTextColor(Color.parseColor("#8a8a8a"));
            //设置字体的大少
            ((EditText) view).setTextSize(16);
        }
    }

    public void setNumberPickerDividerColor(NumberPicker numberPicker) {
        NumberPicker picker = numberPicker;
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    //设置分割线的颜色值 透明
                    pf.set(picker, new ColorDrawable(getResources().getColor(R.color.login_up)));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        // 分割线高度
        for (Field pf2 : pickerFields) {
            if (pf2.getName().equals("mSelectionDividerHeight")) {
                pf2.setAccessible(true);
                try {
                    int result = 6;
                    pf2.set(picker, result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }
}