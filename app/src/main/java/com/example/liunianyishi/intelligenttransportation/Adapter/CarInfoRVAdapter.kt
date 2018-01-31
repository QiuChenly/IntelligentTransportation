package com.example.liunianyishi.intelligenttransportation.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.liunianyishi.intelligenttransportation.Bean.illegalQuery
import com.example.liunianyishi.intelligenttransportation.R

/**
 * Created by qiuchen on 2018/1/31.
 */
class CarInfoRVAdapter(var queryResult: ArrayList<illegalQuery.result>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    /**
     * 移除数据集合中的实例数据
     */
    fun removeItem(id: Int) {
        this.notifyItemRemoved(id)
        queryResult.removeAt(id)
        notifyItemRangeChanged(0, queryResult.size)
    }

    fun addItem(s: illegalQuery.result) {
        queryResult.add(s)
        this.notifyItemInserted(0)
        notifyItemRangeChanged(0, queryResult.size)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val s: illegalQuery.result = queryResult[position]
        with(holder?.itemView) {
            this?.findViewById<ImageView>(R.id.mSearchResult_CarDetails_Remove)
                    ?.setOnClickListener({
                        removeItem(position)
                    })
//            this?.findViewById<TextView>(R.id.items_car_id)?.text=s.
            //TODO 暂停工作
        }
    }

    override fun getItemCount() = queryResult.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.items_carquery_rv_cars, parent, false)
        return mVH(v)
    }

    class mVH(itemView: View?) : RecyclerView.ViewHolder(itemView)
}