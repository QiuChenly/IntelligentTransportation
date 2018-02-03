package com.example.liunianyishi.intelligenttransportation.Adapter

import android.support.v7.widget.RecyclerView
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.liunianyishi.intelligenttransportation.Bean.illegalCarListBean
import com.example.liunianyishi.intelligenttransportation.Bean.illegalQuery
import com.example.liunianyishi.intelligenttransportation.R
import com.example.liunianyishi.intelligenttransportation.R.id.items_car_id
import kotlinx.android.synthetic.main.items_carquery_rv_cars.*

/**
 * Created by qiuchen on 2018/1/31.
 */
class CarInfoRVAdapter(var queryResult: ArrayList<illegalCarListBean>,
                       val rv: RecyclerView,
                       val iGetDetails: CarInfoRVAdapter.importAllObject
                       )
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {

    /**
     * 移除数据集合中的实例数据
     */
    fun removeItem(id: Int) {
        this.notifyItemRemoved(id)
        queryResult.removeAt(id)
        notifyItemRangeChanged(0, queryResult.size)
    }

    /**
     * 加入一行数据
     */
    fun addItem(s: illegalCarListBean) {
        queryResult.add(s)
        this.notifyItemInserted(0)
        notifyItemRangeChanged(0, queryResult.size)
        //需要持有一个RecyclerView实例
        rv.scrollToPosition(0)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val s = queryResult[position]
        with(holder?.itemView) {
            this?.findViewById<ImageView>(R.id.mSearchResult_CarDetails_Remove)
                    ?.setOnClickListener({
                        removeItem(position)
                    })
            this?.findViewById<TextView>(R.id.items_car_id)?.text = s.carID
            this?.findViewById<TextView>(R.id.items_car_noHandle)?.text = "未处理违章" + s.noHandleCount.toString() + "次"
            this?.findViewById<TextView>(R.id.items_car_deductScore)?.text = "扣" + s.deductCount.toString() + "分"
            this?.findViewById<TextView>(R.id.items_car_forfeit)?.text = "罚款" + s.forfeitCount.toString() + "元"
            this?.setOnClickListener(this@CarInfoRVAdapter)
            this?.tag = position
        }
    }

    override fun onClick(p0: View?) {
        val r = iGetDetails.getDetailsRecyclerV()
    }



    fun hasItem(carID: String): Boolean {
        queryResult.forEach {
            if (it.carID == carID) {
                return true
            }
        }
        return false
    }

    override fun getItemCount() = queryResult.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.items_carquery_rv_cars, parent, false)
        return mVH(v)
    }

    class mVH(itemView: View?) : RecyclerView.ViewHolder(itemView)

    interface importAllObject{
        fun getDetailsRecyclerV():RecyclerView
    }
}