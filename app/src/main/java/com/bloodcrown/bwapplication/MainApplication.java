package com.bloodcrown.bwapplication;

import android.app.Application;
import android.util.Log;

import com.bloodcrown.applicationcomponent.ApplicationComponent;
import com.bloodcrown.applicationcomponent.ListenerAdapter;

/**
 * 作者 ： BloodCrown
 * 时间 ： 2018/7/1 上午10:01
 * 描述 ：
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationComponent.getInstance().attach(this);
        ApplicationComponent.getInstance().registerListenerAdapter(new ListenerAdapter() {

            @Override
            public void onAppStart() {
                super.onAppStart();
                Log.d("AAA", "onAppStart: app 首次启动");
                Log.d("AAA", "app 信息如下：" + ApplicationComponent.getInstance().phoneInfoManage.getPhoneInfo());
            }

            @Override
            public void onGoToBackground() {
                super.onGoToBackground();
                Log.d("AAA", "onGoToBackground: app 切入后台");
                ApplicationComponent.getInstance().killProcess();
            }

            @Override
            public void onGoTofront() {
                super.onGoTofront();
                Log.d("AAA", "onGoTofront: app 切回前台");
            }
        });
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ApplicationComponent.getInstance().onTerminate(level);
    }
}
