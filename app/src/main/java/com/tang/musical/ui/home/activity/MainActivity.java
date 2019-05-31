package com.tang.musical.ui.home.activity;

import com.tang.musical.baseui.activity.BaseActivityPresenter;
import com.tang.musical.ui.home.delegate.MainDelegate;

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
