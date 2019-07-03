package com.william.ftd_core;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Util {

    public static String getUUID() {
        String strUUID = UUID.randomUUID().toString();
        return strUUID.substring(0, 8) + strUUID.substring(9, 13) + strUUID.substring(14, 18) + strUUID.substring(19, 23) + strUUID.substring(24);
    }

    public static String generateAuthorization(String appKey, String appSecret, String pathUrl) {
        long ts = System.currentTimeMillis();
        String sign = appKey + ":" + ts + ":" + SHA1(pathUrl + appSecret + ts);
        String result = String.format("Bearer %s", Base64.encodeToString(sign.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT));
        return result.replaceAll("\n", "").replaceAll(" ", "");
    }

    public static String SHA1(String str) {
        byte[] digesta = null;
        try {
            MessageDigest alga = MessageDigest.getInstance("SHA-1");
            alga.update(str.getBytes());
            digesta = alga.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuffer hs = new StringBuffer();
        String stmp;
        for (byte b : digesta) {
            stmp = Integer.toHexString(b & 0XFF);
            if (stmp.length() == 1) {
                hs.append("0");
            }
            hs.append(stmp);
        }
        return hs.toString();
    }

    public static String getWifiMac(Context ctx) {
        WifiManager wifi = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String str = info.getMacAddress();
        if (str == null) str = "";
        return str;
    }
}
