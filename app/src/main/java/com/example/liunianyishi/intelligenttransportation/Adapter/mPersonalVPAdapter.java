package com.example.liunianyishi.intelligenttransportation.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.liunianyishi.intelligenttransportation.Interface.iPagerEvent;
import com.example.liunianyishi.intelligenttransportation.Interface.iPersonPagerEvent;

import java.util.List;

/**
 * Created by Administrator on 2018/2/5 0005.
 */

public class mPersonalVPAdapter extends PagerAdapter {
    List<View> list;
    iPersonPagerEvent event;
    public mPersonalVPAdapter(List<View> list,iPersonPagerEvent event){
        this.list = list;
        this.event = event;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View v, Object o) {
        return v == o;
    }

    @Override
    public Object instantiateItem(ViewGroup c, int p) {
        View v = list.get(p);
        event.PersonPagerEvent(v,p);
        c.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup c, int p, Object o) {
        c.removeView((View) o);
    }
}
