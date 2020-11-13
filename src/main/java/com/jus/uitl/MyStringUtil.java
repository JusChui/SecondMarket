package com.jus.uitl;

import java.util.Base64;

/**
 * @Auther: JusChui
 * @Date: 2020/11/12 10:10
 * @Description:
 */
public class MyStringUtil {
    /**
     * base64加密字符串
     *
     * @param text
     * @return
     */
    public static String encrypt(String text) {
        String encodeText = null;
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] textByte = text.getBytes("UTF-8");
            encodeText = encoder.encodeToString(textByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeText;
    }

    /**
     * base64解密
     *
     * @param text
     * @return
     */
    public static String decrypt(String text) {
        String decodeText = null;
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            decodeText = new String(decoder.decode(text), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodeText;
    }
}
