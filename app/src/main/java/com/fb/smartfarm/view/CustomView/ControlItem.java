package com.fb.smartfarm.view.CustomView;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.fb.smartfarm.R;
import com.fb.smartfarm.UtilsTools.LogUtil;

/**
 * Created by echo on 2017/4/28.
 */

public class ControlItem extends LinearLayout implements CompoundButton.OnCheckedChangeListener {
    private final String TAG = ControlItem.class.getSimpleName();
    private Switch mSwitch;
    private TextView mTv;
    private ImageView mIv;
    private Context mContext;
    private ControlStateListener mListener;
    private int mOnResId;
    private int mOffResId;
    private boolean mCheck;

    public ControlItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public ControlItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_control_item, this, true);
        mTv = (TextView) findViewById(R.id.control_tv_curr_state);
        mIv = (ImageView) findViewById(R.id.control_icon);
        mSwitch = (Switch) findViewById(R.id.control_sw_state);

        mSwitch.setOnCheckedChangeListener(this);
    }

    public void setImgRes(int on,int off) {
        mOnResId = on;
        mOffResId = off;
    }

    public interface ControlStateListener {
        void onChange(boolean isCheck);
    }

    public void setControlStateListener(ControlStateListener listener) {
        mListener = listener;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (mListener != null) {
            mListener.onChange(isChecked);
        }
        mCheck = isChecked;
        calibration();
    }

    private void setImg(boolean isCheck){
        if(mIv == null)
            return;
        LogUtil.d(TAG,"isCheck :: "+isCheck);
        if(isCheck){
            mIv.setImageResource(mOnResId);
        }else {
            mIv.setImageResource(mOffResId);
        }
    }

    private void setStateText(boolean isCheck) {
        if (mTv == null)
            return;
        StringBuffer sb = new StringBuffer(mContext.getString(R.string.curr_state));
        if (isCheck) {
            String on = mContext.getString(R.string.turn_on);
            sb.append(on);
            int start = sb.toString().indexOf(on);
            SpannableStringBuilder style = new SpannableStringBuilder(sb.toString());
            ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
            LogUtil.d(TAG,"start :: "+start);
            style.setSpan(redSpan, start, start+1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            ForegroundColorSpan blackspan = new ForegroundColorSpan(Color.BLACK);
            style.setSpan(blackspan, 0, start, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            mTv.setText(style);
        } else {
            sb.append(mContext.getString(R.string.turn_off));
            mTv.setText(sb.toString());
        }

    }

    public void calibration(){
        mCheck = mSwitch.isChecked();
        setImg(mCheck);
        setStateText(mCheck);
    }

}
