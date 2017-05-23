package com.fb.smartfarm.Module.Mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.fb.smartfarm.R;
import com.fb.smartfarm.view.CustomView.BaseActivity;
import com.fb.smartfarm.view.CustomView.TopBar;

/**
 * Created by echo on 2017/5/1.
 */

public class ChangePasswordActivity extends BaseActivity implements TopBar.TopListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTopBar.setListener(this);
        mTopBar.setCenterText(getString(R.string.change_password));
        mTopBar.setRightText(getString(R.string.save));
        mTopBar.showRightText();
        mTopBar.showLeft();
        mTopBar.setLeftBgRes(R.drawable.u978);
        LayoutInflater.from(this).inflate(R.layout.layout_change_activity,mContentLayout,true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTopBar.removeListener();
    }

    @Override
    public void clickLeft() {
        finish();
    }

    @Override
    public void clickRight() {
        Toast.makeText(this,"save",Toast.LENGTH_LONG).show();
    }
}
