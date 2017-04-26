package com.example.administrator.speeddemo.net;

import android.icu.text.SimpleDateFormat;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/27.
 */

public class PublicUtil {
    /*对字符串进行md5加密
    * @parten string 需要加密的字符串
    * */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /*获取当前时间
    * @pater 返回时间的格式
    * */
    public static String getDate(String dateSimple){
        Date currentTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat(dateSimple);
        String mdate = format.format(currentTime);
        return mdate;
    }
}
