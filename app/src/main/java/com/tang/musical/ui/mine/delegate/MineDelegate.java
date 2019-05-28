package com.tang.musical.ui.mine.delegate;

import com.tang.musical.R;
import com.tang.musical.baseui.delegate.TransparentToolbarDelegate;

/**
 * @author TangAnna
 * @description:
 * @date :${DATA} 13:45
 */
public class MineDelegate extends TransparentToolbarDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        setTitle("我的");
    }
}
