package com.zhang.myapplication.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * 作者：Administrator on 2016/9/26 14:12
 * 描述：
 */
public class UpdateApk {

    /**
     * 检查版本
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int VersionCode =-1;
        try {
            VersionCode =context.getPackageManager().getPackageInfo("com.zhang.myapplication",0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("msg",e.getMessage());
        }
        return VersionCode;
    }
}
