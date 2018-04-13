package com.stt.iotest.util;

public class MD5 {
    public static String getMD5(byte[] source) {
        String s = null;

        // 字符数组，用来存放每个16进制字符
        char hexDigits[] =
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {

            // 生成一个MD5加密计算摘要
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");

            // 将数据传递给消息摘要
            md.update(source);

            // 获得消息摘要字节数组
            byte tmp[] = md.digest();

            // 字符数组用来组成结果字符串（一个byte是八位二进制，也就是2位十六进制字符）
            char str[] = new char[16 * 2];

            // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }

            // 字符数组组合成字符串返回
            s = new String(str);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}