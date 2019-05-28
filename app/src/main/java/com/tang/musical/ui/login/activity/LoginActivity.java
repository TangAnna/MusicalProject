package com.tang.musical.ui.login.activity;

import com.tang.musical.baseui.activity.BaseActivityPresenter;
import com.tang.musical.ui.login.delegate.LoginDelegate;

/**
 * @author TangAnna
 * @description: 登录页面
 * @date :${DATA} 16:33
 */
public class LoginActivity extends BaseActivityPresenter<LoginDelegate> {
    @Override
    protected Class<LoginDelegate> getDelegateClass() {
        return LoginDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }
}
