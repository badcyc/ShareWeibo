package com.example.test.ui;

import android.view.View;

/**
 * Created by cyc20 on 2017/12/20.
 */

public interface I_Activity {
    int DESTROY = 0;
    int STOP = 2;
    int PAUSE = 1;
    int RESUME = 3;

    /**
     * 设置root界面
     */
    void setRootView();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 在线程中初始化数据
     */
    void initDataFromThread();

    /**
     * 初始化控件
     */
    void initWidget();

    /**
     * 点击事件回调方法
     */
    void widgetClick(View v);
}