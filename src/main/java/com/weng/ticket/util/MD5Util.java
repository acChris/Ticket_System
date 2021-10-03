package com.weng.ticket.util;

import java.security.MessageDigest;

/**
 * 对密码MD5加密
 */
public class MD5Util {

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        // 将范围从 [-128,127] 扩到 [0, 255]
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

//    初始密码，字符集
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            // 复制原先输入的密码
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 判字符集是否为空
            if (charsetname == null || "".equals(charsetname))
                // 引用默认信息摘要算法
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                // 根据字符集名引用信息摘要算法
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}
