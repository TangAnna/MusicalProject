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
 * @description: 首页的New Albums模块
 * @date :${DATA} 11:11
 */
public class AlbumsArea extends BaseRelativeLayout<List<MusicalModel>> {
    private RecyclerView mRecyclerView;
    private List<MusicalModel> mData;
    private CommonAdapter mAdapter;

    public AlbumsArea(Context context) {
        super(context);
    }

    public AlbumsArea(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlbumsArea(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        ((HomeItemTitle) findViewById(R.id.title_area)).setData("New Albums");
        findViewById(R.id.view_line).setVisibility(VISIBLE);
        mRecyclerView = findViewById(R.id.rv_body);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        mData = new ArrayList<>();
        mRecyclerView.setAdapter(mAdapter = new CommonAdapter<MusicalModel>(getContext(), R.layout.item_albums, mData) {
            @Override
            protected void convert(ViewHolder holder, MusicalModel musicalModel, int position) {

            }
        });


    }

    @Override
    public void setData(List<MusicalModel> data) {
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
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
