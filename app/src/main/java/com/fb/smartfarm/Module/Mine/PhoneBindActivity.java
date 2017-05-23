package com.fb.smartfarm.Module.Mine;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fb.smartfarm.R;
import com.fb.smartfarm.view.CustomView.BaseActivity;
import com.fb.smartfarm.view.CustomView.TopBar;

/**
 * Created by echo on 2017/5/1.
 */

public class PhoneBindActivity extends BaseActivity implements View.OnClickListener ,TopBar.TopListener{
    private Button mBtnBind;
    private Button mBtnGetVerify;
    private TextView mTvBindInfo;
    private EditText mEdPhoneNum;
    private EditText mEdVerifyNum;
    private ImageView mIcon;
    private LinearLayout mLayoutBindState;
    private LinearLayout mLayoutBinding;

    private boolean isBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
       initView();
    }

    private void initView(){
        mTopBar.setLeftBgRes(R.drawable.u181);
        mTopBar.showLeft();
        mTopBar.setCenterText(getString(R.string.bind_phone));
        mTopBar.setRightText(getString(R.string.save));
        mTopBar.setListener(this);
        mTopBar.setLeftBgRes(R.drawable.u978);
        LayoutInflater.from(this).inflate(R.layout.layout_phone_bind_activity,mContentLayout,true);
        boolean isBind = getIntent().getBooleanExtra("isBind",false);
        mLayoutBindState = (LinearLayout) mContentLayout.findViewById(R.id.bind_state);
        mLayoutBinding = (LinearLayout) mContentLayout.findViewById(R.id.binding);
        mBtnBind = (Button) mContentLayout.findViewById(R.id.bind_btn_bind);
        mTvBindInfo = (TextView) mContentLayout.findViewById(R.id.bind_state_info);
        mEdPhoneNum = (EditText) mContentLayout.findViewById(R.id.bind_num);
        mEdVerifyNum = (EditText) mContentLayout.findViewById(R.id.bind_verify_number);
        mBtnGetVerify = (Button) mContentLayout.findViewById(R.id.get_verify_number);
        mIcon = (ImageView) mContentLayout.findViewById(R.id.bind_icon);
        hintBinding();
        mBtnBind.setOnClickListener(this);
        mBtnGetVerify.setOnClickListener(this);
        if(mIcon!=null){
            if(isBind){
                mIcon.setImageResource(R.drawable.u1176);
            } else {
                mIcon.setImageResource(R.drawable.u1162);
            }
        }

        if(mTvBindInfo!=null){
            if(isBind){
                mTvBindInfo.setText(getString(R.string.your_bind_phone_number_is)+"\n"+getBindPhone());
            }else {
                mTvBindInfo.setText(getString(R.string.you_are_not_bind_phone_number));
            }
        }
        if(mBtnBind!=null){
            if(isBind){
                mBtnBind.setText(getString(R.string.change_bind_phone));
            }else {
                mBtnBind.setText(getString(R.string.bind_phone));
            }
        }
    }

    private String getBindPhone(){
        return "10086";
    }

    private void hintBinding(){
        if(isBinding){
            mTopBar.showRightText();
            mLayoutBinding.setVisibility(View.VISIBLE);
            mLayoutBindState.setVisibility(View.GONE);
        }else {
            mLayoutBinding.setVisibility(View.GONE);
            mLayoutBindState.setVisibility(View.VISIBLE);
            mTopBar.showRightImg();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(isBinding){
            isBinding = false;
            hintBinding();
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.bind_btn_bind:
                isBinding = true;
                hintBinding();
                break;
            case R.id.get_verify_number:
                Toast.makeText(this,"get verify number",Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void clickLeft() {
        if(isBinding){
            isBinding = false;
            hintBinding();
        }else {
            finish();
        }
    }

    @Override
    public void clickRight() {
        Toast.makeText(this,"save",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTopBar.removeListener();
    }
}
