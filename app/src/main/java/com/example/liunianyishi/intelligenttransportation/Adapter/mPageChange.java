package com.example.liunianyishi.intelligenttransportation.Adapter;

import android.support.v4.view.ViewPager;

import com.example.liunianyishi.intelligenttransportation.Interface.iPageChange;

/**
 * Created by LiuNianyishi on 2018/1/24.
 */

public class mPageChange implements ViewPager.OnPageChangeListener {
    iPageChange change;

    public mPageChange(iPageChange change){
        this.change = change;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        change.PageChange(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
