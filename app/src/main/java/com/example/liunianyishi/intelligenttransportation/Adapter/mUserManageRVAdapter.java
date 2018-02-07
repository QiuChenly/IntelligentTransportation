package com.example.liunianyishi.intelligenttransportation.Adapter;

import android.app.AlertDialog;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liunianyishi.intelligenttransportation.DataBase.UserInfo;
import com.example.liunianyishi.intelligenttransportation.Interface.iCarRecharge;
import com.example.liunianyishi.intelligenttransportation.R;
import com.example.liunianyishi.intelligenttransportation.Util.mSharedContext;
import com.example.liunianyishi.intelligenttransportation.View.MainActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/4 0004.
 */

public class mUserManageRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    List<UserInfo> list;
    private onItemClickListener onItemClickListener;
    Map<Integer,Boolean> checkboxItems;
    iCarRecharge carRecharge;
    public mUserManageRVAdapter(List<UserInfo> list,iCarRecharge carRecharge){
        this.list = list;
        this.carRecharge = carRecharge;
        checkboxInit();
    }

    public void checkboxInit(){
        checkboxItems = new HashMap<>();
        for (int a =0;a<list.size();a++){
            checkboxItems.put(a,false);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup p, int vt) {
       View v = LayoutInflater.from(p.getContext()).inflate(R.layout.item_usermanage_rv,p,false);
       return  new VH(v);

    }

    public void addListData(List<UserInfo> list){
        this.list = list;
    }


    public void setItemChecked(int position) {
        if (checkboxItems.get(position)) {
            checkboxItems.put(position, false);
        } else {
            checkboxItems.put(position, true);
        }
        notifyItemChanged(position);
    }

    public Map<Integer, Boolean> getCheckedItems() {
        return checkboxItems;
    }

    public List<UserInfo> getItems() {
        return list;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int p) {
            final VH v = (VH) holder;
        final UserInfo user = list.get(p);
        v.carCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkboxItems.put(v.getAdapterPosition(),isChecked);
            }
        });
            v.index.setText(p+1+"");
            v.carNo.setText(user.carNo);
            v.carMaster.setText("车主："+ user.carMaster);
            v.carMoney.setText("余额："+ user.carMoney);
            if (user.carMoney< mSharedContext.threshold){
                v.carMoney.setTextColor(Color.RED);
            }else{
                v.carMoney.setTextColor(Color.WHITE);
            }
            if (v.carCheckBox==null){
                checkboxItems.put(p,false);
            }
            switch (user.imageId){
                case 1:
                    v.carImage.setImageResource(R.drawable.bwm);
                    break;
                case 2:
                    v.carImage.setImageResource(R.drawable.china);
                    break;
                case 3:
                    v.carImage.setImageResource(R.drawable.sanling);
                    break;
                case 4:
                    v.carImage.setImageResource(R.drawable.mazoa);
                    break;
            }
            v.rechargeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    carRecharge.CarRecharge(user.carNo,user.carMaster,user.carMoney);
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
}
public interface onItemClickListener{
    void setOnClick(View v, int p);
}
    class VH extends RecyclerView.ViewHolder {
        TextView index,carNo,carMaster,carMoney;
        ImageView carImage;
        CheckBox carCheckBox;
        Button rechargeBtn;
        View root;
        public VH(View v) {
            super(v);
            root = v;
            index = v.findViewById(R.id.index_txt);
            carNo = v.findViewById(R.id.carNo);
            carMaster = v.findViewById(R.id.carMaster);
            carMoney = v.findViewById(R.id.carMoney);
            carImage = v.findViewById(R.id.carImage);
            carCheckBox = v.findViewById(R.id.carCheckBox);
            rechargeBtn = v.findViewById(R.id.carRechargeBtn);
        }
    }
}
