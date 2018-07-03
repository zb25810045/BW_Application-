package com.bloodcrown.applicationcomponent;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * 作者 ： BloodCrown
 * 时间 ： 2018/6/30 下午3:18
 * 描述 ：
 */

public class ApplicationComponent {

    private static ApplicationComponent instance;

    // 静态单例
    public Application application;
    // 手机信息管理器
    public PhoneInfoManage phoneInfoManage;
    // app 状态管理器
    public AppStateManage appStateManage;
    // app activity 栈管理器，暂时只提供 activity 计数功能
    public ActivityManage activityManage;
    // app 生命周期回调函数适配器
    public ListenerAdapter listenerAdapter;

    public static ApplicationComponent getInstance() {

        if (instance == null) {
            synchronized (ApplicationComponent.class) {
                if (instance == null) {
                    instance = new ApplicationComponent();
                }
            }
        }
        return instance;
    }

    public void attach(Application application) {

        if (application != null) {
            this.application = application;
            initParameter();
            registerActivityLifecycleCallback();
        }
    }

    private void initParameter() {

        phoneInfoManage = new PhoneInfoManage();
        appStateManage = new AppStateManage();
        activityManage = new ActivityManage();
    }

    public void registerListenerAdapter(ListenerAdapter adapter) {
        this.listenerAdapter = adapter;
    }

    private void registerActivityLifecycleCallback() {

        if (getInstance() == null) {
            return;
        }

        getInstance().application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if (listenerAdapter != null) {
                    listenerAdapter.onActivityCreated(activity, savedInstanceState);
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {

                if (listenerAdapter != null) {
                    listenerAdapter.onActivityStarted(activity);
                }

                if (appStateManage == null) {
                    return;
                }

                // app 首次启动
                if (appStateManage.STATE_APPSTATE == appStateManage.currentState) {
                    appStateManage.setCurrentState(appStateManage.STATE_FRONT);
                    activityManage.LiveActivityNum++;
                    if (listenerAdapter != null) {
                        listenerAdapter.onAppStart();
                    }
                    return;
                }

                // 切换到前台
                if (activityManage.LiveActivityNum == 0) {
                    appStateManage.setCurrentState(appStateManage.STATE_FRONT);
                    if (listenerAdapter != null) {
                        listenerAdapter.onGoTofront();
                    }
                }

                activityManage.LiveActivityNum++;
            }

            @Override
            public void onActivityResumed(Activity activity) {

                if (listenerAdapter != null) {
                    listenerAdapter.onActivityResumed(activity);
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {

                if (listenerAdapter != null) {
                    listenerAdapter.onActivityPaused(activity);
                }
            }

            @Override
            public void onActivityStopped(Activity activity) {

                if (listenerAdapter != null) {
                    listenerAdapter.onActivityStopped(activity);
                }

                activityManage.LiveActivityNum--;
                if (activityManage.LiveActivityNum < 0) {
                    activityManage.LiveActivityNum = 0;
                }

                // app 切换到后台
                if (activityManage.LiveActivityNum == 0) {
                    appStateManage.setCurrentState(appStateManage.STATE_BACKGROUD);
                    if (listenerAdapter != null) {
                        listenerAdapter.onGoToBackground();
                    }
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                if (listenerAdapter != null) {
                    listenerAdapter.onActivitySaveInstanceState(activity, outState);
                }
            }

            @Override
            public void onActivityDestroyed(Activity activity) {

                if (listenerAdapter != null) {
                    listenerAdapter.onActivityDestroyed(activity);
                }
            }
        });

    }

    public void onTerminate(int level) {

        if (listenerAdapter != null) {
            listenerAdapter.onTerminate();
        }

        if (level == Application.TRIM_MEMORY_MODERATE) {
            killProcess();
        }
    }

    public void killProcess() {

//        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}
