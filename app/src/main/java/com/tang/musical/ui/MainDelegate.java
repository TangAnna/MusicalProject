package com.tang.musical.ui;

import com.tang.musical.R;
import com.tang.musical.baseui.delegate.ToolbarDelegate;

/**
 * @author TangAnna
 * @description:
 * @date :${DATA} 11:32
 */
public class MainDelegate extends ToolbarDelegate {

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        hindLine();
        setTitle("Musical");
        setLeft(R.drawable.menu);
        setRight(R.drawable.search);

    }

}
