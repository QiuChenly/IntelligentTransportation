package com.example.liunianyishi.intelligenttransportation.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liunianyishi.intelligenttransportation.DataBase.RechargeHistory;
import com.example.liunianyishi.intelligenttransportation.R;

import java.util.List;

/**
 * Created by Administrator on 2018/2/5 0005.
 */

public class mRechargeRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<RechargeHistory> list;
    public mRechargeRVAdapter(List<RechargeHistory> list){
        this.list = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup p, int vt) {
        View v = LayoutInflater.from(p.getContext()).inflate(R.layout.item_recharge_rv,p,false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, int p) {
            VH v = (VH) h;
            RechargeHistory history = list.get(p);
            v.historyIndex.setText(p+1+"");
            v.historyCarNo.setText(history.carNo);
            v.historyCarMaster.setText(history.carMaster);
            v.historyCarMoney.setText(history.carMoney+"元");
    }

    public void updateHistory(List<RechargeHistory> list){
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class VH extends RecyclerView.ViewHolder {
        TextView historyIndex,historyCarNo,historyCarMaster,historyCarMoney;
        public VH(View v) {
            super(v);
            historyIndex = v.findViewById(R.id.historyIndex);
            historyCarNo = v.findViewById(R.id.historyCarNo);
            historyCarMaster = v.findViewById(R.id.historyCarMaster);
            historyCarMoney = v.findViewById(R.id.historyCarMoney);
        }
    }
}
