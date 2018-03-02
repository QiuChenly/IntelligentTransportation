package com.example.liunianyishi.intelligenttransportation.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liunianyishi.intelligenttransportation.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Eason on 2018/3/2.
 */

public class mPersonInofRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> mlist;

    public mPersonInofRVAdapter(List<String> mlist) {
        this.mlist = mlist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_person_info_rv, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class VH extends RecyclerView.ViewHolder {

        ImageView Img_Vehicle_icon;
        TextView tv_License_plate_number;

        public VH(View itemView) {
            super(itemView);
            Img_Vehicle_icon = itemView.findViewById(R.id.Img_Vehicle_icon);
            tv_License_plate_number = itemView.findViewById(R.id.tv_License_plate_number);
        }
    }
}
