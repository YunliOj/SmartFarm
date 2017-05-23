package com.fb.smartfarm.view.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fb.smartfarm.R;

/**
 * Created by echo on 2017/5/1.
 */

public class SymmetryTextView extends LinearLayout {
    private TextView tvRight;
    private TextView tvLeft;
    private Context mContext;
    private String textLeft;

    public SymmetryTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray ta = mContext.obtainStyledAttributes(attrs,R.styleable.SymmetryTextView);
        textLeft = ta.getString(R.styleable.SymmetryTextView_left_text);
        ta.recycle();
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_symmetry_text, this, true);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvRight = (TextView) findViewById(R.id.tv_right);
        if(!TextUtils.isEmpty(textLeft)){
            tvLeft.setText(textLeft);
        }
    }

    public void setRightText(String str) {
        if (tvRight != null) {
            tvRight.setText(str);
        }
    }

    public void setLeftText(String str) {
        if (tvLeft != null) {
            tvLeft.setText(str);
        }
    }
}
