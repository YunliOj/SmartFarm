package com.fb.smartfarm.view.CustomView;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fb.smartfarm.R;
import com.fb.smartfarm.UtilsTools.LogUtil;

/**
 * Created by echo on 2017/4/27.
 */

public class TopBar extends LinearLayout implements View.OnClickListener {

    private final String TAG = TopBar.class.getSimpleName();

    private Context mContext;
    private ImageButton mIvLeft;
    private ImageButton mIvRight;
    private TextView mTvCenter;
    private TextView mTvRight;
    private LinearLayout mLayoutLeft;
    private TopListener mListener;
    private ImageView mIvIsShowBaseStation;
    private TextView mTvBaseStation;
    private boolean isShowing;

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_top_bar, this, true);
        mIvLeft = (ImageButton) findViewById(R.id.btn_top_left);
        mIvRight = (ImageButton) findViewById(R.id.btn_top_right);
        mTvCenter = (TextView) findViewById(R.id.tv_top_center);
        mTvRight = (TextView) findViewById(R.id.tv_top_right);
        mLayoutLeft = (LinearLayout) findViewById(R.id.btn_show_list);
        mIvIsShowBaseStation = (ImageView) findViewById(R.id.img_is_show_base_station);
        mTvBaseStation = (TextView) findViewById(R.id.tv_base_station);
        mTvRight.setOnClickListener(this);
        mIvLeft.setOnClickListener(this);
        mIvRight.setOnClickListener(this);
        mLayoutLeft.setOnClickListener(this);
    }

    public void setListener(TopListener listener) {
        this.mListener = listener;
    }

    public interface TopListener {
        void clickLeft();

        void clickRight();
    }

    public void setLeftBgRes(int resId) {
        if (mIvLeft != null) {
            mIvLeft.setImageResource(resId);
        }
    }

    public void setRightBgRes(int resId) {
        if (mIvLeft != null) {
            mIvRight.setImageResource(resId);
        }
    }

    public void setCenterText(String text) {
        if (TextUtils.isEmpty(text))
            return;
        if (mTvCenter != null) {
            mTvCenter.setText(text);
        }
    }

    public void hintLeft() {
        if (mIvLeft != null) {
            mIvLeft.setVisibility(View.GONE);
        }
        if(mLayoutLeft!=null){
            mLayoutLeft.setVisibility(View.GONE);
        }
    }

    public void showLeftLayout(){
        if(mIvLeft!=null){
            mIvLeft.setVisibility(View.GONE);
        }
        if(mLayoutLeft!=null){
            mLayoutLeft.setVisibility(View.VISIBLE);
        }
    }

    public void showLeft() {
        if (mIvLeft != null) {
            mIvLeft.setVisibility(View.VISIBLE);
        }
        if(mLayoutLeft!=null){
            mLayoutLeft.setVisibility(View.GONE);
        }
    }

    public void removeListener() {
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (mListener == null)
            return;
        switch (id) {
            case R.id.btn_top_left:
                mListener.clickLeft();
                break;
            case R.id.btn_show_list:
                mListener.clickLeft();
                changeShowState();
                break;
            case R.id.btn_top_right:
            case R.id.tv_top_right:
                mListener.clickRight();
                break;
        }
    }

    public void setRightText(String str) {
        if (mTvRight != null) {
            LogUtil.d(TAG,"setRightText :: "+str);
            mTvRight.setText(str);
        }
    }

    private void changeShowState(){
        if(isShowing){
            mIvIsShowBaseStation.setImageResource(R.mipmap.hint);
            isShowing = false;
        }else {
            mIvIsShowBaseStation.setImageResource(R.mipmap.show);
            isShowing = true;
        }
    }

    public void showRightText(){
        if (mIvLeft != null) {
            mIvRight.setVisibility(View.GONE);
        }
        if(mTvRight!=null){
            mTvRight.setVisibility(View.VISIBLE);
        }
    }
    public void showRightImg(){
        if (mTvRight != null) {
            mIvRight.setVisibility(View.VISIBLE);
        }
        if(mTvRight!=null){
            mTvRight.setVisibility(View.GONE);
        }
    }

    public void setBaseStationText(String s){
        if(mTvBaseStation!=null){
            mTvBaseStation.setText(s);
        }
    }

    public void setBigCenter() {
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, this.getResources().getDisplayMetrics());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height);
        lp.weight = 1;
        mTvCenter.setLayoutParams(lp);
    }

    public void setSmallCenter() {
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, this.getResources().getDisplayMetrics());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, height);
        lp.weight = 1;
        mTvCenter.setLayoutParams(lp);
    }
}
