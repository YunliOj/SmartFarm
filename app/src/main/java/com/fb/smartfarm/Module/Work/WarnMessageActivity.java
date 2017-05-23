package com.fb.smartfarm.Module.Work;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fb.smartfarm.R;
import com.fb.smartfarm.view.CustomView.BaseActivity;
import com.fb.smartfarm.view.CustomView.SymmetryTextView;
import com.fb.smartfarm.view.CustomView.TopBar;

/**
 * Created by echo on 2017/5/1.
 */

public class WarnMessageActivity extends BaseActivity implements TopBar.TopListener{
    private SymmetryTextView mDate;
    private SymmetryTextView mPlace;
    private SymmetryTextView mHardWare;
    private SymmetryTextView mState;
    private WindowManager mManager;
    private Context mContext;
    private boolean isAddView;
    private LinearLayout windowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        init();
    }

    private void init() {
        mTopBar.setCenterText(getString(R.string.warning_message));
        mTopBar.showRightText();
        mTopBar.setRightText(getString(R.string.handle));
        mTopBar.showLeft();
        mTopBar.setLeftBgRes(R.drawable.u978);
        LayoutInflater.from(this).inflate(R.layout.layout_warning_message_activity,mContentLayout,true);
        mDate = (SymmetryTextView) findViewById(R.id.tv_warning_date);
        mPlace = (SymmetryTextView) findViewById(R.id.tv_warning_place);
        mHardWare = (SymmetryTextView) findViewById(R.id.tv_warning_hardware);
        mState = (SymmetryTextView) findViewById(R.id.tv_warning_state);

        String date = getIntent().getStringExtra("date");
        String place = getIntent().getStringExtra("place");
        String hardware = getIntent().getStringExtra("hardware");
        String state = getIntent().getStringExtra("state");

        mDate.setRightText(date);
        mPlace.setRightText(place);
        mHardWare.setRightText(hardware);
        mState.setRightText(state);

        mManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);


        mTopBar.setListener(this);
    }

    private LinearLayout getLayout(){
        windowLayout = new LinearLayout(mContext);
        windowLayout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_message_window,windowLayout,true);
        TextView cancel = (TextView) windowLayout.findViewById(R.id.btn_msg_cancel);
        TextView confirm = (TextView) windowLayout.findViewById(R.id.btn_msg_confirm);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mManager!=null&&isAddView){
                    if(windowLayout!=null){
                        mManager.removeView(windowLayout);
                    }
                }
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mManager!=null&&isAddView){
                    if(windowLayout!=null){
                        Toast.makeText(mContext,"confirm",Toast.LENGTH_LONG).show();
                        mManager.removeView(windowLayout);
                    }
                }
            }
        });
        return windowLayout;
    }
    private WindowManager.LayoutParams getWindowParams(){
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, this.getResources().getDisplayMetrics());
        int wight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, this.getResources().getDisplayMetrics());

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = wight;
        lp.height = height;
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        return lp;
    }

    @Override
    public void clickLeft() {
        finish();
    }

    @Override
    public void clickRight() {
        mManager.addView(getLayout(),getWindowParams());
        isAddView = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTopBar.removeListener();
    }
}
