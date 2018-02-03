package com.example.liunianyishi.intelligenttransportation.View;

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
import android.widget.Toast;

import com.example.liunianyishi.intelligenttransportation.Adapter.mMainVPAdapter;
import com.example.liunianyishi.intelligenttransportation.Adapter.mMenuRVAdapter;
import com.example.liunianyishi.intelligenttransportation.Adapter.mPageChange;
import com.example.liunianyishi.intelligenttransportation.Adapter.mPageChangedListener;
import com.example.liunianyishi.intelligenttransportation.Bean.illegalCarListBean;
import com.example.liunianyishi.intelligenttransportation.Bean.illegalQuery;
import com.example.liunianyishi.intelligenttransportation.Interface.iItemClick;
import com.example.liunianyishi.intelligenttransportation.Interface.iPageChange;
import com.example.liunianyishi.intelligenttransportation.Interface.iPagerEvent;
import com.example.liunianyishi.intelligenttransportation.Presenter.mPresenter;
import com.example.liunianyishi.intelligenttransportation.R;
import com.example.liunianyishi.intelligenttransportation.View.ViewReslove.illegalQueryResult;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * QiuChenLuoYe 2018.2.3
 * 主界面类,全局主页,页面代码托管中转处理,高度解耦合,MVP设计方法
 */
public class MainActivity extends AppCompatActivity implements iPagerEvent, iItemClick, iPageChange, View.OnClickListener, mPresenter.queryCallback {
    ViewPager mainVP;
    RecyclerView menuRV;
    DrawerLayout mainDL;
    List<View> viewList;
    List<String> stringList;
    Button moreRecharge, rechargeHistory;
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
                "用户管理", "公交查询", "红绿灯管理", "违章查询", "查询结果", "监控抓拍", "路况查询", "生活助手", "数据分析", "个人中心", "你的创意"
        };

        //Arrays.asList() 转换数组为List加入
        stringList.addAll(Arrays.asList(items));
        //此代码已弃用
//        for (int i = 0; i < items.length; i++) {
//            stringList.add(items[i]);
//        }
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
                R.layout.view_search_result,
                R.layout.view_camara_capture,
                R.layout.view_road_status_result,
                R.layout.view_life_helper,
                R.layout.view_data_resolve,
                R.layout.view_personalcenter,
                R.layout.view_design
        };
        //枚举集合类,直接foreach就行了,不需要用for
        for (int view : views) {
            View v = LayoutInflater.from(this).inflate(view, null);
            viewList.add(v);
        }
//        for (int i = 0; i < views.length; i++) {
//            View v = LayoutInflater.from(this).inflate(views[i], null);
//            viewList.add(v);
//        }
        mainVP = findViewById(R.id.mainVP);
        mainVP.addOnPageChangeListener(new mPageChange(this));
        mainAdapter = new mMainVPAdapter(viewList, this);
        mainVP.setAdapter(mainAdapter);
        pageChanged = new mPageChangedListener(viewList, this);
        mainVP.addOnPageChangeListener(pageChanged);
    }

    private mPageChangedListener pageChanged = null;

    /**
     * 多重回调,实现内存optimization
     *
     * @param p 被销毁的VP值
     */
    @Override
    public void PagerDestroy(int p) {
        //通知此页面被销毁,下次打开需要重新初始化  缓解内存压力
        //此方法只适用于多页面>=6
        //低于5个页面直接 setOffscreenPageLimit(5)
        pageChanged.pageBeDestroy(p);
    }

    @Override
    public void PagerEvent(View v, int p) {
        //todo 容易造成内存泄露/ANR异常,使用lazy加载设计模式,此方法以后不允许使用
    }

    @Override
    public void ItemClick(int p) {
        mainVP.setCurrentItem(p);
        mainDL.closeDrawer(Gravity.START);
    }


    @Override
    public void PageChange(int p) {
        title.setText(items[p]);
        rechargeHistory.setVisibility(p > 0 ? View.GONE : View.VISIBLE);
        moreRecharge.setVisibility(p > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Fl_menuBtn:
                mainDL.openDrawer(Gravity.START);
                break;
        }
    }


    @Override
    public void retQueryResult(int isWho, @Nullable illegalQuery queryResult) {
        int vID = 0;
        switch (isWho) {
            case mPresenter.WHO_QUERY_RESULT:
                //数据查询到的时候,开始跳转
                if (queryResult.allList == null || queryResult.allList.size() <= 0) {
                    Toast.makeText(this, "没有查到任何数据!", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                Toast.makeText(this, "发现犯罪分子!违章次数:" + queryResult.allList.size(), Toast.LENGTH_LONG).show();
                vID = R.layout.view_search_result;
                illegalQueryResult s = (illegalQueryResult) pageChanged.getThis(4);
                if (s == null) {
                    //修正重复加载的BUG
                    jumpPage(vID);//首先跳转到此页面,由于使用了lazy技术,导致若不先jump初始化页面,会导致NullPointRefCallException
                    s = (illegalQueryResult) pageChanged.getThis(4);
                }
                //下面开始初始化请求
                illegalCarListBean bean = new illegalCarListBean();
                bean.carID = "鲁" + queryResult.carID;//完整车牌号
                bean.shortCarID = queryResult.carID;
                for (illegalQuery.result rut : queryResult.allList) {
                    if (rut.handleState == 1) {
                        bean.deductCount += rut.DeductInt;
                        bean.forfeitCount += rut.Forfeit;
                        bean.noHandleCount++;
                    }
                }
                if (!s.hasItemEx(queryResult.carID)) {
                    s.addAllResultToCarsRv(bean);
                } else
                    //应该同步跳转到对应的记录,懒得做了,题目也没说要
                    Toast.makeText(this, "此记录已存在!", Toast.LENGTH_SHORT).show();
                break;
            case mPresenter.WHO_QUERY_Details:
                vID = R.layout.view_illegal;
                break;
        }
        jumpPage(vID);
    }

    void jumpPage(int id) {
        int c = 0;
        for (int a : views) {
            if (a == id) {
                //修正 重复设置当前页面的问题
                if (mainVP.getCurrentItem() != c)
                    mainVP.setCurrentItem(c);
                break;
            }
            c++;
        }
    }
}


