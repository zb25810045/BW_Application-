package com.bloodcrown.applicationcomponent;

import android.app.ActivityManager;
import android.content.Context;

/**
 * 作者 ： BloodCrown
 * 时间 ： 2018/7/1 上午10:00
 * 描述 ：
 */

public class ProcessUtils {

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return "";
    }

}
