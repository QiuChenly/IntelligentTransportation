package com.example.liunianyishi.intelligenttransportation.View;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liunianyishi.intelligenttransportation.Adapter.mMainVPAdapter;
import com.example.liunianyishi.intelligenttransportation.Adapter.mMenuRVAdapter;
import com.example.liunianyishi.intelligenttransportation.Adapter.mPageChange;
import com.example.liunianyishi.intelligenttransportation.Adapter.mPageChangedListener;
import com.example.liunianyishi.intelligenttransportation.Adapter.mPersonalPageChange;
import com.example.liunianyishi.intelligenttransportation.Adapter.mPersonalVPAdapter;
import com.example.liunianyishi.intelligenttransportation.Adapter.mRechargeRVAdapter;
import com.example.liunianyishi.intelligenttransportation.Adapter.mUserManageRVAdapter;
import com.example.liunianyishi.intelligenttransportation.Bean.illegalCarListBean;
import com.example.liunianyishi.intelligenttransportation.Bean.illegalQueryBean;
import com.example.liunianyishi.intelligenttransportation.DataBase.UserInfo;
import com.example.liunianyishi.intelligenttransportation.Interface.iCarRecharge;
import com.example.liunianyishi.intelligenttransportation.Interface.iItemClick;
import com.example.liunianyishi.intelligenttransportation.Interface.iPageChange;
import com.example.liunianyishi.intelligenttransportation.Interface.iPagerEvent;
import com.example.liunianyishi.intelligenttransportation.Interface.iPersonPagerEvent;
import com.example.liunianyishi.intelligenttransportation.Interface.iPersonalPageChange;
import com.example.liunianyishi.intelligenttransportation.Presenter.mPresenter;
import com.example.liunianyishi.intelligenttransportation.R;
import com.example.liunianyishi.intelligenttransportation.Utils.mSharedContext;
import com.example.liunianyishi.intelligenttransportation.Utils.mUtils;
import com.example.liunianyishi.intelligenttransportation.View.ViewReslove.illegalQueryResult;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * QiuChenLuoYe 2018.2.3
 * 主界面类,全局主页,页面代码托管中转处理,高度解耦合,MVP设计方法
 */
