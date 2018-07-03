package com.bloodcrown.applicationcomponent;

/**
 * 作者 ： BloodCrown
 * 时间 ： 2018/6/30 下午4:23
 * 描述 ：
 */

public class AppStateManage {

    // app 首次启动
    public static final int STATE_APPSTATE = 0;
    // app 在前台
    public static final int STATE_FRONT = 1;
    // app 在后台
    public static final int STATE_BACKGROUD = -1;
    // app 当前状态
    public int currentState = STATE_APPSTATE;

    public void setCurrentState(int state) {
        currentState = state;
    }

}
