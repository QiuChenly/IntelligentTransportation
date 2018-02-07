package com.example.liunianyishi.intelligenttransportation.Interface;

import android.view.View;

/**
 * Created by LiuNianyishi on 2018/1/24.
 */

public interface iPagerEvent {
    void PagerEvent(View v, int p);

    /**
     * 当页面被销毁时,此方法被调用
     * @param p 被销毁的VP值
     */
    void PagerDestroy(int p);
}
