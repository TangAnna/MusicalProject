package com.tang.musical.ui.activitys;

import com.tang.musical.baseui.fragment.BaseFragmentPresenter;
import com.tang.musical.ui.delegate.ActivityDelegate;

/**
 * @author TangAnna
 * @description: 活动
 * @date :${DATA} 9:46
 */
public class ActivityFragment extends BaseFragmentPresenter<ActivityDelegate> {
    @Override
    protected Class<ActivityDelegate> getDelegateClass() {
        return ActivityDelegate.class;
    }
}
