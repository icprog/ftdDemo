package com.william.ftd_core;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class PhrUrlSignUtil {

    /**
     * 验证签名
     * @param sign
     * @param newSign
     * @return
     */
    public static boolean checkSign(String sign, String newSign) {
        return sign.equals(newSign);
    }

    private static boolean checkTime(String timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date paramTime = null;
        try {
            paramTime = sdf.parse(timestamp);
        } catch (Exception e) {
            e.getMessage();
        }

        if (paramTime == null) {
            return false;
        } else {
            Date now = new Date();
            long delta = now.getTime() - paramTime.getTime();
            long minutes = toMinutes(delta);
            if (minutes > 10L) {
                return false;
            }
        }
        return true;
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    /**
     * 加签方法
     * @param source
     * @return
     */
    public static String sign(String source) {
//        return DigestUtils.sha1Hex(source);
        return Util.SHA1(source);
    }

    /**
     * 排序方法
     */
    public static String sort(String url, String appKey, String appSecret, String timestamp, String bodyJsonStr) {
        String[] strArray = {url,appKey, appSecret, timestamp, bodyJsonStr};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }
        return sb.toString();
    }
}