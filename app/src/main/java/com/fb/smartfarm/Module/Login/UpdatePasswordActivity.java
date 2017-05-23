package com.fb.smartfarm.Module.Login;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.fb.smartfarm.R;
import com.fb.smartfarm.view.CustomView.BaseActivity;
import com.fb.smartfarm.view.CustomView.TopBar;

/**
 * Created by echo on 2017/5/6.
 */

public class UpdatePasswordActivity extends BaseActivity implements TopBar.TopListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater.from(this).inflate(R.layout.layout_activity_update_password,mContentLayout,true);
        mTopBar.setCenterText("忘记密码");
        mTopBar.showLeft();
        mTopBar.setLeftBgRes(R.drawable.u312);
        mTopBar.setListener(this);
    }

    @Override
    public void clickLeft() {
        finish();
    }

    @Override
    public void clickRight() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTopBar.removeListener();
    }
}
