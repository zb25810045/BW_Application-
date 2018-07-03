package com.bloodcrown.applicationcomponent;

import android.os.Build;
import android.widget.Toast;

/**
 * 作者 ： BloodCrown
 * 时间 ： 2018/6/30 下午3:48
 * 描述 ：
 * <p>
 * StringBuffer phoneI签: " + android.os.Build.TAGS) ;
 * phoneInfo.append("\n手机型号: " + android.os.Build.MODEL);
 * phoneInfo.append("\nSDK版本: " + android.os.Build.VERSION.SDK);
 * phoneInfo.append ("\n系统版本: " + android.os.Build.VERSION.RELEASE);
 * phoneInfo.append ("\n设备驱动: " + android.os.Build.DEVICE);
 * phoneInfo.append("\n版本号: " + android.os.Build.DISPLAY);
 * phoneInfo.append("\n品牌: " + android.os.Build.BRAND);
 * phoneInfo.append("\n主板: " + android.os.Build.BOARD);
 * phoneInfo.append("\n指纹: " + android.os.Build.FINGERPRINT);
 * phoneInfo.append("\nID: " + android.os.Build.ID);
 * phoneInfo.append("\n制造商: " + android.os.Build.MANUFACTURER);
 * phoneInfo.append("\n用户组: " + android.os.Build.USER);
 */

public class PhoneInfoManage {

    public String brand = "";
    public String modle = "";
    public String sdkVersion = "";
    public String sysVersion = "";

    public PhoneInfoManage() {
        brand = Build.BRAND;
        modle = Build.MODEL;
        sdkVersion = Build.VERSION.SDK;
        sysVersion = Build.VERSION.RELEASE;
    }

    public String getPhoneInfo() {
        return "PhoneInfoManage{" +
                "brand='" + brand + '\'' +
                ", modle='" + modle + '\'' +
                ", sdkVersion='" + sdkVersion + '\'' +
                ", sysVersion='" + sysVersion + '\'' +
                '}';
    }

    public void print() {
        Toast.makeText(ApplicationComponent.getInstance().application, getPhoneInfo(), Toast.LENGTH_SHORT).show();
    }

}
