package com.fb.smartfarm.view.CustomView;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fb.smartfarm.R;

/**
 * Created by echo on 2017/4/25.
 */

public class BottomBar extends LinearLayout implements View.OnClickListener {
    private ImageView mBtnMonitor;
    private ImageView mBtnControl;
    private ImageView mBtnWork;
    private ImageView mBtnMine;

    private TextView mTvMonitor;
    private TextView mTvControl;
    private TextView mTvWork;
    private TextView mTvMine;
    private Context mContext;
    private BottomListener mlistener;

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_bottom_bar, this, true);
        mBtnMonitor = (ImageView) findViewById(R.id.btn_monitor);
        mBtnControl = (ImageView) findViewById(R.id.btn_control);
        mBtnWork = (ImageView) findViewById(R.id.btn_work);
        mBtnMine = (ImageView) findViewById(R.id.btn_mine);

        mTvControl = (TextView) findViewById(R.id.tv_bottom_control);
        mTvMine = (TextView) findViewById(R.id.tv_bottom_mine);
        mTvMonitor = (TextView) findViewById(R.id.tv_bottom_monitor);
        mTvWork = (TextView) findViewById(R.id.tv_bottom_work);

        mBtnMonitor.setOnClickListener(this);
        mBtnControl.setOnClickListener(this);
        mBtnWork.setOnClickListener(this);
        mBtnMine.setOnClickListener(this);
        setBarBackGround(R.id.btn_monitor);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (mlistener == null)
            return;
        switch (id) {
            case R.id.btn_monitor:
                mlistener.openMonitor();
                break;
            case R.id.btn_control:
                mlistener.openControl();
                break;
            case R.id.btn_work:
                mlistener.openWork();
                break;
            case R.id.btn_mine:
                mlistener.openMine();
                break;
        }
        setBarBackGround(id);
    }

    private void setBarBackGround(int id){
        switch (id){
            case R.id.btn_monitor:
                mBtnMonitor.setImageResource(R.mipmap.monitor_click);
                mBtnControl.setImageResource(R.mipmap.control);
                mBtnMine.setImageResource(R.mipmap.mine);
                mBtnWork.setImageResource(R.mipmap.work);

                mTvMonitor.setTextColor(Color.parseColor("#24bd9a"));
                mTvControl.setTextColor(Color.parseColor("#545151"));
                mTvWork.setTextColor(Color.parseColor("#545151"));
                mTvMine.setTextColor(Color.parseColor("#545151"));
                break;
            case R.id.btn_control:
                mBtnMonitor.setImageResource(R.mipmap.monitor);
                mBtnControl.setImageResource(R.mipmap.control_click);
                mBtnMine.setImageResource(R.mipmap.mine);
                mBtnWork.setImageResource(R.mipmap.work);

                mTvMonitor.setTextColor(Color.parseColor("#545151"));
                mTvControl.setTextColor(Color.parseColor("#24bd9a"));
                mTvWork.setTextColor(Color.parseColor("#545151"));
                mTvMine.setTextColor(Color.parseColor("#545151"));
                break;
            case R.id.btn_work:
                mBtnMonitor.setImageResource(R.mipmap.monitor);
                mBtnControl.setImageResource(R.mipmap.control);
                mBtnMine.setImageResource(R.mipmap.mine);
                mBtnWork.setImageResource(R.mipmap.work_click);

                mTvMonitor.setTextColor(Color.parseColor("#545151"));
                mTvControl.setTextColor(Color.parseColor("#545151"));
                mTvWork.setTextColor(Color.parseColor("#24bd9a"));
                mTvMine.setTextColor(Color.parseColor("#545151"));
                break;
            case R.id.btn_mine:
                mBtnMonitor.setImageResource(R.mipmap.monitor);
                mBtnControl.setImageResource(R.mipmap.control);
                mBtnMine.setImageResource(R.mipmap.mine_click);
                mBtnWork.setImageResource(R.mipmap.work);

                mTvMonitor.setTextColor(Color.parseColor("#545151"));
                mTvControl.setTextColor(Color.parseColor("#545151"));
                mTvWork.setTextColor(Color.parseColor("#545151"));
                mTvMine.setTextColor(Color.parseColor("#24bd9a"));
                break;
        }
    }

    public void setBottomListener(BottomListener listener) {
        this.mlistener = listener;
    }

    public interface BottomListener {
        void openMonitor();

        void openControl();

        void openWork();

        void openMine();
    }

    public void removeListener() {
        mlistener = null;
    }
}
