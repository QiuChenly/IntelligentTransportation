package com.example.liunianyishi.intelligenttransportation.View.ViewReslove

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.example.liunianyishi.intelligenttransportation.Presenter.mPresenter
import com.example.liunianyishi.intelligenttransportation.R

/**
 * Created by qiuchen on 2018/1/31.
 */
class illegalQueryResult(private var v: View, private val cb: mPresenter.queryCallback) : BaseViewResolve(v) {

    val mAddMore: ImageView = fb(R.id.mSearchResult_AddQuery, true)
    val mCarsRV: RecyclerView = fb(R.id.mSearchResult_Cars)
    val mCarDetails: RecyclerView = fb(R.id.mSearchResult_CarDetails)

    init {

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            mAddMore.id -> {
                cb.retQueryResult(mPresenter.WHO_QUERY_Details, null)
            }
        }
    }
}