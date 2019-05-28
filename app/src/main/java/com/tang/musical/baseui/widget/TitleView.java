package com.tang.musical.baseui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tang.musical.R;
import com.tang.musical.utils.QMUIStatusBarHelper;

/**
 * @author TangAnna
 * @description: 布局中使用的title
 * @date :${DATA} 17:35
 */
public class TitleView extends RelativeLayout {
    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context, attrs);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context, attrs);
    }

    private void initLayout(Context context, AttributeSet attrs) {
        View inflate = inflate(context, R.layout.title_layout, this);
        //设置顶部的padding值
        findViewById(R.id.root_layout).setPadding(0, QMUIStatusBarHelper.getStatusbarHeight(getContext()), 0, 0);

        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        String title = typedArray.getString(R.styleable.TitleView_title);
        if (!TextUtils.isEmpty(title)) {
            ((TextView) findViewById(R.id.tv_title)).setText(title);
        }
        typedArray.recycle();
        findViewById(R.id.iv_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }

    /**
     * 设置title
     *
     * @param title
     */
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            ((TextView) findViewById(R.id.tv_title)).setText(title);
        }
    }
}
