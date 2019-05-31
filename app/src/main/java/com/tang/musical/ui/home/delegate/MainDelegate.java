package com.tang.musical.ui.home.delegate;

import com.tang.musical.R;
import com.tang.musical.baseui.delegate.ToolbarDelegate;
import com.tang.musical.model.MusicalModel;
import com.tang.musical.ui.home.area.AlbumsArea;
import com.tang.musical.ui.home.area.BrowseArea;
import com.tang.musical.ui.home.area.MostPlayedArea;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TangAnna
 * @description:
 * @date :${DATA} 11:32
 */
public class MainDelegate extends ToolbarDelegate {

    private BrowseArea mBrowseArea;
    private AlbumsArea mAlbumsArea;
    private AlbumsArea mAlbumsBottomArea;
    private MostPlayedArea mMostPlayedArea;


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

        List<MusicalModel> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            data.add(new MusicalModel());
        }
        mBrowseArea = get(R.id.browse_area);
        mBrowseArea.setData(data);

        mAlbumsArea = get(R.id.albums_area);
        mAlbumsArea.setData(data);

        mMostPlayedArea = get(R.id.most_area);
        mMostPlayedArea.setData(data);

        mAlbumsBottomArea = get(R.id.albums_area_bottom);
        mAlbumsBottomArea.setData(data);
    }

}
