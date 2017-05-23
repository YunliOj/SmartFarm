package com.fb.smartfarm.view.CustomView;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fb.smartfarm.R;
import com.githang.statusbar.StatusBarCompat;

/**
 * Created by echo on 2017/4/27.
 */

public abstract class BaseActivity extends Activity{
    private final String TAG = BaseActivity.class.getSimpleName();
    protected TopBar mTopBar;
    protected LinearLayout mContentLayout;
    private boolean isTopBig = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base_activity);
        mTopBar = (TopBar) findViewById(R.id.base_top_bar);
        mContentLayout = (LinearLayout) findViewById(R.id.base_content_linearlayout);
        StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.green),false);
    }

    private void setBigTop(){
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,this.getResources().getDisplayMetrics());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height);
        mTopBar.setLayoutParams(lp);
        isTopBig = true;
    }
    private void setSmallTop(){
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,this.getResources().getDisplayMetrics());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height);
        mTopBar.setLayoutParams(lp);
        isTopBig = false;
    }

    protected void setTopSize(boolean isNeedBig){
        if(isNeedBig && !isTopBig){
            setBigTop();
            mTopBar.setBigCenter();
        }else if(!isNeedBig && isTopBig){
            setSmallTop();
            mTopBar.setSmallCenter();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
