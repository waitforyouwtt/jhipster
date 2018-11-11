package com.book.utils;

import java.util.Random;

/**
 * @Author: 一点点
 * @Date: 2018/11/11 12:01
 * @Version 1.0
 */
public class AutoGeneration {
    /**
     * 生成随机数字和字母
     * @param length
     * @return
     */
    public String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        //length为几位密码
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
    /**
     *随机生成6位数字
     */
    public static int autosixNumber(){
        //10000 -5位 1000-4位
        return  (int)((Math.random()*9+1)*100000);
    }

    /**
     * 随机生成5-6位数字
     * @return
     */
    public static int autoNumber(){
        int[] array = {0,1,2,3,4,5,6,7,8,9};
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        int result = 0;
        for(int i = 0; i < 6; i++){
            result = result * 10 + array[i];
        }
        return result;
    }
}
