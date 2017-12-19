package com.example.test;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.test.ui.ActivityStack;
import com.example.test.ui.AnnotateUtil;
import com.example.test.ui.I_Activity;
import com.example.test.ui.SupportFragment;

import java.lang.ref.SoftReference;

/**
 * Created by cyc20 on 2017/12/20.
 */

public class SupportActivity extends AppCompatActivity implements View.OnClickListener,I_Activity {
    public static final int WHICH_MSG = 0X37210;

    public Activity aty;

    protected SupportFragment currentKJFragment;
    protected SupportFragment currentSupportFragment;
    private ThreadDataCallBack callback;
    private ActivityHandle threadHandle = new ActivityHandle(this);
    public int activityState = DESTROY;

    /**
     * 一个私有回调类，线程中初始化数据完成后的回调
     */
    private interface ThreadDataCallBack {
        void onSuccess();
    }


    private static class ActivityHandle extends Handler {
        private final SoftReference<SupportActivity> mOuterInstance;

        ActivityHandle(SupportActivity outer) {
            mOuterInstance = new SoftReference<>(outer);
        }

        // 当线程中初始化的数据初始化完成后，调用回调方法
        @Override
        public void handleMessage(android.os.Message msg) {
            SupportActivity aty = mOuterInstance.get();
            if (msg.what == WHICH_MSG && aty != null) {
                aty.callback.onSuccess();
            }
        }
    }

    /**
     * 如果调用了initDataFromThread()，则当数据初始化完成后将回调该方法。
     */
    protected void threadDataInited() {
    }

    /**
     * 在线程中初始化数据，注意不能在这里执行UI操作
     */
    @Override
    public void initDataFromThread() {
        callback = new ThreadDataCallBack() {
            @Override
            public void onSuccess() {
                threadDataInited();
            }
        };
    }
    @Override
    public void initData() {
    }

    @Override
    public void initWidget() {
    }
    private void initializer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                initDataFromThread();
                threadHandle.sendEmptyMessage(WHICH_MSG);
            }
        }).start();
        initData();
        initWidget();
    }
    @Override
    public void widgetClick(View v) {
    }

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T bindView(int id, boolean click) {
        T view = (T) findViewById(id);
        if (click) {
            view.setOnClickListener(this);
        }
        return view;
    }


    /***************************************************************************
     * print Activity callback methods
     ***************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        aty = this;
        ActivityStack.create().addActivity(this);
       // KJLoger.state(this.getClass().getName(), "---------onCreat ");
        super.onCreate(savedInstanceState);

        setRootView(); // 必须放在annotate之前调用
        AnnotateUtil.initBindView(this);
        initializer();
        //registerBroadcast();
    }

    @Override
    public void setRootView() {

    }

    @Override
    protected void onStart() {
        super.onStart();
       // KJLoger.state(this.getClass().getName(), "---------onStart ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityState = RESUME;
       // KJLoger.state(this.getClass().getName(), "---------onResume ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityState = PAUSE;
       // KJLoger.state(this.getClass().getName(), "---------onPause ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        activityState = STOP;
       // KJLoger.state(this.getClass().getName(), "---------onStop ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
       // KJLoger.state(this.getClass().getName(), "---------onRestart ");
    }

    @Override
    protected void onDestroy() {
       // unRegisterBroadcast();
        activityState = DESTROY;
        //KJLoger.state(this.getClass().getName(), "---------onDestroy ");
        super.onDestroy();
        ActivityStack.create().finishActivity(this);
        currentKJFragment = null;
        currentSupportFragment = null;
        callback = null;
        threadHandle = null;
        aty = null;
    }
   /* public void changeFragment(int resView, SupportFragment targetFragment) {
        if (targetFragment.equals(currentKJFragment)) {
            return;
        }
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.add(resView, targetFragment, targetFragment.getClass()
                    .getName());
        }
        if (targetFragment.isHidden()) {
            transaction.show(targetFragment);
            targetFragment.onChange();
        }
        if (currentKJFragment != null && currentKJFragment.isVisible()) {
            transaction.hide(currentKJFragment);
        }
        currentKJFragment = targetFragment;
        transaction.commit();
    }
*/
    /**
     * 用Fragment替换视图
     *
     * @param resView        将要被替换掉的视图
     * @param targetFragment 用来替换的Fragment
     */
    public void changeFragment(int resView, SupportFragment targetFragment) {
        if (targetFragment.equals(currentSupportFragment)) {
            return;
        }
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.add(resView, targetFragment, targetFragment.getClass()
                    .getName());
        }
        if (targetFragment.isHidden()) {
            transaction.show(targetFragment);
            targetFragment.onChange();
        }
        if (currentSupportFragment != null
                && currentSupportFragment.isVisible()) {
            transaction.hide(currentSupportFragment);
        }
        currentSupportFragment = targetFragment;
        transaction.commit();
    }
}

