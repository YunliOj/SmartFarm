package com.fb.smartfarm.Module.Mine;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.fb.smartfarm.R;
import com.fb.smartfarm.view.CustomView.BaseActivity;
import com.fb.smartfarm.view.CustomView.TopBar;

/**
 * Created by echo on 2017/5/1.
 */

public class AboutActivity extends BaseActivity implements TopBar.TopListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        LayoutInflater.from(this).inflate(R.layout.layout_about_activity,mContentLayout,true);
        mTopBar.setCenterText(getString(R.string.about_us));
        mTopBar.showLeft();
        mTopBar.setLeftBgRes(R.drawable.u978);
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
