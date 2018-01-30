package com.example.liunianyishi.intelligenttransportation.View;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.liunianyishi.intelligenttransportation.Adapter.mMainVPAdapter;
import com.example.liunianyishi.intelligenttransportation.Adapter.mMenuRVAdapter;
import com.example.liunianyishi.intelligenttransportation.Adapter.mPageChange;
import com.example.liunianyishi.intelligenttransportation.Interface.iItemClick;
import com.example.liunianyishi.intelligenttransportation.Interface.iPageChange;
import com.example.liunianyishi.intelligenttransportation.Interface.iPagerEvent;
import com.example.liunianyishi.intelligenttransportation.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements iPagerEvent, iItemClick, iPageChange,View.OnClickListener {
    ViewPager mainVP;
    RecyclerView menuRV;
    DrawerLayout mainDL;
    List<View> viewList;
    List<String> stringList;
    Button moreRecharge,rechargeHistory;
    mMainVPAdapter mainAdapter;
    mMenuRVAdapter menuAdapter;
    TextView title;
    String[] items;
    FrameLayout Fl_menuBtn;
    int[] views;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        moreRecharge = findViewById(R.id.moreRecharge);
        rechargeHistory = findViewById(R.id.rechargeHistory);
        Fl_menuBtn = findViewById(R.id.Fl_menuBtn);
        Fl_menuBtn.setOnClickListener(this);
        title = findViewById(R.id.title);
        stringList = new ArrayList<>();
        items = new String[]{
                "用户管理", "公交查询", "红绿灯管理", "违章查询", "个人中心"
        };
        for (int i = 0; i < items.length; i++) {
            stringList.add(items[i]);
        }
        mainDL = findViewById(R.id.mainDL);
        menuRV = findViewById(R.id.menuRV);
        menuRV.setLayoutManager(new LinearLayoutManager(this));
        menuAdapter = new mMenuRVAdapter(stringList, this);
        menuRV.setAdapter(menuAdapter);
        viewList = new ArrayList<>();

        views = new int[]{
                R.layout.view_usermanage,
                R.layout.view_bus,
                R.layout.view_trafficlights,
                R.layout.view_illegal,
                R.layout.view_personalcenter
        };
        for (int i = 0; i < views.length; i++) {
            View v = LayoutInflater.from(this).inflate(views[i], null);
            viewList.add(v);
        }
        mainVP = findViewById(R.id.mainVP);
        mainVP.setOffscreenPageLimit(4);
        mainVP.addOnPageChangeListener(new mPageChange(this));
        mainAdapter = new mMainVPAdapter(viewList, this);
        mainVP.setAdapter(mainAdapter);
    }

    @Override
    public void PagerEvent(View v, int p) {

    }

    @Override
    public void ItemClick(int p) {
        mainVP.setCurrentItem(p);
        mainDL.closeDrawer(Gravity.START);
    }



    @Override
    public void PageChange(int p) {
            title.setText(items[p]);
            if (p>0){
                rechargeHistory.setVisibility(View.GONE);
                moreRecharge.setVisibility(View.GONE);
            }
            else{
                rechargeHistory.setVisibility(View.VISIBLE);
                moreRecharge.setVisibility(View.VISIBLE);
            }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Fl_menuBtn:
                mainDL.openDrawer(Gravity.START);
                break;
        }
    }
}

