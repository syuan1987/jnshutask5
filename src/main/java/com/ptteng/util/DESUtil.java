package com.ptteng.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * Created by litao on 2017/3/3.
 */

@Component
public class DESUtil {

    /**
     *  DES加密介绍 DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
     * 后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
     * 24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现 。
     * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
     *
     * */

    /**
     * 加密
     * */

    public byte[] encrypt(byte[] datasource, String password){

        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            //创建一个秘钥工厂,然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKey);

            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用秘钥初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE,secretKey,random);

            //现在获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource);

        }catch (Throwable e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * */
    public byte[] decrypt(byte[] src, String password) throws Exception{

        //DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        //创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        //创建一个秘钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        //将DESkeySpec对象转换成SecretKey对象
        SecretKey secretKey = keyFactory.generateSecret(desKey);
        //Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        //用秘钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE,secretKey,random);
        //真正开始解密操作
        return cipher.doFinal(src);
    }

}
