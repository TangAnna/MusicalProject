package com.tang.musical.baseui.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * @author TangAnna
 * @description:
 * @date :${DATA} 11:30
 */
public class CustomSmartRefreshLayout extends SmartRefreshLayout {
    public CustomSmartRefreshLayout(Context context) {
        super(context);
    }

    public CustomSmartRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomSmartRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

}
