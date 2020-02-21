package com.dmdddm.ads;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncoderByMD5 {

    EncoderByMD5(){

    }
    /**输入字符串 输出加密后的32位字符串**/
    public static String getMD5SDtring(String string){
        /**输入为空时返回空**/
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        /**输入字符串不为空时 初始化**/
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


}
