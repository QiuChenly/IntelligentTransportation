package com.example.liunianyishi.intelligenttransportation.View.ViewReslove

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.liunianyishi.intelligenttransportation.Presenter.mPresenter
import com.example.liunianyishi.intelligenttransportation.R

/**
 * Created by qiuchen on 2018/1/31.
 */
class illegalQueryViewInstance(private var v: View,
                               private val cb: mPresenter.queryCallback)
    : View.OnClickListener, BaseViewResolve(v) {

    val BtnQuery: Button = fb(R.id.mCarInfo_Query, true)
    val EditInputCarID: EditText = fb(R.id.mCarInfo_Input)

    override fun onClick(p0: View?) {
        when (p0?.id) {
            BtnQuery.id -> {
                if (EditInputCarID.text.toString().isEmpty()) {
                    Toast.makeText(v.context, "请输入有效的数据再来查询!", Toast.LENGTH_SHORT)
                            .show()
                    return
                }
                mPresenter.queryillegal(EditInputCarID.text.toString(), cb)
            }
        }
    }
}
