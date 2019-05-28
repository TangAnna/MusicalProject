package com.tang.musical.ui.home.area;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.tang.musical.R;
import com.tang.musical.baseui.widget.BaseRelativeLayout;
import com.tang.musical.ui.login.activity.LoginActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TangAnna
 * @description: banner
 * @date :${DATA} 14:11
 */
public class BannerArea extends BaseRelativeLayout implements ImageLoaderInterface, OnBannerListener {

    private Banner mBanner;

    private List<Integer> mData/* = Arrays.asList(R.drawable.banner1, R.drawable.banner2)*/;

    public BannerArea(Context context) {
        super(context);
    }

    public BannerArea(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerArea(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mBanner = findViewById(R.id.banner);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(this);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        // 点击监听
        mBanner.setOnBannerListener(this);

        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
        mData = new ArrayList<>();
        mData.add(R.drawable.banner1);
        mData.add(R.drawable.banner2);
        mData.add(R.drawable.banner3);
        mBanner.update(mData);
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.area_banner;
    }

    @Override
    public void displayImage(Context context, Object path, View imageView) {
        Integer imgId = (Integer) path;
        ((ImageView) imageView).setImageResource(imgId);
    }

    @Override
    public View createImageView(Context context) {
        return new ImageView(context);
    }

    @Override
    public void OnBannerClick(int position) {

        getContext().startActivity(new Intent(getContext(), LoginActivity.class));
    }
}
