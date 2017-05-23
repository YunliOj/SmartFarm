package com.fb.smartfarm.Module.Mine;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fb.smartfarm.Define.ModifyType;
import com.fb.smartfarm.R;
import com.fb.smartfarm.UtilsTools.LogUtil;
import com.fb.smartfarm.view.CustomView.BaseActivity;
import com.fb.smartfarm.view.CustomView.TopBar;

/**
 * Created by echo on 2017/4/30.
 */

public class ModifyInfoActivity extends BaseActivity implements TopBar.TopListener {
    private final String TAG = ModifyInfoActivity.class.getSimpleName();
    private EditText mEdText;
    private Button mBtnDel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int type = getIntent().getIntExtra("type", -1);
        if (type == ModifyType.ACCOUNT) {
            mTopBar.setCenterText(getString(R.string.login_account));
        } else if (type == ModifyType.NAME) {
            mTopBar.setCenterText(getString(R.string.user_name));
        }
        mTopBar.showRightText();
        mTopBar.setRightText(getString(R.string.save));
        LayoutInflater.from(this).inflate(R.layout.layout_modify_activity,mContentLayout,true);
        mTopBar.setListener(this);
        mTopBar.setLeftBgRes(R.drawable.u978);
        mTopBar.showLeft();
        mEdText = (EditText) mContentLayout.findViewById(R.id.modify_ed_txt);
        mBtnDel = (Button) mContentLayout.findViewById(R.id.modify_btn_del);
        mBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEdText!=null){
                    mEdText.setText("");
                }
            }
        });

        mEdText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                LogUtil.d(TAG,"onEditorAction");
                return (!event.isShiftPressed());
            }
        });
    }

    @Override
    public void clickLeft() {
        finish();
    }

    @Override
    public void clickRight() {
        Toast.makeText(this, "save", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTopBar.removeListener();
    }
}
