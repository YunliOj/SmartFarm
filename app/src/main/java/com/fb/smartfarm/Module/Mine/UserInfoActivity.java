package com.fb.smartfarm.Module.Mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.fb.smartfarm.Define.ModifyType;
import com.fb.smartfarm.R;
import com.fb.smartfarm.UtilsTools.LogUtil;
import com.fb.smartfarm.view.CustomView.BaseActivity;
import com.fb.smartfarm.view.CustomView.MineItem;
import com.fb.smartfarm.view.CustomView.TopBar;

/**
 * Created by echo on 2017/4/30.
 */

public class UserInfoActivity extends BaseActivity implements View.OnClickListener,TopBar.TopListener{
    private final String TAG = UserInfoActivity.class.getSimpleName();
    private MineItem mAccount;
    private MineItem mName;
    private MineItem mManagerPlace;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(this).inflate(R.layout.layout_user_info_activity,mContentLayout,true);
        mAccount = (MineItem) view.findViewById(R.id.user_account);
        mName = (MineItem) view.findViewById(R.id.user_name);
        mManagerPlace = (MineItem) findViewById(R.id.user_manager_place);
        mAccount.setOnClickListener(this);
        mName.setOnClickListener(this);
        mManagerPlace.setOnClickListener(this);
        mTopBar.setCenterText(getString(R.string.person_info));
        mTopBar.setLeftBgRes(R.drawable.u978);
        mTopBar.showLeft();
        mTopBar.setListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.user_account:
                LogUtil.d(TAG,"account");
                Intent account = new Intent();
                account.setClass(this,ModifyInfoActivity.class);
                account.putExtra("type", ModifyType.ACCOUNT);
                startActivity(account);
                break;
            case R.id.user_name:
                LogUtil.d(TAG,"name");
                Intent name = new Intent();
                name.setClass(this,ModifyInfoActivity.class);
                name.putExtra("type", ModifyType.NAME);
                startActivity(name);
                break;
            case R.id.user_manager_place:
                LogUtil.d(TAG,"place");
                break;
        }
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
        if(mTopBar!=null){
            mTopBar.removeListener();
        }
    }
}
