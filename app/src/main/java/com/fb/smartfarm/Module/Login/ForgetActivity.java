package com.fb.smartfarm.Module.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.style.UpdateAppearance;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fb.smartfarm.R;
import com.fb.smartfarm.view.CustomView.BaseActivity;
import com.fb.smartfarm.view.CustomView.TopBar;

/**
 * Created by echo on 2017/5/6.
 */

public class ForgetActivity extends BaseActivity implements TopBar.TopListener{
    private TextView mNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        LayoutInflater.from(this).inflate(R.layout.layout_forget_activity,mContentLayout,true);
        mNext = (TextView) findViewById(R.id.tv_next);
        mTopBar.setCenterText("忘记密码");
        mTopBar.showLeft();
        mTopBar.setLeftBgRes(R.drawable.u312);
        mTopBar.setListener(this);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ForgetActivity.this, UpdatePasswordActivity.class);
                startActivity(intent);
            }
        });
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
