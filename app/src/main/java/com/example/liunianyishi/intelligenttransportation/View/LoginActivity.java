package com.example.liunianyishi.intelligenttransportation.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.liunianyishi.intelligenttransportation.Presenter.mPresenter;
import com.example.liunianyishi.intelligenttransportation.R;

public class LoginActivity extends AppCompatActivity implements mPresenter.loginCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_login);
        //调用登录
        String u, p;
        u = p = "";
        mPresenter.Companion.login(u, p, this);
    }

    /**
     * 登录结果回调
     *
     * @param state =1 登陆成功,=2登陆失败
     */
    @Override
    public void login_Result(int state) {
        if (state == 1) {
            //登录成功处理
        } else {
            //登录失败
        }
    }
}
