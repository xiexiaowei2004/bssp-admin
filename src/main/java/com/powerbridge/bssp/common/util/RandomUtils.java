package com.powerbridge.bssp.common.util;
import java.util.Random; 
/**
 * 
 * @ClassName RandomUtils
 * @Description 随机数、随即字符串工具
 * @author Simon.xie
 * @Date 2017年6月2日 上午11:03:27
 * @version 1.0.0
 */
public class RandomUtils {
	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";   
    public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";   
    public static final String numberChar = "0123456789";   
    
    private static RandomUtils randomUtils = new RandomUtils();
    
    public static RandomUtils getRandomUtils() {
		return randomUtils;
	}

	/**  
     * 返回一个定长的随机字符串(只包含大小写字母、数字)  
     *  
     * @param length 随机字符串长度  
     * @return 随机字符串  
     */   
    public static String generateString(int length) {   
            StringBuffer sb = new StringBuffer();   
            Random random = new Random();   
            for (int i = 0; i < length; i++) {   
                    sb.append(allChar.charAt(random.nextInt(allChar.length())));   
            }   
            return sb.toString();   
    }   

    /**  
     * 返回一个定长的随机纯字母字符串(只包含大小写字母)  
     *  
     * @param length 随机字符串长度  
     * @return 随机字符串  
     */   
    public static String generateMixString(int length) {   
            StringBuffer sb = new StringBuffer();   
            Random random = new Random();   
            for (int i = 0; i < length; i++) {   
                    sb.append(allChar.charAt(random.nextInt(letterChar.length())));   
            }   
            return sb.toString();   
    }   

    /**  
     * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)  
     *  
     * @param length 随机字符串长度  
     * @return 随机字符串  
     */   
    public static String generateLowerString(int length) {   
            return generateMixString(length).toLowerCase();   
    }   

    /**  
     * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)  
     *  
     * @param length 随机字符串长度  
     * @return 随机字符串  
     */   
    public static String generateUpperString(int length) {   
            return generateMixString(length).toUpperCase();   
    }   

    /**  
     * 生成一个定长的纯0字符串  
     *  
     * @param length 字符串长度  
     * @return 纯0字符串  
     */   
    public static String generateZeroString(int length) {   
            StringBuffer sb = new StringBuffer();   
            for (int i = 0; i < length; i++) {   
                    sb.append('0');   
            }   
            return sb.toString();   
    }   

    /**  
     * 根据数字生成一个定长的字符串，长度不够前面补0  
     *  
     * @param num             数字  
     * @param fixdlenth 字符串长度  
     * @return 定长的字符串  
     */   
    public static String toFixdLengthString(long num, int fixdlenth) {   
            StringBuffer sb = new StringBuffer();   
            String strNum = String.valueOf(num);   
            if (fixdlenth - strNum.length() >= 0) {   
                    sb.append(generateZeroString(fixdlenth - strNum.length()));   
            } else {   
                    throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！");   
            }   
            sb.append(strNum);   
            return sb.toString();   
    }   

    /**  
     * 根据数字生成一个定长的字符串，长度不够前面补0  
     *  
     * @param num             数字  
     * @param fixdlenth 字符串长度  
     * @return 定长的字符串  
     */   
    public static String toFixdLengthString(int num, int fixdlenth) {   
            StringBuffer sb = new StringBuffer();   
            String strNum = String.valueOf(num);   
            if (fixdlenth - strNum.length() >= 0) {   
                    sb.append(generateZeroString(fixdlenth - strNum.length()));   
            } else {   
                    throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！");   
            }   
            sb.append(strNum);   
            return sb.toString();   
    }   
    
    /* 
     * 返回长度为【strLength】的随机数，在前面补0 
     */  
    public static String getFixLenthString(int strLength) {  
          
        Random rm = new Random();  
          
        // 获得随机数  
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);  
      
        // 将获得的获得随机数转化为字符串  
        String fixLenthString = String.valueOf(pross);  
      
        // 返回固定的长度的随机数  
        return fixLenthString.substring(1, strLength + 1);  
    }  

    public static void main(String[] args) {   
            System.out.println(generateString(15));   
            System.out.println(generateMixString(15));   
            System.out.println(generateLowerString(15));   
            System.out.println(generateUpperString(15));   
            System.out.println(generateZeroString(15));   
            System.out.println(toFixdLengthString(123, 15));   
            System.out.println(toFixdLengthString(123L, 15));   
            int random=(int)(Math.random()*10);
            System.out.println(random);  
            System.out.println(getFixLenthString(6));  
    }
}
