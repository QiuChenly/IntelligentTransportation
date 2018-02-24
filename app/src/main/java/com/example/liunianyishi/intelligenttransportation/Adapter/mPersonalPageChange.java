package com.example.liunianyishi.intelligenttransportation.Adapter;

import android.support.v4.view.ViewPager;

import com.example.liunianyishi.intelligenttransportation.Interface.iPersonalPageChange;

/**
 * Created by Administrator on 2018/2/5 0005.
 */

public class mPersonalPageChange implements ViewPager.OnPageChangeListener {
    iPersonalPageChange change;

    public mPersonalPageChange(iPersonalPageChange change){
        this.change = change;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        change.PersonalPageChange(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
