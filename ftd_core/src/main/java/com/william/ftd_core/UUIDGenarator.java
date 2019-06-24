package com.william.ftd_core;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.util.UUID;

public class UUIDGenarator {

	public static String getUUID(){
		String strUUID = UUID.randomUUID().toString();
		return strUUID.substring(0,8)+strUUID.substring(9,13)+strUUID.substring(14,18)+strUUID.substring(19,23)+strUUID.substring(24); 
		
	}

	public static String getWifiMac(Context ctx) {
		WifiManager wifi = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		String str = info.getMacAddress();
		if (str == null) str = "";
		return str;
	}


}
