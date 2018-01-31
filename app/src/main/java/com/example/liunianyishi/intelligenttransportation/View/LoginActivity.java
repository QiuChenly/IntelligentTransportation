package com.example.liunianyishi.intelligenttransportation.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liunianyishi.intelligenttransportation.R;
import com.example.liunianyishi.intelligenttransportation.Util.mSP;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar Login_toolbar;
    Button Login_btn_network, Login_btn_Login, Login_btn_registered;
    EditText Login_et_user, Login_et_pass;
    CheckBox Login_cb_rememberPass, Login_cb_autoLogin;
    private mSP.Companion companion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_login);

        findId();

        Login_btn_Login.setOnClickListener(this);
        Login_btn_registered.setOnClickListener(this);

    }

    public <T extends View> T fd(int id) {
        return (T) findViewById(id);
    }

    public void findId() {
        Login_toolbar = fd(R.id.Login_toolbar);
        Login_btn_network = fd(R.id.Login_btn_network);
        Login_btn_Login = fd(R.id.Login_btn_Login);
        Login_btn_registered = fd(R.id.Login_btn_registered);
        Login_et_user = fd(R.id.Login_et_user);
        Login_et_pass = fd(R.id.Login_et_pass);
        Login_cb_rememberPass = fd(R.id.Login_cb_rememberPass);
        Login_cb_autoLogin = fd(R.id.Login_cb_autoLogin);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Login_btn_Login:
                if (Login_et_user.getText().toString().equals("user1") && Login_et_pass.getText().toString().equals("123456")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "账号或密码错误！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Login_btn_registered:
                Toast.makeText(this, "注册不存在的！", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
