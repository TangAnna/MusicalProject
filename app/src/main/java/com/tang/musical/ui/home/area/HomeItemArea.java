package com.tang.musical.ui.home.area;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tang.musical.R;
import com.tang.musical.baseui.widget.BaseRelativeLayout;
import com.tang.musical.model.MusicalModel;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TangAnna
 * @description: 首页的每一个横着的item
 * @date :${DATA} 11:11
 */
public class HomeItemArea extends BaseRelativeLayout {
    private RecyclerView mRecyclerView;
    private List<MusicalModel> mData;

    public HomeItemArea(Context context) {
        super(context);
    }

    public HomeItemArea(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeItemArea(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mRecyclerView = findViewById(R.id.rv_body);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        mData = new ArrayList<>();
        mRecyclerView.setAdapter(new CommonAdapter<MusicalModel>(getContext(), R.layout.item_home_area, mData) {
            @Override
            protected void convert(ViewHolder holder, MusicalModel musicalModel, int position) {

            }
        });


    }

    @Override
    public void setData(Object data) {

    }

    /**
     * 设置左侧的title
     *
     * @param title
     */
    public void setTitle(String title) {
        TextView textView = findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(title)) {
            textView.setText(title);
        }
    }

    public void setOnAllClickListener() {
        if (mAllListener != null) {
            mAllListener.allClick();
        }
    }

    @Override
    protected int bindLayout() {
        return R.layout.area_home_item;
    }

    private AllListener mAllListener;

    public interface AllListener {
        void allClick();
    }
}
