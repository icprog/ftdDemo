package com.william.ftd_core;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;

import com.william.ftd_core.param.HeaderParam;

public class InitHandler extends ContentProvider {
    public InitHandler() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    private String appKey;
    private String appId;
    private String appCode;
    private String companyCode;
    private String appSecret;
    private String companyId;
    private String companyPid;
    private String phrAppKey;
    private String phrAppSecret;
    @Override
    public boolean onCreate() {
        Context context = getContext();
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
//            Log.e(TAG, "init: ", e);
        }

        appId = String.valueOf(appInfo.metaData.getInt("laiKang.appId"));
        appCode = String.valueOf(appInfo.metaData.getInt("laiKang.appCode"));
        appKey = appInfo.metaData.getString("laiKang.appKey");
        appSecret = appInfo.metaData.getString("laiKang.appSecret");
        companyId = String.valueOf(appInfo.metaData.getInt("laiKang.companyId"));
        companyPid = String.valueOf(appInfo.metaData.getInt("laiKang.companyPid"));
        companyCode = appInfo.metaData.getString("laiKang.companyCode");
        phrAppKey = appInfo.metaData.getString("laiKang.phrAppKey");
        phrAppSecret = appInfo.metaData.getString("laiKang.phrAppSecret");

        HeaderParam param = new HeaderParam();
        param.appId = appId;
        param.appCode = appCode;
        param.appKey = appKey;
        param.appSecret = appSecret;
        param.companyId = companyId;
        param.companyPid = companyPid;
        param.companyCode = companyCode;
        param.phrAppKey = phrAppKey;
        param.phrAppSecret = phrAppSecret;
        ServerConnection.getInstance().init(param);
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