public class MainActivity extends AppCompatActivity implements iPagerEvent,
        iItemClick, iPageChange, View.OnClickListener, mPresenter.queryCallback,
        iCarRecharge,iPersonPagerEvent,iPersonalPageChange {
    ViewPager mainVP,personalVP;
    RecyclerView menuRV,userRV,rechargeRV;
    DrawerLayout mainDL;
    List<View> viewList,personViews;
    List<String> stringList;
    Button moreRecharge, rechargeHistory,thresholdSetBtn,busInfoBtn;
    mMainVPAdapter mainAdapter;
    mMenuRVAdapter menuAdapter;
    mPersonalVPAdapter personalAdapter;
    mUserManageRVAdapter userAdapter;
    mRechargeRVAdapter rechargeAdapter;
    TextView title,thresholdSetNow,busDistance1;
    EditText thresholdValue;
    String[] items;
    FrameLayout Fl_menuBtn;
    int[] views;
    List<UserInfo> userList;
    LinearLayout centerHospitalStation,lenevoStation,centerHospitalBus,lenevoBus;
    ImageView arrowImg1,arrowImg2;
    int t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);
        mUtils.getShare();
        userList = new ArrayList<>();
        //改用全局数据管理,局部数据库无法覆盖全局作用范围
        userList = mSharedContext.JDBHelper.SearchUserManage();

        rechargeAdapter = new mRechargeRVAdapter(mSharedContext.JDBHelper.SearchRechargeHistory());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        moreRecharge = findViewById(R.id.moreRecharge);
        moreRecharge.setOnClickListener(this);
        rechargeHistory = findViewById(R.id.rechargeHistory);
        rechargeHistory.setOnClickListener(this);
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
        //TODO 容易造成内存泄露/ANR异常,使用lazy加载设计模式,此方法以后不允许使用
        switch (p){
            case 0:
                userRV = v.findViewById(R.id.UserManageRV);
                userRV.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        outRect.bottom = 10;
                        outRect.left = 5;
                        outRect.right = 5;
                    }
                });
                userRV.setLayoutManager(new LinearLayoutManager(this));
                userAdapter = new mUserManageRVAdapter(userList,this);
                userAdapter.setOnItemClickListener(new mUserManageRVAdapter.onItemClickListener() {
                    @Override
                    public void setOnClick(View v, int p) {
                          userAdapter.setItemChecked(p);
                    }
                });
                userRV.setAdapter(userAdapter);
                break;
            case 1:
                centerHospitalStation = v.findViewById(R.id.centerHospitalStation);
                lenevoStation = v.findViewById(R.id.lenevoStation);
                lenevoBus = v.findViewById(R.id.lenevoBus);
                centerHospitalBus = v.findViewById(R.id.centerHospitalBus);
                arrowImg1 = v.findViewById(R.id.arrowImg1);
                arrowImg2 = v.findViewById(R.id.arrowImg2);
                busInfoBtn = v.findViewById(R.id.busInfoBtn);
                busInfoBtn.setOnClickListener(this);
                centerHospitalStation.setOnClickListener(this);
                lenevoStation.setOnClickListener(this);
                busDistance1 = v.findViewById(R.id.bus_distance1);
                break;
            case 9:
                personInfo = v.findViewById(R.id.personalInfo);
                rechargeCenter = v.findViewById(R.id.rechargeCenter);
                rechargeCenter.setTextColor(Color.RED);
                thresholdSetting = v.findViewById(R.id.thresholdSetting);
                personInfo.setOnClickListener(this);
                rechargeCenter.setOnClickListener(this);
                thresholdSetting.setOnClickListener(this);
                personViews = new ArrayList<>();
                titleList = new ArrayList<>();
                titleList.add(personInfo);
                titleList.add(rechargeCenter);
                titleList.add(thresholdSetting);
                int[] Views = new int[]{
                        R.layout.item_person_info,
                        R.layout.item_recharge_center,
                        R.layout.item_threshold_setting
                };
                for (int view :Views){
                    View vi = LayoutInflater.from(this).inflate(view,null);
                    personViews.add(vi);
                }
                personalVP = v.findViewById(R.id.personalVP);
                personalVP.setOffscreenPageLimit(2);

                personalAdapter = new mPersonalVPAdapter(personViews,this);
                personalVP.setAdapter(personalAdapter);
                personalVP.setCurrentItem(1);
                personalVP.addOnPageChangeListener(new mPersonalPageChange(this));
                break;
        }
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
            case R.id.moreRecharge:
                moreRecharge();
                break;
            case R.id.rechargeHistory:
                mainVP.setCurrentItem(9);
                break;
            case R.id.personalInfo:
                personalVP.setCurrentItem(0);
                break;
            case R.id.rechargeCenter:
                personalVP.setCurrentItem(1);
                break;
            case R.id.thresholdSetting:
                personalVP.setCurrentItem(2);
                break;
            case R.id.thresholdSetBtn:
                if (thresholdValue.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"阈值不能为空",Toast.LENGTH_LONG).show();
                }else{
                    thresholdSetNow.setText(thresholdValue.getText().toString());
                    t =  Integer.parseInt(thresholdSetNow.getText().toString());
                    thresholdValue.setText("");
                    mUtils.put("threshold",t);
                }
                break;
            case R.id.centerHospitalStation:
                if (centerHospitalBus.getVisibility()==View.VISIBLE){
                    centerHospitalBus.setVisibility(View.GONE);
                    arrowImg1.setImageResource(R.drawable.ic_chevron_right_black_24dp);
                }
                else{
                    centerHospitalBus.setVisibility(View.VISIBLE);
                    arrowImg1.setImageResource(R.drawable.ic_expand_more_black_24dp);
                }
                break;
            case R.id.lenevoStation:
                if (lenevoBus.getVisibility()==View.VISIBLE){
                    lenevoBus.setVisibility(View.GONE);
                    arrowImg2.setImageResource(R.drawable.ic_chevron_right_black_24dp);
                }
                else{
                    lenevoBus.setVisibility(View.VISIBLE);
                    arrowImg2.setImageResource(R.drawable.ic_expand_more_black_24dp);
                }
                break;
            case R.id.busInfoBtn:

                break;
        }
    }


   public void moreRecharge() {
        UserInfo u = new UserInfo();
        Map<Integer, Boolean> list = userAdapter.getCheckedItems();
        List<UserInfo> user = userAdapter.getItems();
        Iterator<Map.Entry<Integer, Boolean>> s = list.entrySet().iterator();
        List<UserInfo> result = new ArrayList<>();
        StringBuilder res = new StringBuilder();
        while (s.hasNext()) {
            Map.Entry<Integer, Boolean> a = s.next();
            if (a.getValue()) {
                result.add(user.get(a.getKey()));
                res.append(user.get(a.getKey()).carNo).append(",");
            }
        }
        this.CarRecharge(res.toString(),"user1",0);
    }

    @Override
    public void retQueryResult(int isWho, @Nullable illegalQueryBean queryResult) {
        int vID = 0;
        switch (isWho) {
            case mPresenter.WHO_QUERY_RESULT:
                //数据查询到的时候,开始跳转
                if (queryResult.allList == null || queryResult.allList.size() <= 0) {
                    Toast.makeText(this, "没有查到任何数据!", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
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
                for (illegalQueryBean.result rut : queryResult.allList) {
                    if (rut.handleState == 1) {
                        bean.deductCount += rut.DeductInt;
                        bean.forfeitCount += rut.Forfeit;
                        bean.noHandleCount++;
                    }
                }
                if (!s.hasItemEx(bean.carID)) {
                    s.addAllResultToCarsRv(bean);
                    //解决重复提示
                    Toast.makeText(this, "发现犯罪分子!违章次数:" + queryResult.allList.size(), Toast.LENGTH_LONG).show();
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
    Button dialog_rechargeBtn,dialog_cancelBtn;
    TextView getCarNo;
    EditText getCarMoney;

    public void busInfoDialog(){

    }
    @Override
    public void CarRecharge(final String carNo, final String carMaster, final int RechargeMoney) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_recharge,null);
        dialog_rechargeBtn = v.findViewById(R.id.dialog_rechargeBtn);
        dialog_cancelBtn = v.findViewById(R.id.dialog_cancelBtn);
        getCarNo = v.findViewById(R.id.getCarNo);
        getCarMoney = v.findViewById(R.id.getCarMoney);
        dialog.setView(v);

        getCarNo.setText(carNo);
        final AlertDialog dialog1 = dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        dialog_cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.cancel();
            }
        });
        dialog_rechargeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = getCarMoney.getText().toString();
                int m = 0;
                if (money.equals("")||(m=Integer.parseInt(money))<=0){
                    Toast.makeText(MainActivity.this,"金额不能为空或小于等于0",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (carNo.contains(",")) {

                        String IDs = carNo.substring(0,carNo.length()-1);
                        String[] mlist = IDs.split(",");
                        for (String carNo : mlist){
                            int t = mSharedContext.JDBHelper.mGetMoneyByCarID(carNo)+m;
                            mSharedContext.JDBHelper.InsertRechargeHistory(carNo,carMaster,m);
                            mSharedContext.JDBHelper.UpdateMoney(carNo,t);
                            userAdapter.addListData(mSharedContext.JDBHelper.SearchUserManage());
                            userAdapter.notifyDataSetChanged();
                            rechargeAdapter.updateHistory(mSharedContext.JDBHelper.SearchRechargeHistory());
                            rechargeAdapter.notifyDataSetChanged();
                            dialog1.cancel();

                        }
                    } else {
                        mSharedContext.JDBHelper.InsertRechargeHistory(carNo, carMaster, m);
                        mSharedContext.JDBHelper.UpdateMoney(carNo, RechargeMoney + m);
                        Toast.makeText(MainActivity.this, "充值成功！", Toast.LENGTH_LONG).show();
                        userAdapter.addListData(mSharedContext.JDBHelper.SearchUserManage());
                        userAdapter.notifyDataSetChanged();
                        rechargeAdapter.updateHistory(mSharedContext.JDBHelper.SearchRechargeHistory());
                        rechargeAdapter.notifyDataSetChanged();
                        dialog1.cancel();
                        return;
                    }
                }
            }
        });

    }

    @Override
    public void PersonPagerEvent(View v, int p) {
        switch (p){
            case 1:
                rechargeRV = v.findViewById(R.id.rechargeRV);
                rechargeRV.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        outRect.bottom = 10;
                        outRect.right = 5;
                        outRect.left = 5;
                    }
                });
                rechargeRV.setLayoutManager(new LinearLayoutManager(this));

                rechargeRV.setAdapter(rechargeAdapter);
                break;
            case 2:
                thresholdValue = v.findViewById(R.id.thresholdValue);
                thresholdSetNow = v.findViewById(R.id.thresholdValueNow);
                thresholdSetBtn = v.findViewById(R.id.thresholdSetBtn);

                thresholdSetNow.setText(mSharedContext.threshold()+"");
                thresholdSetBtn.setOnClickListener(this);

                break;
        }
    }

    TextView personInfo,rechargeCenter,thresholdSetting;
    List<TextView> titleList;
    @Override
    public void PersonalPageChange(int p) {
        for (int a=0;a<titleList.size();a++){
            titleList.get(a).setTextColor(Color.WHITE);
        }
        titleList.get(p).setTextColor(Color.RED);
    }
}


