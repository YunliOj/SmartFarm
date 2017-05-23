package com.fb.smartfarm.Module.Mine;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fb.smartfarm.R;
import com.fb.smartfarm.view.CustomView.MineItem;
import com.fb.smartfarm.view.CustomView.MyBaseFragment;

/**
 * Created by echo on 2017/4/30.
 */

public class MineFragment extends MyBaseFragment implements View.OnClickListener {
    private LinearLayout mLayout;
    private Context mContext;
    private MineItem mBind;
    private MineItem mPassword;
    private MineItem mAbout;
    private MineItem mExit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayout = (LinearLayout) inflater.inflate(R.layout.layout_fragment_mine, container, false);
        return mLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        initView();
    }

    private void initView() {
        mBind = (MineItem) mLayout.findViewById(R.id.mine_bind);
        mPassword = (MineItem) mLayout.findViewById(R.id.mine_change_password);
        mAbout = (MineItem) mLayout.findViewById(R.id.mine_about_us);
        mExit = (MineItem) mLayout.findViewById(R.id.mine_exit_login);

        mBind.setOnClickListener(this);
        mPassword.setOnClickListener(this);
        mAbout.setOnClickListener(this);
        mExit.setOnClickListener(this);
    }

    private boolean getBindState() {
        return false;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();
        switch (id) {
            case R.id.mine_bind:
                intent.setClass(mContext, PhoneBindActivity.class);
                intent.putExtra("isBind", getBindState());
                startActivity(intent);
                break;
            case R.id.mine_about_us:
                intent.setClass(mContext,AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_change_password:
                intent.setClass(mContext,ChangePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_exit_login:
                break;
        }
    }
}
