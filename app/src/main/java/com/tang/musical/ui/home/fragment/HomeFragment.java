package com.tang.musical.ui.home.fragment;

import com.tang.musical.baseui.fragment.BaseFragmentPresenter;
import com.tang.musical.ui.home.delegate.HomeDelegate;

/**
 * @author TangAnna
 * @description: 首页
 * @date :${DATA} 13:44
 */
public class HomeFragment extends BaseFragmentPresenter<HomeDelegate> {
    @Override
    protected Class<HomeDelegate> getDelegateClass() {
        return HomeDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();

    }
}
