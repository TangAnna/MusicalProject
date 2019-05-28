package com.tang.musical.ui;

import com.tang.musical.baseui.activity.BaseActivityPresenter;

public class MainActivity extends BaseActivityPresenter<MainDelegate> {

    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();

    }

}
