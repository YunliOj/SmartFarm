package com.fb.smartfarm.Module.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fb.smartfarm.MainActivity;
import com.fb.smartfarm.R;
import com.fb.smartfarm.UtilsTools.LogUtil;
import com.fb.smartfarm.view.CustomView.BaseActivity;

/**
 * Created by echo on 2017/5/6.
 */

public class LoginActivity extends Activity implements View.OnClickListener{
    private EditText mAccount;
    private EditText mPassword;
    private TextView mTvLogin;
    private TextView mTvForget;

    private ImageView mIvShowPsw;
    private boolean isShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_activity);
        mAccount = (EditText) findViewById(R.id.tv_login_account);
        mPassword = (EditText) findViewById(R.id.tv_login_password);
        mTvLogin = (TextView) findViewById(R.id.tv_login);
        mTvForget = (TextView) findViewById(R.id.tv_login_forget);
        mIvShowPsw = (ImageView) findViewById(R.id.show_password);
        mIvShowPsw.setOnClickListener(this);
        mTvLogin.setOnClickListener(this);
        mTvForget.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.tv_login:
                String account = mAccount.getText().toString();
                String password = mPassword.getText().toString();
                if(TextUtils.isEmpty(account) || TextUtils.isEmpty(password)){
                    Toast.makeText(this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if("admin".equals(account)&&"admin".equals(password)){
                    Intent intent = new Intent();
                    intent.setClass(this, MainActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_login_forget:
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,ForgetActivity.class);
                startActivity(intent);
                break;
            case R.id.show_password:
                if(isShow){
                    mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isShow = false;
                }else {
                    mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isShow = true;
                }
                break;
        }
    }
}
