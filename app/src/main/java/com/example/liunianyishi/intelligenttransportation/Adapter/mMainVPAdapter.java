package com.example.liunianyishi.intelligenttransportation.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.liunianyishi.intelligenttransportation.Interface.iPagerEvent;

import java.util.List;

/**
 * Created by LiuNianyishi on 2018/1/23.
 */

public class mMainVPAdapter extends PagerAdapter {
    List<View> list;
    iPagerEvent event;
    public mMainVPAdapter(List<View> list,iPagerEvent event){
        this.list = list;
        this.event = event;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View v, Object o) {
        return v==o;
    }

    @Override
    public Object instantiateItem(ViewGroup c, int p) {
        View v = list.get(p);
        c.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup c, int p, Object o) {
       c.removeView((View) o);
    }
}
