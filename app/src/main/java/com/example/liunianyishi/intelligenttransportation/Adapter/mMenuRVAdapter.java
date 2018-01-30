package com.example.liunianyishi.intelligenttransportation.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liunianyishi.intelligenttransportation.Interface.iItemClick;
import com.example.liunianyishi.intelligenttransportation.R;

import java.util.List;

/**
 * Created by LiuNianyishi on 2018/1/24.
 */

public class mMenuRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    List<String> list;
    iItemClick click;
    public mMenuRVAdapter(List<String> list,iItemClick click){
        this.list = list;
        this.click = click;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup p, int vt) {
        View v = LayoutInflater.from(p.getContext()).inflate(R.layout.item_menu_rv,p,false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, int p) {
            VH v = (VH) h;
            v.itemView.setTag(p);
            v.menuItem.setText(list.get(p));
            v.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        int p = (int) v.getTag();
        click.ItemClick(p);
    }

    class VH extends RecyclerView.ViewHolder {
        TextView menuItem;
        View root;
        public VH(View v) {
            super(v);
            root = v;
            menuItem = v.findViewById(R.id.menuItem);
        }
    }
}
