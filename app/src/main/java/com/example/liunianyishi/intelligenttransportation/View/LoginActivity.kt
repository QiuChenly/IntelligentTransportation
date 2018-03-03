package com.example.liunianyishi.intelligenttransportation.View

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.liunianyishi.intelligenttransportation.Presenter.mPresenter
import com.example.liunianyishi.intelligenttransportation.R
import com.example.liunianyishi.intelligenttransportation.Utils.mSP
import kotlinx.android.synthetic.main.view_login.*

class LoginActivity : AppCompatActivity(), mPresenter.loginCallback, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_login)
        Login_btn_Login.setOnClickListener(this)
        Login_btn_registered.setOnClickListener(this)
        if (mSP.getRMPassState()) {
            Login_cb_rememberPass.isChecked = true
            Login_et_user.setText(mSP.getUserName())
            Login_et_pass.setText(mSP.getUserPass())
        }
    }

    /**
     * 登录结果回调,异步请求
     *
     * @param state =1 登陆成功,=2登陆失败
     */
    override fun login_Result(state: Int) {
        if (state == 1) {
            //登录成功处理
            if (Login_cb_rememberPass.isChecked)
                mSP.saveUserPass(u, p)
            mSP.saveRMPassState(Login_cb_rememberPass.isChecked)
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        } else {
            //登录失败
            Toast.makeText(this, "账号或密码错误！", Toast.LENGTH_SHORT).show()
        }
    }

    var u = ""
    var p = ""
    override fun onClick(view: View) {
        when (view.id) {
            Login_btn_Login.id -> {
                p = Login_et_pass.text.toString()
                u = Login_et_user.text.toString()
                if (u.isEmpty() || p.isEmpty()) {
                    Toast.makeText(this, "请输入正确的账户或密码,谢谢合作!", Toast.LENGTH_SHORT)
                            .show()
                    return
                }
                //调用登录
                mPresenter.login(u, p, this)
            }
            Login_btn_registered.id -> Toast.makeText(this, "注册不存在的！", Toast.LENGTH_SHORT).show()
        }
    }
}
