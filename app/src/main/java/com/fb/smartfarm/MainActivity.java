package com.fb.smartfarm;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.fb.smartfarm.Define.MouleDefine;
import com.fb.smartfarm.Module.Control.ControlFragment;
import com.fb.smartfarm.Module.Login.LoginActivity;
import com.fb.smartfarm.Module.Mine.MineFragment;
import com.fb.smartfarm.Module.Mine.UserInfoActivity;
import com.fb.smartfarm.Module.Monitor.MonitorFragment;
import com.fb.smartfarm.Module.WarningActivity;
import com.fb.smartfarm.Module.Work.WorkActivity;
import com.fb.smartfarm.Module.Work.WorkFragment;
import com.fb.smartfarm.UtilsTools.LogUtil;
import com.fb.smartfarm.view.CustomView.BaseActivity;
import com.fb.smartfarm.view.CustomView.BottomBar;
import com.fb.smartfarm.view.CustomView.DoubleListView;
import com.fb.smartfarm.view.CustomView.TopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements BottomBar.BottomListener, TopBar.TopListener, DoubleListView.OnPlaceChangeListener {
    private final String TAG = MainActivity.class.getSimpleName();
    private BottomBar mBottomBar;
    private FragmentManager mFmManager;
    private FragmentTransaction mFmTransaction;
    private ControlFragment mControlFragment;
    private WorkFragment mWorkFragment;
    private MonitorFragment mMonitorFragment;
    private MineFragment mMineFragment;
    private int mCurrModule;
    private int mLastModule = -1;
    private boolean isWarning = false;
    private boolean isBigTop;
    private boolean isShowing;
    private PopupWindow mPopWindow;
    private DoubleListView mDoubleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
        if (savedInstanceState == null) {
            startMonitor();
        }
    }

    private void initData() {
        mFmManager = getFragmentManager();
    }

    private void initView() {
        LayoutInflater.from(this).inflate(R.layout.activity_main, mContentLayout, true);
        mBottomBar = (BottomBar) mContentLayout.findViewById(R.id.bottom_bar);
        mBottomBar.setBottomListener(this);
        mTopBar.setListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void openMonitor() {
        setTopSize(false);
        startMonitor();
    }

    @Override
    public void openControl() {
        setTopSize(false);
        startControl();
    }

    @Override
    public void openWork() {
        setTopSize(false);
        startWork();
    }

    @Override
    public void openMine() {
        setTopSize(true);
        startMine();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBottomBar != null) {
            mBottomBar.removeListener();
        }
        if (mTopBar != null) {
            mTopBar.removeListener();
        }
        if (mDoubleListView != null) {
            mDoubleListView.removeListener();
        }
    }

    private void startWork() {
        LogUtil.d(TAG, "openWork");
        mFmTransaction = mFmManager.beginTransaction();
        if (mWorkFragment == null) {
            mWorkFragment = new WorkFragment();
            mFmTransaction.add(R.id.fragment_layout, mWorkFragment, "work");
        }
        mCurrModule = MouleDefine.MODULE_WORK;
        showCurrFragment();
        mTopBar.hintLeft();
        mTopBar.setRightBgRes(R.drawable.u417);
        mTopBar.setCenterText(getString(R.string.work));
    }

    private void startMine() {
        LogUtil.d(TAG, "openMine");
        mFmTransaction = mFmManager.beginTransaction();
        if (mMineFragment == null) {
            mMineFragment = new MineFragment();
            mFmTransaction.add(R.id.fragment_layout, mMineFragment, "mine");
        }
        mCurrModule = MouleDefine.MODULE_MINE;
        mTopBar.setRightBgRes(R.drawable.u312);
        mTopBar.hintLeft();
        mTopBar.setCenterText("欢迎你");
        showCurrFragment();
    }

    private void startControl() {
        LogUtil.d(TAG, "openControl");
        mFmTransaction = mFmManager.beginTransaction();
        if (mControlFragment == null) {
            mControlFragment = new ControlFragment();
            mFmTransaction.add(R.id.fragment_layout, mControlFragment, "control");
        }
        mCurrModule = MouleDefine.MODULE_CONTROL;
        showCurrFragment();
        mTopBar.setCenterText(getString(R.string.control));
        showLeftWarningBg();
        mTopBar.hintLeft();
    }

    private void startMonitor() {
        LogUtil.d(TAG, "openMonitor");
        mFmTransaction = mFmManager.beginTransaction();
        if (mMonitorFragment == null) {
            mMonitorFragment = new MonitorFragment();
            mFmTransaction.add(R.id.fragment_layout, mMonitorFragment, "monitor");
        }
        mCurrModule = MouleDefine.MODULE_MONITOR;
        showCurrFragment();
        showLeftWarningBg();
        mTopBar.setCenterText(getString(R.string.monitor));
        mTopBar.showLeftLayout();
    }

    private void showCurrFragment() {
        if (mCurrModule == mLastModule)
            return;
        switch (mCurrModule) {
            case MouleDefine.MODULE_MONITOR:
                mFmTransaction.show(mMonitorFragment);
                break;
            case MouleDefine.MODULE_CONTROL:
                mFmTransaction.show(mControlFragment);
                break;
            case MouleDefine.MODULE_WORK:
                mFmTransaction.show(mWorkFragment);
                break;
            case MouleDefine.MODULE_MINE:
                mFmTransaction.show(mMineFragment);
                break;
        }
        if (mLastModule != -1) {
            switch (mLastModule) {
                case MouleDefine.MODULE_MONITOR:
                    mFmTransaction.hide(mMonitorFragment);
                    break;
                case MouleDefine.MODULE_CONTROL:
                    mFmTransaction.hide(mControlFragment);
                    break;
                case MouleDefine.MODULE_WORK:
                    mFmTransaction.hide(mWorkFragment);
                    break;
                case MouleDefine.MODULE_MINE:
                    mFmTransaction.hide(mMineFragment);
                    break;
            }
        }
        mFmTransaction.commit();
        mLastModule = mCurrModule;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void showBaseStationInfo() {
        if (mPopWindow == null) {
            View view = getLayoutInflater().inflate(R.layout.layout_base_station, null, true);
            mDoubleListView = (DoubleListView) view.findViewById(R.id.double_list_view_base_station);
            mDoubleListView.setDoubleData(getDoubleData());
            mDoubleListView.setOnPlaceChangerListener(this);
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, this.getResources().getDisplayMetrics());
            mPopWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, height, true);
            mPopWindow.setOutsideTouchable(true);
            mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        }
        mPopWindow.showAsDropDown(mTopBar, 0, 0);
    }

    private HashMap<String, List<String>> getDoubleData() {
        Map<String, List<String>> map = new HashMap();
        List<String> list1 = new ArrayList<>();
        list1.add("a1");
        list1.add("a2");
        list1.add("a3");
        list1.add("a4");
        map.put("a", list1);
        List<String> list2 = new ArrayList<>();
        list2.add("b1");
        list2.add("b2");
        list2.add("b3");
        map.put("b", list2);
        return (HashMap) map;
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (mWorkFragment == null && fragment instanceof WorkFragment) {
            mWorkFragment = (WorkFragment) fragment;
        }
        if (mMineFragment == null && fragment instanceof MineFragment) {
            mMineFragment = (MineFragment) fragment;
        }
        if (mMonitorFragment == null && fragment instanceof MonitorFragment) {
            mMonitorFragment = (MonitorFragment) fragment;
        }
        if (mControlFragment == null && fragment instanceof ControlFragment) {
            mControlFragment = (ControlFragment) fragment;
        }

    }

    @Override
    public void clickLeft() {
        LogUtil.d(TAG, "clickLeft");
        showBaseStationInfo();
    }

    @Override
    public void clickRight() {
        LogUtil.d(TAG, "clickRight");
        handleRight();
    }

    private void handleRight() {
        switch (mCurrModule) {
            case MouleDefine.MODULE_MONITOR:
            case MouleDefine.MODULE_CONTROL:
                openWarningActivity();
                break;
            case MouleDefine.MODULE_WORK:
                openWorkActivity();
                break;
            case MouleDefine.MODULE_MINE:
                openMineActivity();
        }
    }

    private void showLeftWarningBg() {
        if (isWarning) {
            mTopBar.setRightBgRes(R.mipmap.warning_message);
        } else {
            mTopBar.setRightBgRes(R.mipmap.update_message);
        }
    }

    private void openMineActivity() {
        LogUtil.d(TAG, "openMineActivity");
        Intent intent = new Intent();
        intent.setClass(this, UserInfoActivity.class);
        startActivity(intent);
    }

    private void openWorkActivity() {
        LogUtil.d(TAG, "openWorkActivity");
        Intent intent = new Intent();
        intent.setClass(this, WorkActivity.class);
        intent.setAction("new");
        startActivity(intent);
    }

    private void openWarningActivity() {
        LogUtil.d(TAG, "openWarningActivity");
        Intent intent = new Intent();
        intent.setClass(this, WarningActivity.class);
        startActivity(intent);
    }

    @Override
    public void onChange(String s) {
        mTopBar.setBaseStationText(s);
    }
}
