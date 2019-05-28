package com.tang.musical.ui.mine.fragment;

import com.tang.musical.baseui.fragment.BaseFragmentPresenter;
import com.tang.musical.ui.mine.delegate.MineDelegate;

/**
 * @author TangAnna
 * @description: 我的
 * @date :${DATA} 13:44
 */
public class MineFragment extends BaseFragmentPresenter<MineDelegate> {

    @Override
    protected Class<MineDelegate> getDelegateClass() {
        return MineDelegate.class;
    }
}
