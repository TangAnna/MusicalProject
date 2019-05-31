package com.tang.musical.ui.home.area;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.tang.musical.R;
import com.tang.musical.baseui.widget.BaseRelativeLayout;
import com.tang.musical.model.MusicalModel;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TangAnna
 * @description: 首页的Browse模块
 * @date :${DATA} 11:11
 */
public class MostPlayedArea extends BaseRelativeLayout<List<MusicalModel>> {
    private RecyclerView mRecyclerView;
    private List<MusicalModel> mData;
    private CommonAdapter mAdapter;

    public MostPlayedArea(Context context) {
        super(context);
    }

    public MostPlayedArea(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MostPlayedArea(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        ((HomeItemTitle) findViewById(R.id.title_area)).setData("Most Played Tracks");
        mRecyclerView = findViewById(R.id.rv_body);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        mData = new ArrayList<>();
        mRecyclerView.setAdapter(mAdapter = new CommonAdapter<MusicalModel>(getContext(), R.layout.item_most_played, mData) {
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
