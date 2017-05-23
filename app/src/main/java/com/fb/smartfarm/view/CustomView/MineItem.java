package com.fb.smartfarm.view.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fb.smartfarm.R;

/**
 * Created by echo on 2017/4/30.
 */

public class MineItem extends LinearLayout {
    private Context mContext;
    private TextView mTpye;
    private TextView mTxt;
    private ImageView mIv;
    private String left;
    private String right;
    public MineItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray ta = mContext.obtainStyledAttributes(attrs,R.styleable.MineItem);
        left = ta.getString(R.styleable.MineItem_mine_left_text);
        right = ta.getString(R.styleable.MineItem_mine_right_text);
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_mine_item, this, true);
        mTpye = (TextView) findViewById(R.id.item_mine_title_type);
        mTxt = (TextView) findViewById(R.id.item_mine_txt);
        mIv = (ImageView) findViewById(R.id.item_mine_iv);
        if(!TextUtils.isEmpty(left)){
            mTpye.setText(left);
        }
        if(!TextUtils.isEmpty(right)){
            mTxt.setText(right);
        }
    }

    public void setTitleText(String str) {
        if (mTpye != null) {
            mTpye.setText(str);
        }
    }

    public void setTxtText(String str) {
        if (mTxt != null) {
            mTxt.setText(str);
        }
    }

    public void setTxtVisable() {
        if (mTxt != null) {
            mTxt.setVisibility(View.VISIBLE);
        }
    }

    public void setIconVisable() {
        if (mIv != null) {
            mIv.setVisibility(View.VISIBLE);
        }
    }

}
