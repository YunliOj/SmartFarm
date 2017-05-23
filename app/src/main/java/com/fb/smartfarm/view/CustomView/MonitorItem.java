package com.fb.smartfarm.view.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fb.smartfarm.R;

/**
 * Created by echo on 2017/4/29.
 */

public class MonitorItem extends LinearLayout{
    private Context mContext;
    private TextView mTvType;
    private TextView mTvData;
    private ImageView mIvIcon;
    private int iConRes;
    private String hintText;
    public MonitorItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray ta = mContext.obtainStyledAttributes(attrs,R.styleable.MonitorItem);
        iConRes = ta.getResourceId(R.styleable.MonitorItem_monitor_icon,-1);
        hintText = ta.getString(R.styleable.MonitorItem_monitor_hint);
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_monitor_item,this,true);
        mTvType = (TextView) findViewById(R.id.item_monitor_tv_type);
        mTvData = (TextView) findViewById(R.id.item_monitor_tv_data);
        mIvIcon = (ImageView) findViewById(R.id.item_monitor_iv_icon);
        try{if(iConRes!=-1){
            mIvIcon.setImageResource(iConRes);
        }}catch (Exception e){

        }


        if (!TextUtils.isEmpty(hintText)){
            mTvType.setText(hintText);
        }
    }

}
