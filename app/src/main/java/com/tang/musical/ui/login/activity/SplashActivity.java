package com.tang.musical.ui.login.activity;

import android.os.Handler;
import android.os.Message;

import com.tang.musical.baseui.activity.BaseActivityPresenter;
import com.tang.musical.ui.MainActivity;
import com.tang.musical.ui.login.delegate.SplashDelegate;

/**
 * @author TangAnna
 * @description: 闪屏页面
 * @date :${DATA} 10:19
 */
public class SplashActivity extends BaseActivityPresenter<SplashDelegate> {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            onNext();
        }
    };

    @Override
    protected Class<SplashDelegate> getDelegateClass() {
        return SplashDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();

        mHandler.sendEmptyMessageDelayed(0, 3000);
    }

    public void onNext() {
        openActivity(MainActivity.class);
        finish();
    }
}
