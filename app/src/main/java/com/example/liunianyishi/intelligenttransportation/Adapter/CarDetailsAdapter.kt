package com.example.liunianyishi.intelligenttransportation.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.liunianyishi.intelligenttransportation.Bean.illegalQueryBean
import com.example.liunianyishi.intelligenttransportation.Presenter.MainPresenter
import com.example.liunianyishi.intelligenttransportation.R
import kotlinx.android.synthetic.main.item_carinfo_details.view.*

/**
 * Created by qiuchen on 2018/2/24.
 */
class CarDetailsAdapter(private var mList: ArrayList<illegalQueryBean.result>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = mList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseVH(LayoutInflater.from(parent.context).inflate(R.layout.item_carinfo_details, parent, false))
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder.itemView) {
            with(mList[position]) {
                mCardDetail_Time.text = MainPresenter.TimeTick2String(fooTime)
                mCardDetail_ResolveState.text = if (this.handleState == 2) "已处理" else "未处理"
                mCardDetail_Location.text = Location
                mCardDetail_Cause.text = Cause
                mCardDetail_Forfeit.text = Forfeit.toString()
                mCardDetail_Deduct.text = DeductInt.toString()
            }
        }
    }

    fun showData(mList: ArrayList<illegalQueryBean.result>) {
        this.mList = mList
        /**
         * 清除已处理
         */
        this.mList.filter { it.handleState == 2 }.forEach { this.mList.remove(it) }
        notifyDataSetChanged()
    }
}