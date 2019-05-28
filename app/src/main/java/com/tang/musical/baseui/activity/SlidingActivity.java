package com.tang.musical.baseui.activity;

import android.os.Bundle;

import com.tang.musical.baseui.widget.SlidingLayout;
import com.kymjs.frame.view.IDelegate;

/**
 * @param <T>
 */
public abstract class SlidingActivity<T extends IDelegate> extends BaseActivityPresenter<T> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (enableSliding()) {
            SlidingLayout rootView = new SlidingLayout(this);
            rootView.bindActivity(this);
        }
    }

    protected boolean enableSliding() {
        return true;
    }

}
