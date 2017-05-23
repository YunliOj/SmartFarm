package com.fb.smartfarm.Module.Work;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.fb.smartfarm.R;
import com.fb.smartfarm.view.CustomView.BaseActivity;
import com.fb.smartfarm.view.CustomView.SymmetryTextView;
import com.fb.smartfarm.view.CustomView.TopBar;

/**
 * Created by echo on 2017/5/3.
 */

public class WorkRecordActivity extends BaseActivity implements TopBar.TopListener{
    private SymmetryTextView mWork;
    private SymmetryTextView place;
    private SymmetryTextView crop;
    private SymmetryTextView canliu;
    private SymmetryTextView type;
    private SymmetryTextView feiliao;
    private SymmetryTextView yongliang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater.from(this).inflate(R.layout.layout_work_record_activity,mContentLayout,true);
        mTopBar.setCenterText("查看工作记录");
        mTopBar.showRightText();
        mTopBar.setRightText("编辑");
        mTopBar.showLeft();
        mTopBar.setLeftBgRes(R.drawable.u978);
//        mWork = mContentLayout.findViewById(R.id.)
        mTopBar.setListener(this);
    }

    @Override
    public void clickLeft() {
        finish();
    }

    @Override
    public void clickRight() {
        Intent intent = new Intent();
        intent.setClass(this, WorkActivity.class);
        intent.setAction("editor");
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTopBar.removeListener();
    }
}
