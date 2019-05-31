package com.tang.musical.ui.home.area;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tang.musical.R;
import com.tang.musical.baseui.widget.BaseRelativeLayout;

/**
 * @author TangAnna
 * @description: 首页中每一个title
 * @date :${DATA} 16:04
 */
public class HomeItemTitle extends BaseRelativeLayout<String> {

    public HomeItemTitle(Context context) {
        super(context);
    }

    public HomeItemTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeItemTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

    }

    /**
     * 设置title
     *
     * @param data
     */
    @Override
    public void setData(String data) {
        ((TextView) findViewById(R.id.tv_title)).setText(data);
    }

    @Override
    protected int bindLayout() {
        return R.layout.area_home_title;
    }
}
