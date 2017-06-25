package com.ptteng;

import com.ptteng.util.DESUtil;

/**
 * Created by litao on 2017/3/3.
 */
public class Main {

    public static void main(String[] args){
        DESUtil util = new DESUtil();
        String str = "12345678";

        //密码,长度要是8的倍数
        String password = "12345678";
        byte[] result = util.encrypt(str.getBytes(), password);
        System.out.println("加密后内容为:"+new String(result));

        try{
            byte[] decryResult = util.decrypt(result,password);
            System.out.println("解密后内容为:" + new String(decryResult));
        }catch (Exception e){

        }
    }
}
